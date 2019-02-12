package com.lixiaozhuo.house.service.house.impl;

import java.util.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.lixiaozhuo.house.base.uitls.HouseSort;
import com.lixiaozhuo.house.base.uitls.LoginUserUtil;
import com.lixiaozhuo.house.entity.*;
import com.lixiaozhuo.house.repository.*;
import com.lixiaozhuo.house.service.ServiceMultiResult;
import com.lixiaozhuo.house.service.ServiceResult;
import com.lixiaozhuo.house.service.house.IHouseService;
import com.lixiaozhuo.house.service.house.IQiNiuService;
import com.lixiaozhuo.house.service.search.ISearchService;
import com.lixiaozhuo.house.web.dto.HouseDTO;
import com.lixiaozhuo.house.web.dto.HouseDetailDTO;
import com.lixiaozhuo.house.web.dto.HousePictureDTO;
import com.lixiaozhuo.house.web.dto.HouseSubscribeDTO;
import com.lixiaozhuo.house.web.form.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.google.common.collect.Maps;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;

/**
 * 房屋服务
 */
@Service
public class HouseServiceImpl implements IHouseService {
    //java对象自动映射工具
    @Autowired
    private ModelMapper modelMapper;
    //房屋主体信息DAO
    @Autowired
    private HouseRepository houseRepository;
    //房屋详细信息DAO
    @Autowired
    private HouseDetailRepository houseDetailRepository;
    //房屋图片信息DAO
    @Autowired
    private HousePictureRepository housePictureRepository;
    //房屋标签信息DAO
    @Autowired
    private HouseTagRepository houseTagRepository;
    //预约看房信息DAO
    @Autowired
    private HouseSubscribeRepository houseSubscribeRepository;
    //地铁线路信息DAO
    @Autowired
    private SubwayRepository subwayRepository;
    //地铁站信息DAO
    @Autowired
    private SubwayStationRepository subwayStationRepository;
    //查找服务
    @Autowired
    private ISearchService searchService;
    //七牛云服务
    @Autowired
    private IQiNiuService qiNiuService;
    //图片路径
    @Value("${qiniu.cdn.prefix}")
    private String cdnPrefix;

    /**
     * 新增房屋信息
     */
    @Override
    public ServiceResult<HouseDTO> save(HouseForm houseForm) {
        HouseDetail houseDetail = new HouseDetail();
        //房屋详细信息填充
        ServiceResult serviceResultHouse= wrapperHouseDetail(houseDetail, houseForm);
        if (!serviceResultHouse.isSuccess()) {
            return serviceResultHouse;
        }
        //######################房屋主体信息######################
        House house = new House();
        //房屋主体信息填充
        modelMapper.map(houseForm, house);
        //补充信息
        //创建时间
        house.setCreateTime(new Date());
        //最后更新时间
        house.setLastUpdateTime(new Date());
        //将当前登录管理员设置为房屋所属管理员
        house.setAdminId(LoginUserUtil.getLoginUserId());
        //保存房屋主体信息
        house = houseRepository.save(house);
        //######################房屋详细信息######################
        //设置房屋详细信息中房屋对应id
        houseDetail.setHouseId(house.getId());
        //保存房屋详细信息
        houseDetail = houseDetailRepository.save(houseDetail);
        //######################房屋图片信息######################
        //图片信息列表填充
        List<HousePicture> housePictures = wrapperHousePictures(houseForm, house.getId());
        //保存图片列表
        housePictureRepository.saveAll(housePictures);
        //######################房屋标签信息######################
        List<String> tags = houseForm.getTags();
        if (tags != null && !tags.isEmpty()) {
            List<HouseTag> houseTags = new ArrayList<>();
            for (String tag : tags) {
                houseTags.add(new HouseTag(house.getId(), tag));
            }
            //保存所有标签
            houseTagRepository.saveAll(houseTags);
        }
        //生成对应房屋DTO信息
        //House转换为HouseDTO
        HouseDTO houseDTO = modelMapper.map(house, HouseDTO.class);
        //HouseDetail转换为HouseDetailDTO
        HouseDetailDTO houseDetailDTO = modelMapper.map(houseDetail, HouseDetailDTO.class);
        houseDTO.setHouseDetail(houseDetailDTO);
        //HousePicture转换为HousePictureDTO
        List<HousePictureDTO> housePictureDTOS = new ArrayList<>();
        for(HousePicture housePicture : housePictures){
            housePictureDTOS.add(modelMapper.map(housePicture, HousePictureDTO.class));
        }
        houseDTO.setPictures(housePictureDTOS);
        //设置标签
        if(tags != null && !tags.isEmpty()){
            houseDTO.setTags(tags);
        }
        //设置封面
        houseDTO.setCover(this.cdnPrefix + houseDTO.getCover());
        return ServiceResult.ofSuccess(houseDTO);
    }

    /**
     *更新房屋信息
     */
    @Override
    @Transactional
    public ServiceResult update(HouseForm houseForm) {
        //获取房屋源信息
        Optional<House> houseOptional = this.houseRepository.findById(houseForm.getId());
        if (!houseOptional.isPresent()) {
            return ServiceResult.notFound();
        }
        House house = houseOptional.get();
        //获取房屋对应房屋详细信息
        HouseDetail houseDetail = this.houseDetailRepository.findByHouseId(house.getId());
        if (houseDetail == null) {
            return ServiceResult.notFound();
        }
        //更新房屋详细信息
        ServiceResult wrapperHouseResult = wrapperHouseDetail(houseDetail, houseForm);
        if (!wrapperHouseResult.isSuccess()) {
            return wrapperHouseResult;
        }
        //保存房屋详细信息
        houseDetailRepository.save(houseDetail);
        //图片信息列表填充
        List<HousePicture> housePictures= wrapperHousePictures(houseForm, houseForm.getId());
        //保存图片列表
        housePictureRepository.saveAll(housePictures);
        //设置房屋封面
        if (houseForm.getCover() == null) {
            houseForm.setCover(house.getCover());
        }
        //填充房屋数据
        modelMapper.map(houseForm, house);
        //更新最后操作时间
        house.setLastUpdateTime(new Date());
        //保存房屋信息
        houseRepository.save(house);
        //如果房屋状态为审核通过状态,对房屋加入索引
        if (house.getStatus() == House.HouseStatus.PASSES.getValue()) {
            searchService.index(house.getId());
        }
        return ServiceResult.ofSuccess();
    }
    /**
     *移除图片
     */
    @Override
    public ServiceResult removePhoto(Long id) {
        //获取房屋图片
        Optional<HousePicture> housePictureOptional = housePictureRepository.findById(id);
        if (!housePictureOptional.isPresent()) {
            return ServiceResult.notFound();
        }
        HousePicture housePicture = housePictureOptional.get();
        try {
            //从服务器删除图片
            Response response = this.qiNiuService.delete(housePicture.getPath());
            if (response.isOK()) {
                //删除图片信息
                housePictureRepository.deleteById(id);
                return ServiceResult.ofSuccess();
            } else {
                return new ServiceResult(false, response.error);
            }
        } catch (QiniuException e) {
            e.printStackTrace();
            return new ServiceResult(false, e.getMessage());
        }
    }

    /**
     * 更新封面
     */
    @Override
    @Transactional
    public ServiceResult updateCover(Long coverId, Long targetId) {
        //获取图片
        Optional<HousePicture> housePictureOptional = housePictureRepository.findById(coverId);
        if (!housePictureOptional.isPresent()) {
            return ServiceResult.notFound();
        }
        HousePicture cover = housePictureOptional.get();
        //更新封面
        houseRepository.updateCover(targetId, cover.getPath());
        return ServiceResult.ofSuccess();
    }

    /**
     * 新增标签
     */
    @Override
    @Transactional
    public ServiceResult addTag(Long houseId, String tag) {
        //获取房间信息
        Optional<House> houseOptional = houseRepository.findById(houseId);
        if (!houseOptional.isPresent()) {
            return ServiceResult.notFound();
        }
        House house = houseOptional.get();
        //查看标签信息是否已存在
        HouseTag houseTag = houseTagRepository.findByNameAndHouseId(tag, houseId);
        if (houseTag != null) {
            return new ServiceResult(false, "标签已存在");
        }
        //保存标签信息
        houseTagRepository.save(new HouseTag(houseId, tag));
        return ServiceResult.ofSuccess();
    }

    /**
     * 移除标签
     */
    @Override
    @Transactional
    public ServiceResult removeTag(Long houseId, String tag) {
        //获取房间信息
        Optional<House> houseOptional = houseRepository.findById(houseId);
        if (!houseOptional.isPresent()) {
            return ServiceResult.notFound();
        }
        House house = houseOptional.get();
        //获取标签信息
        HouseTag houseTag = houseTagRepository.findByNameAndHouseId(tag, houseId);
        if (houseTag == null) {
            return new ServiceResult(false, "标签不存在");
        }
        //删除标签
        houseTagRepository.deleteById(houseTag.getId());
        return ServiceResult.ofSuccess();
    }

    /**
     * 更新房源状态
     */
    @Override
    @Transactional
    public ServiceResult updateStatus(Long id, int status) {
        //获取房屋信息
        Optional<House> houseOptional = houseRepository.findById(id);
        if (!houseOptional.isPresent()) {
            return ServiceResult.notFound();
        }
        House house = houseOptional.get();
        //判断新状态是否发生改变
        if (house.getStatus() == status) {
            return new ServiceResult(false, "状态没有发生变化");
        }
        //已出租状态
        if (house.getStatus() == House.HouseStatus.RENTED.getValue()) {
            return new ServiceResult(false, "已出租的房源不允许修改状态");
        }
        //已删除状态
        if (house.getStatus() == House.HouseStatus.DELETED.getValue()) {
            return new ServiceResult(false, "已删除的资源不允许操作");
        }
        //更新房屋状态
        houseRepository.updateStatus(id, status);
        // 上架更新索引 其他情况删除索引
        if (status == House.HouseStatus.PASSES.getValue()) {
            searchService.index(id);
        } else {
            searchService.remove(id);
        }
        return ServiceResult.ofSuccess();
    }


    //////////////////////////////////查找房屋///////////////////////////////////////////////////////////
    /**
     * 管理员查询房屋信息
     */
    @Override
    public ServiceMultiResult<HouseDTO> adminQuery(DatatableSearch searchBody) {
        List<HouseDTO> houseDTOS = new ArrayList<>();
        //排序设置
        Sort sort = new Sort(Sort.Direction.fromString(searchBody.getDirection()), searchBody.getOrderBy());
        //页数
        int page = searchBody.getStart() / searchBody.getLength();
        //分页设置
        Pageable pageable = PageRequest.of(page, searchBody.getLength(), sort);
        //Specification<House>:用于封装查询条件
        Specification<House> specification = new Specification<House>(){
            /**
             * Predicate:封装了 单个的查询条件
             * @param root 根对象。封装了查询条件的对象
             * @param criteriaQuery 定义了一个基本的查询(一般不使用)
             * @param criteriaBuilder 创建一个查询条件
             */
            @Override
            public Predicate toPredicate(Root<House> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                //当前登录管理员为房屋管理者
                Predicate predicate = criteriaBuilder.equal(root.get("adminId"), LoginUserUtil.getLoginUserId());
                //房屋状态不为删除状态
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.notEqual(root.get("status"), House.HouseStatus.DELETED.getValue()));
                //城市
                if (searchBody.getCity() != null) {
                    predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("cityEnName"), searchBody.getCity()));
                }
                //状态
                if (searchBody.getStatus() != null) {
                    predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("status"), searchBody.getStatus()));
                }
                //最早创建时间
                if (searchBody.getCreateTimeMin() != null) {
                    predicate = criteriaBuilder.and(predicate, criteriaBuilder.greaterThanOrEqualTo(root.get("createTime"), searchBody.getCreateTimeMin()));
                }
                //最晚创建时间
                if (searchBody.getCreateTimeMax() != null) {
                    predicate = criteriaBuilder.and(predicate, criteriaBuilder.lessThanOrEqualTo(root.get("createTime"), searchBody.getCreateTimeMax()));
                }
                //标题
                if (searchBody.getTitle() != null) {
                    predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("title"), "%" + searchBody.getTitle() + "%"));
                }
                return predicate;
            }
        };
        //查找数据
        Page<House> houses = houseRepository.findAll(specification, pageable);
        //将House数据转换为HouseDTO
        for(House house : houses){
            HouseDTO houseDTO = modelMapper.map(house, HouseDTO.class);
            houseDTO.setCover(this.cdnPrefix + house.getCover());
            houseDTOS.add(houseDTO);
        }
        return new ServiceMultiResult<>(houses.getTotalElements(), houseDTOS);
    }
    /**
     * 查询指定房源信息
     */
    @Override
    public ServiceResult<HouseDTO> findCompleteOne(Long id) {
        //获取房屋主体信息
        Optional<House> houseOptional = houseRepository.findById(id);
        if (!houseOptional.isPresent()) {
            return ServiceResult.notFound();
        }
        House house = houseOptional.get();
        //获取房屋详细信息
        HouseDetail houseDetail = houseDetailRepository.findByHouseId(id);
        //获取房屋图片信息
        List<HousePicture> housePictures = housePictureRepository.findAllByHouseId(id);
        //获取房屋标签信息
        List<HouseTag> houseTags = houseTagRepository.findAllByHouseId(id);
        //将House转换为HouseDTO
        HouseDTO houseDTO = modelMapper.map(house, HouseDTO.class);
        //设置房屋详细信息DTO
        HouseDetailDTO houseDetailDTO= modelMapper.map(houseDetail, HouseDetailDTO.class);
        houseDTO.setHouseDetail(houseDetailDTO);
        //设置房屋图片DTO
        List<HousePictureDTO> housePictureDTOS = new ArrayList<>();
        for (HousePicture housePicture : housePictures) {
            HousePictureDTO housePictureDTO = modelMapper.map(housePicture, HousePictureDTO.class);
            housePictureDTOS.add(housePictureDTO);
        }
        houseDTO.setPictures(housePictureDTOS);
        //设置标签
        List<String> tags = new ArrayList<>();
        for (HouseTag houseTag : houseTags) {
            tags.add(houseTag.getName());
        }
        houseDTO.setTags(tags);
        // 已登录用户
        if (LoginUserUtil.getLoginUserId() > 0) {
            //获取预约信息
            HouseSubscribe houseSubscribe = houseSubscribeRepository.findByHouseIdAndUserId(house.getId(), LoginUserUtil.getLoginUserId());
            if (houseSubscribe != null) {
                //设置预约状态
                houseDTO.setSubscribeStatus(houseSubscribe.getStatus());
            }
        }
        return ServiceResult.ofSuccess(houseDTO);
    }


    /**
     * 查询房源信息集
     */
    @Override
    public ServiceMultiResult<HouseDTO> query(RentSearch rentSearch) {
        //查询关键字不为空
        if (rentSearch.getKeywords() != null && !rentSearch.getKeywords().isEmpty()) {
            //查询
            ServiceMultiResult<Long> serviceResult = searchService.query(rentSearch);
            //结果集为空
            if (serviceResult.getTotal() == 0) {
                return new ServiceMultiResult<>(0, new ArrayList<>());
            }
            return new ServiceMultiResult<>(serviceResult.getTotal(), wrapperHouse(serviceResult.getResult()));
        }
        //查询关键字为空,进行简单查询
        return simpleQuery(rentSearch);
    }

    /**
     * 简单查询
     * @param rentSearch 房屋搜索请求
     */
    private ServiceMultiResult<HouseDTO> simpleQuery(RentSearch rentSearch) {
        //排序设置
        Sort sort = HouseSort.generateSort(rentSearch.getOrderBy(), rentSearch.getOrderDirection());
        //页数
        int page = rentSearch.getStart() / rentSearch.getSize();
        //分页设置
        Pageable pageable = PageRequest.of(page,rentSearch.getSize(),sort);
        //Specification<House>:用于封装查询条件
        Specification<House> specification =new Specification<House>() {
            /**
             * Predicate:封装了 单个的查询条件
             * @param root 根对象。封装了查询条件的对象
             * @param criteriaQuery 定义了一个基本的查询(一般不使用)
             * @param criteriaBuilder 创建一个查询条件
             * @return Predicate:定义了查询条件
             */
            @Override
            public Predicate toPredicate(Root<House> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                //房屋状态
                Predicate predicate = criteriaBuilder.equal(root.get("status"), House.HouseStatus.PASSES.getValue());
                //城市简称
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("cityEnName"), rentSearch.getCityEnName()));
                //排序条件为地铁距离
                if (HouseSort.DISTANCE_TO_SUBWAY_KEY.equals(rentSearch.getOrderBy())) {
                    //地铁距离大于-1(地铁距离存在)
                    predicate = criteriaBuilder.and(predicate, criteriaBuilder.gt(root.get(HouseSort.DISTANCE_TO_SUBWAY_KEY), -1));
                }
                return predicate;
            }
        };
        //超找数据
        Page<House> houses = houseRepository.findAll(specification, pageable);
        //房屋DTO集合
        List<HouseDTO> houseDTOS = new ArrayList<>();
        //房屋id集合
        List<Long> houseIds = new ArrayList<>();
        //id和房屋DTO映射
        Map<Long, HouseDTO> idToHouseMap = Maps.newHashMap();
        for(House house : houses){
            //House转换为HouseDTO
            HouseDTO houseDTO = modelMapper.map(house, HouseDTO.class);
            //设置
            houseDTO.setCover(this.cdnPrefix + house.getCover());
            houseDTOS.add(houseDTO);
            houseIds.add(house.getId());
            idToHouseMap.put(house.getId(), houseDTO);
        }
        //填充详细信息及标签
        wrapperHouseList(houseIds, idToHouseMap);
        //生成结果
        return new ServiceMultiResult<>(houses.getTotalElements(), houseDTOS);
    }
    ////////////////////////////////////预约服务////////////////////////////////////////////////////////
    /**
     * 加入预约清单
     */
    @Override
    @Transactional
    public ServiceResult addSubscribeOrder(Long houseId) {
        //获取当前登录用户
        Long userId = LoginUserUtil.getLoginUserId();
        //获取预约看房信息
        HouseSubscribe houseSubscribe = houseSubscribeRepository.findByHouseIdAndUserId(houseId, userId);
        if (houseSubscribe != null) {
            return ServiceResult.ofMessage(false, "已加入预约");
        }
        //获取房屋信息
        Optional<House> houseOptional = houseRepository.findById(houseId);
        if (!houseOptional.isPresent()) {
            return ServiceResult.ofMessage(false, "查无此房");
        }
        House house = houseOptional.get();
        houseSubscribe = new HouseSubscribe();
        //设置房源id
        houseSubscribe.setHouseId(houseId);
        //设置用户id
        houseSubscribe.setUserId(userId);
        //设置房源发布者id
        houseSubscribe.setAdminId(house.getAdminId());
        //设置房间状态为已加入待看清单
        houseSubscribe.setStatus(HouseSubscribe.HouseSubscribeStatus.IN_ORDER_LIST.getValue());
        //设置创建时间
        houseSubscribe.setCreateTime( new Date());
        //记录更新时间
        houseSubscribe.setLastUpdateTime( new Date());
        //保存预约信息
        houseSubscribeRepository.save(houseSubscribe);
        return ServiceResult.ofSuccess();
    }

    /**
     *预约看房
     */
    @Override
    @Transactional
    public ServiceResult subscribe(Long houseId, Date orderTime, String telephone, String desc) {
        //获取当前登录用户
        Long userId = LoginUserUtil.getLoginUserId();
        //获取预约看房信息
        HouseSubscribe houseSubscribe = houseSubscribeRepository.findByHouseIdAndUserId(houseId, userId);
        if (houseSubscribe == null) {
            return ServiceResult.ofMessage(false, "无预约记录");
        }
        //当前房屋状态不为已加入待看清单,无法预约
        if (houseSubscribe.getStatus() != HouseSubscribe.HouseSubscribeStatus.IN_ORDER_LIST.getValue()) {
            return ServiceResult.ofMessage(false, "无法预约");
        }
        //更新房间状态为已经预约看房
        houseSubscribe.setStatus(HouseSubscribe.HouseSubscribeStatus.IN_ORDER_TIME.getValue());
        //更新最后更新时间
        houseSubscribe.setLastUpdateTime(new Date());
        //设置用户用户描述
        houseSubscribe.setDesc(desc);
        //设置联系电话
        houseSubscribe.setTelephone(telephone);
        //设置预约时间
        houseSubscribe.setOrderTime(orderTime);
        //保存预约信息
        houseSubscribeRepository.save(houseSubscribe);
        return ServiceResult.ofSuccess();
    }

    /**
     * 取消预约
     */
    @Override
    @Transactional
    public ServiceResult cancelSubscribe(Long houseId) {
        //获取当前登录用户
        Long userId = LoginUserUtil.getLoginUserId();
        //获取预约看房信息
        HouseSubscribe houseSubscribe = houseSubscribeRepository.findByHouseIdAndUserId(houseId, userId);
        if (houseSubscribe == null) {
            return ServiceResult.ofMessage(false, "无预约记录");
        }
        //删除预约记录
        houseSubscribeRepository.deleteById(houseSubscribe.getId());
        return ServiceResult.ofSuccess();
    }
    /**
     * 完成预约
     */
    @Override
    @Transactional
    public ServiceResult finishSubscribe(Long houseId) {
        //获取当前登录用户
        Long adminId = LoginUserUtil.getLoginUserId();
        //获取预约看房信息
        HouseSubscribe houseSubscribe = houseSubscribeRepository.findByHouseIdAndAdminId(houseId, adminId);
        if (houseSubscribe == null) {
            return ServiceResult.ofMessage(false, "无预约记录");
        }
        //更新预约状态为已完成预约
        houseSubscribeRepository.updateStatus(houseSubscribe.getId(), HouseSubscribe.HouseSubscribeStatus.FINISH.getValue());
        //更新被看次数
        houseRepository.updateWatchTimes(houseId);
        return ServiceResult.ofSuccess();
    }
    /**
     * 管理员查询指定状态的预约列表
     */
    @Override
    public ServiceMultiResult<Pair<HouseDTO, HouseSubscribeDTO>> findSubscribeList(int start, int size) {
        //获取当前登录用户
        Long userId = LoginUserUtil.getLoginUserId();
        //设置分页信息
        Pageable pageable = PageRequest.of(start / size, size, new Sort(Sort.Direction.DESC, "orderTime"));
        //获取已预约看房信息集合
        Page<HouseSubscribe> page = houseSubscribeRepository.findAllByAdminIdAndStatus(userId, HouseSubscribe.HouseSubscribeStatus.IN_ORDER_TIME.getValue(), pageable);
        //将数据转化为指定结果
        return pageToResult(page);
    }

    /**
     * 用户查询预约信息集合
     */
    @Override
    public ServiceMultiResult<Pair<HouseDTO, HouseSubscribeDTO>> querySubscribeList(
            HouseSubscribe.HouseSubscribeStatus status,
            int start,
            int size) {
        //获取当前登录用户
        Long userId = LoginUserUtil.getLoginUserId();
        //设置分页信息
        Pageable pageable = PageRequest.of(start / size, size, new Sort(Sort.Direction.DESC, "createTime"));
        //获取指定状态预约信息集合
        Page<HouseSubscribe> page = houseSubscribeRepository.findAllByUserIdAndStatus(userId, status.getValue(), pageable);
        //将数据转化为指定结果
        return pageToResult(page);
    }
    ////////////////////////////////////地图查找服务//////////////////////////////////////////////////////
    /**
     * 全地图查询
     */
    @Override
    public ServiceMultiResult<HouseDTO> wholeMapQuery(MapSearch mapSearch) {
        //城市级别地图数据查询
        ServiceMultiResult<Long> serviceResult = searchService.mapQuery(mapSearch.getCityEnName(), mapSearch.getOrderBy(), mapSearch.getOrderDirection(), mapSearch.getStart(), mapSearch.getSize());
        //不存在数据
        if (serviceResult.getTotal() == 0) {
            return new ServiceMultiResult<>(0, new ArrayList<>());
        }
        //房屋信息填充
        List<HouseDTO> houses = wrapperHouse(serviceResult.getResult());
        return new ServiceMultiResult<>(serviceResult.getTotal(), houses);
    }

    /**
     * 精确范围数据查询
     */
    @Override
    public ServiceMultiResult<HouseDTO> boundMapQuery(MapSearch mapSearch) {
        //精确范围地图数据查询
        ServiceMultiResult<Long> serviceResult = searchService.mapQuery(mapSearch);
        //不存在数据
        if (serviceResult.getTotal() == 0) {
            return new ServiceMultiResult<>(0, new ArrayList<>());
        }
        //房屋信息填充
        List<HouseDTO> houses = wrapperHouse(serviceResult.getResult());
        return new ServiceMultiResult<>(serviceResult.getTotal(), houses);
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * 根据房屋id集合填充房屋信息集合
     */
    private List<HouseDTO> wrapperHouse(List<Long> houseIds) {
        //房屋信息
        List<HouseDTO> houseDTOS = new ArrayList<>();
        //id和房屋映射
        Map<Long, HouseDTO> idToHouseMap = new HashMap<>();
        //获取房屋信息集合
        Iterable<House> houses = houseRepository.findAllById(houseIds);
        //将House转换为HouseDTO
        for(House house : houses){
            HouseDTO houseDTO = modelMapper.map(house, HouseDTO.class);
            //设置封面
            houseDTO.setCover(this.cdnPrefix + house.getCover());
            idToHouseMap.put(house.getId(), houseDTO);
        }
        //房屋信息中添加详细信息及标签
        wrapperHouseList(houseIds, idToHouseMap);
        // 矫正房屋顺序
        for (Long houseId : houseIds) {
            houseDTOS.add(idToHouseMap.get(houseId));
        }
        return houseDTOS;
    }
    /**
     * 房源详细信息填充
     * @param houseDetail 房屋详细信息
     * @param houseForm 房屋表单
     * @return 是否成功填充
     */
    private ServiceResult wrapperHouseDetail(HouseDetail houseDetail, HouseForm houseForm) {
        //获取地铁线信息
        Optional<Subway> subwayOptional = subwayRepository.findById(houseForm.getSubwayLineId());
        if (!subwayOptional.isPresent()) {
            return ServiceResult.ofMessage(false, "地铁线信息不存在!");
        }
        //获取地铁站信息
        Optional<SubwayStation> subwayStationOptional = subwayStationRepository.findById(houseForm.getSubwayStationId());
        if (!subwayStationOptional.isPresent() ) {
            return ServiceResult.ofMessage(false, "地铁站信息不存在!");
        }
        //地铁线
        Subway subway = subwayOptional.get();
        //地铁站
        SubwayStation subwayStation = subwayStationOptional.get();
        //地铁站和地铁站不对应
        if (!subway.getId().equals(subwayStation.getSubwayId())){
            return ServiceResult.ofMessage(false, "地铁线中不存在此站!");
        }
        //补全属性
        //详细描述
        houseDetail.setDescription(houseForm.getDescription());
        //户型介绍
        houseDetail.setLayoutDesc(houseForm.getLayoutDesc());
        //交通出行
        houseDetail.setTraffic(houseForm.getTraffic());
        //周围配套
        houseDetail.setRoundService(houseForm.getRoundService());
        //租赁方式
        houseDetail.setRentWay(houseForm.getRentWay());
        //详细地址
        houseDetail.setAddress(houseForm.getAddress());
        //地铁线id
        houseDetail.setSubwayLineId(subway.getId());
        //地铁线名称
        houseDetail.setSubwayLineName(subway.getName());
        //地铁站id
        houseDetail.setSubwayStationId(subwayStation.getId());
        //地铁站名称
        houseDetail.setSubwayStationName(subwayStation.getName());
        return ServiceResult.ofSuccess();
    }

    /**
     *  图片信息列表填充
     * @param houseForm 房屋表单
     * @param houseId 房屋id
     */
    private List<HousePicture> wrapperHousePictures(HouseForm houseForm, Long houseId) {
        List<HousePicture> housePictures = new ArrayList<>();
        if (houseForm.getPhotos() == null || houseForm.getPhotos().isEmpty()) {
            return housePictures;
        }
        //遍历图片集,将图片加入集合
        for (PhotoForm photoForm : houseForm.getPhotos()) {
            HousePicture housePicture = new HousePicture();
            //设置属性
            //房屋id
            housePicture.setHouseId(houseId);
            //图片路径
            housePicture.setCdnPrefix(cdnPrefix);
            //文件名
            housePicture.setPath(photoForm.getPath());
            //宽
            housePicture.setWidth(photoForm.getWidth());
            //高
            housePicture.setHeight(photoForm.getHeight());
            //添加进集合
            housePictures.add(housePicture);
        }
        return housePictures;
    }


    /**
     * 房屋信息中添加详细信息及标签
     * @param houseIds 房屋id集合
     * @param idToHouseMap 房屋id和房屋信息映射关系
     */
    private void wrapperHouseList(List<Long> houseIds, Map<Long, HouseDTO> idToHouseMap) {
        //根据房屋id集合获取房屋详细信息集合
        List<HouseDetail> houseDetails = houseDetailRepository.findAllByHouseIdIn(houseIds);
        //遍历添加房屋详细信息
        for(HouseDetail houseDetail : houseDetails){
            HouseDTO houseDTO = idToHouseMap.get(houseDetail.getHouseId());
            //HouseDetail转换为HouseDetailDTO
            HouseDetailDTO houseDetailDTO = modelMapper.map(houseDetail, HouseDetailDTO.class);
            houseDTO.setHouseDetail(houseDetailDTO);
        }
        //根据房屋id集合获取房屋标签集合
        List<HouseTag> houseTags = houseTagRepository.findAllByHouseIdIn(houseIds);
        //遍历添加房屋标签
        for (HouseTag houseTag : houseTags){
            HouseDTO house = idToHouseMap.get(houseTag.getHouseId());
            house.getTags().add(houseTag.getName());
        }
    }

    /**
     * 渲染数据,将Page数据转换为ServiceMultiResult
     * @param page 查询数据
     */
    private ServiceMultiResult<Pair<HouseDTO, HouseSubscribeDTO>> pageToResult(Page<HouseSubscribe> page) {
        List<Pair<HouseDTO, HouseSubscribeDTO>> result = new ArrayList<>();
        //数据条数为0
        if (page.getSize() < 1) {
            return new ServiceMultiResult<>(page.getTotalElements(), result);
        }
        //房屋描述集合
        List<HouseSubscribeDTO> houseSubscribeDTOS = new ArrayList<>();
        //房屋id集合
        List<Long> houseIds = new ArrayList<>();
        //遍历数据
        for(HouseSubscribe houseSubscribe : page){
            //将HouseSubscribe转换为HouseSubscribeDTO
            houseSubscribeDTOS.add(modelMapper.map(houseSubscribe, HouseSubscribeDTO.class));
            houseIds.add(houseSubscribe.getHouseId());
        }
        //id,房屋DTO映射
        Map<Long, HouseDTO> idToHouseMap = new HashMap<>();
        //获取id对应的房屋信息
        Iterable<House> houses = houseRepository.findAllById(houseIds);
        //遍历数据
        for (House house : houses){
            idToHouseMap.put(house.getId(), modelMapper.map(house, HouseDTO.class));
        }
        //生成Pair
        for (HouseSubscribeDTO houseSubscribeDTO : houseSubscribeDTOS) {
            Pair<HouseDTO, HouseSubscribeDTO> pair = Pair.of(idToHouseMap.get(houseSubscribeDTO.getHouseId()), houseSubscribeDTO);
            result.add(pair);
        }
        //生成结果
        return new ServiceMultiResult<>(page.getTotalElements(), result);
    }






}

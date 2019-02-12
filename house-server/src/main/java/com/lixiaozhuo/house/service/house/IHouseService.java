package com.lixiaozhuo.house.service.house;

import java.util.Date;

import com.lixiaozhuo.house.entity.HouseSubscribe;
import com.lixiaozhuo.house.service.ServiceMultiResult;
import com.lixiaozhuo.house.service.ServiceResult;
import com.lixiaozhuo.house.web.dto.HouseDTO;
import com.lixiaozhuo.house.web.dto.HouseSubscribeDTO;
import com.lixiaozhuo.house.web.form.DatatableSearch;
import com.lixiaozhuo.house.web.form.HouseForm;
import com.lixiaozhuo.house.web.form.MapSearch;
import com.lixiaozhuo.house.web.form.RentSearch;
import org.springframework.data.util.Pair;
/**
 * 房屋管理服务接口
 */
public interface IHouseService {
    /**
     * 新增房屋信息
     * @param houseForm 房屋表单数据
     */
    ServiceResult<HouseDTO> save(HouseForm houseForm);

    /**
     * 更新房屋信息
     * @param houseForm 房屋表单数据
     */
    ServiceResult update(HouseForm houseForm);

    /**
     * 查询指定房源信息
     * @param id 房屋id
     */
    ServiceResult<HouseDTO> findCompleteOne(Long id);

    /**
     * 查询房源信息集合
     * @param rentSearch 房屋搜索请求
     */
    ServiceMultiResult<HouseDTO> query(RentSearch rentSearch);

    /**
     * 管理员查询房屋信息
     * @param searchBody DataTable搜索请求
     */
    ServiceMultiResult<HouseDTO> adminQuery(DatatableSearch searchBody);
    //////////////////////////////////更新房屋属性///////////////////////////////////////////////////////////
    /**
     * 移除图片
     * @param id 房屋id
     */
    ServiceResult removePhoto(Long id);

    /**
     * 更新封面
     * @param coverId  封面id
     * @param targetId 目标id
     */
    ServiceResult updateCover(Long coverId, Long targetId);

    /**
     * 新增标签
     * @param houseId 房屋id
     * @param tag 标签
     */
    ServiceResult addTag(Long houseId, String tag);

    /**
     * 移除标签
     * @param houseId  房屋id
     * @param tag 标签
     */
    ServiceResult removeTag(Long houseId, String tag);

    /**
     * 更新房源状态
     * @param id 房屋id
     * @param status  状态
     */
    ServiceResult updateStatus(Long id, int status);
    ////////////////////////////////////地图查找服务//////////////////////////////////////////////////////

    /**
     * 全地图查询
     * @param mapSearch 地图搜索请求
     */
    ServiceMultiResult<HouseDTO> wholeMapQuery(MapSearch mapSearch);

    /**
     * 精确范围数据查询
     * @param mapSearch 地图搜索请求
     */
    ServiceMultiResult<HouseDTO> boundMapQuery(MapSearch mapSearch);

    ////////////////////////////////////预约服务////////////////////////////////////////////////////////
    /**
     * 加入预约清单
     * @param houseId 房屋id
     */
    ServiceResult addSubscribeOrder(Long houseId);

    /**
     * 预约看房
     * @param houseId 房屋id
     * @param orderTime 预约时间
     * @param telephone 手机号码
     * @param desc 用户描述
     */
    ServiceResult subscribe(Long houseId, Date orderTime, String telephone, String desc);

    /**
     * 取消预约
     * @param houseId 房屋id
     */
    ServiceResult cancelSubscribe(Long houseId);

    /**
     * 完成预约
     * @param houseId 房屋id
     */
    ServiceResult finishSubscribe(Long houseId);

    /**
     * 查询指定状态的预约列表
     * @param status 预约状态
     * @param start 开始页
     * @param size 页面大小
     */
    ServiceMultiResult<Pair<HouseDTO, HouseSubscribeDTO>> querySubscribeList(HouseSubscribe.HouseSubscribeStatus status, int start, int size);

    /**
     * 查询预约信息集合
     * @param start 开始页
     * @param size 页面大小
     */
    ServiceMultiResult<Pair<HouseDTO, HouseSubscribeDTO>> findSubscribeList(int start, int size);


}

package com.lixiaozhuo.parking.service.impl;

import com.lixiaozhuo.parking.dao.PlaceDao;
import com.lixiaozhuo.parking.exception.CustomException;
import com.lixiaozhuo.parking.pojo.Condition;
import com.lixiaozhuo.parking.pojo.Place;
import com.lixiaozhuo.parking.service.IPlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * 车位业务
 */
@Service
public class PlaceServiceImpl implements IPlaceService {
    @Autowired
    private PlaceDao dao;

    //删除车位信息
    @Override
    public void deletePlace(int id) {
        Place place = getPlace(id);
        //状态为占用,不可删除
        if(place.getState()){
            throw new CustomException("车位状态为占用,不可删除");
        }
        //删除
        dao.delete(place);
    }

    //保存车位信息
    @Override
    public void savePlace(Place place) {
        dao.save(place);
    }


    //根据车位编号获取车位信息
    @Override
    public Place getPlace(int id) {
        return dao.getOne(id);
    }

    //获取未分配的固定车位
    @Override
    public List<Place> listFreeParkFixedPlace() {
        return dao.listFreeParkFixedPlace();
    }

    //获取未分配的临时车位
    @Override
    public List<Place> listFreeParkTemporaryPlace() {
        return  dao.listFreeParkTemporaryPlace();
    }


    //获取分页后车位信息列表
    @Override
    public Page<Place> listPlace(int page) {
        page --;
        //分页
        Pageable pageable = PageRequest.of(page,15);
        //获取数据
        return  dao.findAll(pageable);
    }

    //根据查询条件获取分页后车位信息列表
    @Override
    public Page<Place> listPlace(Condition condition, int page) {
        page --;
        /**
         * Specification<Place>:用于封装查询条件
         */
        Specification<Place> spec = new Specification<Place>() {

            //Predicate:封装了 单个的查询条件

            /**
             * Root<Users> root:根对象。封装了查询条件的对象
             * CriteriaQuery<?> query：定义了一个基本的查询。一般不使用
             * CriteriaBuilder cb:创建一个查询条件
             * @return Predicate:定义了查询条件
             */
            @Override
            public Predicate toPredicate(Root<Place> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //条件Key
                String conditionKey = condition.getKey();
                //条件Value
                String conditionValue = condition.getValue();
                //设置条件
                Predicate pre = null;
                //车位编号  区域:固定车位或者临时车位
                if("no".equals(conditionKey) || "section".equals(conditionKey)) {
                    pre = cb.like(root.get(conditionKey), "%" + conditionValue + "%");
                    //位置状态:是否被占用
                }else if("state".equals(conditionKey)){
                    String value = conditionValue.toString().toLowerCase().trim();
                    //搜索词包含占用或true
                    if(value.contains("占用") || value.contains("true") ){
                        pre = cb.equal(root.get(conditionKey),true);
                    }else if(value.contains("空闲") || value.contains("no")){
                        pre = cb.equal(root.get(conditionKey),false);
                    }
                }
                return pre;
            }
        };
        //排序
        Sort sort = new Sort(Sort.Direction.ASC,"id");
        //分页
        Pageable pageable = PageRequest.of(page, 15,sort);
        //获取数据
        Page<Place> pages = dao.findAll(spec,pageable);
        return pages;
    }






}

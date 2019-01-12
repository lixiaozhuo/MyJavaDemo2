package com.lixiaozhuo.parking.service;

import com.lixiaozhuo.parking.pojo.Condition;
import com.lixiaozhuo.parking.pojo.Place;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 位置业务接口
 */
public interface IPlaceService {
    /**
     * 删除车位
     * @param id
     */
    void deletePlace(int id);



    /**
     * 保存车位
     * @param place
     */
    void savePlace(Place place);

    /**
     * 获取车位
     * @param id
     * @return
     */
    Place getPlace(int id);

    /**
     * 获取空闲的固定车位
     * @return
     */
    List<Place> listFreeParkFixedPlace();

    /**
     * 获取空闲的临时车位
     * @return
     */
    List<Place> listFreeParkTemporaryPlace();

    /**
     * 获取分页后的车位列表
     * @param page
     * @return
     */
    Page<Place> listPlace(int page);

    /**
     * 根据条件获取分页后的车位列表
     * @param condition
     * @param page
     * @return
     */
    Page<Place> listPlace(Condition condition, int page);
}

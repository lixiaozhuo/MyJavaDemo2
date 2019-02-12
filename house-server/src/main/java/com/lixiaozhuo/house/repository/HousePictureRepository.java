package com.lixiaozhuo.house.repository;

import java.util.List;

import com.lixiaozhuo.house.entity.HousePicture;
import org.springframework.data.repository.CrudRepository;

/**
 * 房屋图片信息DAO
 */
public interface HousePictureRepository extends CrudRepository<HousePicture, Long> {
    /**
     * 根据房屋id获取房屋图片集合
     * @param houseId  房屋id
     * @return 房屋图片集合
     */
    List<HousePicture> findAllByHouseId(Long houseId);
}

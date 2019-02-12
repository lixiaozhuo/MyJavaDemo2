package com.lixiaozhuo.house.repository;

import java.util.List;

import com.lixiaozhuo.house.entity.HouseDetail;
import org.springframework.data.repository.CrudRepository;

/**
 * 房屋详细信息DAO
 */
public interface HouseDetailRepository extends CrudRepository<HouseDetail, Long>{
    /**
     * 根据房屋id获取房屋详细信息
     * @param houseId 房屋id
     * @return 房屋详细信息
     */
    HouseDetail findByHouseId(Long houseId);

    /**
     * 根据房屋id集合获取所有房屋详细信息
     * @param houseIds 房屋id集合
     * @return 房屋详细信息集合
     */
    List<HouseDetail> findAllByHouseIdIn(List<Long> houseIds);
}

package com.lixiaozhuo.house.repository;

import java.util.List;

import com.lixiaozhuo.house.entity.HouseTag;
import org.springframework.data.repository.CrudRepository;


/**
 * 房屋标签信息DAO
 */
public interface HouseTagRepository extends CrudRepository<HouseTag, Long> {
    /**
     * 根据标签名和房屋id获取标签
     * @param name 标签名
     * @param houseId 房屋id
     * @return 标签
     */
    HouseTag findByNameAndHouseId(String name, Long houseId);

    /**
     * 根据房屋id获取房屋标签集合
     * @param houseId 房屋id
     * @return 房屋标签集合
     */
    List<HouseTag> findAllByHouseId(Long houseId);

    /**
     * 根据房屋id集合获取所要求房屋标签集合
     * @param houseIds 房屋id集合
     * @return 房屋标签集合
     */
    List<HouseTag> findAllByHouseIdIn(List<Long> houseIds);
}

package com.lixiaozhuo.house.repository;

import java.util.List;

import com.lixiaozhuo.house.entity.SubwayStation;
import org.springframework.data.repository.CrudRepository;


/**
 * 地铁站信息DAO
 */
public interface SubwayStationRepository extends CrudRepository<SubwayStation, Long> {
    /**
     * 根据地铁线路获取其所有地铁站信息
     * @param subwayId 地铁线路id
     * @return 地铁站信息集合
     */
    List<SubwayStation> findAllBySubwayId(Long subwayId);
}

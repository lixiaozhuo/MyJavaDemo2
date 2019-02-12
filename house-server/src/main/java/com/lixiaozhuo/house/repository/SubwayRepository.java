package com.lixiaozhuo.house.repository;

import java.util.List;

import com.lixiaozhuo.house.entity.Subway;
import org.springframework.data.repository.CrudRepository;


/**
 * 地铁线路信息DAO
 */
public interface SubwayRepository extends CrudRepository<Subway, Long>{
    /**
     * 获取指定城市所有地铁线路信息
     * @param cityEnName 城市英文名称缩写
     * @return 地铁线路信息集合
     */
    List<Subway> findAllByCityEnName(String cityEnName);
}

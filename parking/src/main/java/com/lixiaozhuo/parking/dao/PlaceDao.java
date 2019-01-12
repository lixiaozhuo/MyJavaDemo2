package com.lixiaozhuo.parking.dao;

import com.lixiaozhuo.parking.pojo.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 停车位置Repository接口
 */
public interface PlaceDao extends JpaRepository<Place, Integer>, JpaSpecificationExecutor<Place> {
    //获取空闲的固定车位
    @Query("FROM Place WHERE section='固定车位' AND state = 0")
    List<Place> listFreeParkFixedPlace();

    //获取空闲的临时车位
    @Query("FROM Place WHERE section='临时车位' AND state = 0")
    List<Place> listFreeParkTemporaryPlace();
}

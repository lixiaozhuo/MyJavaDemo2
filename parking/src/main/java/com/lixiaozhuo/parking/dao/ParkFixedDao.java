package com.lixiaozhuo.parking.dao;

import com.lixiaozhuo.parking.pojo.ParkFixed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 固定停车Repository接口
 */
public interface ParkFixedDao extends JpaRepository<ParkFixed, Integer>, JpaSpecificationExecutor<ParkFixed> {
}

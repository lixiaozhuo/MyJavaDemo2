package com.lixiaozhuo.parking.dao;

import com.lixiaozhuo.parking.pojo.ParkTemporary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 临时停车Repository接口
 */
public interface ParkTemporaryDao extends JpaRepository<ParkTemporary, Integer>, JpaSpecificationExecutor<ParkTemporary> {
}

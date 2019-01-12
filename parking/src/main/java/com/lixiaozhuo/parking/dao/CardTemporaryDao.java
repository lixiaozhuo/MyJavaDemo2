package com.lixiaozhuo.parking.dao;

import com.lixiaozhuo.parking.pojo.CardTemporary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 临时IC卡Repository接口
 */
public interface CardTemporaryDao extends JpaRepository<CardTemporary, Integer>, JpaSpecificationExecutor<CardTemporary> {
    //获取未入场的临时IC卡
    @Query("FROM CardTemporary WHERE state = 0")
    List<CardTemporary> listCardTemporaryNotEntry();
}

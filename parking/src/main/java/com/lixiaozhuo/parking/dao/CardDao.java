package com.lixiaozhuo.parking.dao;

import com.lixiaozhuo.parking.pojo.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * IC卡Repository接口
 */
public interface CardDao extends JpaRepository<Card, Integer>, JpaSpecificationExecutor<Card> {
    //获取未入场的IC卡
    @Query("FROM Card  WHERE state = 0")
    List<Card> listCardNotEntry();
}

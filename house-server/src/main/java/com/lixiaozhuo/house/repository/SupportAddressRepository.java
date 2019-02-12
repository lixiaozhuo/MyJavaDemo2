package com.lixiaozhuo.house.repository;

import java.util.List;


import com.lixiaozhuo.house.entity.SupportAddress;
import org.springframework.data.repository.CrudRepository;

/**
 *支持地址信息DAO
 */
public interface SupportAddressRepository extends CrudRepository<SupportAddress, Long>{
    /**
     * 根据指定行政级别获取其所有支持地址信息
     * @param level 行政级别 city-市 region-区
     * @return 支持地址信息集合
     */
    List<SupportAddress> findAllByLevel(String level);

    /**
     * 根据行政单位名称和行政级别获取所支持地址信息
     * @param enName 行政单位英文名缩写
     * @param level 行政级别city-市 region-区
     * @return 支持地址信息
     */
    SupportAddress findByEnNameAndLevel(String enName, String level);

    /**
     * 根据行政单位名称和上一级行政单位获取所支持地址信息
     * @param enName 行政单位英文名缩写
     * @param belongTo 上一级行政单位
     * @return 支持地址信息
     */
    SupportAddress findByEnNameAndBelongTo(String enName, String belongTo);

    /**
     *根据行政级别和上一级行政单位获取支持地址信息集合
     * @param level 行政级别city-市 region-区
     * @param belongTo 上一级行政单位
     * @return 支持地址信息集合
     */
    List<SupportAddress> findAllByLevelAndBelongTo(String level, String belongTo);

}

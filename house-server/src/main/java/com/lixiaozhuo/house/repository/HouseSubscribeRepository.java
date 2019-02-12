package com.lixiaozhuo.house.repository;

import com.lixiaozhuo.house.entity.HouseSubscribe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

/**
 * 预约看房信息DAO
 */
public interface HouseSubscribeRepository extends PagingAndSortingRepository<HouseSubscribe, Long>{
    /**
     * 根据用户id和房屋id获取预约看房信息
     * @param houseId  房屋id
     * @param userId 用户id
     * @return 预约看房信息
     */
    HouseSubscribe findByHouseIdAndUserId(Long houseId, Long userId);

    /**
     * 根据用户id和预约状态获取预约看房信息集合
     * @param userId 用户id
     * @param status 预约状态
     * @param pageable 分页信息
     * @return 预约看房信息集合
     */
    Page<HouseSubscribe> findAllByUserIdAndStatus(Long userId, int status, Pageable pageable);

    /**
     *根据房屋id和房源发布者id获取预约看房信息
     * @param houseId 房屋id
     * @param adminId 房源发布者id
     * @return 预约看房信息
     */
    HouseSubscribe findByHouseIdAndAdminId(Long houseId, Long adminId);

    /**
     * 根据房源发布者id和预约状态获取预约看房信息集合
     * @param adminId 房源发布者id
     * @param status 预约状态
     * @param pageable 分页信息
     * @return 预约看房信息集合
     */
    Page<HouseSubscribe> findAllByAdminIdAndStatus(Long adminId, int status, Pageable pageable);

    /**
     * 更新预约状态
     * @param id  预约信息id
     * @param status 预约状态
     */
    @Modifying
    @Query("update HouseSubscribe as subscribe set subscribe.status = :status where subscribe.id = :id")
    void updateStatus(@Param(value = "id") Long id, @Param(value = "status") int status);
}

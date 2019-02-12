package com.lixiaozhuo.house.repository;

import com.lixiaozhuo.house.entity.House;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;


/**
 * 房屋主体信息DAO
 */
public interface HouseRepository extends PagingAndSortingRepository<House, Long>, JpaSpecificationExecutor<House> {
    /**
     * 更新房屋封面
     * @param id  房屋id
     * @param cover 房屋新封面
     */
    @Modifying
    @Query("update House as house set house.cover = :cover where house.id = :id")
    void updateCover(@Param(value = "id") Long id, @Param(value = "cover") String cover);

    /**
     *更新房屋状态
     * @param id 房屋id
     * @param status 房屋新状态
     */
    @Modifying
    @Query("update House as house set house.status = :status where house.id = :id")
    void updateStatus(@Param(value = "id") Long id, @Param(value = "status") int status);

    /**
     * 更新被看次数
     * @param id 房屋id
     */
    @Modifying
    @Query("update House as house set house.watchTimes = house.watchTimes + 1 where house.id = :id")
    void updateWatchTimes(@Param(value = "id") Long id);

}

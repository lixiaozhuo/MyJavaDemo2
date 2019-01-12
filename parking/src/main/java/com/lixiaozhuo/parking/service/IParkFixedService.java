package com.lixiaozhuo.parking.service;

import com.lixiaozhuo.parking.pojo.Condition;
import com.lixiaozhuo.parking.pojo.ParkFixed;
import org.springframework.data.domain.Page;

/**
 * 固定停车业务接口
 */
public interface IParkFixedService {
    /**
     * 删除固定停车记录
     * @param id
     */
    void deleteParkFixed(int id);

    /**
     * 固定用户车辆进入车场
     * @param card_id
     */
    void entryPark(int card_id);

    /**
     * 固定用户车辆离开车场
     * @param id
     */
    void leavePark(int id);

    /**
     * 获取分页后固定停车记录列表
     * @param page
     * @return
     */
    Page<ParkFixed> listParkFixed(int page);

    /**
     * 根据条件获取分页后固定停车记录列表
     * @param condition
     * @param page
     * @return
     */
    Page<ParkFixed> listParkFixed(Condition condition, int page);
}

package com.lixiaozhuo.parking.service;

import com.lixiaozhuo.parking.pojo.Condition;
import com.lixiaozhuo.parking.pojo.ParkTemporary;
import org.springframework.data.domain.Page;

/**
 * 临时停车业务接口
 */
public interface IParkTemporaryService {
    /**
     * 删除临时停车记录
     * @param id
     */
    void deleteParkTemporary(int id);

    /**
     * 保存临时停车记录
     * @param parkTemporary
     */
    void saveParkTemporary(ParkTemporary parkTemporary);

    /**
     * 临时车辆进入车场
     * @param cardId
     * @param carNo
     */
    void entryParkTemporary(int cardId,String  carNo);

    /**
     * 获取临时停车记录
     * @param id
     * @return
     */
    ParkTemporary getParkTemporary(int id);

    /**
     * 获取分页后的临时停车记录列表
     * @param page
     * @return
     */
    Page<ParkTemporary> listParkTemporary(int page);

    /**
     * 根据条件获取分页后的临时停车记录列表
     * @param condition
     * @param page
     * @return
     */
    Page<ParkTemporary> listParkTemporary(Condition condition, int page);
}

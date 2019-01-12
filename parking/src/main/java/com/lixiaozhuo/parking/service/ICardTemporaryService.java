package com.lixiaozhuo.parking.service;

import com.lixiaozhuo.parking.pojo.CardTemporary;
import com.lixiaozhuo.parking.pojo.Condition;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 临时IC卡业务接口
 */
public interface ICardTemporaryService {
    /**
     * 删除临时IC卡信息
     * @param id
     */
    void deleteCardTemporary(int id);

    /**
     * 保存临时IC卡信息
     * @param card
     */
    void saveCardTemporary(CardTemporary card);

    /**
     * 更新IC卡状态
     * @param cardTemporaryId
     * @param state
     */
    void updateCardTemporaryState(int cardTemporaryId,boolean state);

    /**
     *根据IC卡编号获取临时IC卡信息
     * @param id
     * @return
     */
    CardTemporary getCardTemporary(int id);

    /**
     * 获取所有的未入场的临时IC卡信息
     * @return
     */
    List<CardTemporary> listCardTemporaryNotEntry();

    /**
     * 获取分页后临时IC卡信息列表
     * @param page
     * @return
     */
    Page<CardTemporary> listCardTemporary(int page);

    /**
     * 根据查询条件获取分页后临时IC卡信息列表
     * @param page
     * @return
     */
    Page<CardTemporary> listCardTemporary(Condition condition, int page);
}

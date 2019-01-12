package com.lixiaozhuo.parking.service;

import com.lixiaozhuo.parking.pojo.Card;
import com.lixiaozhuo.parking.pojo.Condition;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * IC卡业务接口
 */
public interface ICardService {
    /**
     * 删除IC卡信息
     * @param id
     */
    void deleteCard(int id);

    /**
     * 保存IC卡信息
     * @param card
     */
    void saveCard(Card card);

    /**
     * 更新IC卡状态
     * @param cardId
     * @param state
     */
    void updateCardState(int cardId,boolean state);

    /**
     *根据IC卡编号获取IC卡信息
     * @param id
     * @return
     */
    Card getCard(int id);

    /**
     * 获取所有的未入场的IC卡信息
     * @return
     */
    List<Card> listCardNotEntry();

    /**
     * 获取分页后IC卡信息列表
     * @param page
     * @return
     */
    Page<Card> listCard(int page);

    /**
     * 根据查询条件获取分页后IC卡信息列表
     * @param page
     * @return
     */
    Page<Card> listCard(Condition condition, int page);
}

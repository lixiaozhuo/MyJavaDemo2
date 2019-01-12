package com.lixiaozhuo.parking.service.impl;

import com.lixiaozhuo.parking.dao.CardDao;
import com.lixiaozhuo.parking.exception.CustomException;
import com.lixiaozhuo.parking.pojo.Card;
import com.lixiaozhuo.parking.pojo.Condition;
import com.lixiaozhuo.parking.pojo.Place;
import com.lixiaozhuo.parking.service.ICardService;
import com.lixiaozhuo.parking.service.IPlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * IC卡业务
 */
@Service
public class CardServiceImpl implements ICardService {
    @Autowired
    private CardDao dao;

    @Autowired
    private IPlaceService placeService;

    //删除IC卡信息
    @Override
    public void deleteCard(int id) {
        Card card = getCard(id);
        //状态为占用,不可删除
        if(card.getState()){
            throw new CustomException("IC卡状态为占用,不可删除");
        }
        //更新车位状态
        card.getPlace().setState(false);
        placeService.savePlace(card.getPlace());
        //删除卡
        dao.delete(card);
    }

    //保存IC卡信息
    @Override
    public void saveCard(Card card) {
        //更新旧车位状态
        if(card.getId() != null){
            //IC卡原信息
            Card oldCard = getCard(card.getId());
            //更新车位状态
            oldCard.getPlace().setState(false);
            placeService.savePlace(oldCard.getPlace());
        }
        //更新新车位状态
        Place place = placeService.getPlace(card.getPlace().getId());
        place.setState(true);
        card.setPlace(place);
        //保存
        dao.save(card);
    }

    //更新IC卡状态
    @Override
    public void updateCardState(int cardId, boolean state) {
        Card card = dao.getOne(cardId);
        //设置状态
        card.setState(state);
        //保存
        dao.save(card);
    }

    //根据IC卡编号获取IC卡信息
    @Override
    public Card getCard(int id) {
        return dao.getOne(id);
    }

    //获取绑定的IC卡
    @Override
    public List<Card> listCardNotEntry() {
        return dao.listCardNotEntry();
    }


    //获取分页后IC卡信息列表
    @Override
    public Page<Card> listCard(int page) {
        page --;
        //分页
        Pageable pageable = PageRequest.of(page, 15);
        //获取数据
        Page<Card> pages = dao.findAll(pageable);
        return pages;
    }

    //根据查询条件获取分页后IC卡信息列表
    @Override
    public Page<Card> listCard(Condition condition, int page) {
        page --;
        /**
         * Specification<Place>:用于封装查询条件
         */
        Specification<Card> spec = new Specification<Card>() {

            //Predicate:封装了 单个的查询条件

            /**
             * Root<Card> root:根对象。封装了查询条件的对象
             * CriteriaQuery<?> query：定义了一个基本的查询。一般不使用
             * CriteriaBuilder cb:创建一个查询条件
             * @return Predicate:定义了查询条件
             */
            @Override
            public Predicate toPredicate(Root<Card> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //条件key
                String conditionKey = condition.getKey();
                //条件Value
                String conditionValue = condition.getValue();
                //设置条件
                Predicate pre = cb.isNotNull(root.get("id"));
                //根据id和位置编号
                if ("id".equals(conditionKey) || "place".equals(conditionKey)) {
                    try{
                        Integer value = Integer.valueOf(conditionValue);
                        pre = cb.equal(root.get(conditionKey), value);
                    }catch(Exception e){ }
                    //根据名称和车辆编号
                } else if ("car_no".equals(conditionKey)) {
                    pre = cb.like(root.get(conditionKey), "%" + conditionValue + "%");
                    //IC卡状态
                } else if("state".equals(conditionKey)){
                    String value = conditionValue.toString().toLowerCase().trim();
                    //搜索词包含占用或true
                    if(value.contains("占用") || value.contains("true") ){
                        pre = cb.equal(root.get(conditionKey),true);
                    }else if(value.contains("空闲") || value.contains("no")){
                        pre = cb.equal(root.get(conditionKey),false);
                    }
                }
                return pre;
            }
        };
        //排序
        Sort sort = new Sort(Sort.Direction.ASC,"id");
        //分页
        Pageable pageable = PageRequest.of(page, 15,sort);
        //获取数据
        Page<Card> pages = dao.findAll(spec, pageable);
        return pages;
    }


}

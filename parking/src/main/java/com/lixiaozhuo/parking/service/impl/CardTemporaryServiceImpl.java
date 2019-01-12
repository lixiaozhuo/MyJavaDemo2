package com.lixiaozhuo.parking.service.impl;

import com.lixiaozhuo.parking.dao.CardTemporaryDao;
import com.lixiaozhuo.parking.exception.CustomException;
import com.lixiaozhuo.parking.pojo.CardTemporary;
import com.lixiaozhuo.parking.pojo.Condition;
import com.lixiaozhuo.parking.pojo.Place;
import com.lixiaozhuo.parking.service.ICardTemporaryService;
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
 * 临时IC卡业务
 */
@Service
public class CardTemporaryServiceImpl implements ICardTemporaryService {
    @Autowired
    private CardTemporaryDao dao;

    @Autowired
    private IPlaceService placeService;


    //删除IC卡信息
    @Override
    public void deleteCardTemporary(int id) {
        CardTemporary cardTemporary = getCardTemporary(id);
        //状态为占用,不可删除
        if(cardTemporary.getState()){
            throw new CustomException("IC卡状态为占用,不可删除");
        }
        //更新车位状态
        cardTemporary.getPlace().setState(false);
        placeService.savePlace(cardTemporary.getPlace());
        //删除
        dao.delete(cardTemporary);
    }

    //保存IC卡信息
    @Override
    public void saveCardTemporary(CardTemporary cardTemporary) {
        //解绑车位
        if(cardTemporary.getId() != null){
            //IC卡旧信息
            CardTemporary oldCard = getCardTemporary(cardTemporary.getId());
            //更新车位状态
            cardTemporary.getPlace().setState(false);
            placeService.savePlace(cardTemporary.getPlace());
        }
        //更新当前车位状态
        Place place = placeService.getPlace(cardTemporary.getPlace().getId());
        place.setState(true);
        cardTemporary.setPlace(place);
        //保存
        dao.save(cardTemporary);
    }

    //更新IC卡状态
    @Override
    public void updateCardTemporaryState(int cardTemporaryId, boolean state) {
        CardTemporary cardTemporary = dao.getOne(cardTemporaryId);
        //设置状态
        cardTemporary.setState(state);
        //保存
        dao.save(cardTemporary);
    }

    //根据IC卡编号获取IC卡信息
    @Override
    public CardTemporary getCardTemporary(int id) {
        return dao.getOne(id);
    }

    //获取未绑定的IC卡
    @Override
    public List<CardTemporary> listCardTemporaryNotEntry() {
        return dao.listCardTemporaryNotEntry();
    }


    //获取分页后IC卡信息列表
    @Override
    public Page<CardTemporary> listCardTemporary(int page) {
        page --;
        //分页
        Pageable pageable = PageRequest.of(page,15);
        //获取数据
        Page<CardTemporary> pages = dao.findAll(pageable);
        return pages;
    }

    //根据查询条件获取分页后IC卡信息列表
    @Override
    public Page<CardTemporary> listCardTemporary(Condition condition, int page) {
        page --;
        /**
         * Specification<CardTemporary>:用于封装查询条件
         */
        Specification<CardTemporary> spec = new Specification<CardTemporary>() {

            //Predicate:封装了 单个的查询条件

            /**
             * Root<Card> root:根对象。封装了查询条件的对象
             * CriteriaQuery<?> query：定义了一个基本的查询。一般不使用
             * CriteriaBuilder cb:创建一个查询条件
             * @return Predicate:定义了查询条件
             */
            @Override
            public Predicate toPredicate(Root<CardTemporary> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //条件key
                String conditionKey = condition.getKey();
                //条件Value
                String conditionValue = condition.getValue();
                //设置条件
                Predicate pre = cb.isNotNull(root.get("id"));
                //根据id和位置编号
                if("id".equals(conditionKey) || "place".equals(conditionKey)) {
                    try{
                        Integer value = Integer.valueOf(conditionValue);
                        pre = cb.equal(root.get(conditionKey), value);
                    }catch(Exception e){ }
                }else if("state".equals(conditionKey)){
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
        Page<CardTemporary> pages = dao.findAll(spec,pageable);
        return pages;
    }
}

package com.lixiaozhuo.parking.service.impl;

import com.lixiaozhuo.parking.dao.ParkFixedDao;
import com.lixiaozhuo.parking.exception.CustomException;
import com.lixiaozhuo.parking.pojo.Card;
import com.lixiaozhuo.parking.pojo.Condition;
import com.lixiaozhuo.parking.pojo.ParkFixed;
import com.lixiaozhuo.parking.service.ICardService;
import com.lixiaozhuo.parking.service.IParkFixedService;
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
import java.util.Date;

/**
 * 固定停车业务
 */
@Service
public class ParkFixedServiceImpl implements IParkFixedService {
    @Autowired
    private ParkFixedDao dao;

    @Autowired
    private ICardService cardService;

    //删除固定车主出入记录信息
    @Override
    public void deleteParkFixed(int id) {
        ParkFixed parkFixed = dao.getOne(id);
        //离开时间不可为空
        if(parkFixed.getLeave_time() == null){
            throw new CustomException("缺失离开时间,删除错误");
        }
        //删除
        dao.delete(parkFixed);
    }

    //设置固定车主入车库
    @Override
    public void entryPark(int card_id) {
        ParkFixed parkFixed = new ParkFixed();
        parkFixed.setCard_id(card_id);
        //更新IC卡状态
        cardService.updateCardState(card_id,true);
        //完善信息
        Card card = cardService.getCard(card_id);
        parkFixed.setCar_no(card.getCar_no());
        parkFixed.setPlace_no(card.getPlace().getNo());
        //设置进入时间
        parkFixed.setEntry_time(new Date());
        //保存
        dao.save(parkFixed);
    }

    //设置固定车主出车库
    @Override
    public void leavePark(int id) {
        ParkFixed parkFixed = dao.getOne(id);
        //更新离开时间
        parkFixed.setLeave_time(new Date());
        //更新IC卡状态
        cardService.updateCardState(parkFixed.getCard_id(),false);
        //保存
        dao.save(parkFixed);
    }


    //获取分页后固定车主出入记录信息列表
    @Override
    public Page<ParkFixed> listParkFixed(int page) {
        page --;
        //分页
        Pageable pageable = PageRequest.of(page, 15);
        //获取数据
        Page<ParkFixed> pages = dao.findAll(pageable);
        return pages;
    }


    //根据查询条件获取分页后固定车主出入记录信息列表
    @Override
    public Page<ParkFixed> listParkFixed(Condition condition, int page) {
        page --;
        /**
         * Specification<ParkFixed>:用于封装查询条件
         */
        Specification<ParkFixed> spec = new Specification<ParkFixed>() {

            //Predicate:封装了 单个的查询条件

            /**
             * Root<ParkFixed> root:根对象。封装了查询条件的对象
             * CriteriaQuery<?> query：定义了一个基本的查询。一般不使用
             * CriteriaBuilder cb:创建一个查询条件
             * @return Predicate:定义了查询条件
             */
            @Override
            public Predicate toPredicate(Root<ParkFixed> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //条件Key
                String conditionKey = condition.getKey();
                //条件Value
                String conditionValue = condition.getValue();
                //设置条件
                Predicate pre = cb.isNotNull(root.get("id"));
                //根据IC卡id
                if("card_id".equals(conditionKey)) {
                    try{
                        Integer value = Integer.valueOf(conditionValue);
                        pre = cb.equal(root.get(conditionKey), value);
                    }catch(Exception e){ }
                    //根据出入场状态
                }else if("place_no".equals(conditionKey) || "car_no".equals(conditionKey)){
                    pre = cb.like(root.get(conditionKey), "%" + conditionValue + "%");
                }
                return pre;
            }
        };
        //排序
        Sort sort = new Sort(Sort.Direction.ASC,"id");
        //分页
        Pageable pageable = PageRequest.of(page, 15,sort);
        //获取数据
        Page<ParkFixed> pages = dao.findAll(spec,pageable);
        return pages;
    }


}

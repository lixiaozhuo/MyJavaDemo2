package com.lixiaozhuo.parking.service.impl;

import com.lixiaozhuo.parking.dao.ParkTemporaryDao;
import com.lixiaozhuo.parking.exception.CustomException;
import com.lixiaozhuo.parking.pojo.CardTemporary;
import com.lixiaozhuo.parking.pojo.Condition;
import com.lixiaozhuo.parking.pojo.ParkTemporary;
import com.lixiaozhuo.parking.service.ICardTemporaryService;
import com.lixiaozhuo.parking.service.IParkTemporaryService;
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
 * 临时停车业务接口
 */
@Service
public class ParkTemporaryServiceImpl implements IParkTemporaryService {
    @Autowired
    private ParkTemporaryDao dao;

    @Autowired
    private ICardTemporaryService cardTemporaryService;

    //删除临时车主出入记录信息
    @Override
    public void deleteParkTemporary(int id) {
        ParkTemporary parkTemporary = dao.getOne(id);
        //离开时间不可为空
        if (parkTemporary.getLeave_time() == null) {
            throw new CustomException("缺少离开时间,删除错误");
        }
        //删除
        dao.delete(parkTemporary);
    }

    //保存临时车主出入记录信息
    @Override
    public void saveParkTemporary(ParkTemporary parkTemporary) {
        //进出场时间间隔
        Long time = parkTemporary.getLeave_time().getTime() - parkTemporary.getEntry_time().getTime();
        time = time / (60 * 1000) == 0 ? time / (60 * 1000) : time / (60 * 1000) + 1;
        //计算停车时间
        int stopHour =time.intValue();
        //计算停车花费金额
        parkTemporary.setCost(parkTemporary.getPrice() * (stopHour + 1));
        //更新临时IC卡状态
        cardTemporaryService.updateCardTemporaryState(parkTemporary.getCard_id(),false);
        //删除临时IC卡
        cardTemporaryService.deleteCardTemporary(parkTemporary.getCard_id());
        //保存
        dao.save(parkTemporary);
    }

    //临时车主进入车库
    @Override
    public void entryParkTemporary(int cardId, String carNo) {
        ParkTemporary parkTemporary = new ParkTemporary();
        parkTemporary.setCard_id(cardId);
        parkTemporary.setCar_no(carNo);
        //更新IC卡状态
        cardTemporaryService.updateCardTemporaryState(cardId, true);
        //完善信息
        CardTemporary card = cardTemporaryService.getCardTemporary(cardId);
        parkTemporary.setPlace_no(card.getPlace().getNo());
        //设置进入时间
        parkTemporary.setEntry_time(new Date());
        //保存
        dao.save(parkTemporary);
    }

    //根据id获取临时车主出入记录
    @Override
    public ParkTemporary getParkTemporary(int id) {
        return dao.getOne(id);
    }


    //获取分页后临时车主出入记录信息列表
    @Override
    public Page<ParkTemporary> listParkTemporary(int page) {
        page--;
        //分页
        Pageable pageable = PageRequest.of(page, 15);
        //获取数据
        Page<ParkTemporary> pages = dao.findAll(pageable);
        return pages;
    }

    //根据查询条件获取分页后临时车主出入记录信息列表
    @Override
    public Page<ParkTemporary> listParkTemporary(Condition condition, int page) {
        page--;
        /**
         * Specification<ParkTemporary>:用于封装查询条件
         */
        Specification<ParkTemporary> spec = new Specification<ParkTemporary>() {

            //Predicate:封装了 单个的查询条件

            /**
             * Root<Users> root:根对象。封装了查询条件的对象
             * CriteriaQuery<?> query：定义了一个基本的查询。一般不使用
             * CriteriaBuilder cb:创建一个查询条件
             * @return Predicate:定义了查询条件
             */
            @Override
            public Predicate toPredicate(Root<ParkTemporary> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //条件Key
                String conditionKey = condition.getKey();
                //条件Value
                String conditionValue = condition.getValue();
                //设置条件
                Predicate pre = cb.isNotNull(root.get("id"));
                //IC卡ID
                if ("card_id".equals(conditionKey)) {
                    try{
                        Integer value = Integer.valueOf(conditionValue);
                        pre = cb.equal(root.get(conditionKey), value);
                    }catch(Exception e){ }
                    //出入场状态
                } else if("place_no".equals(conditionKey) || "car_no".equals(conditionKey)){
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
        Page<ParkTemporary> pages = dao.findAll(spec, pageable);
        return pages;
    }


}

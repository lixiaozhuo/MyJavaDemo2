package com.lixiaozhuo.parking.service.impl;

import com.lixiaozhuo.parking.dao.UserDao;
import com.lixiaozhuo.parking.exception.CustomException;
import com.lixiaozhuo.parking.pojo.Condition;
import com.lixiaozhuo.parking.pojo.User;
import com.lixiaozhuo.parking.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.OneToOne;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * 用户业务
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserDao dao;


    //删除用户信息
    @Override
    public void deleteUser(long id) {
        User user =dao.getOne(id);
        //不可删除管理员账号
        if(user.getId()==1){
            throw new CustomException("不可删除管理员账号");
        }
        //删除
        dao.delete(user);
    }

    //保存用户信息
    @Override
    public void saveUser(User user) {
        if(user.getId() != null){
            //不可修改管理员账号
            if(user.getId()==1){
                throw new CustomException("不可修改管理员账号");
            }
        }else{
            //手机号码存在
            if(checkPhone(user.getPhone())){
                throw new CustomException("手机号码已存在");
            }
        }
        dao.save(user);
    }

    //根据id获取用户信息
    @Override
    public User getUser(long id) {
        User user = dao.getOne(id);
        return user;
    }

    //检查手机号码是否存在
    @Override
    public boolean checkPhone(String phone){
        User user = dao.findByPhone(phone);
        //手机号不存在
        if(user == null){
            return false;
        }
        return true;
    }


    //根据手机号
    @Override
    public User getUserByPhone(String phone) {
        User user = dao.findByPhone(phone);
        return user;
    }

    //获取分页后用户表信息列表
    @Override
    public Page<User> listUser(int page){
        page --;
        //分页
        Pageable pageable = PageRequest.of(page, 15);
        //获取数据
        return dao.findAll(pageable);
    }

    //根据查询条件获取分页后用户表信息列表
    @Override
    public Page<User> listUser(Condition condition, int page) {
        page --;
        /**
         * Specification<Users>:用于封装查询条件
         */
        Specification<User> spec = new Specification<User>() {

            //Predicate:封装了 单个的查询条件

            /**
             * Root<Users> root:根对象。封装了查询条件的对象
             * CriteriaQuery<?> query：定义了一个基本的查询。一般不使用
             * CriteriaBuilder cb:创建一个查询条件
             * @return Predicate:定义了查询条件
             */
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //条件Key
                String conditionKey = condition.getKey();
                //条件Value
                String conditionValue = condition.getValue();
                //设置条件
                Predicate pre = null;
                //手机号码或者用户名
                if("phone".equals(conditionKey) || "username".equals(conditionKey)) {
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
        Page<User> pages = dao.findAll(spec, pageable);
        return pages;
    }

    @Override
    public void updatePassword(String phone, String newPassword) {
        User user = dao.findByPhone(phone);
        //更新密码
        user.setPassword(newPassword);
        //保存数据
        dao.save(user);
    }
}

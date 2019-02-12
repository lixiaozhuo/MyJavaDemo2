package com.lixiaozhuo.house.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 预约看房信息实体
 */
@Entity
@Table(name = "house_subscribe")
public class HouseSubscribe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //房源id
    @Column(name = "house_id")
    private Long houseId;
    //用户id
    @Column(name = "user_id")
    private Long userId;
    //房源发布者id
    @Column(name = "admin_id")
    private Long adminId;
    //预约状态 0-未预约 1-加入待看清单 2-已预约看房时间 3-看房完成
    private int status;
    //用户描述(注意:desc在数据库中为关键字,需加转义字符)
    @Column(name = "`desc`")
    private String desc;
    //联系电话
    private String telephone;
    //数据创建时间
    @Column(name = "create_time")
    private Date createTime;
    //记录更新时间
    @Column(name = "last_update_time")
    private Date lastUpdateTime;
    //预约时间
    @Column(name = "order_time")
    private Date orderTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getHouseId() {
        return houseId;
    }

    public void setHouseId(Long houseId) {
        this.houseId = houseId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    /**
     * 房屋预约状态码
     */
    public enum HouseSubscribeStatus {
        // 未预约
        NO_SUBSCRIBE(0),
        // 已加入待看清单
        IN_ORDER_LIST(1),
        // 已经预约看房时间
        IN_ORDER_TIME(2),
        // 已完成预约
        FINISH(3);

        private int value;

        HouseSubscribeStatus(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        /**
         * 根据值获取对应房屋预约状态
         */
        public static HouseSubscribeStatus of(int value) {
            //遍历状态
            for (HouseSubscribeStatus status : HouseSubscribeStatus.values()) {
                if (status.getValue() == value) {
                    return status;
                }
            }
            //未预约状态
            return HouseSubscribeStatus.NO_SUBSCRIBE;
        }
    }
}

package com.lixiaozhuo.house.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * 预约看房信息DTO
 */
public class HouseSubscribeDTO  implements Serializable {
    //id
    private Long id;
    //房源id
    @JsonProperty(value = "house_id")
    private Long houseId;
    //用户id
    @JsonProperty(value = "user_id")
    private Long userId;
    //房源发布者id
    @JsonProperty(value = "admin_id")
    private Long adminId;
    // 预约状态 1-加入待看清单 2-已预约看房时间 3-看房完成
    private int status;
    //用户描述
    private String desc;
    //联系电话
    private String telephone;
    //数据创建时间
    @JsonProperty(value = "create_time")
    private Date createTime;
    //记录更新时间
    @JsonProperty(value = "last_update_time")
    private Date lastUpdateTime;
    //预约时间
    @JsonProperty(value = "order_time")
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
}

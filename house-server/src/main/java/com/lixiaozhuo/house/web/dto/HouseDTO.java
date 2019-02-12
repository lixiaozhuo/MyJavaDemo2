package com.lixiaozhuo.house.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 房屋信息DTO
 */
public class HouseDTO implements Serializable {
    private Long id;
    //所属管理员id
    @JsonProperty(value = "admin_id")
    private Long adminId;
    //标题
    private String title;
    //封面
    private String cover;
    //价格
    private int price;
    //面积
    private int area;
    //卧室数量
    private int room;
    //客厅数量
    private int parlour;
    //卫生间数量
    private int bathroom;
    //楼层
    private int floor;
    //总楼层
    @JsonProperty(value = "total_floor")
    private int totalFloor;
    //建立年限
    @JsonProperty(value = "build_year")
    private int buildYear;
    //房屋朝向
    private int direction;
    //城市英文简写
    @JsonProperty(value = "city_en_name")
    private String cityEnName;
    //地区英文简写
    @JsonProperty(value = "region_en_name")
    private String regionEnName;
    //街道
    private String street;
    //所在小区
    private String district;
    //据地铁距离
    @JsonProperty(value = "distance_to_subway")
    private int distanceToSubway;
    //被看次数
    @JsonProperty(value = "watch_times")
    private int watchTimes;
    //房屋状态 0-未审核 1-审核通过 2-已出租 3-逻辑删除
    private int status;
    //创建时间
    @JsonProperty(value = "create_time")
    private Date createTime;
    //最近数据更新时间
    @JsonProperty(value = "last_update_time")
    private Date lastUpdateTime;

    //房屋详细信息
    @JsonProperty(value = "house_detail")
    private HouseDetailDTO houseDetail;
    //房屋图片信息集合
    private List<HousePictureDTO> pictures;
    //标签集合
    private List<String> tags;
    //房屋预约状态 1-加入待看清单 2-已预约看房时间 3-看房完成
    @JsonProperty(value = "subscribe_status")
    private int subscribeStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public int getParlour() {
        return parlour;
    }

    public void setParlour(int parlour) {
        this.parlour = parlour;
    }

    public int getBathroom() {
        return bathroom;
    }

    public void setBathroom(int bathroom) {
        this.bathroom = bathroom;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getTotalFloor() {
        return totalFloor;
    }

    public void setTotalFloor(int totalFloor) {
        this.totalFloor = totalFloor;
    }

    public int getBuildYear() {
        return buildYear;
    }

    public void setBuildYear(int buildYear) {
        this.buildYear = buildYear;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public String getCityEnName() {
        return cityEnName;
    }

    public void setCityEnName(String cityEnName) {
        this.cityEnName = cityEnName;
    }

    public String getRegionEnName() {
        return regionEnName;
    }

    public void setRegionEnName(String regionEnName) {
        this.regionEnName = regionEnName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public int getDistanceToSubway() {
        return distanceToSubway;
    }

    public void setDistanceToSubway(int distanceToSubway) {
        this.distanceToSubway = distanceToSubway;
    }

    public int getWatchTimes() {
        return watchTimes;
    }

    public void setWatchTimes(int watchTimes) {
        this.watchTimes = watchTimes;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    public HouseDetailDTO getHouseDetail() {
        return houseDetail;
    }

    public void setHouseDetail(HouseDetailDTO houseDetail) {
        this.houseDetail = houseDetail;
    }

    public List<HousePictureDTO> getPictures() {
        return pictures;
    }

    public void setPictures(List<HousePictureDTO> pictures) {
        this.pictures = pictures;
    }

    public List<String> getTags() {
        if (this.tags == null) {
            tags = new ArrayList<>();
        }
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public int getSubscribeStatus() {
        return subscribeStatus;
    }

    public void setSubscribeStatus(int subscribeStatus) {
        this.subscribeStatus = subscribeStatus;
    }




}

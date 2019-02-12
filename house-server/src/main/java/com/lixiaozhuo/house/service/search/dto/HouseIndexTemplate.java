package com.lixiaozhuo.house.service.search.dto;

import java.util.Date;
import java.util.List;

/**
 *
 * 索引结构模板
 */
public class HouseIndexTemplate {
    //房屋id
    private Long houseId;
    //标题
    private String title;
    //价格
    private int price;
    //面积
    private int area;
    //房屋朝向
    private int direction;
    //城市英文简写
    private String cityEnName;
    //地区英文简写
    private String regionEnName;
    //所在街道
    private String street;
    //所在小区
    private String district;
    //据地铁距离
    private int distanceToSubway;
    //详细描述
    private String description;
    //户型介绍
    private String layoutDesc;
    //交通出行
    private String traffic;
    //周围配套
    private String roundService;
    //租赁方式
    private int rentWay;
    //附近地铁线名称
    private String subwayLineName;
    //附近地铁站名称
    private String subwayStationName;
    //房屋标签
    private List<String> tags;
    //房屋
    private List<HouseSuggest> suggest;
    //地图方位
    private MapLocation location;
    //创建时间
    private Date createTime;
    //最近数据更新时间
    private Date lastUpdateTime;

    public Long getHouseId() {
        return houseId;
    }

    public void setHouseId(Long houseId) {
        this.houseId = houseId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLayoutDesc() {
        return layoutDesc;
    }

    public void setLayoutDesc(String layoutDesc) {
        this.layoutDesc = layoutDesc;
    }

    public String getTraffic() {
        return traffic;
    }

    public void setTraffic(String traffic) {
        this.traffic = traffic;
    }

    public String getRoundService() {
        return roundService;
    }

    public void setRoundService(String roundService) {
        this.roundService = roundService;
    }

    public int getRentWay() {
        return rentWay;
    }

    public void setRentWay(int rentWay) {
        this.rentWay = rentWay;
    }

    public String getSubwayLineName() {
        return subwayLineName;
    }

    public void setSubwayLineName(String subwayLineName) {
        this.subwayLineName = subwayLineName;
    }

    public String getSubwayStationName() {
        return subwayStationName;
    }

    public void setSubwayStationName(String subwayStationName) {
        this.subwayStationName = subwayStationName;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<HouseSuggest> getSuggest() {
        return suggest;
    }

    public void setSuggest(List<HouseSuggest> suggest) {
        this.suggest = suggest;
    }

    public MapLocation getLocation() {
        return location;
    }

    public void setLocation(MapLocation location) {
        this.location = location;
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
}

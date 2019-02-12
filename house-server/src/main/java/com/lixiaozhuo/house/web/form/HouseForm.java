package com.lixiaozhuo.house.web.form;

import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 房屋表单数据
 */
public class HouseForm {
    //id
    private Long id;
    //标题
    @NotNull(message = "大标题不允许为空!")
    @Size(min = 1, max = 30, message = "标题长度必须在1~30之间")
    private String title;
    //封面
    private String cover;
    //价格
    @NotNull(message = "必须填写租赁价格")
    @Min(value = 1)
    private Integer price;
    //面积
    @NotNull(message = "必须填写面积")
    @Min(value = 1)
    private Integer area;
    //卧室数量
    @NotNull(message = "必须填写卧室数量")
    @Min(value = 1, message = "非法的卧室数量")
    private Integer room;
    //客厅数量
    private int parlour;
    //楼层
    @NotNull(message = "必须填写所属楼层")
    private Integer floor;
    //总楼层
    @NotNull(message = "必须填写总楼层")
    private Integer totalFloor;
    //建立年限
    @NotNull(message = "必须填写建筑起始时间")
    @Min(value = 1900, message = "非法的建筑起始时间")
    private Integer buildYear;
    //房屋朝向
    @NotNull(message = "必须填写房屋朝向")
    private Integer direction;
    //城市英文简写
    @NotNull(message = "必须选中一个城市")
    @Size(min = 1, message = "非法的城市")
    private String cityEnName;
    //地区英文简写
    @NotNull(message = "必须选中一个地区")
    @Size(min = 1, message = "非法的地区")
    private String regionEnName;
    //街道
    @NotNull(message = "必须填写街道")
    @Size(min = 1, message = "非法的街道")
    private String street;
    //所在小区
    @NotNull(message = "必须填写小区")
    private String district;
    //据地铁距离
    private int distanceToSubway = -1;
    //详细描述
    @Size(max = 255)
    private String description;
    //户型介绍
    private String layoutDesc;
    //交通出行
    private String traffic;
    //周围配套
    private String roundService;
    //租赁方式
    @NotNull(message = "必须选中一个租赁方式")
    @Min(value = 0)
    @Max(value = 1)
    private Integer rentWay;
    //详细地址
    @NotNull(message = "详细地址不允许为空!")
    @Size(min = 1, max = 30, message = "详细地址长度必须在1~30之间")
    private String address;
    //附近地铁线id
    private Long subwayLineId;
    //附近地铁站id
    private Long subwayStationId;
    //标签集合
    private List<String> tags;
    //图片集合
    private List<PhotoForm> photos;






    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getRoom() {
        return room;
    }

    public void setRoom(Integer room) {
        this.room = room;
    }

    public int getParlour() {
        return parlour;
    }

    public void setParlour(int parlour) {
        this.parlour = parlour;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public Integer getTotalFloor() {
        return totalFloor;
    }

    public void setTotalFloor(Integer totalFloor) {
        this.totalFloor = totalFloor;
    }

    public Integer getDirection() {
        return direction;
    }

    public void setDirection(Integer direction) {
        this.direction = direction;
    }

    public Integer getBuildYear() {
        return buildYear;
    }

    public void setBuildYear(Integer buildYear) {
        this.buildYear = buildYear;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getRentWay() {
        return rentWay;
    }

    public void setRentWay(Integer rentWay) {
        this.rentWay = rentWay;
    }

    public Long getSubwayLineId() {
        return subwayLineId;
    }

    public void setSubwayLineId(Long subwayLineId) {
        this.subwayLineId = subwayLineId;
    }

    public Long getSubwayStationId() {
        return subwayStationId;
    }

    public void setSubwayStationId(Long subwayStationId) {
        this.subwayStationId = subwayStationId;
    }

    public int getDistanceToSubway() {
        return distanceToSubway;
    }

    public void setDistanceToSubway(int distanceToSubway) {
        this.distanceToSubway = distanceToSubway;
    }

    public String getLayoutDesc() {
        return layoutDesc;
    }

    public void setLayoutDesc(String layoutDesc) {
        this.layoutDesc = layoutDesc;
    }

    public String getRoundService() {
        return roundService;
    }

    public void setRoundService(String roundService) {
        this.roundService = roundService;
    }

    public String getTraffic() {
        return traffic;
    }

    public void setTraffic(String traffic) {
        this.traffic = traffic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<PhotoForm> getPhotos() {
        return photos;
    }

    public void setPhotos(List<PhotoForm> photos) {
        this.photos = photos;
    }

}

package com.lixiaozhuo.house.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 房屋描述信息DTO
 */
public class HouseDetailDTO implements Serializable {
    private Long id;
    //对应房屋id
    @JsonProperty(value = "house_id")
    private Long houseId;
    //详细描述
    private String description;
    //户型介绍
    @JsonProperty(value = "layout_desc")
    private String layoutDesc;
    //交通出行
    private String traffic;
    //周围配套
    @JsonProperty(value = "round_service")
    private String roundService;
    //租赁方式
    @JsonProperty(value = "rent_way")
    private int rentWay;
    //详细地址
    private String address;
    //附近地铁线id
    @JsonProperty(value = "subway_line_id")
    private Long subwayLineId;
    //附近地铁线名称
    @JsonProperty(value = "subway_line_name")
    private String subwayLineName;
    //附近地铁站id
    @JsonProperty(value = "subway_station_id")
    private Long subwayStationId;
    //附近地铁站名称
    @JsonProperty(value = "subway_station_name")
    private String subwayStationName;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getSubwayLineId() {
        return subwayLineId;
    }

    public void setSubwayLineId(Long subwayLineId) {
        this.subwayLineId = subwayLineId;
    }

    public String getSubwayLineName() {
        return subwayLineName;
    }

    public void setSubwayLineName(String subwayLineName) {
        this.subwayLineName = subwayLineName;
    }

    public Long getSubwayStationId() {
        return subwayStationId;
    }

    public void setSubwayStationId(Long subwayStationId) {
        this.subwayStationId = subwayStationId;
    }

    public String getSubwayStationName() {
        return subwayStationName;
    }

    public void setSubwayStationName(String subwayStationName) {
        this.subwayStationName = subwayStationName;
    }
}

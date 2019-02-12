package com.lixiaozhuo.house.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 房屋详细信息实体
 */
@Entity
@Table(name = "house_detail")
public class HouseDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //对应房屋id
    @Column(name = "house_id")
    private Long houseId;
    //详细描述
    private String description;
    //户型介绍
    @Column(name = "layout_desc")
    private String layoutDesc;
    //交通出行
    private String traffic;
    //周围配套
    @Column(name = "round_service")
    private String roundService;
    //租赁方式
    @Column(name = "rent_way")
    private int rentWay;
    //详细地址
    private String address;
    //附近地铁线id
    @Column(name = "subway_line_id")
    private Long subwayLineId;
    //附近地铁线名称
    @Column(name = "subway_line_name")
    private String subwayLineName;
    //附近地铁站id
    @Column(name = "subway_station_id")
    private Long subwayStationId;
    //附近地铁站名称
    @Column(name = "subway_station_name")
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

package com.lixiaozhuo.house.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 *地铁站点信息DTO
 */
public class SubwayStationDTO implements Serializable {
    //id
    private Long id;
    //地铁线路编号
    @JsonProperty(value = "subway_id")
    private Long subwayId;
    //地铁站点名称
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSubwayId() {
        return subwayId;
    }

    public void setSubwayId(Long subwayId) {
        this.subwayId = subwayId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

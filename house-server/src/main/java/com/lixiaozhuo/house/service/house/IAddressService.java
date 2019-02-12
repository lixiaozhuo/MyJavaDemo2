package com.lixiaozhuo.house.service.house;

import com.lixiaozhuo.house.entity.SupportAddress;
import com.lixiaozhuo.house.service.ServiceMultiResult;
import com.lixiaozhuo.house.service.ServiceResult;
import com.lixiaozhuo.house.web.dto.SubwayDTO;
import com.lixiaozhuo.house.web.dto.SubwayStationDTO;
import com.lixiaozhuo.house.web.dto.SupportAddressDTO;

import java.util.Map;

/**
 * 地址服务接口
 */
public interface IAddressService {
    //////////////////////////////////指定信息//////////////////////////////////////////////////////////////
    /**
     *  获取指定地区详细信息
     * @param cityEnName  城市英文简写
     * @param regionEnName 地区英文简写
     */
    Map<SupportAddress.Level, SupportAddressDTO> findCityAndRegion(String cityEnName, String regionEnName);
    /**
     * 获取指定城市详细信息
     * @param cityEnName 城市英文简写
     */
    ServiceResult<SupportAddressDTO> findCity(String cityEnName);
    /**
     * 获取指定地铁线信息
     * @param subwayId 地铁线id
     */
    ServiceResult<SubwayDTO> findSubway(Long subwayId);
    /**
     * 获取指定地铁站信息
     * @param stationId 地铁站id
     */
    ServiceResult<SubwayStationDTO> findSubwayStation(Long stationId);
    ////////////////////////////////////信息集合////////////////////////////////////////////////////////
    /**
     * 获取所有支持的城市列表
     */
    ServiceMultiResult<SupportAddressDTO> findAllCities();
    /**
     * 获取指定城市所有支持地区
     * @param cityName 城市英文简写
     */
    ServiceMultiResult<SupportAddressDTO> findAllRegionsByCityName(String cityName);
    /**
     * 获取指定城市所有的地铁线路
     * @param cityEnName 城市英文简写
     */
    ServiceMultiResult<SubwayDTO> findAllSubwayByCity(String cityEnName);
    /**
     * 获取地铁线路所有的站点
     * @param subwayId 地铁线路id
     */
    ServiceMultiResult<SubwayStationDTO> findAllStationBySubway(Long subwayId);
    //////////////////////////////地图服务/////////////////////////////////////////////////////////////////////
    /**
     * 根据城市以及具体地位获取百度地图的经纬度
     */
    //ServiceResult<BaiduMapLocation> getBaiduMapLocation(String city, String address);

    /**
     * 上传LBS数据
     */
    //ServiceResult lbsUpload(BaiduMapLocation location, String title, String address,
                            //long houseId, int price, int area);

    /**
     * 移除LBS数据
     */
    //ServiceResult removeLbs(Long houseId);
}


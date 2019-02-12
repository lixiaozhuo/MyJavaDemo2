package com.lixiaozhuo.house.web.controller.house;

import com.lixiaozhuo.house.base.constant.HouseValueBlock;
import com.lixiaozhuo.house.base.response.APIListResponse;
import com.lixiaozhuo.house.base.response.APIResponse;
import com.lixiaozhuo.house.entity.SupportAddress;
import com.lixiaozhuo.house.service.ServiceMultiResult;
import com.lixiaozhuo.house.service.ServiceResult;
import com.lixiaozhuo.house.service.house.IAddressService;
import com.lixiaozhuo.house.service.house.IHouseService;
import com.lixiaozhuo.house.service.search.ISearchService;
import com.lixiaozhuo.house.service.user.IUserService;
import com.lixiaozhuo.house.web.dto.*;
import com.lixiaozhuo.house.web.form.MapSearch;
import com.lixiaozhuo.house.web.form.RentSearch;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 房屋控制器
 */
@RestController
public class HouseController {
    //java对象自动映射工具
    @Autowired
    private ModelMapper modelMapper;
    //地址服务
    @Autowired
    private IAddressService addressService;
    //房屋服务
    @Autowired
    private IHouseService houseService;
    //用户服务
    @Autowired
    private IUserService userService;
    //查找服务
    @Autowired
    private ISearchService searchService;
    //////////////////////////////////////////支持区域信息///////////////////////////////////////////////////
    /**
     * 获取支持价格块
     */
    @GetMapping("api/search/support/block/price")
    public APIResponse getSupportPriceBlock() {
        List<HouseValueBlockDTO> result = new ArrayList<>();
        //将HouseValueBlock转换为HouseValueBlockDTO
        for(HouseValueBlock houseValueBlock : HouseValueBlock.PRICE_BLOCK.values()){
            HouseValueBlockDTO houseValueBlockDTO = modelMapper.map(houseValueBlock, HouseValueBlockDTO.class);
            result.add(houseValueBlockDTO);
        }
        //响应数据
        return APIResponse.ofSuccess(result);
    }

    /**
     * 获取支持面积块块
     */
    @GetMapping("api/search/support/block/area")
    public APIResponse getSupportAreaBlock() {
        List<HouseValueBlockDTO> result = new ArrayList<>();
        //将HouseValueBlock转换为HouseValueBlockDTO
        for(HouseValueBlock houseValueBlock : HouseValueBlock.AREA_BLOCK.values()){
            HouseValueBlockDTO houseValueBlockDTO = modelMapper.map(houseValueBlock, HouseValueBlockDTO.class);
            result.add(houseValueBlockDTO);
        }
        //响应数据
        return APIResponse.ofSuccess(result);
    }


    /**
     * 获取支持的指定城市
     */
    @GetMapping("api/address/support/city")
    public APIResponse getSupportCity(@RequestParam(name = "city_name") String cityEnName) {
        //获取所有支持的城市列表
        ServiceResult<SupportAddressDTO> city = addressService.findCity(cityEnName);
        //响应数据
        if (city.isSuccess()) {
            return APIResponse.ofSuccess(city.getResult());
        }
        return APIResponse.ofStatus(APIResponse.Status.NOT_FOUND);
    }

    /**
     * 获取支持城市列表
     */
    @GetMapping("api/address/support/cities")
    public APIResponse getSupportCities() {
        //获取所有支持的城市列表
        ServiceMultiResult<SupportAddressDTO> result = addressService.findAllCities();
        //响应数据
        if (result.getResultSize() == 0) {
            return APIResponse.ofStatus(APIResponse.Status.NOT_FOUND);
        }
        return APIResponse.ofSuccess(result.getResult());
    }

    /**
     * 获取对应城市支持区域列表
     * @param cityEnName 城市英文简写
     */
    @GetMapping("api/address/support/regions")
    public APIResponse getSupportRegions(@RequestParam(name = "city_name") String cityEnName) {
        //获取指定城市所有支持地区
        ServiceMultiResult<SupportAddressDTO> result = addressService.findAllRegionsByCityName(cityEnName);
        //响应数据
        if (result.getResultSize() == 0 ) {
            return APIResponse.ofStatus(APIResponse.Status.NOT_FOUND);
        }
        return APIResponse.ofSuccess(result.getResult());
    }

    /**
     * 获取具体城市所支持的地铁线路
     * @param cityEnName 城市英文简写
     */
    @GetMapping("api/address/support/subway/line")
    public APIResponse getSupportSubwayLine(@RequestParam(name = "city_name") String cityEnName) {
        //获取指定城市所有的地铁线路
        ServiceMultiResult<SubwayDTO> result = addressService.findAllSubwayByCity(cityEnName);
        //响应数据
        if (result.getResultSize() == 0) {
            return APIResponse.ofStatus(APIResponse.Status.NOT_FOUND);
        }
        return APIResponse.ofSuccess(result.getResult());
    }

    /**
     * 获取对应地铁线路所支持的地铁站点
     * @param subwayId 地铁线路id
     */
    @GetMapping("api/address/support/subway/station")
    public APIResponse getSupportSubwayStation(@RequestParam(name = "subway_id") Long subwayId) {
        //获取地铁线路所有的站点
        ServiceMultiResult<SubwayStationDTO> result = addressService.findAllStationBySubway(subwayId);
        //响应数据
        if (result.getResultSize() == 0) {
            return APIResponse.ofStatus(APIResponse.Status.NOT_FOUND);
        }
        return APIResponse.ofSuccess(result.getResult());
    }

    //////////////////////////////////房屋出租/////////////////////////////////////////////////////////
    /**
     * 自动补全接口
     */
    @GetMapping("api/rent/house/autocomplete")
    public APIResponse autocomplete(@RequestParam(value = "prefix") String prefix) {
        //参数检测
        if (prefix.isEmpty()) {
            return APIResponse.ofStatus(APIResponse.Status.BAD_REQUEST);
        }
        //获取关键词补全建议
        ServiceResult<List<String>> result = this.searchService.suggest(prefix);
        //响应数据
        if(result.isSuccess()){{
            return APIResponse.ofSuccess(result.getResult());
        }}
        return APIResponse.ofMessage(APIResponse.Status.BAD_REQUEST.getCode(), result.getMessage());
    }


    /**
     * 房屋页
     * @param rentSearch 房屋搜索请求
     */
    @GetMapping("api/rent/house")
    public APIListResponse rentHousePage(@ModelAttribute RentSearch rentSearch) {
        System.out.println(rentSearch);
        //获取城市详细信息
        ServiceResult<SupportAddressDTO> city = addressService.findCity(rentSearch.getCityEnName());
        //城市不存在
        if (!city.isSuccess()) {
            return APIListResponse.ofStatus(APIResponse.Status.BAD_REQUEST);
        }
        //获取该城市所有支持地区
        ServiceMultiResult<SupportAddressDTO> addressResult = addressService.findAllRegionsByCityName(rentSearch.getCityEnName());
        //城市不存在支持地区
        if (addressResult.getResultSize() == 0) {
            return APIListResponse.ofStatus(APIResponse.Status.BAD_REQUEST);
        }
        //查询房源信息集合
        ServiceMultiResult<HouseDTO> serviceMultiResult = houseService.query(rentSearch);
        //跳转页面
        return APIListResponse.ofSuccess(serviceMultiResult.getResult(),serviceMultiResult.getTotal());
    }

    /**
     * 地图找房
     * @param mapSearch 地图搜索请求
     */
    @GetMapping("api/rent/house/map/houses")
    public APIResponse rentMapHouses(@ModelAttribute MapSearch mapSearch) {
        //参数检测
        if (mapSearch.getCityEnName() == null) {
            return APIResponse.ofMessage(APIResponse.Status.BAD_REQUEST.getCode(), "必须选择城市");
        }
        ServiceMultiResult<HouseDTO> serviceMultiResult;
        if (mapSearch.getLevel() < 13) {
            //大地图全地图搜索
            serviceMultiResult = houseService.wholeMapQuery(mapSearch);
        } else {
            // 小地图查询精确范围搜索
            serviceMultiResult = houseService.boundMapQuery(mapSearch);
        }
        //相应数据
        APIResponse response = APIResponse.ofSuccess(serviceMultiResult.getResult());
        //设置是否存在更多数据
        response.setMore(serviceMultiResult.getTotal() > (mapSearch.getStart() + mapSearch.getSize()));
        return response;
    }

}

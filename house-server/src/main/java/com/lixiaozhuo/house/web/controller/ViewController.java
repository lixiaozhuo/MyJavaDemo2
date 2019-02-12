package com.lixiaozhuo.house.web.controller;

import com.lixiaozhuo.house.base.constant.HouseValueBlock;
import com.lixiaozhuo.house.entity.SupportAddress;
import com.lixiaozhuo.house.service.ServiceMultiResult;
import com.lixiaozhuo.house.service.ServiceResult;
import com.lixiaozhuo.house.service.house.IAddressService;
import com.lixiaozhuo.house.service.house.IHouseService;
import com.lixiaozhuo.house.service.search.ISearchService;
import com.lixiaozhuo.house.service.user.IUserService;
import com.lixiaozhuo.house.web.dto.*;
import com.lixiaozhuo.house.web.form.RentSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 视图Controller
 */
@Controller
public class ViewController {
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
    /////////////////////////////////////House///////////////////////////////////////////////////////////////
    /**
     * 房屋页
     * @param rentSearch 房屋搜索请求
     *//*
    @GetMapping("rent/house")
    public String rentHousePage(@ModelAttribute RentSearch rentSearch,
                                Model model, HttpSession session,
                                RedirectAttributes redirectAttributes) {
        //获取城市名称
        if (rentSearch.getCityEnName() == null) {
            String cityEnNameInSession = (String) session.getAttribute("cityEnName");
            //城市名称不存在
            if (cityEnNameInSession == null) {
                redirectAttributes.addAttribute("msg", "must_chose_city");
                return "redirect:/index";
            } else {
                rentSearch.setCityEnName(cityEnNameInSession);
            }
        } else {
            session.setAttribute("cityEnName", rentSearch.getCityEnName());
        }
        //获取城市详细信息
        ServiceResult<SupportAddressDTO> city = addressService.findCity(rentSearch.getCityEnName());
        //城市不存在
        if (!city.isSuccess()) {
            redirectAttributes.addAttribute("msg", "must_chose_city");
            return "redirect:/index";
        }
        //获取该城市所有支持地区
        ServiceMultiResult<SupportAddressDTO> addressResult = addressService.findAllRegionsByCityName(rentSearch.getCityEnName());
        //城市不存在支持地区
        if (addressResult.getResultSize() == 0) {
            redirectAttributes.addAttribute("msg", "must_chose_city");
            return "redirect:/index";
        }
        model.addAttribute("currentCity", city.getResult());
        //查询房源信息集合
        ServiceMultiResult<HouseDTO> serviceMultiResult = houseService.query(rentSearch);
        model.addAttribute("total", serviceMultiResult.getTotal());
        model.addAttribute("houses", serviceMultiResult.getResult());
        //地区设定
        if (rentSearch.getRegionEnName() == null) {
            rentSearch.setRegionEnName("*");
        }
        model.addAttribute("searchBody", rentSearch);
        model.addAttribute("regions", addressResult.getResult());
        model.addAttribute("priceBlocks", HouseValueBlock.PRICE_BLOCK);
        model.addAttribute("areaBlocks", HouseValueBlock.AREA_BLOCK);
        model.addAttribute("currentPriceBlock", HouseValueBlock.matchPrice(rentSearch.getPriceBlock()));
        model.addAttribute("currentAreaBlock", HouseValueBlock.matchArea(rentSearch.getAreaBlock()));
        //跳转页面
        return "rent-list";
    }*/

    /**
     * 房屋展示页
     * @param houseId 房屋id
     */
    @GetMapping("rent/house/show/{id}")
    public String show(@PathVariable(value = "id") Long houseId,
                       Model model) {
        if (houseId <= 0) {
            return "404";
        }
        //查询指定房源信息
        ServiceResult<HouseDTO> serviceResult = houseService.findCompleteOne(houseId);
        if (!serviceResult.isSuccess()) {
            return "404";
        }
        //获取房屋信息
        HouseDTO houseDTO = serviceResult.getResult();
        //查找城市和地区对应信息
        Map<SupportAddress.Level, SupportAddressDTO>
                addressMap = addressService.findCityAndRegion(houseDTO.getCityEnName(), houseDTO.getRegionEnName());
        //城市信息
        SupportAddressDTO city = addressMap.get(SupportAddress.Level.CITY);
        //地区信息
        SupportAddressDTO region = addressMap.get(SupportAddress.Level.REGION);
        model.addAttribute("city", city);
        model.addAttribute("region", region);
        //根据id获取用户
        ServiceResult<UserDTO> userDTOServiceResult = userService.findUserById(houseDTO.getAdminId());
        model.addAttribute("agent", userDTOServiceResult.getResult());
        model.addAttribute("house", houseDTO);
        //聚合指定小区的房间数
        ServiceResult<Long> aggResult = searchService.aggregateDistrictHouse(city.getEnName(), region.getEnName(), houseDTO.getDistrict());
        model.addAttribute("houseCountInDistrict", aggResult.getResult());
        //跳转页面
        return "house-detail";
    }

    /**
     * 地图找房页
     * @param cityEnName 城市缩写
     */
    @GetMapping("rent/house/map")
    public String rentMapPage(@RequestParam(value = "cityEnName") String cityEnName,
                              Model model,
                              HttpSession session,
                              RedirectAttributes redirectAttributes) {
        //获取指定城市详细信息
        ServiceResult<SupportAddressDTO> city = addressService.findCity(cityEnName);
        //城市不存在
        if (!city.isSuccess()) {
            redirectAttributes.addAttribute("msg", "must_chose_city");
            return "redirect:/index";
        } else {
            session.setAttribute("cityName", cityEnName);
            model.addAttribute("city", city.getResult());
        }
        //获取指定城市所有支持地区
        ServiceMultiResult<SupportAddressDTO> regions = addressService.findAllRegionsByCityName(cityEnName);
        //聚合城市数据
        ServiceMultiResult<HouseBucketDTO> serviceResult = searchService.mapAggregate(cityEnName);
        model.addAttribute("aggData", serviceResult.getResult());
        model.addAttribute("total", serviceResult.getTotal());
        model.addAttribute("regions", regions.getResult());
        //跳转页面
        return "rent-map";
    }
    ///////////////////////////////////////Admin//////////////////////////////////////////////////////////////
    /**
     * 管理员登录页
     */
    @GetMapping("/admin/login")
    public String adminLoginPage() {
        return "admin/login";
    }

    /**
     * 后台管理中心
     */
    @GetMapping("/admin/center")
    public String adminCenterPage() {
        return "admin/center";
    }

    /**
     * 欢迎页
     */
    @GetMapping("/admin/welcome")
    public String welcomePage() {
        return "admin/welcome";
    }

    /**
     * 房源列表页
     */
    @GetMapping("admin/house/list")
    public String houseListPage() {
        return "admin/house-list";
    }

    /**
     * 新增房源功能页
     */
    @GetMapping("admin/add/house")
    public String addHousePage() {
        return "admin/house-add";
    }

    /**
     * 房屋预约界面
     */
    @GetMapping("admin/house/subscribe")
    public String houseSubscribe() {
        return "admin/subscribe";
    }

    /**
     * 房源信息编辑页
     */
    @GetMapping("admin/house/edit")
    public String houseEditPage(@RequestParam(value = "id") Long id, Model model) {
        if (id == null || id < 1) {
            return "404";
        }
        //查询指定房源信息
        ServiceResult<HouseDTO> serviceResult = houseService.findCompleteOne(id);
        if (!serviceResult.isSuccess()) {
            return "404";
        }
        //获取房屋信息
        HouseDTO result = serviceResult.getResult();
        model.addAttribute("house", result);
        //查找城市和地区对应信息
        Map<SupportAddress.Level, SupportAddressDTO> addressMap = addressService.findCityAndRegion(result.getCityEnName(), result.getRegionEnName());
        model.addAttribute("city", addressMap.get(SupportAddress.Level.CITY));
        model.addAttribute("region", addressMap.get(SupportAddress.Level.REGION));
        //获取房屋详细信息
        HouseDetailDTO detailDTO = result.getHouseDetail();
        //获取地铁线信息
        ServiceResult<SubwayDTO> subwayServiceResult = addressService.findSubway(detailDTO.getSubwayLineId());
        if (subwayServiceResult.isSuccess()) {
            model.addAttribute("subway", subwayServiceResult.getResult());
        }
        //获取地铁站信息
        ServiceResult<SubwayStationDTO> subwayStationServiceResult = addressService.findSubwayStation(detailDTO.getSubwayStationId());
        if (subwayStationServiceResult.isSuccess()) {
            model.addAttribute("station", subwayStationServiceResult.getResult());
        }
        //跳转编辑页面
        return "admin/house-edit";
    }
}

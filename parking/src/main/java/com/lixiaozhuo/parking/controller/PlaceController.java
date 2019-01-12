package com.lixiaozhuo.parking.controller;

import com.lixiaozhuo.parking.exception.CustomException;
import com.lixiaozhuo.parking.pojo.Condition;
import com.lixiaozhuo.parking.pojo.Place;
import com.lixiaozhuo.parking.service.IPlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

/**
 * 停车位置控制器
 */
@Controller
public class PlaceController {
    @Autowired
    private IPlaceService placeService;

    //显示车位
    @RequestMapping("/showPlace")
    public String showPlace(Model model, Condition condition, @RequestParam(defaultValue = "1") int page) {
        //存储车位数据
        Page<Place> pages;
        //根据条件查询
        pages = placeService.listPlace(condition, page);
        //响应数据
        model.addAttribute("condition", condition);
        model.addAttribute("pages", pages);
        //跳转视图
        return "place/showPlace.html";
    }

    //新增或编辑车位信息
    @RequestMapping("/editPlace")
    public String editPlace(Model model, Integer placeId) {
        //编辑模式
        if (placeId != null) {
            //获取车位信息
            Place place = placeService.getPlace(placeId);
            //状态为占用,不可编辑
            if (place.getState()) {
                throw new CustomException("车位状态为占用,不可编辑");
            }
            //响应数据
            model.addAttribute("place", place);
        }
        //跳转视图
        return "place/editPlace.html";
    }

    //保存车位信息
    @RequestMapping("/savePlace")
    public String savePlace(Place place) {
        placeService.savePlace(place);
        //跳转视图
        return "redirect:/showPlace";
    }

    //删除车位信息
    @RequestMapping("/deletePlace")
    public String deletePlace(Integer placeId) {
        //id不为空
        if (placeId != null) {
            placeService.deletePlace(placeId);
        }
        //跳转视图
        return "redirect:/showPlace";
    }

}

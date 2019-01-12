package com.lixiaozhuo.parking.controller;

import com.lixiaozhuo.parking.pojo.Card;
import com.lixiaozhuo.parking.pojo.Condition;
import com.lixiaozhuo.parking.pojo.ParkFixed;
import com.lixiaozhuo.parking.service.ICardService;
import com.lixiaozhuo.parking.service.IParkFixedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import java.util.List;

/**
 * 固定停车控制器
 */
@Controller
public class ParkFixedController {
    @Autowired
    private IParkFixedService parkFixedService;

    @Autowired
    private ICardService cardService;

    //展示固定停车记录
    @RequestMapping("/showParkFixed")
    public String showPlace(Model model, Condition condition, @RequestParam(defaultValue = "1") int page) {
        //存储停车记录
        Page<ParkFixed> pages;
        //根据条件查询
        pages = parkFixedService.listParkFixed(condition, page);
        //响应数据
        model.addAttribute("condition", condition);
        model.addAttribute("pages", pages);
        //跳转视图
        return "parkFixed/showParkFixed.html";
    }

    //展示进入停车场界面
    @RequestMapping("/showEntryParkFixed")
    public String editPlace(Model model) {
        //查询所有未入场的IC卡信息
        List<Card> cards = cardService.listCardNotEntry();
        //响应数据
        model.addAttribute("cards", cards);
        //跳转视图
        return "parkFixed/editParkFixed.html";
    }

    //进入停车场
    @RequestMapping("/entryParkFixed")
    public String entryParkFixed(Integer cardId) {
        if (cardId != null) {
            parkFixedService.entryPark(cardId);
        }
        //跳转视图
        return "redirect:/showParkFixed";
    }

    //离开停车场
    @RequestMapping("/leaveParkFixed")
    public String leaveParkFixed(Integer parkFixedId) {
        if (parkFixedId != null) {
            parkFixedService.leavePark(parkFixedId);
        }
        //跳转视图
        return "redirect:/showParkFixed";
    }

    //删除停车记录
    @RequestMapping("/deleteParkFixed")
    public String deleteParkFixed(Integer parkFixedId) {
        if (parkFixedId != null) {
            parkFixedService.deleteParkFixed(parkFixedId);
        }
        //跳转视图
        return "redirect:/showParkFixed";
    }
}

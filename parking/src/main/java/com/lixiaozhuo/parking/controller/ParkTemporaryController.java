package com.lixiaozhuo.parking.controller;

import com.lixiaozhuo.parking.pojo.CardTemporary;
import com.lixiaozhuo.parking.pojo.Condition;
import com.lixiaozhuo.parking.pojo.ParkTemporary;
import com.lixiaozhuo.parking.service.ICardTemporaryService;
import com.lixiaozhuo.parking.service.IParkTemporaryService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * 临时停车控制器
 */
@Controller
public class ParkTemporaryController {
    @Autowired
    private IParkTemporaryService parkTemporaryService;

    @Autowired
    private ICardTemporaryService cardTemporaryService;

    //显示临时停车记录
    @RequestMapping("/showParkTemporary")
    public String showPlace(Model model, Condition condition, @RequestParam(defaultValue = "1") int page) {
        //存储临时停车记录
        Page<ParkTemporary> pages;
        //根据条件查询
        pages = parkTemporaryService.listParkTemporary(condition, page);
        //响应数据
        model.addAttribute("condition", condition);
        model.addAttribute("pages", pages);
        //跳转视图
        return "parkTemporary/showParkTemporary.html";
    }

    //显示入场界面
    @RequestMapping("/showEntryParkTemporary")
    public String showEntryParkTemporary(Model model) {
        //查询所有未入场的临时IC卡信息
        List<CardTemporary> cards = cardTemporaryService.listCardTemporaryNotEntry();
        //响应数据
        model.addAttribute("cards", cards);
        //跳转视图
        return "parkTemporary/entryParkTemporary.html";
    }

    //临时停车
    @RequestMapping("/entryParkTemporary")
    public String entryParkTemporary(Model model, Integer cardId, String carNo) {
        //IC卡不为空且汽车号码不为空
        if (cardId != null && Strings.isNotEmpty(carNo)) {
            parkTemporaryService.entryParkTemporary(cardId, carNo);
        }
        //跳转视图
        return "redirect:/showParkTemporary";
    }

    //离开停车场
    @RequestMapping("/editParkTemporary")
    public String editParkTemporary(Model model, Integer parkTemporaryId) {
        if (parkTemporaryId != null) {
            ParkTemporary parkTemporary = parkTemporaryService.getParkTemporary(parkTemporaryId);
            parkTemporary.setLeave_time(new Date());
            model.addAttribute("parkTemporary", parkTemporary);
        }
        //跳转视图
        return "parkTemporary/leaveParkTemporary.html";
    }

    //结算临时停车记录
    @RequestMapping("/saveParkTemporary")
    public String saveParkFixed(ParkTemporary parkTemporary) {
        //保存停车记录
        parkTemporaryService.saveParkTemporary(parkTemporary);
        //跳转视图
        return "redirect:/showParkTemporary";
    }

    //删除临时停车记录
    @RequestMapping("/deleteParkTemporary")
    public String deleteParkTemporary(Integer parkTemporaryId) {
        if (parkTemporaryId != null) {
            parkTemporaryService.deleteParkTemporary(parkTemporaryId);
        }
        //跳转视图
        return "redirect:/showParkTemporary";
    }
}

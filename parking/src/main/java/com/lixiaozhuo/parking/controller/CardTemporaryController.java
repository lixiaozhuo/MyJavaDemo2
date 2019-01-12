package com.lixiaozhuo.parking.controller;

import com.lixiaozhuo.parking.exception.CustomException;
import com.lixiaozhuo.parking.pojo.CardTemporary;
import com.lixiaozhuo.parking.pojo.Condition;
import com.lixiaozhuo.parking.pojo.Place;
import com.lixiaozhuo.parking.service.ICardTemporaryService;
import com.lixiaozhuo.parking.service.IPlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 临时IC卡控制器
 */
@Controller
public class CardTemporaryController {
    @Autowired
    private ICardTemporaryService cardTemporaryService;
    @Autowired
    private IPlaceService placeService;

    //展示IC卡
    @RequestMapping("/showCardTemporary")
    public String showCardTemporary(Model model, Condition condition, @RequestParam(defaultValue = "1") int page) {
        //存储IC卡数据
        Page<CardTemporary> pages;
        //根据条件查询
        pages = cardTemporaryService.listCardTemporary(condition, page);
        //响应数据
        model.addAttribute("condition", condition);
        model.addAttribute("pages", pages);
        //跳转视图
        return "cardTemporary/showCardTemporary.html";
    }

    //编辑IC卡
    @RequestMapping("/editCardTemporary")
    public String editPlace(Model model, Integer cardId) {
        //获取空余临时车位
        List<Place> places = placeService.listFreeParkTemporaryPlace();
        //编辑模式
        if (cardId != null) {
            CardTemporary card = cardTemporaryService.getCardTemporary(cardId);
            //状态为占用,不可编辑
            if (card.getState()) {
                throw new CustomException("IC卡状态为占用,不可编辑");
            }
            System.out.println(card.getId());
            //响应IC卡数据
            model.addAttribute("card", card);
            //将当前占有位置加入空余车位集合
            places.add(card.getPlace());
        }
        //响应空余临时车位数据
        model.addAttribute("places", places);
        //跳转视图
        return "cardTemporary/editCardTemporary.html";
    }

    //保存IC卡
    @RequestMapping("/saveCardTemporary")
    public String saveCardTemporary(CardTemporary card) {
        //保存IC卡
        cardTemporaryService.saveCardTemporary(card);
        //跳转视图
        return "redirect:/showCardTemporary";
    }

    //删除IC卡
    @RequestMapping("/deleteCardTemporary")
    public String deleteCard(Integer cardId) {
        if (cardId != null) {
            //删除IC卡
            cardTemporaryService.deleteCardTemporary(cardId);
        }
        //跳转视图
        return "redirect:/showCardTemporary";
    }
}

package com.lixiaozhuo.parking.controller;

import com.lixiaozhuo.parking.exception.CustomException;
import com.lixiaozhuo.parking.pojo.Card;
import com.lixiaozhuo.parking.pojo.Condition;
import com.lixiaozhuo.parking.pojo.Place;
import com.lixiaozhuo.parking.service.ICardService;
import com.lixiaozhuo.parking.service.IPlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import java.util.List;

/**
 * IC卡控制器
 */
@Controller
public class CardController {
    @Autowired
    private ICardService cardService;
    @Autowired
    private IPlaceService placeService;

    //展示IC卡
    @RequestMapping("/showCard")
    public String showCard(Model model, Condition condition, @RequestParam(defaultValue = "1") int page) {
        //存储IC卡数据
        Page<Card> pages;
        //根据条件查询
        pages = cardService.listCard(condition, page);
        //将IC卡信息和条件返回页面:数据回显
        model.addAttribute("condition", condition);
        model.addAttribute("pages", pages);
        //跳转视图
        return "card/showCard.html";
    }

    //添加或编辑IC卡
    @RequestMapping("/editCard")
    public String editPlace(Model model, Integer cardId) {
        //获取空余固定车位
        List<Place> places = placeService.listFreeParkFixedPlace();
        //编辑模式
        if (cardId != null) {
            //获取当前Card
            Card card = cardService.getCard(cardId);
            //状态为占用,不可编辑
            if (card.getState()) {
                throw new CustomException("IC卡状态为占用,不可编辑");
            }
            //响应IC卡数据
            model.addAttribute("card", card);
            //将当前占有位置加入空余车位集合
            places.add(card.getPlace());
        }
        //响应空余固定车位数据
        model.addAttribute("places", places);
        //跳转视图
        return "card/editCard.html";
    }

    //保存IC卡
    @RequestMapping("/saveCard")
    public String saveCard(Card card) {
        //保存IC卡
        cardService.saveCard(card);
        //跳转视图
        return "redirect:/showCard";
    }

    //删除IC卡
    @RequestMapping("/deleteCard")
    public String deleteCard(Integer cardId) {
        if (cardId != null) {
            //删除IC卡
            cardService.deleteCard(cardId);
        }
        //跳转视图
        return "redirect:/showCard";
    }
}

package com.lixiaozhuo.house.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 响应页面
 */
@Controller
public class HomeController {

    @GetMapping(value = {"/","/index"})
    public String index(){
        return "index";
    }

    @GetMapping(value = "/404")
    public String notFoundPage(){
        return  "404";
    }

    @GetMapping(value = "/403")
    public String accessError() {
        return "403";
    }

    @GetMapping(value = "/500")
    public String internalError() {
        return "500";
    }

    @GetMapping(value = "/logout/page")
    public String logoutPage() {
        return "logout";
    }
}

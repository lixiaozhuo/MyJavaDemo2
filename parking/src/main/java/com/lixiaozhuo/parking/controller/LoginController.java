package com.lixiaozhuo.parking.controller;

import com.lixiaozhuo.parking.service.ILoginService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * 登录控制器
 */
@Controller
public class LoginController {
    @Autowired
    private ILoginService loginService;

    /*
     *登录
     */
    @RequestMapping("/login")
    public String showUser(HttpSession session, String phone, String password) {
        //手机号码和密码不为空
        if (Strings.isNotEmpty(phone) && Strings.isNotEmpty(password)) {
            //检查用户手机号码和密码
            if (loginService.checkLogin(phone, password)) {
                //将用户id保存在session对象中全局使用
                session.setAttribute("phone", phone);
                //转到主页面
                return "redirect:/showMain";
            }
        }
        //登陆错误返回登录页面
        return "index";
    }

    /*
     * 显示主界面
     */
    @RequestMapping("/showMain")
    public String index(HttpSession session) {
        //判断用户是否登录
        Object phone = session.getAttribute("phone");
        if (phone == null) {
            //用户未登录,返回登录界面
            return "index";
        }
        //显示主界面
        return "main";
    }

    /**
     * 登出
     * @param session
     * @return
     */
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        //从session中删除记录
        session.removeAttribute("phone");
        //返回登录界面
        return "index";
    }
}

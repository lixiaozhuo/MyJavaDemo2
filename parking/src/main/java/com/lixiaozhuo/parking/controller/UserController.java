package com.lixiaozhuo.parking.controller;

import com.lixiaozhuo.parking.pojo.Condition;
import com.lixiaozhuo.parking.pojo.User;
import com.lixiaozhuo.parking.service.IUserService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;

/**
 * 用户控制器
 */
@Controller
public class UserController {
    @Autowired
    private IUserService userService;

    //展示用户数据
    @RequestMapping("/showUser")
    public String showUser(Model model, Condition condition, @RequestParam(defaultValue = "1") int page) {
        //用户数
        Page<User> pages;
        //根据条件查询
        pages = userService.listUser(condition, page);
        //响应数据
        model.addAttribute("condition", condition);
        model.addAttribute("pages", pages);
        //跳转视图
        return "user/showUser.html";
    }

    //新增或编辑用户
    @RequestMapping("/editUser")
    public String editUser(Model model, Long userId) {
        //编辑模式
        if (userId != null) {
            //获取用户信息
            User user = userService.getUser(userId);
            //响应数据
            model.addAttribute("user", user);
        }
        //跳转视图
        return "user/editUser.html";
    }

    //保存用户
    @RequestMapping("/saveUser")
    public String saveUser(User user) {
        //密码为空:防止更新时密码丢失
        if (user.getPassword() == null) {
            //获取原密码
            User oldUser = userService.getUser(user.getId());
            //设置密码
            user.setPassword(oldUser.getPassword());
        }
        //保存用户
        userService.saveUser(user);
        //跳转视图
        return "redirect:/showUser";
    }

    //删除用户
    @RequestMapping("/deleteUser")
    public String deleteUser(Long userId) {
        if (userId != null) {
            userService.deleteUser(userId);
        }
        //跳转视图
        return "redirect:/showUser";
    }

    //显示更新密码界面
    @RequestMapping("/showUpdatePassword")
    public String showUpdatePassword() {
        //跳转视图
        return "user/changePassword";
    }

    //更新密码
    @RequestMapping("/updatePassword")
    public String updatePassword(HttpSession session, String newPassword) {
        //获取当前登录用户手机号码
        String phone = (String) session.getAttribute("phone");
        //手机号码和密码不为空
        if (Strings.isNotEmpty(phone) && Strings.isNotEmpty(newPassword)) {
            //更新当前用户密码
            userService.updatePassword(phone, newPassword);
        }
        //跳转视图
        return "redirect:/showUser";
    }
}



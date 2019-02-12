package com.lixiaozhuo.house.base.uitls;

import com.lixiaozhuo.house.entity.User;

import java.util.regex.Pattern;


/**
 * 用户登录工具
 */
public class LoginUserUtil {
    //电话号码正则表达式
    private static final String PHONE_REGEX = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8}$";
    private static final Pattern PHONE_PATTERN = Pattern.compile(PHONE_REGEX);
    //电子邮箱正则表达式
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    /**
     * 验证手机号码是否符合标准
     * 移动号码段:139、138、137、136、135、134、150、151、152、157、158、159、182、183、187、188、147
     * 联通号码段:130、131、132、136、185、186、145
     * 电信号码段:133、153、180、189
     * @param target 目标号码
     */
    public static boolean checkTelephone(String target) {
        return PHONE_PATTERN.matcher(target).matches();
    }

    /**
     * 验证英文邮箱是否符合标准
     * @param target 目标邮箱
     */
    public static boolean checkEmail(String target) {
        return EMAIL_PATTERN.matcher(target).matches();
    }


    /**
     * 加载当前登录用户
     */
    public static User load() {
        /*Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal != null && principal instanceof User) {
            return (User) principal;
        }*/
        return null;
    }

    /**
     * 获得当前登录的用户id
     */
    public static Long getLoginUserId() {
        //获取当前登录用户
        User user = load();
        if (user == null) {
            return -1L;
        }
        //返回用户id
        return user.getId();
    }
}

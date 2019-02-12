package com.lixiaozhuo.house.web.controller.user;

import com.lixiaozhuo.house.base.response.APIResponse;
import com.lixiaozhuo.house.base.uitls.LoginUserUtil;
import com.lixiaozhuo.house.entity.HouseSubscribe;
import com.lixiaozhuo.house.service.ServiceMultiResult;
import com.lixiaozhuo.house.service.ServiceResult;
import com.lixiaozhuo.house.service.house.IHouseService;
import com.lixiaozhuo.house.service.user.IUserService;
import com.lixiaozhuo.house.web.dto.HouseDTO;
import com.lixiaozhuo.house.web.dto.HouseSubscribeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * 用户控制器
 */
@RestController
public class UserController {
    //用户服务
    @Autowired
    private IUserService userService;

    //房屋服务
    @Autowired
    private IHouseService houseService;

    /**
     *  更新用户属性
     * @param profile 指定属性值
     * @param value 值
     */
    @PostMapping(value = "api/user/info")
    public APIResponse updateUserInfo(@RequestParam(value = "profile") String profile,
                                      @RequestParam(value = "value") String value) {
        //值为空
        if (value.isEmpty()) {
            return APIResponse.ofStatus(APIResponse.Status.BAD_REQUEST);
        }
        //邮箱格式错误
        if ("email".equals(profile) && !LoginUserUtil.checkEmail(value)) {
            return APIResponse.ofMessage(APIResponse.Status.BAD_REQUEST.getCode(), "不支持的邮箱格式");
        }
        //修改指定属性值
        ServiceResult result = userService.modifyUserProfile(profile, value);
        //响应数据
        if (result.isSuccess()) {
            return APIResponse.ofSuccess();
        }
        return APIResponse.ofMessage(APIResponse.Status.BAD_REQUEST.getCode(), result.getMessage());
    }

    /**
     * 加入预约清单
     * @param houseId 房屋id
     */
    @PostMapping(value = "api/user/house/subscribe")
    public APIResponse subscribeHouse(@RequestParam(value = "house_id") Long houseId) {
        //加入预约
        ServiceResult result = houseService.addSubscribeOrder(houseId);
        //响应数据
        if (result.isSuccess()) {
            return APIResponse.ofSuccess();
        }
        return APIResponse.ofMessage(APIResponse.Status.BAD_REQUEST.getCode(), result.getMessage());
    }

    /**
     * 取消预约
     * @param houseId 房屋id
     */
    @DeleteMapping(value = "api/user/house/subscribe")
    public APIResponse cancelSubscribe(@RequestParam(value = "houseId") Long houseId) {
        //取消预约
        ServiceResult result = houseService.cancelSubscribe(houseId);
        //响应数据
        if (result.isSuccess()) {
            return APIResponse.ofSuccess();
        }
        return APIResponse.ofMessage(APIResponse.Status.BAD_REQUEST.getCode(), result.getMessage());
    }

    /**
     * 获取对应状态的预约列表
     * @param start 开始页
     * @param size 页面大小
     * @param status 状态
     */
    @GetMapping(value = "api/user/house/subscribe/list")
    public APIResponse subscribeList(
            @RequestParam(value = "start", defaultValue = "0") int start,
            @RequestParam(value = "size", defaultValue = "3") int size,
            @RequestParam(value = "status") int status) {
        //获取对应状态的预约列表
        ServiceMultiResult<Pair<HouseDTO, HouseSubscribeDTO>> result = houseService.querySubscribeList(HouseSubscribe.HouseSubscribeStatus.of(status), start, size);
        //响应数据
        if (result.getResultSize() == 0) {
            return APIResponse.ofSuccess(result.getResult());
        }
        APIResponse response = APIResponse.ofSuccess(result.getResult());
        //设置是否存在更多数据
        response.setMore(result.getTotal() > (start + size));
        return response;
    }

    /**
     * 预约看房
     * @param houseId 房屋id
     * @param orderTime 预约时间
     * @param desc 用户描述
     * @param telephone 电话号码
     */
    @PostMapping(value = "api/user/house/subscribe/date")
    public APIResponse subscribeDate(
            @RequestParam(value = "houseId") Long houseId,
            @RequestParam(value = "orderTime") @DateTimeFormat(pattern = "yyyy-MM-dd") Date orderTime,
            @RequestParam(value = "desc", required = false) String desc,
            @RequestParam(value = "telephone") String telephone) {
        //预约时间为空
        if (orderTime == null) {
            return APIResponse.ofMessage(APIResponse.Status.BAD_REQUEST.getCode(), "请选择预约时间");
        }
        //手机格式错误
        if (!LoginUserUtil.checkTelephone(telephone)) {
            return APIResponse.ofMessage(APIResponse.Status.BAD_REQUEST.getCode(), "手机格式不正确");
        }
        //预约看房
        ServiceResult result = houseService.subscribe(houseId, orderTime, telephone, desc);
        //响应数据
        if (result.isSuccess()) {
            return APIResponse.ofSuccess();
        }
        return APIResponse.ofMessage(APIResponse.Status.BAD_REQUEST.getCode(), result.getMessage());
    }


}

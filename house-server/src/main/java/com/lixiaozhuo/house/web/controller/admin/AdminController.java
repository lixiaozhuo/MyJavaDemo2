package com.lixiaozhuo.house.web.controller.admin;

import com.google.common.base.Strings;
import com.google.gson.Gson;
import com.lixiaozhuo.house.base.constant.HouseOperation;
import com.lixiaozhuo.house.base.response.APIDataTableResponse;
import com.lixiaozhuo.house.base.response.APIResponse;
import com.lixiaozhuo.house.entity.House;
import com.lixiaozhuo.house.entity.SupportAddress;
import com.lixiaozhuo.house.service.ServiceMultiResult;
import com.lixiaozhuo.house.service.ServiceResult;
import com.lixiaozhuo.house.service.house.IAddressService;
import com.lixiaozhuo.house.service.house.IHouseService;
import com.lixiaozhuo.house.service.house.IQiNiuService;
import com.lixiaozhuo.house.service.user.IUserService;
import com.lixiaozhuo.house.web.dto.*;
import com.lixiaozhuo.house.web.form.DatatableSearch;
import com.lixiaozhuo.house.web.form.HouseForm;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * 管理员控制器
 */
@RestController
public class AdminController {
    //七牛服务
    @Autowired
    private IQiNiuService qiNiuService;
    //地址服务
    @Autowired
    private IAddressService addressService;
    //房屋服务
    @Autowired
    private IHouseService houseService;
    //用户服务
    @Autowired
    private IUserService userService;
    //Gson
    @Autowired
    private Gson gson;
    /**
     * 上传图片
     * @param file 文件
     */
    @PostMapping(value = "api/admin/upload/photo", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public APIResponse uploadPhoto(@RequestParam("file") MultipartFile file) {
        //文件为空
        if (file.isEmpty()) {
            return APIResponse.ofStatus(APIResponse.Status.BAD_REQUEST);
        }
        //获取文件名
        String fileName = file.getOriginalFilename();
        try {
            InputStream inputStream = file.getInputStream();
            //上传文件
            Response response = qiNiuService.uploadFile(inputStream);
            if (response.isOK()) {
                //解析数据
                QiNiuDTO ret = gson.fromJson(response.bodyString(), QiNiuDTO.class);
                //成功上传
                return APIResponse.ofSuccess(ret);
            } else {
                return APIResponse.ofMessage(response.statusCode, response.getInfo());
            }
        } catch (QiniuException e) {
            Response response = e.response;
            try {
                return APIResponse.ofMessage(response.statusCode, response.bodyString());
            } catch (QiniuException e1) {
                return APIResponse.ofStatus(APIResponse.Status.INTERNAL_SERVER_ERROR);
            }
        } catch (IOException e) {
            return APIResponse.ofStatus(APIResponse.Status.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 查询用户信息接口
     * @param userId 用户id
     */
    @GetMapping("api/admin/user/{userId}")
    public APIResponse getUserInfo(@PathVariable(value = "userId") Long userId) {
        //参数检测
        if (userId == null || userId < 1) {
            return APIResponse.ofStatus(APIResponse.Status.BAD_REQUEST);
        }
        //获取用户信息
        ServiceResult<UserDTO> result = userService.findUserById(userId);
        //响应数据
        if (result.isSuccess()) {
            return APIResponse.ofSuccess(result.getResult());
        }
        return APIResponse.ofMessage(APIResponse.Status.NOT_FOUND.getCode(), result.getMessage());
    }

    /**
     * 查询房屋信息
     * @param searchBody 搜索请求
     */
    @PostMapping("api/admin/house")
    public APIDataTableResponse houses(@ModelAttribute DatatableSearch searchBody) {
        //查询房屋信息
        ServiceMultiResult<HouseDTO> result = houseService.adminQuery(searchBody);
        //封装响应
        APIDataTableResponse response = new APIDataTableResponse(APIResponse.Status.SUCCESS);
        response.setData(result.getResult());
        response.setRecordsFiltered(result.getTotal());
        response.setRecordsTotal(result.getTotal());
        response.setDraw(searchBody.getDraw());
        return response;
    }

    /**
     * 新增房源接口
     * @param houseForm 房屋表单
     */
    @PostMapping("api/admin/house/add")
    public APIResponse addHouse(@Valid @ModelAttribute("form-house-add") HouseForm houseForm, BindingResult bindingResult) {
        //请求数据存在错误
        if (bindingResult.hasErrors()) {
            return new APIResponse(APIResponse.Status.BAD_REQUEST.getCode(), bindingResult.getAllErrors().get(0).getDefaultMessage(), null);
        }
        //图片不存在
        if (houseForm.getPhotos() == null || houseForm.getCover() == null) {
            return APIResponse.ofMessage(APIResponse.Status.BAD_REQUEST.getCode(), "必须上传图片");
        }
        //查找城市和地区对应信息
        Map<SupportAddress.Level, SupportAddressDTO> addressMap = addressService.findCityAndRegion(houseForm.getCityEnName(), houseForm.getRegionEnName());
        //城市和地区信息错误
        if (addressMap.keySet().size() != 2) {
            return APIResponse.ofStatus(APIResponse.Status.BAD_REQUEST);
        }
        //保存房源信息
        ServiceResult<HouseDTO> result = houseService.save(houseForm);
        //响应数据
        if (result.isSuccess()) {
            return APIResponse.ofSuccess();
        }
        return APIResponse.ofMessage(APIResponse.Status.BAD_REQUEST.getCode(), result.getMessage());
    }

    /**
     * 更新房源接口
     */
    @PostMapping("api/admin/house/edit")
    public APIResponse saveHouse(@Valid @ModelAttribute("form-house-edit") HouseForm houseForm, BindingResult bindingResult) {
        //请求数据存在错误
        if (bindingResult.hasErrors()) {
            return new APIResponse(APIResponse.Status.BAD_REQUEST.getCode(), bindingResult.getAllErrors().get(0).getDefaultMessage(), null);
        }
        //查找城市和地区对应信息
        Map<SupportAddress.Level, SupportAddressDTO> addressMap = addressService.findCityAndRegion(houseForm.getCityEnName(), houseForm.getRegionEnName());
        //城市和地区信息错误
        if (addressMap.keySet().size() != 2) {
            return APIResponse.ofSuccess(APIResponse.Status.BAD_REQUEST);
        }
        //更新房屋信息
        ServiceResult result = houseService.update(houseForm);
        //响应数据
        if (result.isSuccess()) {
            return APIResponse.ofSuccess();
        }
        return APIResponse.ofMessage(APIResponse.Status.BAD_REQUEST.getCode(), result.getMessage());
    }


    /**
     * 移除图片接口
     * @param id id
     */
    @DeleteMapping("api/admin/house/photo")
    public APIResponse removeHousePhoto(@RequestParam(value = "id") Long id) {
        //移除图片
        ServiceResult result = this.houseService.removePhoto(id);
        //响应数据
        if (result.isSuccess()) {
            return APIResponse.ofSuccess();
        }
        return APIResponse.ofMessage(APIResponse.Status.BAD_REQUEST.getCode(), result.getMessage());
    }

    /**
     * 修改封面接口
     * @param coverId 封面
     * @param targetId 源封面
     */
    @PostMapping("api/admin/house/cover")
    public APIResponse updateCover(@RequestParam(value = "cover_id") Long coverId,
                                   @RequestParam(value = "target_id") Long targetId) {
        //更新封面
        ServiceResult result = this.houseService.updateCover(coverId, targetId);
        //响应数据
        if (result.isSuccess()) {
            return APIResponse.ofSuccess();
        }
        return APIResponse.ofMessage(APIResponse.Status.BAD_REQUEST.getCode(), result.getMessage());
    }

    /**
     * 增加标签接口
     * @param houseId 房屋id
     * @param tag 标签名
     */
    @PostMapping("api/admin/house/tag")
    public APIResponse addHouseTag(@RequestParam(value = "house_id") Long houseId,
                                   @RequestParam(value = "tag") String tag) {
        //参数检测
        if (houseId < 1 || Strings.isNullOrEmpty(tag)) {
            return APIResponse.ofStatus(APIResponse.Status.BAD_REQUEST);
        }
        //增加标签
        ServiceResult result = this.houseService.addTag(houseId, tag);
        //响应数据
        if (result.isSuccess()) {
            return APIResponse.ofSuccess();
        }
        return APIResponse.ofMessage(APIResponse.Status.BAD_REQUEST.getCode(), result.getMessage());
    }

    /**
     * 移除标签接口
     * @param houseId 房屋id
     * @param tag 标签名
     */
    @DeleteMapping("api/admin/house/tag")
    public APIResponse removeHouseTag(@RequestParam(value = "house_id") Long houseId,
                                      @RequestParam(value = "tag") String tag) {
        //参数检测
        if (houseId < 1 || Strings.isNullOrEmpty(tag)) {
            return APIResponse.ofStatus(APIResponse.Status.BAD_REQUEST);
        }
        //移除标签
        ServiceResult result = this.houseService.removeTag(houseId, tag);
        //响应数据
        if (result.isSuccess()) {
            return APIResponse.ofSuccess();
        }
        return APIResponse.ofMessage(APIResponse.Status.BAD_REQUEST.getCode(), result.getMessage());
    }

    /**
     * 房屋审核接口
     * @param id id
     * @param operation 房屋操作
     */
    @PutMapping("api/admin/house/operate/{id}/{operation}")
    public APIResponse operateHouse(@PathVariable(value = "id") Long id,
                                    @PathVariable(value = "operation") int operation) {
        //参数检测
        if (id <= 0) {
            return APIResponse.ofStatus(APIResponse.Status.NOT_VALID_PARAM);
        }
        ServiceResult result;
        //根据操作更新房屋状态
        switch (operation) {
            case HouseOperation.PASS://通过审核
                result = this.houseService.updateStatus(id, House.HouseStatus.PASSES.getValue());
                break;
            case HouseOperation.PULL_OUT://下架,重新审核
                result = this.houseService.updateStatus(id, House.HouseStatus.NOT_AUDITED.getValue());
                break;
            case HouseOperation.DELETE://删除
                result = this.houseService.updateStatus(id, House.HouseStatus.DELETED.getValue());
                break;
            case HouseOperation.RENT://出租
                result = this.houseService.updateStatus(id, House.HouseStatus.RENTED.getValue());
                break;
            default:
                return APIResponse.ofStatus(APIResponse.Status.BAD_REQUEST);
        }
        //响应数据
        if (result.isSuccess()) {
            return APIResponse.ofSuccess();
        }
        return APIResponse.ofMessage(APIResponse.Status.BAD_REQUEST.getCode(), result.getMessage());
    }

    /**
     * 管理员查询预约信息集合接口
     * @param draw
     * @param start 开始页
     * @param size 页面大小
     */
    @GetMapping("api/admin/house/subscribe/list")
    public APIResponse subscribeList(@RequestParam(value = "draw") int draw,
                                     @RequestParam(value = "start") int start,
                                     @RequestParam(value = "length") int size) {
        //查询预约信息集合
        ServiceMultiResult<Pair<HouseDTO, HouseSubscribeDTO>> result = houseService.findSubscribeList(start, size);
        //封装响应信息
        APIDataTableResponse response = new APIDataTableResponse(APIResponse.Status.SUCCESS);
        response.setData(result.getResult());
        response.setDraw(draw);
        response.setRecordsFiltered(result.getTotal());
        response.setRecordsTotal(result.getTotal());
        return response;
    }

    /**
     * 完成预约接口
     * @param houseId 房屋id
     */
    @PostMapping("api/admin/house/subscribe/finish")
    public APIResponse finishSubscribe(@RequestParam(value = "house_id") Long houseId) {
        //参数检测
        if (houseId < 1) {
            return APIResponse.ofStatus(APIResponse.Status.BAD_REQUEST);
        }
        //完成预约
        ServiceResult result = houseService.finishSubscribe(houseId);
        //响应数据
        if (result.isSuccess()) {
            return APIResponse.ofSuccess();
        }
        return APIResponse.ofMessage(APIResponse.Status.BAD_REQUEST.getCode(), result.getMessage());
    }
}

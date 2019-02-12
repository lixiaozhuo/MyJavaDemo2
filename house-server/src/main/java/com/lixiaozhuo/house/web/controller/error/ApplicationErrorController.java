package com.lixiaozhuo.house.web.controller.error;


import com.lixiaozhuo.house.base.response.APIResponse;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 异常处理
 */
@Controller
public class ApplicationErrorController implements ErrorController {
    private static final String ERROR_PATH = "/error";

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

    /**
     * Web页面错误跳转
     */
    @RequestMapping(value = ERROR_PATH, produces = "text/html")
    public String errorPageHandler(HttpServletRequest request){
        APIResponse.Status status = getStatus(request);
        switch(status.getCode()){
            case 403:
                return "403";
            case 404:
                return "404";
            case 500:
                return "500";
        }
        return "index";
    }

    /**
     * 接口错误处理
     */
    @RequestMapping(value = ERROR_PATH)
    @ResponseBody
    public APIResponse errorAPIHandler(HttpServletRequest request){
        //获取响应码
        return APIResponse.ofStatus(getStatus(request));
    }

    /**
     * 获取请求的响应码
     */
    private APIResponse.Status getStatus(HttpServletRequest request) {
        Integer status = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if(status != null){
            return APIResponse.of(status);
        }
        return APIResponse.Status.INTERNAL_SERVER_ERROR;
    }


}

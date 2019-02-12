import Vue from 'vue'
import qs from "qs"
import axios from "axios"

//Axios配置
Vue.prototype.HOST = "/api";

// 添加请求拦截器
axios.interceptors.request.use(function (request) {
    // 发送请求之前
    if(request.method === "post"){
        request.data = qs.stringify(request.data);
    }
    return request;
}, function (error) {
    // 请求错误
    return Promise.reject(error);
});

// 添加响应拦截器
axios.interceptors.response.use(function (response) {
    // 响应数据
    /*if(!response.data){
        return {
            msg:"数据返回不合理"
        }
    }*/
    return response;
}, function (error) {
    // 响应错误
    return Promise.reject(error);
});

export default axios;
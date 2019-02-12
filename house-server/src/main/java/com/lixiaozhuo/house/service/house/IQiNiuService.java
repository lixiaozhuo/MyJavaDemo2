package com.lixiaozhuo.house.service.house;

import java.io.File;
import java.io.InputStream;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;

/**
 * 七牛云服务接口
 */
public interface IQiNiuService {
    /**
     * 上传文件
     * @param file 文件
     */
    Response uploadFile(File file) throws QiniuException;

    /**
     * 上传文件流
     * @param inputStream 文件流
     */
    Response uploadFile(InputStream inputStream) throws QiniuException;

    /**
     * 删除文件
     * @param key 文件key
     */
    Response delete(String key) throws QiniuException;
}

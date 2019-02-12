package com.lixiaozhuo.house.service.house.impl;

import java.io.File;
import java.io.InputStream;

import com.lixiaozhuo.house.service.house.IQiNiuService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;

/**
 * 七牛云服务
 */
@Service
public class QiNiuServiceImpl implements IQiNiuService, InitializingBean {
    //七牛上传工具
    @Autowired
    private UploadManager uploadManager;
    //七牛空间管理
    @Autowired
    private BucketManager bucketManager;
    //认证信息
    @Autowired
    private Auth auth;

    //存储空间
    @Value("${qiniu.bucket}")
    private String bucket;

    //上传策略
    private StringMap putPolicy;

    @Override
    public void afterPropertiesSet() throws Exception {
        //初始化上传策略
        this.putPolicy = new StringMap();
        putPolicy.put("returnBody", "{\"key\":\"$(key)\",\"hash\":\"$(etag)\",\"bucket\":\"$(bucket)\"," +
                "\"width\":$(imageInfo.width), \"height\":${imageInfo.height}}");
    }


    /**
     * 上传文件
     */
    @Override
    public Response uploadFile(File file) throws QiniuException {
        Response response = this.uploadManager.put(file, null, getUploadToken());
        //上传出错后重试3次
        int retry = 0;
        while (response.needRetry() && retry < 3) {
            response = this.uploadManager.put(file, null, getUploadToken());
            retry++;
        }
        return response;
    }

    /**
     * 上传文件流
     */
    @Override
    public Response uploadFile(InputStream inputStream) throws QiniuException {
        Response response = this.uploadManager.put(inputStream, null, getUploadToken(), null, null);
        //上传出错后重试3次
        int retry = 0;
        while (response.needRetry() && retry < 3) {
            response = this.uploadManager.put(inputStream, null, getUploadToken(), null, null);
            retry++;
        }
        return response;
    }

    /**
     * 删除文件
     */
    @Override
    public Response delete(String key) throws QiniuException {
        Response response = bucketManager.delete(this.bucket, key);
        //删除出错后重试3次
        int retry = 0;
        while (response.needRetry() && retry++ < 3) {
            response = bucketManager.delete(bucket, key);
        }
        return response;
    }



    /**
     * 获取上传凭证
     */
    private String getUploadToken() {
        return this.auth.uploadToken(bucket, null, 3600, putPolicy);
    }
}

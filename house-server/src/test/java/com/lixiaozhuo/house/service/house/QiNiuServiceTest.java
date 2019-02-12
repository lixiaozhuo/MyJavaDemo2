package com.lixiaozhuo.house.service.house;

import com.lixiaozhuo.house.ApplicationTests;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;

/**
 * 七牛业务测试
 */
public class QiNiuServiceTest extends ApplicationTests {
    //七牛服务
    @Autowired
    private IQiNiuService qiNiuService;

    @Test
    public void testUploadFile() {
        String fileName = "";
        File file = new File(fileName);
        Assert.assertTrue(file.exists());
        try {
            //上传文件
            Response response = qiNiuService.uploadFile(file);
            Assert.assertTrue(response.isOK());
        } catch (QiniuException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDelete() {
        String key = "FhRdUkZQvlByVYno45zVT6DbDS60";
        try {
            //删除文件
            Response response = qiNiuService.delete(key);
            Assert.assertTrue(response.isOK());
        } catch (QiniuException e) {
            e.printStackTrace();
        }
    }
}

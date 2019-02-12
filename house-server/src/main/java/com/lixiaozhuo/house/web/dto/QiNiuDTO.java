package com.lixiaozhuo.house.web.dto;

import java.io.Serializable;

/**
 * 七牛云结果集
 */
public final class QiNiuDTO implements Serializable {
    //key
    public String key;
    //文件hash
    public String hash;
    //文件所在空间
    public String bucket;
    //宽
    public int width;
    //高
    public int height;
}

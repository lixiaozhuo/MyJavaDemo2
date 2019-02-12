package com.lixiaozhuo.house.web.form;

/**
 * 图片表单数据
 */
public class PhotoForm {
    //文件名
    private String path;
    //宽
    private int width;
    //高
    private int height;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}

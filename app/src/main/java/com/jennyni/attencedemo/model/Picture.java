package com.jennyni.attencedemo.model;

public class Picture {
    private String title;                               //定义字符串，表示图像标题
    private int imageId;                               //定义int变量，表示图像的二进制值
    //默认构造函数
    public Picture(){
        super();
    }
    //定义有参构造函数
    public Picture(String title , int imageId) {
        super();
        this.title = title;                             //为图像标题赋值
        this.imageId = imageId;                         //为图像的二进制值赋值
    }
    //定义图片标题的可读属性
    public String getTitle() {
        return title;
    }
    //定义图片标题的可写属性
    public void setTitle(String title) {
        this.title = title;
    }
    //定义图片二进制值的可读属性
    public int getImageId() {
        return imageId;
    }
    //定义图片二进制值的可写属性
    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}

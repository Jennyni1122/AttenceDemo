package com.jennyni.attencedemo.db;

import java.io.Serializable;

/**
 * 考勤记录表
 * Created by Jenny on 2019/5/31.
 */

public class Tb_record implements Serializable {
    /**
     * \
     * id 编号   //时间戳+4位随机数
     * attNo    考勤序号  //由课程号和学号组成,xx_xx系列
     * attResult    考勤结果
     * arrData      考勤时间
     */

    /**
     *
     * @param courseCode 课程号
     * @param courCode 学号
     * @return
     */
    public static String getAttNo(String courseCode, String courCode) {
        return courseCode + "_" + courCode;
    }

    private String id;
    private String attNo;
    private String attResult;
    private String arrData;

    public Tb_record() {
        super();
    }

    public Tb_record(String id, String attNo, String attResult, String arrData) {
        this.id = id;
        this.attNo = attNo;
        this.attResult = attResult;
        this.arrData = arrData;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAttNo() {
        return attNo;
    }

    public void setAttNo(String attNo) {
        this.attNo = attNo;
    }

    public String getAttResult() {
        return attResult;
    }

    public void setAttResult(String attResult) {
        this.attResult = attResult;
    }

    public String getArrData() {
        return arrData;
    }

    public void setArrData(String arrData) {
        this.arrData = arrData;
    }
}

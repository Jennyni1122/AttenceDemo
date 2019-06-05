package com.jennyni.attencedemo.db;

/**
 * 考勤记录表
 * Created by Jenny on 2019/5/31.
 */

public class Tb_record {
    /**
     * attNo    考勤序号  //由课程号和学号组成
     * attResult    考勤结果
     * arrData      考勤时间
     */
    private String attNo;
    private String attResult;
    private String arrData;

    public Tb_record() {
        super();
    }

    public Tb_record(String attNo, String attResult, String arrData) {
        this.attNo = attNo;
        this.attResult = attResult;
        this.arrData = arrData;
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

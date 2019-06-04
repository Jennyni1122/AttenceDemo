package com.jennyni.attencedemo.db;

import java.io.Serializable;

/**
 * 学生信息表
 * Created by Jenny on 2019/5/31.
 */

public class Tb_student implements Serializable {
    /**
     * courcode  学号
     * name      姓名
     * mac       蓝牙地址
     * classNo   课程编号
     * major     专业(班级)
     * academy   学院
     * phone     电话
     * assemail  辅导员邮箱
     * courseCode  课程
     */

    private String courcode;
    private String name;
    private String mac;
    private String classNo;
    private String major;
    private String academy;
    private String phone;
    private String assemail;
    private String courseCode;


    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public Tb_student() {
        super();
    }

    public Tb_student(String courcode, String name, String mac, String classNo, String major, String academy, String phone,
                      String assemail,String courseCode) {
        this.courcode = courcode;
        this.name = name;
        this.mac = mac;
        this.classNo = classNo;
        this.major = major;
        this.academy = academy;
        this.phone = phone;
        this.assemail = assemail;
        this.courseCode = courseCode;

    }


    public String getCourcode() {
        return courcode;
    }

    public void setCourcode(String courcode) {
        this.courcode = courcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getClassNo() {
        return classNo;
    }

    public void setClassNo(String classNo) {
        this.classNo = classNo;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getAcademy() {
        return academy;
    }

    public void setAcademy(String academy) {
        this.academy = academy;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAssemail() {
        return assemail;
    }

    public void setAssemail(String assemail) {
        this.assemail = assemail;
    }
}

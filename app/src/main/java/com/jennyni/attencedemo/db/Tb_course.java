package com.jennyni.attencedemo.db;

/**
 *课程信息表
 * Created by Jenny on 2019/5/31.
 */

public class Tb_course {

    /**
     * courcode  课程编号
     * courname  课程名称
     * courtime  上课时间
     * courplace  上课地点
     * teacher    任课老师
     */

    private String courcode;
    private String courname;
    private String courtime;
    private String courplace;
    private String teacher;

    public Tb_course(){
        super();
    }

    public Tb_course(String courcode,String courname,String courtime,String courplace,String teacher){
        super();

        this.courcode = courcode;
        this.courname = courname;
        this.courtime = courtime;
        this.courplace = courplace;
        this.teacher = teacher;
    }

    public String getCourcode() {
        return courcode;
    }

    public void setCourcode(String courcode) {
        this.courcode = courcode;
    }

    public String getCourname() {
        return courname;
    }

    public void setCourname(String courname) {
        this.courname = courname;
    }

    public String getCourtime() {
        return courtime;
    }

    public void setCourtime(String courtime) {
        this.courtime = courtime;
    }

    public String getCourplace() {
        return courplace;
    }

    public void setCourplace(String courplace) {
        this.courplace = courplace;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }
}

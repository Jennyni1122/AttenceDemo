package com.jennyni.attencedemo.db;

/**
 * 考勤统计表
 * Created by Jenny on 2019/5/31.
 */

public class Tb_stas {
    /**
     * attNo   考勤序号
     * stuNo     学号
     * courcode  课程编号
     * sumTm     总次数
     * realTm    实到次数
     * absTm     旷课次数
     * latTm     迟到次数
     * score     平时成绩
     */

    private String attNo;
    private String stuNo;
    private String courcode;
    private int sumTm;
    private int realTm;
    private int absTm;
    private int latTm;
    private double score;

    public Tb_stas(){
        super();
    }

    public Tb_stas(String attNo,String stuNo,String courcode,int sumTm,int realTm,int absTm,int latTm,double score){
        super();

        this.attNo = attNo;
        this.stuNo = stuNo;
        this.courcode = courcode;
        this.sumTm = sumTm;
        this.realTm = realTm;
        this.absTm = absTm;
        this.latTm = latTm;
        this.score = score;
    }

    public String getAttNo() {
        return attNo;
    }

    public void setAttNo(String attNo) {
        this.attNo = attNo;
    }

    public String getStuNo() {
        return stuNo;
    }

    public void setStuNo(String stuNo) {
        this.stuNo = stuNo;
    }

    public String getCourcode() {
        return courcode;
    }

    public void setCourcode(String courcode) {
        this.courcode = courcode;
    }

    public int getSumTm() {
        return sumTm;
    }

    public void setSumTm(int sumTm) {
        this.sumTm = sumTm;
    }

    public int getRealTm() {
        return realTm;
    }

    public void setRealTm(int realTm) {
        this.realTm = realTm;
    }

    public int getAbsTm() {
        return absTm;
    }

    public void setAbsTm(int absTm) {
        this.absTm = absTm;
    }

    public int getLatTm() {
        return latTm;
    }

    public void setLatTm(int latTm) {
        this.latTm = latTm;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}

package com.jennyni.attencedemo.db;

/**
 * 扣分信息表
 * Created by Jenny on 2019/5/31.
 */

public class Tb_score {
    /**
     * scoreset 扣分值
     */

    private double scoreset;

    public Tb_score(){
        super();
    }

    public Tb_score (double scoreset){
        super();
        this.scoreset = scoreset;
    }

    public double getScoreset() {
        return scoreset;
    }

    public void setScoreset(double scoreset) {
        this.scoreset = scoreset;
    }

}

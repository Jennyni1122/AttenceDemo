package com.jennyni.attencedemo.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 *
 * Created by Jenny on 2019/5/31.
 */

public class DBOpenHelper extends SQLiteOpenHelper {

    private static final int VERSION  = 1;               //定义数据库版本号
    private static final String DBNAME = "account.db";  //定义数据库名

    //重写基类的构造函数
    public DBOpenHelper(Context context) {
        super(context, DBNAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //创建课程信息表
        db.execSQL("create table tb_course(" +
                "courcode varchar(20) primary key autoincrement," +
                "courname varchar(20)," +
                "courtime varchar(50),"+
                "courplace varchar(50)," +
                "teacher varchar(10)" +
                ")");

        //创建学生信息表
        db.execSQL("create table tb_student(" +
                "courcode varchar(20) primary key autoincrement," +
                "name varchar(10)," +
                "mac varchar(10),"+
                "classNo varchar(20)," +
                "major varchar(20)," +
                "academy varchar(20)," +
                "phone varchar(20),"+
                "assemail varchar(20)" +
                ")");

        //创建考勤统计表
        db.execSQL("create table tb_stas(" +
                "attNo varchar(50) primary key autoincrement," +
                "stuNo varchar(20)," +
                "courcode varchar(20),"+
                "sumTm integer," +
                "realTm integer," +
                "absTm integer,"+
                "latTm integer," +
                "score decimal" +
                ")");

        //创建考勤记录表
        db.execSQL("create table tb_record(" +
                "id varchar(20) primary key autoincrement," +
                "attNo varchar(50) ," +
                "attResult varchar(50),"+
                "arrData varchar(50)" +
                ")");

        //创建扣分信息表
        db.execSQL("create table tb_score(" +
                "scoreset decimal primary key autoincrement" +
                ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

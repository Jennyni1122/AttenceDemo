package com.jennyni.attencedemo.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Jenny on 2019/5/31.
 */

public class DBOpenHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;               //定义数据库版本号
    private static final String DBNAME = "account.db";  //定义数据库名
    public static final String TB_COURSE = "tb_course";
    public static final String TB_STUDENT = "tb_student";
    public static final String TB_STAS = "tb_stas";
    public static final String TB_RECORD = "tb_record";
    public static final String TB_SCORE = "tb_record";

    //重写基类的构造函数
    public DBOpenHelper(Context context) {
        super(context, DBNAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //创建课程信息表
        String str = "create table tb_course(" +
                "id SMALLINT UNSIGNED AUTO_INCREMENT PRIMARY KEY," +
                "courcode varchar(20)," +
                "courname varchar(20)," +
                "courtime varchar(50)," +
                "courplace varchar(50)," +
                "teacher varchar(10)" +
                ")";
        Log.e("onCreate: ", str);
        db.execSQL(str);

        str = "create table tb_student(" +
                "id SMALLINT UNSIGNED AUTO_INCREMENT PRIMARY KEY," +
                "courcode varchar(20)," +
                "name varchar(10)," +
                "mac varchar(10)," +
                "classNo varchar(20)," +
                "major varchar(20)," +
                "academy varchar(20)," +
                "phone varchar(20)," +
                "assemail varchar(20)" +
                ")";

        //创建学生信息表
        Log.e("onCreate: ", str);
        db.execSQL(str);

        //创建考勤统计表
        str = "create table tb_stas(" +
                "id SMALLINT UNSIGNED AUTO_INCREMENT PRIMARY KEY," +
                "attNo varchar(50)," +
                "stuNo varchar(20)," +
                "courcode varchar(20)," +
                "sumTm integer," +
                "realTm integer," +
                "absTm integer," +
                "latTm integer," +
                "score double" +
                ")";
        Log.e("onCreate: ", str);
        db.execSQL(str);

        //创建考勤记录表
        str = "create table tb_record(" +
                "id varchar(50)," +
                "attNo varchar(50) ," +
                "attResult varchar(50)," +
                "arrData varchar(50)" +
                ")";
        Log.e("onCreate: ", str);
        db.execSQL(str);

        //创建扣分信息表
        str = "create table tb_score(" +
                "id SMALLINT UNSIGNED AUTO_INCREMENT PRIMARY KEY," +
                "scoreset int" +
                ")";
        Log.e("onCreate: ", str);
        db.execSQL(str);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

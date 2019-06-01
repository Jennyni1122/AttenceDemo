package com.jennyni.attencedemo.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.jennyni.attencedemo.db.DBOpenHelper;
import com.jennyni.attencedemo.db.Tb_student;

/**
 *  主要用来对学生信息进行管理，
 * 包括学生信息 的添加、修改、删除、查询及获取最大编号、总记录数等功能
 * Created by Jenny on 2019/6/1.
 */

public class StudentDAO {
    private DBOpenHelper helper;                        //创建DBOpenHelper对象
    private SQLiteDatabase db;                          //创建SQLiteDatabase对象

    public StudentDAO(Context context){               //定义构造函数
        helper = new DBOpenHelper(context);             //初始化DBOpenHelper对象
    }

    /**
     * 添加学生信息
     * @param tb_student
     */
//    public void addStudentInfo(Tb_student tb_student){
//        db = helper.getWritableDatabase();              //初始化SQLiteDatabase对象
//        //执行添加支出信息操作
//        db.execSQL("insert into tb_student (courcode,name,mac,classNo,academy,phone,assemail) values(?,?,?,?,?,?,?)",
//                new Object[]{
//                        tb_student.getCourcode(),
//                        tb_student.getName(),
//                        tb_student.getMac(),
//                        tb_student.getClassNo(),
//                        tb_student.getAcademy(),
//                        tb_student.getPhone(),
//                        tb_student.getAssemail()
//        });
//    }

//    /**
//     * 更新学生信息
//     * 主要功能是根据指定的编号修改收入信息
//     * @param tb_student
//     */
//    public void updateStudentInfo(Tb_student tb_student){
//        db = helper.getWritableDatabase();              //初始化SQLiteDatabase对象
//        //执行修改信息操作
//        db.execSQL("update tb_student set name = ?,mac = ?,classNo = ?,major = ?, academy = ?, phone = ?,assemail = ? where courcode = ?",
//                new Object[]{
//                        tb_student.getName(),
//                        tb_student.getMac(),
//                        tb_student.getClassNo(),
//                        tb_student.getMajor(),
//                        tb_student.getAcademy(),
//                        tb_student.getPhone(),
//                        tb_student.getAssemail(),
//                        tb_student.getCourcode()
//        });
//    }

    /**
     * 查找学生信息
     * 主要功能是根据指定的编号查找收入信息
     * @param courcode
     * @return
     */
//    public Tb_student findStudentInfo(int courcode){
//        db = helper.getWritableDatabase();                  //初始化SQLiteDatabase对象
//        Cursor cursor  = db.rawQuery("selete courcode,name,mac,classNo,major,academy,phone,assemail from tb_student where courcode = ? ",new String[]{
//                String.valueOf(courcode)                  //根据编号查找收入信息，并存储到Cursor类中
//        });
//        if (cursor.moveToNext()){                   //遍历查找到的收入信息
//            //将遍历到的收入信息存储到Tb_inaccount类中
//
//            return new Tb_student(cursor.getInt(cursor.getColumnIndex("courcode")),
//                    cursor.getString(cursor.getColumnIndex("name")),
//                    cursor.getString(cursor.getColumnIndex("mac")),
//                    cursor.getString(cursor.getColumnIndex("classNo")),
//                    cursor.getString(cursor.getColumnIndex("major")),
//                    cursor.getString(cursor.getColumnIndex("academy")),
//                    cursor.getString(cursor.getColumnIndex("phone")),
//                    cursor.getString(cursor.getColumnIndex("assemail"))
//            );
//        }
//        return null;                                        //如果没有信息，返回null
//    }

    /**
     * 删除收入信息
     * 主要功能是根据指定的一系列编号删除收入信息
     * @param courcodes
     */
    public void deteleStudentInfo(Integer... courcodes){
        if (courcodes.length > 0){                                        //判断是否存在要删除的id
            StringBuffer sb = new StringBuffer();                   //创建StringBuffer对象
            for(int i = 0; i < courcodes.length;i++){                    //遍历要删除的id集合
                sb.append('?').append(',');                         //将删除条件添加到StringBuffer对象中
            }
            sb.deleteCharAt(sb.length() - 1 );                       //去掉最后一个“，”字符
            db.execSQL("delete from tb_student where courcode in ("+sb+")",(Object[])courcodes);
        }
    }

    /**
     * 获取学生信息最大编号
     * @return 返回值为获取到的最大编号
     */
    public int getMaxId(){
        db = helper.getWritableDatabase();                  //初始化SQLiteDatabase对象
        Cursor cursor = db.rawQuery("select max(courcode) from tb_student",null);//获取收入信息表中的最大编号
        //访问Cursor中的最后一条数据
        while(cursor.moveToLast()){
            return cursor.getInt(0);                    //获取访问到的数据，即最大编号
        }
        return 0;                                       //如果没有数据，则返回0
    }

}

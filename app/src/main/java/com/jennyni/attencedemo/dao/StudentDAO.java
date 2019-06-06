package com.jennyni.attencedemo.dao;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.jennyni.attencedemo.contentprovider.ProviderContract;
import com.jennyni.attencedemo.db.DBOpenHelper;
import com.jennyni.attencedemo.db.Tb_student;

import java.util.ArrayList;
import java.util.List;

/**
 * 主要用来对学生信息进行管理，
 * 包括学生信息 的添加、修改、删除、查询及获取最大编号、总记录数等功能
 * Created by Jenny on 2019/6/1.
 */

public class StudentDAO {

    private ContentResolver contentResolver;

    public StudentDAO(ContentResolver contentResolver) {               //定义构造函数
        this.contentResolver = contentResolver;          //初始化DBOpenHelper对象
    }

    /**
     * 添加学生信息
     *
     * @param student
     */
    public void addStudentInfo(Tb_student student) {
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
        /**
         * "id SMALLINT UNSIGNED AUTO_INCREMENT PRIMARY KEY," +
         "courcode varchar(20)," +
         "name varchar(10)," +
         "mac varchar(10)," +
         "classNo varchar(20)," +
         "major varchar(20)," +
         "academy varchar(20)," +
         "phone varchar(20)," +
         "assemail varchar(20)" +
         */

        ContentValues contentValues = new ContentValues();
        contentValues.put(ProviderContract.StudentEntry.COLUMN_COURCODE, student.getCourcode());
        contentValues.put(ProviderContract.StudentEntry.COLUMN_NAME, student.getName());
        contentValues.put(ProviderContract.StudentEntry.COLUMN_MAC, student.getMac());
        contentValues.put(ProviderContract.StudentEntry.COLUMN_CLASSNO, student.getClassNo());
        contentValues.put(ProviderContract.StudentEntry.COLUMN_MAJOR, student.getMajor());
        contentValues.put(ProviderContract.StudentEntry.COLUMN_ACADEMY, student.getAcademy());
        contentValues.put(ProviderContract.StudentEntry.COLUMN_PHONE, student.getPhone());
        contentValues.put(ProviderContract.StudentEntry.COLUMN_ASSEMAIL, student.getAssemail());
        contentValues.put(ProviderContract.StudentEntry.COLUMN_COURSECODE, student.getCourseCode());
        contentResolver.insert(ProviderContract.StudentEntry.CONTENT_URI, contentValues);

    }

    /**
     * 更新学生信息
     * 主要功能是根据指定的编号修改收入信息
     *
     * @param student
     */
    public void updateStudentInfo(Tb_student student) {
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
        ContentValues contentValues = new ContentValues();
        contentValues.put(ProviderContract.StudentEntry.COLUMN_COURCODE, student.getCourcode());
        contentValues.put(ProviderContract.StudentEntry.COLUMN_NAME, student.getName());
        contentValues.put(ProviderContract.StudentEntry.COLUMN_MAC, student.getMac());
        contentValues.put(ProviderContract.StudentEntry.COLUMN_CLASSNO, student.getClassNo());
        contentValues.put(ProviderContract.StudentEntry.COLUMN_MAJOR, student.getMajor());
        contentValues.put(ProviderContract.StudentEntry.COLUMN_ACADEMY, student.getAcademy());
        contentValues.put(ProviderContract.StudentEntry.COLUMN_PHONE, student.getPhone());
        contentValues.put(ProviderContract.StudentEntry.COLUMN_ASSEMAIL, student.getAssemail());
        contentValues.put(ProviderContract.StudentEntry.COLUMN_COURSECODE, student.getCourseCode());
        contentResolver.update(ProviderContract.StudentEntry.CONTENT_URI, contentValues, ProviderContract.StudentEntry.COLUMN_COURCODE + "=?", new String[]{student.getCourcode()});

    }

    /**
     * 查找学生信息
     * 主要功能是根据指定的编号查找收入信息
     *
     * @param courcode
     * @return
     */
    public Tb_student findStudentInfo(int courcode) {
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

        Cursor cursor = contentResolver.query(ProviderContract.StudentEntry.CONTENT_URI, null, ProviderContract.StudentEntry.COLUMN_COURCODE + "=?", new String[]{courcode + ""}, null);
        if (cursor.moveToNext()) {
            return new Tb_student(cursor.getString(cursor.getColumnIndex("courcode")),
                    cursor.getString(cursor.getColumnIndex("name")),
                    cursor.getString(cursor.getColumnIndex("mac")),
                    cursor.getString(cursor.getColumnIndex("classNo")),
                    cursor.getString(cursor.getColumnIndex("major")),
                    cursor.getString(cursor.getColumnIndex("academy")),
                    cursor.getString(cursor.getColumnIndex("phone")),
                    cursor.getString(cursor.getColumnIndex("assemail")),
                    cursor.getString(cursor.getColumnIndex("courseCode"))
            );
        }
        return null;
    }

    /**
     * 删除收入信息
     * 主要功能是根据指定的一系列编号删除收入信息
     *
     * @param courcodes
     */
    public void deteleStudentInfo(String... courcodes) {
//        if (courcodes.length > 0) {                                        //判断是否存在要删除的id
//            StringBuffer sb = new StringBuffer();                   //创建StringBuffer对象
//            for (int i = 0; i < courcodes.length; i++) {                    //遍历要删除的id集合
//                sb.append('?').append(',');                         //将删除条件添加到StringBuffer对象中
//            }
//            sb.deleteCharAt(sb.length() - 1);                       //去掉最后一个“，”字符
//            db.execSQL("delete from tb_student where courcode in (" + sb + ")", (Object[]) courcodes);
//        }

        contentResolver.delete(ProviderContract.StudentEntry.CONTENT_URI, ProviderContract.StudentEntry.COLUMN_COURCODE + "=?", courcodes);

    }

    /**
     * 获取学生信息最大编号
     *
     * @return 返回值为获取到的最大编号
     */
    public int getMaxId() {
//        db = helper.getWritableDatabase();                  //初始化SQLiteDatabase对象
//        Cursor cursor = db.rawQuery("select max(courcode) from tb_student", null);//获取收入信息表中的最大编号
//        //访问Cursor中的最后一条数据
//        while (cursor.moveToLast()) {
//            return cursor.getInt(0);                    //获取访问到的数据，即最大编号
//        }
        Cursor cursor = contentResolver.query(ProviderContract.StudentEntry.CONTENT_URI, new String[]{ProviderContract.StudentEntry.COLUMN_COURCODE}, null, null, " ORDER BY " + ProviderContract.StudentEntry.COLUMN_COURCODE + " asc");
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            return cursor.getInt(0);
        }

        return 1;                                       //如果没有数据，则返回1
    }

    public List<Tb_student> queryAll() {
        List<Tb_student> list = new ArrayList<>();
        Cursor cursor = contentResolver.query(ProviderContract.StudentEntry.CONTENT_URI, null, null, null, null);

        while (cursor.moveToNext()) {
            Tb_student tb_student = new Tb_student(cursor.getString(cursor.getColumnIndex("courcode")),
                    cursor.getString(cursor.getColumnIndex("name")),
                    cursor.getString(cursor.getColumnIndex("mac")),
                    cursor.getString(cursor.getColumnIndex("classNo")),
                    cursor.getString(cursor.getColumnIndex("major")),
                    cursor.getString(cursor.getColumnIndex("academy")),
                    cursor.getString(cursor.getColumnIndex("phone")),
                    cursor.getString(cursor.getColumnIndex("assemail")),
                    cursor.getString(cursor.getColumnIndex("courseCode"))
            );
            list.add(tb_student);
        }
        return list;
    }

    public List<Tb_student> querByCourseCode(String courseCode) {
        List<Tb_student> list = new ArrayList<>();
        Cursor cursor = contentResolver.query(ProviderContract.StudentEntry.CONTENT_URI, null, ProviderContract.StudentEntry.COLUMN_COURSECODE+"=?", new String[]{courseCode}, null);

        while (cursor.moveToNext()) {
            Tb_student tb_student = new Tb_student(cursor.getString(cursor.getColumnIndex("courcode")),
                    cursor.getString(cursor.getColumnIndex("name")),
                    cursor.getString(cursor.getColumnIndex("mac")),
                    cursor.getString(cursor.getColumnIndex("classNo")),
                    cursor.getString(cursor.getColumnIndex("major")),
                    cursor.getString(cursor.getColumnIndex("academy")),
                    cursor.getString(cursor.getColumnIndex("phone")),
                    cursor.getString(cursor.getColumnIndex("assemail")),
                    cursor.getString(cursor.getColumnIndex("courseCode"))
            );
            list.add(tb_student);
        }
        return list;
    }


    public List<Tb_student> querByCourCode(String courCode) {
        List<Tb_student> list = new ArrayList<>();
        Cursor cursor = contentResolver.query(ProviderContract.StudentEntry.CONTENT_URI, null, ProviderContract.StudentEntry.COLUMN_COURCODE+"=?", new String[]{courCode}, null);
        while (cursor.moveToNext()) {
            Tb_student tb_student = new Tb_student(cursor.getString(cursor.getColumnIndex("courcode")),
                    cursor.getString(cursor.getColumnIndex("name")),
                    cursor.getString(cursor.getColumnIndex("mac")),
                    cursor.getString(cursor.getColumnIndex("classNo")),
                    cursor.getString(cursor.getColumnIndex("major")),
                    cursor.getString(cursor.getColumnIndex("academy")),
                    cursor.getString(cursor.getColumnIndex("phone")),
                    cursor.getString(cursor.getColumnIndex("assemail")),
                    cursor.getString(cursor.getColumnIndex("courseCode"))
            );
            list.add(tb_student);
        }
        return list;
    }


    /**
     * 判断是否已经存在该学号
     *
     * @param courCode
     * @return
     */
    public boolean isHasCourCode(String courCode,String courseCode) {
        Cursor cursor = contentResolver.query(ProviderContract.StudentEntry.CONTENT_URI, null, ProviderContract.StudentEntry.COLUMN_COURCODE + "=?", new String[]{courCode + ""}, null);

        while (cursor.moveToNext()){
            String courseCode1 = cursor.getString(cursor.getColumnIndex(ProviderContract.StudentEntry.COLUMN_COURSECODE));
            if (courseCode.endsWith(courseCode1)){
                return true;
            }
        }

        return false;
    }
}

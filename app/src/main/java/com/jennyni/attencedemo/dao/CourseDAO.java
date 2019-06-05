package com.jennyni.attencedemo.dao;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import com.jennyni.attencedemo.contentprovider.ProviderContract;
import com.jennyni.attencedemo.db.Tb_course;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jenny on 2019/6/1.
 */

public class CourseDAO {

    private ContentResolver contentResolver;

    public CourseDAO(ContentResolver contentResolver) {
        this.contentResolver = contentResolver;
    }


    public void insert(Tb_course course) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ProviderContract.CourseEntry.COLUMN_COURCODE, course.getCourcode());
        contentValues.put(ProviderContract.CourseEntry.COLUMN_COURNAME, course.getCourname());
        contentValues.put(ProviderContract.CourseEntry.COLUMN_COURPLACE, course.getCourplace());
        contentValues.put(ProviderContract.CourseEntry.COLUMN_COURTIME, course.getCourtime());
        contentValues.put(ProviderContract.CourseEntry.COLUMN_TEACHER, course.getTeacher());
        contentResolver.insert(ProviderContract.CourseEntry.CONTENT_URI, contentValues);
    }


    public void updateByCourCode(Tb_course course) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ProviderContract.CourseEntry.COLUMN_COURCODE, course.getCourcode());
        contentValues.put(ProviderContract.CourseEntry.COLUMN_COURNAME, course.getCourname());
        contentValues.put(ProviderContract.CourseEntry.COLUMN_COURPLACE, course.getCourplace());
        contentValues.put(ProviderContract.CourseEntry.COLUMN_COURTIME, course.getCourtime());
        contentValues.put(ProviderContract.CourseEntry.COLUMN_TEACHER, course.getTeacher());
        contentResolver.update(ProviderContract.CourseEntry.CONTENT_URI, contentValues, ProviderContract.CourseEntry.COLUMN_COURCODE + "=?", new String[]{course.getCourcode()});
    }


    public void deleteByCourCode(Tb_course course) {

        contentResolver.delete(ProviderContract.CourseEntry.CONTENT_URI, ProviderContract.CourseEntry.COLUMN_COURCODE + "=?", new String[]{course.getCourcode()});
    }

    public List<Tb_course> queryAll() {
        Cursor cursor = contentResolver.query(ProviderContract.CourseEntry.CONTENT_URI, null, null, null, null);
        List<Tb_course> list = new ArrayList<>();
        Log.e("queryAll: ", cursor.getCount() + "");

        while (cursor.moveToNext()) {
            Tb_course tb_course = new Tb_course(
                    cursor.getString(cursor.getColumnIndex(ProviderContract.CourseEntry.COLUMN_COURCODE)),
                    cursor.getString(cursor.getColumnIndex(ProviderContract.CourseEntry.COLUMN_COURNAME)),
                    cursor.getString(cursor.getColumnIndex(ProviderContract.CourseEntry.COLUMN_COURTIME)),
                    cursor.getString(cursor.getColumnIndex(ProviderContract.CourseEntry.COLUMN_COURPLACE)),
                    cursor.getString(cursor.getColumnIndex(ProviderContract.CourseEntry.COLUMN_TEACHER))
            );
            list.add(tb_course);
        }
        return list;
    }


    public boolean isHasCourseCode(String courseCode) {
        Cursor cursor = contentResolver.query(ProviderContract.CourseEntry.CONTENT_URI, null, ProviderContract.CourseEntry.COLUMN_COURCODE, new String[]{courseCode}, null);
        return cursor.getCount() != 0;
    }

}

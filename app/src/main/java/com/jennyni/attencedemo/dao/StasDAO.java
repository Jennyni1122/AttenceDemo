package com.jennyni.attencedemo.dao;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;

import com.jennyni.attencedemo.contentprovider.ProviderContract;
import com.jennyni.attencedemo.db.Tb_stas;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jenny on 2019/6/1.
 */

public class StasDAO {


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
    private ContentResolver contentResolver;


    public StasDAO(ContentResolver contentResolver) {
        this.contentResolver = contentResolver;
    }


    public void insert(Tb_stas stas) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ProviderContract.StasEntry.COLUMN_ATTNO, stas.getAttNo());
        contentValues.put(ProviderContract.StasEntry.COLUMN_STUNO, stas.getStuNo());
        contentValues.put(ProviderContract.StasEntry.COLUMN_COURCODE, stas.getcoursecode());
        contentValues.put(ProviderContract.StasEntry.COLUMN_SUMTM, stas.getSumTm());
        contentValues.put(ProviderContract.StasEntry.COLUMN_REALTM, stas.getRealTm());
        contentValues.put(ProviderContract.StasEntry.COLUMN_ABSTM, stas.getAbsTm());
        contentValues.put(ProviderContract.StasEntry.COLUMN_LATTM, stas.getLatTm());
        contentValues.put(ProviderContract.StasEntry.COLUMN_SCORE, stas.getScore());
        contentResolver.insert(ProviderContract.StasEntry.CONTENT_URI, contentValues);
    }


    public void update(Tb_stas stas) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ProviderContract.StasEntry.COLUMN_ATTNO, stas.getAttNo());
        contentValues.put(ProviderContract.StasEntry.COLUMN_STUNO, stas.getStuNo());
        contentValues.put(ProviderContract.StasEntry.COLUMN_COURCODE, stas.getcoursecode());
        contentValues.put(ProviderContract.StasEntry.COLUMN_SUMTM, stas.getSumTm());
        contentValues.put(ProviderContract.StasEntry.COLUMN_REALTM, stas.getRealTm());
        contentValues.put(ProviderContract.StasEntry.COLUMN_ABSTM, stas.getAbsTm());
        contentValues.put(ProviderContract.StasEntry.COLUMN_LATTM, stas.getLatTm());
        contentValues.put(ProviderContract.StasEntry.COLUMN_SCORE, stas.getScore());
        contentResolver.update(ProviderContract.StasEntry.CONTENT_URI, contentValues, ProviderContract.StasEntry.COLUMN_ATTNO, new String[]{stas.getAttNo()});
    }


    public void delete(String attNo) {
        contentResolver.delete(ProviderContract.StasEntry.CONTENT_URI, ProviderContract.StasEntry.COLUMN_ATTNO, new String[]{attNo});
    }


    /**
     * 统计全部迟到次数等
     */
    public void queryByAttNo(String attoNo) {
        Cursor cursor = contentResolver.query(ProviderContract.StasEntry.CONTENT_URI,
                null, ProviderContract.StasEntry.COLUMN_ATTNO, new String[]{attoNo}, null);

        List<Tb_stas> list = new ArrayList<>();

        while (cursor.moveToNext()) {
            Tb_stas tb_stas = new Tb_stas(cursor.getString(cursor.getColumnIndex(ProviderContract.StasEntry.COLUMN_ATTNO)),
                    cursor.getString(cursor.getColumnIndex(ProviderContract.StasEntry.COLUMN_STUNO)),
                    cursor.getString(cursor.getColumnIndex(ProviderContract.StasEntry.COLUMN_COURCODE)),
                    cursor.getInt(cursor.getColumnIndex(ProviderContract.StasEntry.COLUMN_SUMTM)),
                    cursor.getInt(cursor.getColumnIndex(ProviderContract.StasEntry.COLUMN_REALTM)),
                    cursor.getInt(cursor.getColumnIndex(ProviderContract.StasEntry.COLUMN_ABSTM)),
                    cursor.getInt(cursor.getColumnIndex(ProviderContract.StasEntry.COLUMN_LATTM)),
                    cursor.getDouble(cursor.getColumnIndex(ProviderContract.StasEntry.COLUMN_SCORE))
            );
            list.add(tb_stas);


        }
    }

    public void insertOrUpdateStas(Tb_stas tb_stas) {
        Cursor cursor = contentResolver.query(ProviderContract.StasEntry.CONTENT_URI,
                null, ProviderContract.StasEntry.COLUMN_ATTNO+"=?", new String[]{tb_stas.getAttNo()}, null);
        if (cursor.getCount() == 0) {
            insert(tb_stas);
        } else {
            update(tb_stas);
        }
    }

}

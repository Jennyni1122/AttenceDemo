package com.jennyni.attencedemo.dao;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;

import com.jennyni.attencedemo.contentprovider.ProviderContract;
import com.jennyni.attencedemo.db.Tb_record;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jenny on 2019/6/1.
 */

public class RecordDAO {


    public ContentResolver contentResolver;


    public RecordDAO(ContentResolver contentResolver) {
        this.contentResolver = contentResolver;
    }

    public void insertList(List<Tb_record> list) {
        for (int i = 0; i < list.size(); i++) {
            Tb_record tb_record = list.get(i);
            insert(tb_record);
        }
    }

    public void insert(Tb_record tb_record) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ProviderContract.RecordEntry.COLUMN_ATTNO, tb_record.getAttNo());
        contentValues.put(ProviderContract.RecordEntry.COLUMN_ARRDATA, tb_record.getArrData());
        contentValues.put(ProviderContract.RecordEntry.COLUMN_ATTRESULT, tb_record.getAttResult());
        contentValues.put(ProviderContract.RecordEntry.COLUMN_ID, tb_record.getId());
        contentResolver.insert(ProviderContract.RecordEntry.CONTENT_URI, contentValues);
    }

    public List<Tb_record> queryByAttNo(String attNo) {
        Cursor cursor = contentResolver.query(ProviderContract.RecordEntry.CONTENT_URI, null, ProviderContract.RecordEntry.COLUMN_ATTNO + "=?", new String[]{attNo}, null);
        List<Tb_record> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            Tb_record tb_record = new Tb_record(
                    cursor.getString(cursor.getColumnIndex(ProviderContract.RecordEntry.COLUMN_ID)),
                    cursor.getString(cursor.getColumnIndex(ProviderContract.RecordEntry.COLUMN_ATTNO)),
                    cursor.getString(cursor.getColumnIndex(ProviderContract.RecordEntry.COLUMN_ATTRESULT)),
                    cursor.getString(cursor.getColumnIndex(ProviderContract.RecordEntry.COLUMN_ARRDATA))

            );

            list.add(tb_record);
        }

        return list;
    }


    public void updateById(Tb_record tb_record, String id) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ProviderContract.RecordEntry.COLUMN_ATTNO, tb_record.getAttNo());
        contentValues.put(ProviderContract.RecordEntry.COLUMN_ARRDATA, tb_record.getArrData());
        contentValues.put(ProviderContract.RecordEntry.COLUMN_ATTRESULT, tb_record.getAttResult());
        contentResolver.update(ProviderContract.RecordEntry.CONTENT_URI, contentValues, "id=?", new String[]{String.valueOf(id)});
    }


}

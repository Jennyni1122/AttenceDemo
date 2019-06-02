package com.jennyni.attencedemo.activity.contentprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.jennyni.attencedemo.db.DBOpenHelper;

/**
 * Created by YellowHuang on 2019/6/2
 */

public class UnKnownProvider extends ContentProvider {


    private DBOpenHelper mySqliteHelper;

    static UriMatcher buildUriMatcher() {
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        String authority = ProviderContract.CONTENT_AUTHORITY;
        uriMatcher.addURI(authority, ProviderContract.PATH_TB_COURSE, ProviderContract.RETURNCODE_TB_COURSE);
        uriMatcher.addURI(authority, ProviderContract.PATH_TB_RECORD, ProviderContract.RETURNCODE_TB_RECORD);
        uriMatcher.addURI(authority, ProviderContract.PATH_TB_SCORE, ProviderContract.RETURNCODE_TB_SCORE);
        uriMatcher.addURI(authority, ProviderContract.PATH_TB_STAS, ProviderContract.RETURNCODE_TB_STAS);
        uriMatcher.addURI(authority, ProviderContract.PATH_TB_STUDENT, ProviderContract.RETURNCODE_TB_STUDENT);
        return uriMatcher;
    }


    @Override
    public boolean onCreate() {
        mySqliteHelper = new DBOpenHelper(getContext());
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri,
                        @Nullable String[] projection,
                        @Nullable String selection,
                        @Nullable String[] selectionArgs,
                        @Nullable String sortOrder) {

        Cursor cursor = null;
        String table = null;
        switch (buildUriMatcher().match(uri)) {
            case ProviderContract.RETURNCODE_TB_COURSE:
                table = ProviderContract.CourseEntry.TABLE_NAME;
                break;
            case ProviderContract.RETURNCODE_TB_RECORD:
                table = ProviderContract.RecordEntry.TABLE_NAME;
                break;
            case ProviderContract.RETURNCODE_TB_SCORE:
                table = ProviderContract.ScoreEntry.TABLE_NAME;
                break;
            case ProviderContract.RETURNCODE_TB_STAS:
                table = ProviderContract.StasEntry.TABLE_NAME;
                break;
            case ProviderContract.RETURNCODE_TB_STUDENT:
                table = ProviderContract.StudentEntry.TABLE_NAME;
                break;
            default:
                throw new RuntimeException(" query unknown " + uri + " excetion ~");

        }
        SQLiteDatabase db = mySqliteHelper.getReadableDatabase();
        cursor = db.query(table, projection, selection, selectionArgs, sortOrder, null, null);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {

        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        Uri uri1 = null;
        String table;
        switch (buildUriMatcher().match(uri)) {
            case ProviderContract.RETURNCODE_TB_COURSE:
                table = ProviderContract.CourseEntry.TABLE_NAME;
                break;
            case ProviderContract.RETURNCODE_TB_RECORD:
                table = ProviderContract.RecordEntry.TABLE_NAME;
                break;
            case ProviderContract.RETURNCODE_TB_SCORE:
                table = ProviderContract.ScoreEntry.TABLE_NAME;
                break;
            case ProviderContract.RETURNCODE_TB_STAS:
                table = ProviderContract.StasEntry.TABLE_NAME;
                break;
            case ProviderContract.RETURNCODE_TB_STUDENT:
                table = ProviderContract.StudentEntry.TABLE_NAME;
                break;
            default:
                throw new RuntimeException(" insert unknown " + uri + " excetion ~");

        }
        SQLiteDatabase db = mySqliteHelper.getWritableDatabase();
        long _id = db.insert(table, null, values);
        if (_id > 0) {
            uri1 = ProviderContract.buildUri(_id, uri);
        } else {
            throw new android.database.SQLException("Failed to insert row into " + uri);
        }
        return uri1;
    }

    @Override
    public int delete(@NonNull Uri uri,
                      @Nullable String selection,
                      @Nullable String[] selectionArgs) {

        String table ;
        switch (buildUriMatcher().match(uri)) {
            case ProviderContract.RETURNCODE_TB_COURSE:
                table = ProviderContract.CourseEntry.TABLE_NAME;
                break;
            case ProviderContract.RETURNCODE_TB_RECORD:
                table = ProviderContract.RecordEntry.TABLE_NAME;
                break;
            case ProviderContract.RETURNCODE_TB_SCORE:
                table = ProviderContract.ScoreEntry.TABLE_NAME;
                break;
            case ProviderContract.RETURNCODE_TB_STAS:
                table = ProviderContract.StasEntry.TABLE_NAME;
                break;
            case ProviderContract.RETURNCODE_TB_STUDENT:
                table = ProviderContract.StudentEntry.TABLE_NAME;
                break;
            default:
                throw new RuntimeException(" insert unknown " + uri + " excetion ~");

        }
        SQLiteDatabase db = mySqliteHelper.getWritableDatabase();
        return db.delete(table, selection, selectionArgs);
    }

    @Override
    public int update(@NonNull Uri uri,
                      @Nullable ContentValues values,
                      @Nullable String selection,
                      @Nullable String[] selectionArgs) {
        String table ;
        switch (buildUriMatcher().match(uri)) {
            case ProviderContract.RETURNCODE_TB_COURSE:
                table = ProviderContract.CourseEntry.TABLE_NAME;
                break;
            case ProviderContract.RETURNCODE_TB_RECORD:
                table = ProviderContract.RecordEntry.TABLE_NAME;
                break;
            case ProviderContract.RETURNCODE_TB_SCORE:
                table = ProviderContract.ScoreEntry.TABLE_NAME;
                break;
            case ProviderContract.RETURNCODE_TB_STAS:
                table = ProviderContract.StasEntry.TABLE_NAME;
                break;
            case ProviderContract.RETURNCODE_TB_STUDENT:
                table = ProviderContract.StudentEntry.TABLE_NAME;
                break;
            default:
                throw new RuntimeException(" insert unknown " + uri + " excetion ~");

        }
        SQLiteDatabase db = mySqliteHelper.getWritableDatabase();
        return db.update(table, values, selection, selectionArgs);
    }
}
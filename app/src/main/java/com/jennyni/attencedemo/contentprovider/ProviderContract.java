package com.jennyni.attencedemo.contentprovider;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

import com.jennyni.attencedemo.db.DBOpenHelper;


/**
 * Created by YellowHuang on 2019/6/2
 */

public class ProviderContract {


    //包名
    protected static final String CONTENT_AUTHORITY = "com.jennyni.attencedemo";

    protected static final String PATH_TB_COURSE = DBOpenHelper.TB_COURSE;
    protected static final String PATH_TB_RECORD = DBOpenHelper.TB_RECORD;
    protected static final String PATH_TB_SCORE = DBOpenHelper.TB_SCORE;
    protected static final String PATH_TB_STAS = DBOpenHelper.TB_STAS;
    protected static final String PATH_TB_STUDENT = DBOpenHelper.TB_STUDENT;

    protected static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    protected static final int RETURNCODE_TB_COURSE = 0x11;
    protected static final int RETURNCODE_TB_RECORD = 0x12;
    protected static final int RETURNCODE_TB_SCORE = 0x13;
    protected static final int RETURNCODE_TB_STAS = 0x14;
    protected static final int RETURNCODE_TB_STUDENT = 0x15;

    public static final class CourseEntry implements BaseColumns {

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(CourseEntry.TABLE_NAME).build();

        protected static final String TABLE_NAME = PATH_TB_COURSE;

        /**
         * "create table tb_course(" +
         "id SMALLINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,"+
         "courcode varchar(20)," +
         "courname varchar(20)," +
         "courtime varchar(50),"+
         "courplace varchar(50)," +
         "teacher varchar(10)" +
         ")"
         */
        public static final String COLUMN_COURCODE = "courcode";
        public static final String COLUMN_COURNAME = "courname";
        public static final String COLUMN_COURTIME = "courtime";
        public static final String COLUMN_COURPLACE = "courplace";
        public static final String COLUMN_TEACHER = "teacher";
    }

    public static final class RecordEntry implements BaseColumns {

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(RecordEntry.TABLE_NAME).build();

        protected static final String TABLE_NAME = PATH_TB_RECORD;

        /**
         *   "id SMALLINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,"+
         "attNo varchar(50) ," +
         "attResult varchar(50),"+
         "arrData varchar(50)" +
         ")"
         */
        public static final String COLUMN_ATTNO = "attNo";
        public static final String COLUMN_ATTRESULT = "attResult";
        public static final String COLUMN_ARRDATA = "arrData";
    }

    public static final class ScoreEntry implements BaseColumns {

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(ScoreEntry.TABLE_NAME).build();

        protected static final String TABLE_NAME = PATH_TB_SCORE;

        /**
         * "id SMALLINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,"+
         "scoreset int" +
         "
         */
        public static final String COLUMN_SCORESET = "scoreset";
    }

    public static final class StasEntry implements BaseColumns {

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(StasEntry.TABLE_NAME).build();

        protected static final String TABLE_NAME = PATH_TB_STAS;

        /**
         *  "id SMALLINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,"+
         "attNo varchar(50)," +
         "stuNo varchar(20)," +
         "courcode varchar(20),"+
         "sumTm integer," +
         "realTm integer," +
         "absTm integer,"+
         "latTm integer," +
         "score decimal"
         */
        public static final String COLUMN_ATTNO = "attNo";
        public static final String COLUMN_STUNO = "stuNo";
        public static final String COLUMN_COURCODE = "courcode";
        public static final String COLUMN_SUMTM = "sumTm";
        public static final String COLUMN_REALTM = "realTm";
        public static final String COLUMN_ABSTM = "absTm";
        public static final String COLUMN_LATTM = "latTm";
    }

    public static final class StudentEntry implements BaseColumns {

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(StudentEntry.TABLE_NAME).build();

        protected static final String TABLE_NAME = PATH_TB_STUDENT;

//        public static final String COLUMN_NAME = "name";
    }


    protected static Uri buildUri(long id, Uri uri) {
        return ContentUris.withAppendedId(uri, id);
    }
}
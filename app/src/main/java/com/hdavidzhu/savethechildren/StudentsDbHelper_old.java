//package com.hdavidzhu.savethechildren;
//
//import android.content.Context;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//
//public class StudentsDbHelper extends SQLiteOpenHelper {
//
//    public static final int DATABASE_VERSION = 1;
//    public static final String DATABASE_NAME = "Students.db";
//
//    public StudentsDbHelper (Context context){
//        super(context, DATABASE_NAME, null, DATABASE_VERSION);
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        db.execSQL(StudentsContract.StudentEntry.SQL_CREATE_ENTRIES);
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//
//    }
//}
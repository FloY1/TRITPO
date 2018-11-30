package com.example.admin;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public  class DataDAO extends SQLiteOpenHelper {
    private static  DataDAO dataDAO = null;
    public static final int DB_VERSION = 1;
    public static final String DATABASE_NAME = "contactDB";
    public static final String TABLE_CONTACT = "contact";
    public static final String KEY_Product = "product";
    public static final String KEY_Count = "count";
    public static final String KEY_Cost = "cost";
    public static final String KEY_ID = "_id";
     public DataDAO(Context context) {
        super(context, DATABASE_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " +TABLE_CONTACT+"(" +KEY_ID+" integer primary key," +KEY_Product+" text, "+ KEY_Cost +" integer, " + KEY_Count + " integer )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

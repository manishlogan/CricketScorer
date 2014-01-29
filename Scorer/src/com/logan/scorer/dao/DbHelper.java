package com.logan.scorer.dao;

import com.logan.scorer.Constants;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Scorer.db";
    
    private static final String CREATE_PLAYER_TABLE = "create table "+
    Constants.PLAYER_TABLE + "(_ID Integer primary key,name text)";
    
    private static final String SQL_DELETE_ENTRIES =
    	    "DROP TABLE IF EXISTS " + "mytable";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_PLAYER_TABLE);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
package com.example.belisolbokregistratieapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper
{
    private static final int DATABASE_VERSION = 1;
    protected static final String DATABASE_NAME = "LoggingDatabase";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String sql = "CREATE TABLE Logging " +
                "( id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "messageType TEXT, " +
                "messageValue TEXT ) ";

        db.execSQL(sql);

        sql = "CREATE TABLE LoggingTypes " +
                "( id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "LoggingType TEXT, " +
                "isActive INTEGER ) ";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        String sql = "DROP TABLE IF EXISTS logging";
        db.execSQL(sql);

        sql = "DROP TABLE IF EXISTS LoggingTypes";
        db.execSQL(sql);

        onCreate(db);
    }
}

package com.example.belisolbokregistratieapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class TableControllerLogTypes extends DatabaseHandler
{
    public int messageType;
    public boolean messageValue;

    public TableControllerLogTypes(Context context)
    {
        super(context);
    }

    public boolean create() {

        /*
        ContentValues values;
        SQLiteDatabase db = this.getWritableDatabase();

        values = new ContentValues();
        values.put("LoggingType", "VERBOSE");
        values.put("isActive", true);

        boolean createSuccessful = db.insert("LoggingTypes", null, values) > 0;

        db.close();
        */

        return true;
    }


    public int returnIdFromCode(String loggingType)
    {
        String sqlstr = "select * from LoggingTypes where LoggingType ='" + loggingType + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sqlstr, null);

        int Id=0;

        if (cursor.moveToFirst()) {
            do {

                Id = Integer.parseInt(cursor.getString(cursor.getColumnIndex("id")));

            } while (cursor.moveToNext());
        }

        return Id;
    }



    public boolean isActive()
    {
        String sqlstr = "select isActive from LoggingTypes where Id="+messageType;
        int isActive=0;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sqlstr, null);

        if (cursor.moveToFirst()) {
            do {

                isActive = cursor.getInt(cursor.getColumnIndex("isActive"));

            } while (cursor.moveToNext());
        }

        if(isActive==1)
        {
            return true;
        }
        else
        {
            return false;
        }
    }


    public boolean update()
    {

        SQLiteDatabase db = this.getWritableDatabase();


        String sqlstr1;
        int isActive=1;

        if (messageValue==false)
        {
            isActive=0;
        }

        sqlstr1 = "update LoggingTypes set isActive=" + isActive + " where Id="+messageType;

        db.execSQL(sqlstr1);
        return true;

    }

}

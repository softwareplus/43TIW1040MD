package com.example.belisolbokregistratieapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class TableControllerLogging extends DatabaseHandler
{
    public String messageType;
    public String messageValue;
    public Context myContext;

    public TableControllerLogging(Context context)
    {
        super(context);
        myContext = context;
    }

    public boolean create()
    {
        boolean createSuccessful=true;
        TableControllerLogTypes mySettings = new TableControllerLogTypes(myContext);
        mySettings.messageType = mySettings.returnIdFromCode(messageType);
        boolean isActive = mySettings.isActive();

        if(isActive)
        {
            ContentValues values = new ContentValues();

            values.put("messageType", messageType);
            values.put("messageValue", messageValue);

            SQLiteDatabase db = this.getWritableDatabase();

            createSuccessful = db.insert("Logging", null, values) > 0;
            db.close();
        }

        return createSuccessful;
    }

    //later uitbreiden
    //te bespreken met Bart en Expeditie
    public List<Logging> returnAllLoggings()
    {
        String sqlstr = "select * from Logging";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sqlstr, null);

        Logging logitem = new Logging();
        List<Logging> logitems = new ArrayList<Logging>();
        logitems.clear();




        if (cursor.moveToFirst()) {
            do {
                Logging logging = new Logging();
                logging.id = cursor.getString(cursor.getColumnIndex("id"));
                logging.messageType = cursor.getString(cursor.getColumnIndex("messageType"));
                logging.messageValue = cursor.getString(cursor.getColumnIndex("messageValue"));
                logitems.add(logging);


            } while (cursor.moveToNext());
        }

        return logitems;
    }

}

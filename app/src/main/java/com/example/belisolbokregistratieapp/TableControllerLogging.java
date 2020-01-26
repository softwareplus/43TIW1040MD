package com.example.belisolbokregistratieapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

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
}

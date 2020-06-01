package com.roy.bluetoothcommunication.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MySQLHelper extends SQLiteOpenHelper {
    public static final String TBNAME = "friend";


    public MySQLHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("drop table if exists " + TBNAME);
        db.execSQL("CREATE TABLE " +
                TBNAME + "( id integer primary key autoincrement," +
                "name varchar)");
        db.execSQL("insert into friend('name') values('123')");
    }
}

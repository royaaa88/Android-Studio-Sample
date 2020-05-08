package com.example.example1.mywechat.crud;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.example1.mywechat.MainActivity.result;

/*
    SQLite数据库打开助手DbHelper作为抽象类SQLiteOpenHelper的子类
    需要重写2个抽象方法onCreate()和onUpgrade()
*/
public class MyDBHelper extends SQLiteOpenHelper{
    public static final String TA_NAME = "friends";  //表名

    //构造方法：第1参数为上下文，第2参数库库名，第3参数为游标工厂，第4参数为版本
    public MyDBHelper(Context context) {
        super(context, "test.db", null, 1);  //创建或打开数据库
        result.append("—创建或打开数据库；\n");
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        //当表不存在时，创建表；第一字段为自增长类型
        db.execSQL("DROP TABLE IF EXISTS "+TA_NAME);
        db.execSQL("CREATE TABLE " +
                TA_NAME + "( id integer primary key autoincrement," +
                "name varchar," + "age integer"+ ")");
        db.execSQL("insert into "+TA_NAME+"(name,age) values('qwe',123)");
        db.execSQL("insert into "+TA_NAME+"(name,age) values('wsh',789)");
        result.append("—创建数据表并添加记录；\n");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // 执行SQL命令
        db.execSQL("DROP TABLE IF EXISTS " + TA_NAME);
        onCreate(db);
        result.append("—修改了数据表结构和记录；\n");
    }
}
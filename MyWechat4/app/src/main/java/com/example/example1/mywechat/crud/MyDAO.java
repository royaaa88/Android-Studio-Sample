package com.example.example1.mywechat.crud;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


/*
    本类MyDAO调用了打开数据库的助手类DbHelper
    本类MyDAO提供的CRUD针对数据库test.db的表friends
    查询数据库表所有记录的方法：allQuery()
    插入记录的方法：insertInfo(String name,int age)
    删除记录的方法：deleteInfo(String selId)
    修改记录方法：updateInfo(String name,int age,String selId)
*/
public class MyDAO {
    private SQLiteDatabase myDb;  //类的成员
    private MyDBHelper dbHelper;  //类的成员

    public MyDAO(Context context) {  //构造方法，参数为上下文对象
        dbHelper = new MyDBHelper(context);
    }

    public Cursor allQuery(){    //查询所有记录
        myDb = dbHelper.getReadableDatabase();
        return myDb.rawQuery("select * from friends",null);
    }
    public  int getRecordsNumber(){  //返回数据表记录数
        myDb = dbHelper.getReadableDatabase();
        Cursor cursor= myDb.rawQuery("select * from "+MyDBHelper.TA_NAME,null);
        return cursor.getCount();
    }

    public void insertInfo(String name,int age){  //插入记录
        myDb = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("age", age);
        long rowid=myDb.insert(MyDBHelper.TA_NAME, null, values);
        if(rowid==-1)
            Log.i("myDbDemo", "数据插入失败！");
        else
            Log.i("myDbDemo", "数据插入成功！"+rowid);
    }
    public void deleteInfo(String selId){  //删除记录
        String where = "id=" + selId;
        int i = myDb.delete(MyDBHelper.TA_NAME, where, null);
        if (i > 0)
            Log.i("myDbDemo", "数据删除成功！");
        else
            Log.i("myDbDemo", "数据未删除！");
    }
    public void updateInfo(String name,int age,String selId){  //修改记录
        //方法中的第三参数用于修改选定的记录
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("age", age);
        String where="id="+selId;
        int i=myDb.update(MyDBHelper.TA_NAME, values, where, null);

        //上面几行代码的功能可以用下面的一行代码实现
        //myDb.execSQL("update friends set name = ? ,age = ? where id = ?",new Object[]{name,age,selId});

        if(i>0)
            Log.i("myDbDemo","数据更新成功！");
        else
            Log.i("myDbDemo","数据未更新！");
    }
}
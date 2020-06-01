package com.kyriexu.homework06.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.kyriexu.homework06.pojo.Friend;

import java.util.ArrayList;
import java.util.List;

public class SQLUtil {
    private SQLiteDatabase db;
    private MySQLHelper helper;

    public SQLUtil(Context context) {
        // 上下文，数据库名称，游标，版本号
        helper = new MySQLHelper(context, "homework.db", null, 1);
        // 默认为可读不可写的
        db = helper.getReadableDatabase();
    }

    // 由于这里就对一个表查询所以就不使用泛型来写了
    public List<Friend> getAllFriends() {
        Cursor cursor = db.rawQuery("select * from " + MySQLHelper.TBNAME, null);
        List<Friend> friends = new ArrayList<>(cursor.getCount());
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            friends.add(new Friend(id, name));
        }
        cursor.close();
        return friends;
    }

    // 插入操作
    public boolean insertFriend(Friend friend) {
        db = helper.getWritableDatabase();
        ContentValues cv = new ContentValues(1);
        cv.put("name", friend.getName());
        long res = db.insert(MySQLHelper.TBNAME, null, cv);
        return res > 0;
    }

    // 插入列表
    public long insertFriends(List<Friend> friends) {
        db = helper.getWritableDatabase();
        ContentValues cv = new ContentValues(friends.size());
        for (Friend friend : friends) {
            cv.put("name", friend.getName());
        }
        return db.insert(MySQLHelper.TBNAME, null, cv);
    }
}

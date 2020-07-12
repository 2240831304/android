package com.example.stock.eink.lib.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class MySqliteOpenHelper extends SQLiteOpenHelper {

    public MySqliteOpenHelper(Context context, String name,
            SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    //创建数据库时,调用此方法
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table stock if ";

        Log.d("stock database:", "数据库创建成功");

        //创建一个学生表
        //db.execSQL("create table student(_id integer primary key autoincrement, name char(10), age integer, no integer, cpp float, math float, english float)");
    }

    //数据库升级时调用此方法
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.d("MainActivity", "数据库升级成功");
    }

}

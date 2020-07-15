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
        String sql = "create table if not exists shanghai(id INTEGER PRIMARY KEY  AUTOINCREMENT,name varchar(15),code varchar(10),means TEXT," +
                "curprice integer,grap integer,minprice integer,maxprice integer,state integer,calprice integer)";
        db.execSQL(sql);

        sql = null;
        sql = "create table if not exists smallboard(id INTEGER PRIMARY KEY  AUTOINCREMENT,name varchar(15),code varchar(10),means TEXT," +
                "curprice integer,grap integer,minprice integer,maxprice integer,state integer,calprice integer)";
        db.execSQL(sql);

        sql = null;
        sql = "create table if not exists chinext(id INTEGER PRIMARY KEY  AUTOINCREMENT,name varchar(15),code varchar(10),means TEXT," +
                "curprice integer,grap integer,minprice integer,maxprice integer,state integer,calprice integer)";
        db.execSQL(sql);

        sql = null;
        sql = "create table if not exists money(id INTEGER PRIMARY KEY  AUTOINCREMENT,name varchar(15),code varchar(10)," +
                "date DATE,zhuli integer,sanhu integer, totle integer)";
        db.execSQL(sql);

        Log.d("stock database:", "数据库创建成功");

    }

    //数据库升级时调用此方法
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.d("MainActivity", "数据库升级成功");
    }

}

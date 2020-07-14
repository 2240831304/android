package com.example.stock.eink.pioneer.stockadd;


import android.database.sqlite.SQLiteDatabase;

import com.example.stock.MyApplication;
import com.example.stock.eink.lib.filesystem.ConfigParameter;
import com.example.stock.eink.lib.filesystem.FileSystemManager;
import com.example.stock.eink.lib.sqlite.MySqliteOpenHelper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class StockAddDataSave {

    private String databaseName = "stock.db";
    private String tablename;
    private String databaseFilePath;
    private static final String Class_Name = "org.sqlite.JDBC";
    private MyApplication normal;

    public StockAddDataSave(String tableName) {
        tablename = tableName;
        FileSystemManager fileManeger = new FileSystemManager();
        databaseFilePath = fileManeger.getStockFilePath() + databaseName;
        normal = new MyApplication();
    }

    public void save(){
        try {

            int stockVersion = ConfigParameter.StockDatabaseVersion;
            MySqliteOpenHelper oh = new MySqliteOpenHelper(normal.getContext(), databaseFilePath,
                    null, stockVersion);
            SQLiteDatabase db = oh.getWritableDatabase();
            String sql = "INSERT INTO shanghai(name,code) VALUES(\"swsws\",\"frfrfrf\")";

            db.execSQL(sql);
            db.close();

        }catch (Exception e){
            System.out.println("lianjieshuju:" + e);
            e.printStackTrace();
        }


    }

    public void close(){

    }

}

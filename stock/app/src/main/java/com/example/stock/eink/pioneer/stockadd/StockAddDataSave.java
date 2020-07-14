package com.example.stock.eink.pioneer.stockadd;


import android.database.sqlite.SQLiteDatabase;

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


    public StockAddDataSave(String tableName) {
        tablename = tableName;
        FileSystemManager fileManeger = new FileSystemManager();
        databaseFilePath = fileManeger.getStockFilePath() + databaseName;
    }

    public void save(){
        try {


        }catch (Exception e){
            System.out.println("lianjieshuju:" + e);
            e.printStackTrace();
        }


    }

    public void close(){

    }

}

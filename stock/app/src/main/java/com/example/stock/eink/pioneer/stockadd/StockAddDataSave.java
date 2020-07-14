package com.example.stock.eink.pioneer.stockadd;


import android.database.sqlite.SQLiteDatabase;

import com.example.stock.eink.lib.filesystem.ConfigParameter;
import com.example.stock.eink.lib.filesystem.FileSystemManager;
import com.example.stock.eink.lib.sqlite.MySqliteOpenHelper;

public class StockAddDataSave {

    private String databaseName = "stock.db";
    private String tablename;
    private String databaseFilePath;


    public StockAddDataSave(String tableName) {
        tablename = tableName;
        FileSystemManager fileManeger = new FileSystemManager();
        databaseFilePath = fileManeger.getStockFilePath() + databaseName;

    }

    public void save(){

    }

    public void close(){

    }

}

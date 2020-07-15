package com.example.stock.eink.pioneer.stockadd;


import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.example.stock.MyApplication;
import com.example.stock.eink.lib.filesystem.ConfigParameter;
import com.example.stock.eink.lib.filesystem.FileSystemManager;
import com.example.stock.eink.lib.sqlite.MySqliteOpenHelper;


public class StockAddDataSave {

    private String databaseName = "stock.db";
    private String tablename;
    private String databaseFilePath;
    private MyApplication normalActivity;
    private SQLiteDatabase db;

    public StockAddDataSave(String tableName) {
        tablename = tableName;
        FileSystemManager fileManeger = new FileSystemManager();
        databaseFilePath = fileManeger.getStockFilePath() + databaseName;
        normalActivity = new MyApplication();

        int stockVersion = ConfigParameter.StockDatabaseVersion;
        MySqliteOpenHelper oh = new MySqliteOpenHelper(normalActivity.getContext(), databaseFilePath,
                null, stockVersion);
        db = oh.getWritableDatabase();
    }

    public void save(String[] list,String stockCode){
        if(list.length < 3){
            return;
        }

        try {
            ContentValues values = new ContentValues();
            values.put("name", list[1]);
            values.put("code",stockCode);
            values.put("curprice", list[3]);
            values.put("grap", list[4]);
            db.insert(tablename,null,values);

        }catch (Exception e){
            System.out.println("lianjieshuju:" + e);
            e.printStackTrace();
        }

    }

    public void close(){
        db.close();
        System.out.println("StockAddDataSave is finished,close stock database!!!");
    }

}

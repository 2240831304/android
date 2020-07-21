package com.example.stock.eink.pioneer.stocklookup;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.stock.MyApplication;
import com.example.stock.eink.lib.filesystem.ConfigParameter;
import com.example.stock.eink.lib.filesystem.FileSystemManager;
import com.example.stock.eink.lib.sqlite.MySqliteOpenHelper;

public class StockDetailsDataHandle {

    private MyApplication normalActivity;

    private String databaseName = "stock.db";
    private String databasePath;
    private SQLiteDatabase db;

    private StockDetailsActivity StockDetailsPt;

    public StockDetailsDataHandle(StockDetailsActivity pt) {
        StockDetailsPt = pt;

        FileSystemManager fileManeger = new FileSystemManager();
        databasePath = fileManeger.getStockFilePath() + databaseName;
        normalActivity = new MyApplication();

        int stockVersion = ConfigParameter.StockDatabaseVersion;
        MySqliteOpenHelper oh = new MySqliteOpenHelper(normalActivity.getContext(), databasePath,
                null, stockVersion);
        db = oh.getWritableDatabase();
    }


    public void ObatinStockInfo(String stockName){
        Cursor cursor = db.query("totalstock", new String[] {"name", "code","means","curprice","grap",
                        "minprice","maxprice","calprice" },"name=?", new String[] { stockName },
                null, null, null);

        StockData data = new StockData();
        // 将光标移动到下一行，从而判断该结果集是否还有下一条数据，如果有则返回true，没有则返回false
        while (cursor.moveToNext()) {
            //System.out.println("StockLookupDataHandle selectData result 444444444444444");
            data.name = cursor.getString(cursor.getColumnIndex("name"));
            data.code = cursor.getString(cursor.getColumnIndex("code"));
            data.means = cursor.getString(cursor.getColumnIndex("means"));
            data.curprice = cursor.getString(cursor.getColumnIndex("curprice"));
            data.grap = cursor.getString(cursor.getColumnIndex("grap"));
            data.yearMinPrice = cursor.getString(cursor.getColumnIndex("minprice"));
            data.yearMaxPrice = cursor.getString(cursor.getColumnIndex("maxprice"));
            data.calPrice = cursor.getString(cursor.getColumnIndex("calprice"));
        }

        StockDetailsPt.InitData(data);

    }


    public void saveCalprice(String stockCode,String price,String grade){
        ContentValues values = new ContentValues();
        values.put("calprice", price);
        values.put("state", grade);
        db.update("totalstock",values,"code=?",new String[] { stockCode });

        if(stockCode.contains("sh60")){
            db.update("shanghai",values,"code=?",new String[] { stockCode });
        }else if(stockCode.contains("sz00")){
            db.update("smallboard",values,"code=?",new String[] { stockCode });
        }

    }

    public void quit(){
        db.close();
    }


}

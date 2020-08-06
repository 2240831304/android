package com.example.stock.eink.pioneer.stocklookup;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.stock.MyApplication;
import com.example.stock.eink.lib.filesystem.ConfigParameter;
import com.example.stock.eink.lib.filesystem.FileSystemManager;
import com.example.stock.eink.lib.sqlite.MySqliteOpenHelper;

import java.util.Hashtable;

public class StockDetailsDataHandle {

    private MyApplication normalActivity;

    private String databaseName = "stock.db";
    private String databasePath;
    private SQLiteDatabase db;

    private StockDetailsActivity StockDetailsPt;


    public StockDetailsDataHandle(){
        FileSystemManager fileManeger = new FileSystemManager();
        databasePath = fileManeger.getStockFilePath() + databaseName;
        normalActivity = new MyApplication();

        int stockVersion = ConfigParameter.StockDatabaseVersion;
        MySqliteOpenHelper oh = new MySqliteOpenHelper(normalActivity.getContext(), databasePath,
                null, stockVersion);
        db = oh.getWritableDatabase();
    }

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
                        "minprice","maxprice","calprice","classify" },"name=?", new String[] { stockName },
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
            data.classify = cursor.getString(cursor.getColumnIndex("classify"));
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

    public void saveMeans(String stockCode,String means){
        ContentValues values = new ContentValues();
        values.put("means", means);
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


    public Hashtable<String,Integer> getStockClassifyNames(){
        Hashtable<String,Integer> nameMap = new Hashtable<String,Integer>();
        try{

            int idtmp = 0;
            String nametmp = null;

            Cursor cursor = db.query("board", new String[] {"id","classify"},"id>?", new String[] {"0"},
                    null, null, null);
            // 将光标移动到下一行，从而判断该结果集是否还有下一条数据，如果有则返回true，没有则返回false
            while (cursor.moveToNext()) {
                idtmp = cursor.getInt(cursor.getColumnIndex("id"));
                nametmp = cursor.getString(cursor.getColumnIndex("classify"));
                nameMap.put(nametmp,idtmp);
            }

        }catch (Exception e){
            System.out.println("StockLookupDataHandle error::" + e);
            e.printStackTrace();
        }

        return nameMap;
    }


    public void setStockClassify(int index,String stockName){
        ContentValues values = new ContentValues();
        values.put("classify",index);

        db.update("totalstock",values,"name=?",new String[] { stockName });
    }


}

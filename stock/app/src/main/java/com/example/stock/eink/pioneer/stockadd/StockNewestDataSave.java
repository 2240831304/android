package com.example.stock.eink.pioneer.stockadd;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.stock.MyApplication;
import com.example.stock.eink.lib.filesystem.ConfigParameter;
import com.example.stock.eink.lib.filesystem.FileSystemManager;
import com.example.stock.eink.lib.sqlite.MySqliteOpenHelper;

public class StockNewestDataSave {

    private String databaseName = "stock.db";

    private String databaseFilePath;
    private MyApplication normalActivity;
    private SQLiteDatabase db;

    private int minId;
    private int maxId;


    public StockNewestDataSave(){
        FileSystemManager fileManeger = new FileSystemManager();
        databaseFilePath = fileManeger.getStockFilePath() + databaseName;
        normalActivity = new MyApplication();

        int stockVersion = ConfigParameter.StockDatabaseVersion;
        MySqliteOpenHelper oh = new MySqliteOpenHelper(normalActivity.getContext(), databaseFilePath,
                null, stockVersion);
        db = oh.getWritableDatabase();
    }


    public void save(String[] list,String stockCode){
        if(list.length < 4){
            return;
        }

        try {

            float minprice = 0;
            float maxprice = 0;
            int state = 1;
            Cursor cursor = db.query("totalstock", new String[] { "minprice",
                    "maxprice","state" },"code=?", new String[] { stockCode }, null, null, null);
            // 将光标移动到下一行，从而判断该结果集是否还有下一条数据，如果有则返回true，没有则返回false
            while (cursor.moveToNext()) {
                minprice = cursor.getFloat(cursor.getColumnIndex("minprice"));
                maxprice = cursor.getFloat(cursor.getColumnIndex("maxprice"));
                state = cursor.getInt(cursor.getColumnIndex("state"));
            }

            if( (state > 0) && (state < 6) && (minprice > 0)){
                float grapPrice = (maxprice - minprice) / 5;
                float curPriceTemp = Float.parseFloat(list[3]);
                float oneGrade = minprice + grapPrice;
                float towGrade = oneGrade + grapPrice;
                float threeGrap = towGrade + grapPrice;
                float foreGrap = threeGrap + grapPrice;

                if(curPriceTemp <= oneGrade){
                    state = 1;
                }else if(curPriceTemp <= towGrade){
                    state = 2;
                }else if (curPriceTemp <= threeGrap){
                    state = 3;
                }else if (curPriceTemp <= foreGrap){
                    state = 4;
                }else if(curPriceTemp > foreGrap){
                    state = 5;
                }
            }

            ContentValues values = new ContentValues();
            values.put("curprice", list[3]);
            values.put("grap", list[4]);
            values.put("state", state);

            db.update("totalstock",values,"code=?",new String[] { stockCode });

        }catch (Exception e){
            System.out.println("StockNewestDataSave error::" + e);
            e.printStackTrace();
        }

    }

    public void close(){
        db.close();
        System.out.println("StockNewestDataSave is finished,close stock database!!!");
    }

    public void setId(){

        Cursor cursor = db.query("totalstock", new String[] {"MIN(id)","MAX(id)"},null,
                null, null, null, null);

        // 将光标移动到下一行，从而判断该结果集是否还有下一条数据，如果有则返回true，没有则返回false
        while (cursor.moveToNext()) {
            minId = cursor.getInt(cursor.getColumnIndex("MIN(id)"));
            maxId = cursor.getInt(cursor.getColumnIndex("MAX(id)"));
        }
    }

    public int getMinId(){
        return minId;
    }

    public int getMaxId(){
        return maxId;
    }

    public String getStockCode(int id){
        Cursor cursor = db.query("totalstock", new String[] {"code"},"id=?",
                new String[] { Integer.toString(id) }, null, null, null);

        String code = "";
        // 将光标移动到下一行，从而判断该结果集是否还有下一条数据，如果有则返回true，没有则返回false
        while (cursor.moveToNext()) {
            code = cursor.getString(cursor.getColumnIndex("code"));
        }

        return code;

    }


}

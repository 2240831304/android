package com.example.stock.eink.pioneer.stockadd;


import android.content.ContentValues;
import android.database.Cursor;
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
        if(list.length < 4){
            return;
        }

        try {
            String name = null;
            float minprice = 0;
            float maxprice = 0;
            int state = 0;
            Cursor cursor = db.query(tablename, new String[] {"name", "minprice",
                    "maxprice","state" },"code=?", new String[] { stockCode }, null, null, null);
            // 将光标移动到下一行，从而判断该结果集是否还有下一条数据，如果有则返回true，没有则返回false
            while (cursor.moveToNext()) {
                name = cursor.getString(cursor.getColumnIndex("name"));
                minprice = cursor.getFloat(cursor.getColumnIndex("minprice"));
                maxprice = cursor.getFloat(cursor.getColumnIndex("maxprice"));
                state = cursor.getInt(cursor.getColumnIndex("state"));
            }

            if (name == null){
                ContentValues values = new ContentValues();
                values.put("name", list[1]);
                values.put("code",stockCode);
                values.put("curprice", list[3]);
                values.put("grap", list[4]);
                db.insert(tablename,null,values);
            }else {
                System.out.println("StockAddDataSave stock have exist in local database!!");

                if( (state < 6) && (minprice > 0)){
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
                db.update(tablename,values,"code=?",new String[] { stockCode });
            }

        }catch (Exception e){
            System.out.println("StockAddDataSave error::" + e);
            e.printStackTrace();
        }

    }

    public void close(){
        db.close();
        System.out.println("StockAddDataSave is finished,close stock database!!!");
    }

}

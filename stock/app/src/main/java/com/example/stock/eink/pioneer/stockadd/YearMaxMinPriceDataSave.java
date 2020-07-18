package com.example.stock.eink.pioneer.stockadd;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public class YearMaxMinPriceDataSave {

    private SQLiteDatabase db;
    private String tableName;

    public YearMaxMinPriceDataSave(SQLiteDatabase pt,String name){
        db = pt;
        tableName = name;
    }

    public void save(int indexId,int minPrice,int maxPrice,String stockCode){
        ContentValues values = new ContentValues();
        values.put("minprice", minPrice);
        //values.put("maxprice", Integer.toString(maxPrice));
        values.put("maxprice", maxPrice);
        db.update(tableName,values,"id=?",new String[] { Integer.toString(indexId) });

        db.update("totalstock",values,"code=?",new String[] {stockCode});
    }


}

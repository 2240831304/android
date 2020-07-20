package com.example.stock.eink.pioneer.stocklookup;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.stock.MyApplication;
import com.example.stock.R;
import com.example.stock.eink.lib.filesystem.ConfigParameter;
import com.example.stock.eink.lib.filesystem.FileSystemManager;
import com.example.stock.eink.lib.sqlite.MySqliteOpenHelper;
import com.example.stock.eink.pioneer.stockadd.ShangHaiStockAddReq;
import com.example.stock.eink.pioneer.stockadd.SmallBoardStockAddReq;

import java.util.ArrayList;

public class StockLookupDataHandle {

    private StockLookupActivity oPt;
    private MyApplication normalActivity;

    private String databaseName = "stock.db";
    private String databasePath;
    private SQLiteDatabase db;


    public StockLookupDataHandle(StockLookupActivity pt){
        oPt = pt;

        FileSystemManager fileManeger = new FileSystemManager();
        databasePath = fileManeger.getStockFilePath() + databaseName;
        normalActivity = new MyApplication();

        int stockVersion = ConfigParameter.StockDatabaseVersion;
        MySqliteOpenHelper oh = new MySqliteOpenHelper(normalActivity.getContext(), databasePath,
                null, stockVersion);
        db = oh.getWritableDatabase();
    }


    public void selectData(String stockBoard,String stockGrade){
        //System.out.println("StockLookupDataHandle selectData 44444444444444");
        String boardName = "";
        final ArrayList<StockData> dataList =  new ArrayList<>();

        if( stockBoard.equals("沪市A股") ){
            boardName = "shanghai";
        }else if(stockBoard.equals("中小板") ){
            boardName = "smallboard";
        }

        Cursor cursor = db.query("totalstock", new String[] {"name", "code","means","curprice","grap",
                "minprice","maxprice","calprice" },"board=? AND state=?", new String[] { boardName,stockGrade },
                null, null, null);

        // 将光标移动到下一行，从而判断该结果集是否还有下一条数据，如果有则返回true，没有则返回false
        while (cursor.moveToNext()) {
            //System.out.println("StockLookupDataHandle selectData result 444444444444444");
            StockData data = new StockData();
            data.name = cursor.getString(cursor.getColumnIndex("name"));
            data.code = cursor.getString(cursor.getColumnIndex("code"));
            data.means = cursor.getString(cursor.getColumnIndex("means"));
            data.curprice = cursor.getString(cursor.getColumnIndex("curprice"));
            data.grap = cursor.getString(cursor.getColumnIndex("grap"));
            data.yearMinPrice = cursor.getString(cursor.getColumnIndex("minprice"));
            data.yearMaxPrice = cursor.getString(cursor.getColumnIndex("maxprice"));
            data.calPrice = cursor.getString(cursor.getColumnIndex("calprice"));

            dataList.add(data);
        }

        oPt.selectCallback(dataList);
    }

    public void quit(){
        db.close();
    }


}

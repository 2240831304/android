package com.example.stock.eink.pioneer.stockadd;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.stock.MyApplication;
import com.example.stock.eink.lib.filesystem.ConfigParameter;
import com.example.stock.eink.lib.filesystem.FileSystemManager;
import com.example.stock.eink.lib.networkview.HttpRequest;
import com.example.stock.eink.lib.sqlite.MySqliteOpenHelper;

import java.net.HttpURLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GemYearMaxMinReq extends HttpRequest {
    private static final String requesturl =
            "https://q.stock.sohu.com/hisHq?code=cn_%1&start=%2&end=%3&stat=0&order=D&period=m";

    private int startReqCode = 0;
    private int executeIndexId = 0;
    private int tableMaxIndex = 0;
    private String executeReqCode = null;
    private String StartDate = null;
    private String EndData = null;

    private String resultData = "";
    private boolean requestState = false;

    private String databaseName = "stock.db";
    private String databaseFilePath;
    private MyApplication normalActivity;
    private SQLiteDatabase db;

    private YearMaxMinPriceDataParse parser;
    private YearMaxMinPriceDataSave  saver;

    public GemYearMaxMinReq(int startCode,String startDatePt){
        startReqCode = startCode;
        StartDate = startDatePt;
        FileSystemManager fileManeger = new FileSystemManager();
        databaseFilePath = fileManeger.getStockFilePath() + databaseName;
        normalActivity = new MyApplication();

        int stockVersion = ConfigParameter.StockDatabaseVersion;
        MySqliteOpenHelper oh = new MySqliteOpenHelper(normalActivity.getContext(), databaseFilePath,
                null, stockVersion);
        db = oh.getWritableDatabase();

        parser = new YearMaxMinPriceDataParse();
        saver = new YearMaxMinPriceDataSave(db,"chinext");
    }

    public void fillHead(HttpURLConnection connecter)
    {

    }

    public void addData(String data){
        resultData += data;
    }

    public void parseData(){

        //System.out.println("GemYearMaxMinReq data="+resultData);

        if(getErrorMeg().isEmpty()){
            if(resultData.contains("hq")){
                parser.parse(resultData);
                String stockCodeTmp = "sz" + executeReqCode;
                saver.save(executeIndexId,parser.getMinprice(),parser.getMaxprice(),stockCodeTmp);
            }
        }

        resultData = "";

    }

    public void startReq() {
        InitReqStockCode();
        requestState = true;

        Intent intent = new Intent();
        intent.setAction("action.updateUI");

        while (requestState){
            if(executeIndexId > tableMaxIndex){
                break;
            }

            obtainReqStockCode();
            if (executeReqCode == null){
                executeIndexId += 1;
                continue;
            }

            executeReqCode = executeReqCode.replace("sz","");

            intent.putExtra("name", executeReqCode);
            MyApplication.getContext().sendBroadcast(intent);

            String url = requesturl.replace("%2",StartDate);
            url = url.replace("%3",EndData);
            url = url.replace("%1",executeReqCode);

            System.out.println("GemYearMaxMinReq executeIndexId="+Integer.toString(executeIndexId));
            //System.out.println("GemYearMaxMinReq url=" + url);

            setReqUrl(url);
            execute();

            executeIndexId += 1;
        }

        db.close();

        intent.putExtra("name", "finished");
        MyApplication.getContext().sendBroadcast(intent);

    }


    public void stopReq()
    {
        requestState = false;
    }

    private void InitReqStockCode(){
        String codeTemp = "sh" + Integer.toString(startReqCode);
        Cursor cursor = db.query("chinext", new String[] {"id"},"code=?",
                new String[] { codeTemp }, null, null, null);
        // 将光标移动到下一行，从而判断该结果集是否还有下一条数据，如果有则返回true，没有则返回false
        while (cursor.moveToNext()) {
            executeIndexId = cursor.getInt(cursor.getColumnIndex("id"));
        }

        if (executeIndexId == 0){
            executeIndexId = 1;
        }else {
            executeReqCode = codeTemp;
        }


        cursor = db.query("chinext", new String[] {"MAX(id)"},null,
                null, null, null, null);
        // 将光标移动到下一行，从而判断该结果集是否还有下一条数据，如果有则返回true，没有则返回false
        while (cursor.moveToNext()) {
            tableMaxIndex = cursor.getInt(cursor.getColumnIndex("MAX(id)"));
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = dateFormat.format(new Date());
        EndData = currentDate.replace("-","");

        System.out.println("GemYearMaxMinReq start executeIndexId="+Integer.toString(executeIndexId));
        System.out.println("GemYearMaxMinReq start executeReqCode="+executeReqCode);
        System.out.println("GemYearMaxMinReq table tableMaxIndex="+Integer.toString(tableMaxIndex));
    }

    private void obtainReqStockCode(){
        executeReqCode = null;

        Cursor cursor = db.query("chinext", new String[] {"code"},"id=?",
                new String[] { Integer.toString(executeIndexId) }, null, null, null);
        // 将光标移动到下一行，从而判断该结果集是否还有下一条数据，如果有则返回true，没有则返回false
        while (cursor.moveToNext()) {
            executeReqCode = cursor.getString(cursor.getColumnIndex("code"));
        }
    }

}

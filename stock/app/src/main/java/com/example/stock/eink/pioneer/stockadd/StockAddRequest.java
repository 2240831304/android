package com.example.stock.eink.pioneer.stockadd;

import android.widget.TextView;

import com.example.stock.eink.lib.networkview.HttpRequest;

import java.net.HttpURLConnection;

public class StockAddRequest extends HttpRequest {

    private String ResultData;
    private String stockType = null;
    private TextView addStockCode = null;
    private int executeCode = 0;
    private boolean isRequestState = false;


    public StockAddRequest(String stockTypeEnName ,TextView viewPt,int codeNum){
        addStockCode = viewPt;
        executeCode = codeNum;
    }

    public void fillHead(HttpURLConnection connecter)
    {
        //connecter.setRequestProperty("Accept", "application/json");
    }

    public void addData(String data)
    {
        ResultData += data;
    }

    public void parseData()
    {
        System.out.println(ResultData);
    }

    public int getExecuteCode(){
        return executeCode;
    }


    public void startRequest() {
        isRequestState = true;
    }


    public void setRequestState(boolean state){
        isRequestState = state;
    }

}

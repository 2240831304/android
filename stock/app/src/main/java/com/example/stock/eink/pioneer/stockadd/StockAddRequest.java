package com.example.stock.eink.pioneer.stockadd;

import android.widget.TextView;

import com.example.stock.eink.lib.networkview.HttpRequest;

import java.net.HttpURLConnection;

public class StockAddRequest extends HttpRequest {

    private String ResultData;
    private String stockType = null;
    private TextView addStockCode = null;

    public StockAddRequest(String stockTypeData ,TextView viewPt){
        addStockCode = viewPt;

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

}

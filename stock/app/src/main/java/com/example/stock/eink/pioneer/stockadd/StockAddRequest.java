package com.example.stock.eink.pioneer.stockadd;

import android.content.Context;
import android.widget.TextView;

import com.example.stock.eink.lib.networkview.HttpRequest;


public class StockAddRequest {

    private Context context;
    private String stockBoardCnName = null;
    private TextView hintTextView;

    private HttpRequest requestPt = null;


    public StockAddRequest(Context data,String stockTypeCnName,TextView view){
        context = data;
        stockBoardCnName = stockTypeCnName;
        hintTextView = view;
    }

    private void Init(){
        if(stockBoardCnName == ""){

        }
    }

    public void start(){
        Init();

        if(requestPt == null){
            System.out.println("StockAddRequest error,request instance not have!!");
            return;
        }
        requestPt.startReq();
    }

    public void stop(){
        if(requestPt == null){
            System.out.println("StockAddRequest error,request instance not have!!");
            return;
        }

        requestPt.stopReq();
    }


}

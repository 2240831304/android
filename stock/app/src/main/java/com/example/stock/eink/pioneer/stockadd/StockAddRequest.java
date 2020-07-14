package com.example.stock.eink.pioneer.stockadd;

import android.content.Context;
import android.widget.TextView;

import com.example.stock.eink.lib.networkview.HttpRequest;


public class StockAddRequest {

    private String stockBoardCnName = null;

    private HttpRequest requestPt;


    public StockAddRequest(String stockTypeCnName){

        stockBoardCnName = stockTypeCnName;
    }

    private void Init(){
        System.out.println("1111111111111111="+stockBoardCnName);
        stockBoardCnName = "沪市A股";
        if(stockBoardCnName == "沪市A股"){
            System.out.println("22222222222222222222="+stockBoardCnName);
            requestPt = new ShangHaiStockAddReq(600023);
        }
    }

    public void start(){
        Init();
        if(requestPt == null){
            System.out.println("StockAddRequest start error,request instance not have!!");
            return;
        }

        new Thread(new Runnable(){
            @Override
            public void run() {
                requestPt.startReq();
            }
        }).start();

    }

    public void stop(){
        if(requestPt == null){
            System.out.println("StockAddRequest stop error,request instance not have!!");
            return;
        }

        requestPt.stopReq();
    }


}

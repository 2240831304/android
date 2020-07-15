package com.example.stock.eink.pioneer.stockadd;

import android.content.Context;

import com.example.stock.eink.lib.networkview.HttpRequest;
import com.example.stock.R;


public class StockAddRequest {

    private String stockBoardCnName = null;

    private HttpRequest requestPt;
    private Context context;


    public StockAddRequest(Context contextPt,String stockTypeCnName){
        context = contextPt;
        stockBoardCnName = stockTypeCnName;
    }

    private void Init(){
        System.out.println("StockAddRequest stock type name=="+stockBoardCnName);
        int startStockCodeID = 0;

        if( stockBoardCnName.equals("沪市A股") ){
            startStockCodeID = context.getResources().getInteger(R.integer.shanghaistock_get_id);
            requestPt = new ShangHaiStockAddReq(startStockCodeID);
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

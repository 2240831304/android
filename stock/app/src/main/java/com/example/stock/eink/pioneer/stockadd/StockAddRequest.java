package com.example.stock.eink.pioneer.stockadd;

import android.content.Context;
import android.os.Handler;

import com.example.stock.eink.lib.networkview.HttpRequest;
import com.example.stock.R;


public class StockAddRequest {

    private String stockBoardCnName = null;

    private HttpRequest requestPt;
    private Context context;
    private Handler handerOb;


    public StockAddRequest(Context contextPt, String stockTypeCnName, Handler handerPt){
        context = contextPt;
        stockBoardCnName = stockTypeCnName;
        handerOb = handerPt;
    }

    private void Init(){
        System.out.println("StockAddRequest stock type name=="+stockBoardCnName);
        int startStockCodeID = 0;

        if( stockBoardCnName.equals("沪市A股") ){
            startStockCodeID = context.getResources().getInteger(R.integer.shanghaistock_get_id);
            requestPt = new ShangHaiStockAddReq(startStockCodeID,handerOb);
        }else if(stockBoardCnName.equals("中小板") ){
            startStockCodeID = context.getResources().getInteger(R.integer.smallboardstock_get_id);
            requestPt = new SmallBoardStockAddReq(startStockCodeID,handerOb);
        }else if(stockBoardCnName.equals("创业板") ) {
            startStockCodeID = 300000;
            requestPt = new GemStockAddReq(startStockCodeID,handerOb);
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

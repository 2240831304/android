package com.example.stock.eink.pioneer.stockadd;

import android.content.Context;

import com.example.stock.eink.lib.networkview.HttpRequest;
import com.example.stock.R;

public class YearMaxMinPriceReq {
    private String stockBoardCnName = null;

    private HttpRequest requestPt;
    private Context context;

    public YearMaxMinPriceReq(Context contextPt,String stockTypeCnName){
        context = contextPt;
        stockBoardCnName = stockTypeCnName;
    }


    private void Init(){
        System.out.println("YearMaxMinPriceReq stock board=="+stockBoardCnName);
        int startStockCodeID = 0;
        String startDate = context.getResources().getString(R.string.yearmaxminprice_startdate);

        if( stockBoardCnName.equals("沪市A股") ){
            startStockCodeID = context.getResources().getInteger(R.integer.shanghai_yearmaxmin_price_id);
            requestPt = new ShanghaiYearMaxMinReq(startStockCodeID,startDate);
        }else if(stockBoardCnName.equals("中小板")){
            startStockCodeID = context.getResources().getInteger(R.integer.shenzhen_yearmaxmin_price_id);
            requestPt = new SmallBoardMaxMinReq(startStockCodeID,startDate);
        }
    }


    public void start() {
        Init();
        if(requestPt == null){
            System.out.println("YearMaxMinPriceReq start error,request instance not have!!");
            return;
        }

        new Thread(new Runnable(){
            @Override
            public void run() {
                requestPt.startReq();
            }
        }).start();

    }

    public void stop() {
        if(requestPt == null){
            System.out.println("YearMaxMinPriceReq stop error,request instance not have!!");
            return;
        }

        requestPt.stopReq();
    }


}

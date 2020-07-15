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
    

}

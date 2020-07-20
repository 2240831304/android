package com.example.stock.eink.pioneer.stocklookup;

import android.content.Context;
import android.content.Intent;

import com.example.stock.eink.pioneer.stockadd.StockAddActivity;

public class StockLookUpHandle {

    private Context context;
    private String name;

    public StockLookUpHandle(Context contextPt,String nameData){
        context = contextPt;
        name = nameData;
    }

    public void createBoardLookupUI(){
        //System.out.println("StockLookUpHandle createBoardLookupUI111111111111");
        Intent intent = new Intent(context, StockLookupActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("name",name);
        context.startActivity(intent);
    }


    public void createStockDetailsUI(){
        Intent intent = new Intent(context, StockDetailsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("name",name);
        context.startActivity(intent);
    }

}

package com.example.stock.eink.pioneer.stockadd;

import android.content.Context;
import android.content.Intent;
import android.widget.AdapterView;

public class StockAddHandle  {
    private String stockTypeName;
    private Context aContext;

    public StockAddHandle(){

    }

    public StockAddHandle(String name){
        stockTypeName = name;
    }

    public void execute(Context context){
        Intent intent = new Intent(context,StockAddActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("stockTypeName",stockTypeName);
        context.startActivity(intent);
    }


    public void createCurpriceUI(Context context){
        if(stockTypeName.equals("最新股价")){
            Intent intent = new Intent(context,StockNewestDataActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("newestprice",stockTypeName);
            context.startActivity(intent);
        }else if (stockTypeName.equals("基本资料")){
            Intent intent = new Intent(context,BusinessAnalysisActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("name",stockTypeName);
            context.startActivity(intent);
        }


    }
}

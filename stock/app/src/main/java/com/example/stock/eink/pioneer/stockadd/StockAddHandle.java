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
}

package com.example.stock.eink.liabrary.broadcast;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.example.stock.MainActivity;

public class StockAddServer extends Service {

    public static final String ACTION_UPDATEUI = "action.updateUI";

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate(){
        super.onCreate();

        final Intent intent = new Intent();
        intent.setAction(ACTION_UPDATEUI);
        intent.putExtra("count", 1);
        sendBroadcast(intent);

        System.out.println("StockAddServer onCreate33333333333333333");
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        System.out.println("StockAddServer onDestroy 55555555555555555555");
    }

}

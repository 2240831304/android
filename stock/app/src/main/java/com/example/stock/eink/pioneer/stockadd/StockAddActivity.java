package com.example.stock.eink.pioneer.stockadd;

import androidx.appcompat.app.AppCompatActivity;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.stock.R;
import com.example.stock.eink.liabrary.broadcast.StockAddServer;

import android.os.Handler;
import android.os.Message;


public class StockAddActivity extends AppCompatActivity {
    public static final String ACTION_UPDATEUI = "action.updateUI";

    private Button obtainStockBut;
    private Button obtainStockYearMaxMinBut;
    private ImageView quitImageView;
    private boolean obtainStockState = false;
    private boolean obtainStockYearMaxMinState = false;
    private String stockTypeName = null;

    private StockAddRequest stockAddRequest;
    private TextView addStockHintTextView;
    private TextView yearMinMaxHintTextView;

    private YearMaxMinPriceReq yearMaxMinPriceRequest;

    private MaxMinPriceReceiver yearMaxMinPriReceiver;
    private StockAddServer stockAddServerOb;

    //handler+message处理添加股票时时信息更新
    private Handler stockAddhandler = new Handler(){

        public void handleMessage(Message msg) {
            String msgStr = (String)msg.obj;
            switch (msgStr){
                case "finished":
                    obtainStockBut.setText("开始添加");
                    obtainStockBut.setBackgroundColor(Color.parseColor("#D7A6B7"));
                    obtainStockState = false;
                    addStockHintTextView.setText("点击 开始添加");
                    break;

                default:
                    addStockHintTextView.setText(msgStr);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stock_add_activity);

        TextView titleView = (TextView)findViewById(R.id.textview_title_stocktype);
        stockTypeName = getIntent().getStringExtra("stockTypeName");
        titleView.setText(stockTypeName);

        obtainStockBut = (Button)findViewById(R.id.button_get_stock);
        obtainStockBut.setText("开始添加");
        obtainStockBut.setOnClickListener(new ObtainStockButListener());

        obtainStockYearMaxMinBut = (Button)findViewById(R.id.button_start_request);
        obtainStockYearMaxMinBut.setText("开始获取");
        obtainStockYearMaxMinBut.setOnClickListener(new ObtainStockYearMaxMinButListener());

        quitImageView = (ImageView)findViewById(R.id.imageView_title_quit);
        quitImageView.setOnClickListener(listenerQuit);

        addStockHintTextView = (TextView)findViewById(R.id.textView2);
        yearMinMaxHintTextView = (TextView)findViewById(R.id.textView5);


        // 动态注册广播
        IntentFilter filter = new IntentFilter();
        filter.addAction(ACTION_UPDATEUI);
        yearMaxMinPriReceiver = new MaxMinPriceReceiver();
        registerReceiver(yearMaxMinPriReceiver, filter);

        // 启动服务
        //stockAddServerOb = new StockAddServer();
        Intent intent = new Intent(this, StockAddServer.class);
        startService(intent);

        stockAddRequest = new StockAddRequest(this,stockTypeName,stockAddhandler);
        yearMaxMinPriceRequest = new YearMaxMinPriceReq(this,stockTypeName);
    }

    //@获取股票信息
    private class ObtainStockButListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            if(obtainStockState){
                stockAddRequest.stop();
                obtainStockBut.setText("开始添加");
                obtainStockBut.setBackgroundColor(Color.parseColor("#D7A6B7"));
                obtainStockState = false;
            }else{
                stockAddRequest.start();
                obtainStockBut.setText("停止获取");
                obtainStockBut.setBackgroundColor(Color.parseColor("#FF0000"));
                obtainStockState = true;
            }
        }
    }

    //@获取股票一年最大最小价
    private class ObtainStockYearMaxMinButListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if(obtainStockState){
                addStockHintTextView.setText("正在获取股票信息,等会再试");
                return;
            }

            if(obtainStockYearMaxMinState){
                yearMaxMinPriceRequest.stop();
                obtainStockYearMaxMinState = false;
                obtainStockYearMaxMinBut.setText("开始获取");
                obtainStockYearMaxMinBut.setBackgroundColor(Color.parseColor("#D7A6B7"));
            }else {
                yearMaxMinPriceRequest.start();
                obtainStockYearMaxMinState = true;
                obtainStockYearMaxMinBut.setText("停止获取");
                obtainStockYearMaxMinBut.setBackgroundColor(Color.parseColor("#FF0000"));
            }

        }
    };


    //@退出界面
    private View.OnClickListener listenerQuit = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(obtainStockState){
                stockAddRequest.stop();
            }
            if(obtainStockYearMaxMinState){
                yearMaxMinPriceRequest.stop();
            }

            //注销广播
            yearMaxMinPriReceiver.onDestroy();

            StockAddActivity.this.finish();
        }
    };


    //通过广播更新请求股票最大最小价的状态
    private class MaxMinPriceReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            System.out.println("StockAddActivity MaxMinPriceReceiver 5555555555555");
        }

        protected void onDestroy(){
            System.out.println("StockAddActivity MaxMinPriceReceiver 666666666666666");
            // 注销广播
            unregisterReceiver(yearMaxMinPriReceiver);

        }

    }



        //@退出界面
    private class ListenerQuit implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            StockAddActivity.this.finish();
        }
    }


}
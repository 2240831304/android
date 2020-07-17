package com.example.stock.eink.pioneer.stockadd;

import androidx.appcompat.app.AppCompatActivity;


import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.stock.R;
import android.os.Handler;
import android.os.Message;


public class StockAddActivity extends AppCompatActivity {
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

    private Handler handler = new Handler(){

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

        stockAddRequest = new StockAddRequest(this,stockTypeName,handler);
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
            StockAddActivity.this.finish();
        }
    };


    //@退出界面
    private class ListenerQuit implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            StockAddActivity.this.finish();
        }
    }


}
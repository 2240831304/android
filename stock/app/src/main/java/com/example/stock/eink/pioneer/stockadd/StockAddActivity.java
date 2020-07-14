package com.example.stock.eink.pioneer.stockadd;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.stock.R;



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

        stockAddRequest = new StockAddRequest(this,stockTypeName);
    }

    //@获取股票信息
    private class ObtainStockButListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            if(obtainStockState){
                stockAddRequest.stop();
                obtainStockBut.setText("开始添加");
                obtainStockState = false;
            }else{
                stockAddRequest.start();
                obtainStockBut.setText("停止获取");
                obtainStockState = true;
            }
        }
    }

    //@获取股票一年最大最小价
    private class ObtainStockYearMaxMinButListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if(obtainStockYearMaxMinState){
                obtainStockYearMaxMinState = false;
                obtainStockYearMaxMinBut.setText("开始获取");
            }else {
                obtainStockYearMaxMinState = true;
                obtainStockYearMaxMinBut.setText("停止获取");
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
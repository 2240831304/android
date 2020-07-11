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
    private boolean obtainStockState = true;
    private boolean obtainStockYearMaxMinState = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stock_add_activity);

        TextView titleView = (TextView)findViewById(R.id.textview_title_stocktype);
        String stockTypeName = getIntent().getStringExtra("stockTypeName");
        titleView.setText(stockTypeName);

        obtainStockBut = (Button)findViewById(R.id.button_get_stock);
        obtainStockBut.setText("开始添加");
        obtainStockBut.setOnClickListener(new ObtainStockButListener());

        obtainStockYearMaxMinBut = (Button)findViewById(R.id.button_start_request);
        obtainStockYearMaxMinBut.setText("开始获取");
        obtainStockYearMaxMinBut.setOnClickListener(new ObtainStockYearMaxMinButListener());


        quitImageView = (ImageView)findViewById(R.id.imageView_title_quit);
        quitImageView.setOnClickListener(listenerQuit);

    }

    //@停止获取股票信息
    private class ObtainStockButListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            if(obtainStockState){
                obtainStockBut.setText("停止获取");
                obtainStockState = false;
            }else{
                obtainStockBut.setText("开始添加");
                obtainStockState = true;
            }

        }
    }

    //@停止获取股票一年最大最小价
    private class ObtainStockYearMaxMinButListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if(obtainStockYearMaxMinState){
                obtainStockYearMaxMinState = false;
                obtainStockYearMaxMinBut.setText("停止获取");
            }else {
                obtainStockYearMaxMinState = true;
                obtainStockYearMaxMinBut.setText("开始获取");
            }

        }
    };


    //@退出界面
    private View.OnClickListener listenerQuit = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
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
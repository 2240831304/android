package com.example.stock.eink.pioneer.stockadd;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.stock.R;
//import Ths.JDIBridge;


public class BusinessAnalysisActivity extends AppCompatActivity {

    private String titleName;
    private ImageView quitImageView;

    private TextView executeCodeText;
    private Button obtainBut;

    private boolean ReqState = false;


    //handler+message处理添加股票时时信息更新
    private Handler reqmeghandler = new Handler(){

        public void handleMessage(Message msg) {
            String msgStr = (String)msg.obj;
            switch (msgStr){
                case "finished":
                    ReqState = false;
                    obtainBut.setText("开始请求");
                    obtainBut.setBackgroundColor(Color.parseColor("#D7A6B7"));
                    executeCodeText.setText("点击 开始请求");
                    break;

                default:
                    executeCodeText.setText(msgStr);
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.business_analysis_layout);

        TextView titleView = (TextView)findViewById(R.id.textview_title_stocktype);
        titleName = getIntent().getStringExtra("name");
        titleView.setText(titleName);

        quitImageView = (ImageView)findViewById(R.id.imageView_title_quit);
        quitImageView.setOnClickListener(listenerQuit);

        executeCodeText = (TextView)findViewById(R.id.stock_code_textview);
        obtainBut = (Button)findViewById(R.id.get_stock_means_button);
        obtainBut.setOnClickListener(obtainButListner);

    }

    //@退出界面
    private View.OnClickListener listenerQuit = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            BusinessAnalysisActivity.this.finish();
        }
    };

    private View.OnClickListener obtainButListner = new View.OnClickListener(){
        @Override
        public void onClick(View view) {

//            String strResultDataSerious = JDIBridge.THS_DateSerial("002233.SZ","ths_open_price_stock;ths_high_price_stock;ths_low_stock;ths_close_price_stock;" +
//                    "ths_avg_price_stock;ths_vol_stock;ths_trans_num_stock;ths_amt_stock;ths_macd_stock;ths_kdj_stock;" +
//                    "ths_vstd_stock;ths_boll_stock;ths_rsi_stock;ths_ma_stock;ths_sar_stock;ths_wr_stock;ths_cci_stock;" +
//                    "ths_obv_stock;ths_vol_w_stock;ths_vol_m_stock","100;100;100;100;100;100;;;" +
//                    "26,12,9,100,100,100;9,3,3,100,100,100;10,100;26,2,100,100,100;6,100,100;" +
//                    "10,100,100;4,100,100;14,100,100;14,100,100;100,100,100;;",
//                    "Days:Tradedays,Fill:Previous,Interval:D","2018-05-31","2018-06-15");
//            System.out.println("THS_iFinDhis ==> " + strResultDataSerious );


            if(ReqState){
                ReqState = false;
                obtainBut.setText("开始请求");
                obtainBut.setBackgroundColor(Color.parseColor("#D7A6B7"));
            }else {
                ReqState = true;
                obtainBut.setText("停止请求");
                obtainBut.setBackgroundColor(Color.parseColor("#FF0000"));
            }

        }
    };


}
package com.example.stock.eink.pioneer.stockadd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.stock.R;


public class StockNewestDataActivity extends AppCompatActivity {

    private Context content;

    private String titleName;
    private ImageView quitImageView;

    private TextView executeCodeText;
    private Button obtainBut;

    private StockNewestDataReq newestDataReq;

    private boolean newestReqState = false;


    //handler+message处理添加股票时时信息更新
    private Handler newestreqhandler = new Handler(){

        public void handleMessage(Message msg) {
            String msgStr = (String)msg.obj;
            switch (msgStr){
                case "finished":
                    newestReqState = false;
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
        setContentView(R.layout.activity_stock_newest_data);

        this.content = this;

        TextView titleView = (TextView)findViewById(R.id.textview_title_stocktype);
        titleName = getIntent().getStringExtra("newestprice");
        titleView.setText(titleName);

        quitImageView = (ImageView)findViewById(R.id.imageView_title_quit);
        quitImageView.setOnClickListener(listenerQuit);

        executeCodeText = (TextView)findViewById(R.id.stock_newest_textview);
        obtainBut = (Button)findViewById(R.id.stock_newest_button);
        obtainBut.setOnClickListener(obtainButListner);

        newestDataReq = new StockNewestDataReq(newestreqhandler);

    }


    //@退出界面
    private View.OnClickListener listenerQuit = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            newestDataReq.stopReq();

            StockNewestDataActivity.this.finish();
        }
    };

    private View.OnClickListener obtainButListner = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            if(newestReqState){
                newestReqState = false;
                obtainBut.setText("开始请求");
                obtainBut.setBackgroundColor(Color.parseColor("#D7A6B7"));
                newestDataReq.stopReq();
            }else {
                newestReqState = true;
                obtainBut.setText("停止请求");
                obtainBut.setBackgroundColor(Color.parseColor("#FF0000"));
                newestDataReq.startReq();
            }

        }
    };


}
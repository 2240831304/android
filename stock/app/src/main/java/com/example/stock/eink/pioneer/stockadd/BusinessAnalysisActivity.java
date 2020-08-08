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
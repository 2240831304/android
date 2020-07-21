package com.example.stock.eink.pioneer.stockadd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.stock.R;


public class StockNewestDataActivity extends AppCompatActivity {

    private Context content;

    private String titleName;
    private ImageView quitImageView;

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

    }



    //@退出界面
    private View.OnClickListener listenerQuit = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            StockNewestDataActivity.this.finish();
        }
    };


}
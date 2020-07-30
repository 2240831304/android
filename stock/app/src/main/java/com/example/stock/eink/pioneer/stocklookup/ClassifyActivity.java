package com.example.stock.eink.pioneer.stocklookup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.stock.R;

public class ClassifyActivity extends AppCompatActivity {

    private String classifyName;
    private ImageView quitImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classify);

        TextView titleView = (TextView)findViewById(R.id.textview_title_stocktype);
        classifyName = getIntent().getStringExtra("name");
        titleView.setText(classifyName);

        quitImageView = (ImageView)findViewById(R.id.imageView_title_quit);
        quitImageView.setOnClickListener(listenerQuit);


    }

    //@退出界面
    private View.OnClickListener listenerQuit = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ClassifyActivity.this.finish();
        }
    };




}
package com.example.stock.eink.pioneer.stocklookup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.stock.R;

public class StockDetailsActivity extends AppCompatActivity {

    private StockDetailsDataHandle dataHandler;

    private  ImageView quitImageView;
    private String stockName;
    private TextView stockCodeTextView;

    private EditText calpricEdittext;
    private EditText gradeEdittext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stock_details_layout);

        quitImageView = (ImageView)findViewById(R.id.imageView_title_quit);
        quitImageView.setOnClickListener(listenerQuit);

        TextView titleView = (TextView)findViewById(R.id.textview_title_stockname);
        stockName = getIntent().getStringExtra("name");
        titleView.setText(stockName);

        stockCodeTextView = (TextView)findViewById(R.id.textview_title_stockcode);

        calpricEdittext = (EditText)findViewById(R.id.stock_calprice_editview);
        gradeEdittext = (EditText)findViewById(R.id.stock_grade_edit);
        Button saveCalpriceBut = (Button)findViewById(R.id.stock_savemcalpri_button);
        saveCalpriceBut.setOnClickListener(saveCalPriceListner);


        dataHandler = new StockDetailsDataHandle(this);
        dataHandler.ObatinStockInfo(stockName);
    }

    public void InitData(StockData data){
        stockCodeTextView.setText(data.code);
        calpricEdittext.setText(data.calPrice);
    }


    //@退出界面
    private View.OnClickListener listenerQuit = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            dataHandler.quit();
            StockDetailsActivity.this.finish();
        }
    };

    //保存预测价格
    private View.OnClickListener saveCalPriceListner = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            dataHandler.saveCalprice(stockCodeTextView.getText().toString(),
                    calpricEdittext.getText().toString(),gradeEdittext.getText().toString());
        }
    };


}
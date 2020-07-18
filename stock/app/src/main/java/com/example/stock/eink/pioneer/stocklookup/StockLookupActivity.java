package com.example.stock.eink.pioneer.stocklookup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.stock.R;

public class StockLookupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        TextView titleView = (TextView)findViewById(R.id.textview_title_stocktype);
//        stockTypeName = getIntent().getStringExtra("stockTypeName");
//        titleView.setText(stockTypeName);


        setContentView(R.layout.stock_lookup_layout);
    }
}
package com.example.stock.eink.pioneer.stocklookup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.stock.R;
import com.example.stock.eink.pioneer.stockadd.StockAddActivity;

public class StockLookupActivity extends AppCompatActivity {

    private String stockTypeName;
    private ImageView quitImageView;

    private StockLookupAdapter stcokListviewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stock_lookup_layout);

        TextView titleView = (TextView)findViewById(R.id.textview_title_stocktype);
        stockTypeName = getIntent().getStringExtra("name");
        titleView.setText(stockTypeName);

        quitImageView = (ImageView)findViewById(R.id.imageView_title_quit);
        quitImageView.setOnClickListener(listenerQuit);


        stcokListviewAdapter = new StockLookupAdapter(this);
        final ListView stcokListview = (ListView)findViewById(R.id.stock_lookup_listview);
        stcokListview.setAdapter(stcokListviewAdapter);

    }


    //@退出界面
    private View.OnClickListener listenerQuit = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            StockLookupActivity.this.finish();
        }
    };


}
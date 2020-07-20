package com.example.stock.eink.pioneer.stocklookup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.stock.R;
import com.example.stock.eink.pioneer.stockadd.StockAddActivity;

public class StockLookupActivity extends AppCompatActivity {

    private Context content;

    private String stockTypeName;
    private ImageView quitImageView;
    private Button selectBut;
    private TextView gradeEdittext;

    private StockLookupAdapter stcokListviewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stock_lookup_layout);

        this.content = this;

        TextView titleView = (TextView)findViewById(R.id.textview_title_stocktype);
        stockTypeName = getIntent().getStringExtra("name");
        titleView.setText(stockTypeName);

        quitImageView = (ImageView)findViewById(R.id.imageView_title_quit);
        quitImageView.setOnClickListener(listenerQuit);

        gradeEdittext = (EditText)findViewById(R.id.stock_grade_edittext);

        selectBut = (Button)findViewById(R.id.stock_select_button);
        selectBut.setOnClickListener(new SelectButListner());

        stcokListviewAdapter = new StockLookupAdapter(this);
        final ListView stcokListview = (ListView)findViewById(R.id.stock_lookup_listview);
        stcokListview.setAdapter(stcokListviewAdapter);
        stcokListview.setOnItemClickListener(stcokListviewListener);

    }


    private AdapterView.OnItemClickListener stcokListviewListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            TextView tmp = (TextView)view.findViewById(R.id.stock_cn_name);
            StockLookUpHandle handler = new StockLookUpHandle(content,tmp.getText().toString());
            handler.createStockDetailsUI();
        }
    };

    private class SelectButListner implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            //System.out.println("StockLookupActivity SelectButListner is start!!!");
        }
    }

    //@退出界面
    private View.OnClickListener listenerQuit = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            StockLookupActivity.this.finish();
        }
    };


}
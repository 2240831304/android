package com.example.stock.eink.pioneer.stocklookup;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.stock.R;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

public class StockDetailsActivity extends AppCompatActivity {

    private StockDetailsDataHandle dataHandler;

    private  ImageView quitImageView;
    private String stockName;
    private TextView stockCodeTextView;

    private EditText calpricEdittext;
    private EditText gradeEdittext;
    private EditText meansEdittext;

    private TextView curpriceTextview;
    private TextView grapTextview;
    private TextView yearMinpriceTextview;
    private TextView yearMaxpriceTextview;
    private TextView calpriceTextview;

    @Override
    protected void onPause() {
        super.onPause();

        //关闭输入法键盘，如果需要
        if (getCurrentFocus() != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(this.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

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

        curpriceTextview = (TextView)findViewById(R.id.stock_curprice_text);;
        grapTextview = (TextView)findViewById(R.id.stock_upanddown_text);;
        yearMinpriceTextview = (TextView)findViewById(R.id.stock_yearminprice);;
        yearMaxpriceTextview = (TextView)findViewById(R.id.stock_yearmaxprice);;
        calpriceTextview = (TextView)findViewById(R.id.stock_calprice);;

        //保存简介
        meansEdittext = (EditText)findViewById(R.id.stock_means_editview);
        Button saveMeansBut = (Button)findViewById(R.id.stock_savemeans_button);
        saveMeansBut.setOnClickListener(saveMeansListner);

        //保存预测价
        calpricEdittext = (EditText)findViewById(R.id.stock_calprice_editview);
        gradeEdittext = (EditText)findViewById(R.id.stock_grade_edit);
        Button saveCalpriceBut = (Button)findViewById(R.id.stock_savemcalpri_button);
        saveCalpriceBut.setOnClickListener(saveCalPriceListner);

        //添加分类
        Button addClassify = (Button)findViewById(R.id.add_classify_but);
        addClassify.setOnClickListener(addClassifyListner);

        dataHandler = new StockDetailsDataHandle(this);
        dataHandler.ObatinStockInfo(stockName);
    }

    public void InitData(StockData data){
        stockCodeTextView.setText(data.code);
        calpricEdittext.setText(data.calPrice);
        meansEdittext.setText(data.means);

        curpriceTextview.setText(data.curprice);
        grapTextview.setText(data.grap);
        yearMinpriceTextview.setText(data.yearMinPrice);
        yearMaxpriceTextview.setText(data.yearMaxPrice);
        calpriceTextview.setText(data.calPrice);
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

    //保存简介
    private View.OnClickListener saveMeansListner = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            dataHandler.saveMeans(stockCodeTextView.getText().toString(),meansEdittext.getText().toString());
        }
    };

    //添加分类
    private View.OnClickListener addClassifyListner = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            WindowManager windowManager = StockDetailsActivity.this.getWindowManager();
            AddClassifyDialog dialog = new AddClassifyDialog(StockDetailsActivity.this,dataHandler,
                    windowManager,stockName);
            dialog.show();
        }
    };


}
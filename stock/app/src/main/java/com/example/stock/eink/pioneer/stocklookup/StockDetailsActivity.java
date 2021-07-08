package com.example.stock.eink.pioneer.stocklookup;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import com.example.stock.eink.lib.filesystem.FileSystemManager;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
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
    private Button addClassifyBut;

    private  ImageView stock_price_image;

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
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.stock_details_layout);

        quitImageView = (ImageView)findViewById(R.id.imageView_title_quit);
        quitImageView.setOnClickListener(listenerQuit);

        stock_price_image = (ImageView)findViewById(R.id.stock_price_imageview);

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
        addClassifyBut = (Button)findViewById(R.id.add_classify_but);
        addClassifyBut.setOnClickListener(addClassifyListner);

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
        if((data.classify != null) && (data.classify.isEmpty() == false)){
            addClassifyBut.setText("已添加分类");
        }
        gradeEdittext.setText(data.state);

        FileSystemManager fileManeger = new FileSystemManager();
        String path = fileManeger.getImagePath() + data.code + ".gif";
        //System.out.println("4444444444============-----------="+path);
        Bitmap bitmap = BitmapFactory.decodeFile(path);
        stock_price_image.setImageBitmap(bitmap);

    }

    public void InitWeekImage()
    {
        //String bannerpath = "http://image.sinajs.cn/newchart/weekly/n/#.gif";
        //bannerpath = bannerpath.replace("#",stockCodeTextView.getText());
        //System.out.println("3333333333666666@@===" + bannerpath);
        //Bitmap bimage =  getBitmapFromURL(bannerpath);
        //stock_price_image.setImageBitmap(bimage);
    }

    public static Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
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
            //dialog.setOnDismissListener(dialogListener);
        }
    };

    //添加分类窗口关闭监听
    private DialogInterface.OnDismissListener dialogListener = new DialogInterface.OnDismissListener(){
        @Override
        public void onDismiss(DialogInterface dialogInterface) {
            System.out.println("55555555566666666666666666666");
        }
    };

    public void setAddClassifyButState(){
        addClassifyBut.setText("已添加分类");
    }

}
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
            Hashtable<String,Integer> classifyNameMap = dataHandler.getStockClassifyNames();
            if(classifyNameMap.isEmpty()){
                return;
            }
            Enumeration enu = classifyNameMap.keys();
            //while(enu.hasMoreElements()) {
            //    System.out.println(enu.nextElement());
            //}

            //Enumeration enuvalue = classifyNameMap.elements();
            //while(enuvalue.hasMoreElements()) {
            //    System.out.println(enuvalue.nextElement());
            //}

            final Dialog dialog = new Dialog(StockDetailsActivity.this);
            LayoutInflater inflater = LayoutInflater.from(StockDetailsActivity.this);
            View view12 =  inflater.inflate(R.layout.stock_classify_dialog,null);

            ListView testview = view12.findViewById(R.id.classifydialog_listview);
            AddClassifyDialogAdapter adapter = new AddClassifyDialogAdapter(StockDetailsActivity.this);
            testview.setAdapter(adapter);
            testview.setOnItemClickListener(addClassifyNameListener);
            ArrayList<String> classifyNameList = new ArrayList<String>();
            while(enu.hasMoreElements()) {
                //System.out.println(enu.nextElement());
                classifyNameList.add(enu.nextElement().toString());
            }
            adapter.setClassifyNameList(classifyNameList);

            //dialog.getWindow().setContentView(R.layout.stock_classify_dialog);
            dialog.getWindow().setContentView(view12);

            dialog.setCanceledOnTouchOutside(false);

            WindowManager windowManager = StockDetailsActivity.this.getWindowManager();
            Display display = windowManager.getDefaultDisplay();
            System.out.println(display.getWidth());

            //dialog.getWindow().getDecorView().setPadding(0,0,0,0);
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.white);

            // 设置宽度为屏宽, 靠近屏幕底部
            final WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
            lp.gravity = Gravity.BOTTOM; // 紧贴底部
            //lp.width = WindowManager.LayoutParams.FILL_PARENT;
            lp.width = display.getWidth();
            lp.height = display.getHeight() / 2;
            dialog.getWindow().setAttributes(lp);

            dialog.show();

        }
    };

    private AdapterView.OnItemClickListener addClassifyNameListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            CheckBox box = (CheckBox)view;
            if(box.isChecked()){
                box.setChecked(false);
            }else{
                box.setChecked(true);
            }
        }
    };


}
package com.example.stock.eink.pioneer.stocklookup;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;

import com.example.stock.R;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

public class AddClassifyDialog extends Dialog {

    private Context contextPt;
    private StockDetailsDataHandle dataHandler;
    Hashtable<String,Integer> classifyNameMap;
    String  stockClassifyList;
    WindowManager windowManager;
    String classifyName;
    String stockNamept;
    private boolean addclassifyState = false;
    private AddClassifyDialogAdapter adapter;

    public AddClassifyDialog(Context context,StockDetailsDataHandle databaseHandler,
                             WindowManager pt,String stockName){
        super(context);
        contextPt = context;
        dataHandler = databaseHandler;
        windowManager = pt;
        stockNamept = stockName;

        classifyNameMap = dataHandler.getStockClassifyNames();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Hashtable<String,Integer> classifyNameMap = dataHandler.getStockClassifyNames();
        //if(classifyNameMap.isEmpty()){
        //    return;
        //}
        Enumeration enu = classifyNameMap.keys();
        //while(enu.hasMoreElements()) {
        //    System.out.println(enu.nextElement());
        //}

        //Enumeration enuvalue = classifyNameMap.elements();
        //while(enuvalue.hasMoreElements()) {
        //    System.out.println(enuvalue.nextElement());
        //}

        //final Dialog dialog = new Dialog(contextPt);
        LayoutInflater inflater = LayoutInflater.from(contextPt);
        View dialogView =  inflater.inflate(R.layout.stock_classify_dialog,null);

        Button cancleBut = (Button)dialogView.findViewById(R.id.classify_dialog_canclebut);
        cancleBut.setOnClickListener(cancleListen);

        Button okBut = (Button)dialogView.findViewById(R.id.classify_dialog_okbut);
        okBut.setOnClickListener(okListen);

        ListView classifylistview = dialogView.findViewById(R.id.classifydialog_listview);
        adapter = new AddClassifyDialogAdapter(contextPt);
        classifylistview.setAdapter(adapter);

        classifylistview.setOnItemClickListener(addClassifyNameListener);

        stockClassifyList = dataHandler.getStockClassify(stockNamept);
        adapter.setStockClassify(stockClassifyList);

        ArrayList<String> classifyNameList = new ArrayList<String>();
        while(enu.hasMoreElements()) {
            //System.out.println(enu.nextElement());
            classifyNameList.add(enu.nextElement().toString());
        }
        adapter.setClassifyNameList(classifyNameList);

        //dialog.getWindow().setContentView(R.layout.stock_classify_dialog);
        this.getWindow().setContentView(dialogView);

        this.setCanceledOnTouchOutside(false);

        Display display = windowManager.getDefaultDisplay();
        //System.out.println(display.getWidth());

        //dialog.getWindow().getDecorView().setPadding(0,0,0,0);
        this.getWindow().setBackgroundDrawableResource(android.R.color.white);

        // 设置宽度为屏宽, 靠近屏幕底部
        final WindowManager.LayoutParams lp = this.getWindow().getAttributes();
        lp.gravity = Gravity.BOTTOM; // 紧贴底部
        //lp.width = WindowManager.LayoutParams.FILL_PARENT;
        lp.width = display.getWidth();
        lp.height = display.getHeight() / 2;
        this.getWindow().setAttributes(lp);
    }


    private AdapterView.OnItemClickListener addClassifyNameListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            CheckBox box = (CheckBox)view;
            classifyName = box.getText().toString();
            String strtmp = classifyName + "#";
            if(box.isChecked()){
                box.setChecked(false);
                stockClassifyList = stockClassifyList.replace(strtmp,"");
            }else{
                box.setChecked(true);
                stockClassifyList = stockClassifyList + strtmp;
            }
            adapter.setStockClassify(stockClassifyList);
            System.out.println("addClassifyNameListener @@@@@@@@@@@@@@@@@@="+stockClassifyList);
        }
    };


    private View.OnClickListener cancleListen = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            AddClassifyDialog.this.dismiss();
        }
    };


    private View.OnClickListener okListen = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if( stockClassifyList != null ){
                if(stockClassifyList.isEmpty() == false){
                    String classifyIndexIds = "#";
                    String [] stmList = stockClassifyList.split("#");
                    for (String value : stmList){
                        int classifyIndex = classifyNameMap.get(value);
                        classifyIndexIds = classifyIndexIds  + String.valueOf(classifyIndex) + "#";
                    }

                    System.out.println(classifyIndexIds);
                    dataHandler.setStockClassify(classifyIndexIds,stockNamept);
                }else {
                    dataHandler.setStockClassify("",stockNamept);
                }

                addclassifyState = true;
                StockDetailsActivity StockDetailsAcPt = (StockDetailsActivity)contextPt;
                StockDetailsAcPt.setAddClassifyButState();
            }
            AddClassifyDialog.this.dismiss();
        }
    };

    public boolean getAddClassifyState(){
        return addclassifyState;
    }

}

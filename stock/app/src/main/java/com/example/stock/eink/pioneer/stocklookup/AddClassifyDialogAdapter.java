package com.example.stock.eink.pioneer.stocklookup;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.stock.R;

import java.util.ArrayList;

public class AddClassifyDialogAdapter extends BaseAdapter {

    ArrayList<String> dataList;
    private Context contextPt;

    private String classifyList;

    public  AddClassifyDialogAdapter(Context context){
        contextPt = context;
    }

    public void setStockClassify(String classifyPt){
        classifyList = classifyPt;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        if(view == null){
            CheckBox box = new CheckBox(contextPt);
            box.setText(dataList.get(position));
            box.setFocusable(false);
            box.setClickable(false);
            if(classifyList.contains(dataList.get(position))){
                box.setChecked(true);
            }else {
                box.setChecked(false);
            }

            view = box;
        }else{
            CheckBox box = (CheckBox)view;
            box.setText(dataList.get(position));
            box.setFocusable(false);
            box.setClickable(false);
            if(classifyList.contains(dataList.get(position))){
                box.setChecked(true);
            }else {
                box.setChecked(false);
            }
        }

        return view;
    }

    @Override
    public int getCount() {
        if(dataList == null){
            return 0;
        }else {
            return dataList.size();
        }
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setClassifyNameList(ArrayList<String> stockList){
        dataList = stockList;
        notifyDataSetChanged();
    }

}

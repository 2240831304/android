package com.example.stock.eink.pioneer.stocklookup;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import java.util.Hashtable;

public class ClassifyNameAdapter extends BaseAdapter {

    private Hashtable<Integer,String> classifyNameMap;
    private Context contextpt;

    public ClassifyNameAdapter(Context context){
        contextpt = context;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        if(view == null){
            Button nameBut = new Button(contextpt);
            nameBut.setFocusable(false);
            nameBut.setClickable(false);
            nameBut.setText(classifyNameMap.get(position));

            view = nameBut;
        }else{
            Button nameBut = (Button)view;
            nameBut.setText(classifyNameMap.get(position));
        }
        return view;
    }

    public void setInitClassifyName(Hashtable<Integer,String> nameMap){
        classifyNameMap = nameMap;

        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if(classifyNameMap == null){
            return 0;
        }else {
            return classifyNameMap.size();
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

}

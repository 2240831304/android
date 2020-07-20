package com.example.stock.eink.pioneer.stocklookup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.stock.R;

import java.util.ArrayList;

public class StockLookupAdapter extends BaseAdapter {

    private Context context;

    private ArrayList<StockData> dataList ;

    public StockLookupAdapter(Context contextpt){
        context = contextpt;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        StockData data = dataList.get(position);

        if(view == null){
            view = LayoutInflater.from(this.context).inflate(R.layout.stock_lookup_listviewitem,
                    viewGroup,false);

            TextView textview = (TextView)view.findViewById(R.id.stock_cn_name);
            textview.setText(data.name);

            textview = (TextView)view.findViewById(R.id.stock_code);
            textview.setText(data.code);

        }


        return view;
    }

    @Override
    public int getCount() {
        if(dataList == null){
            return 0;
        } else {
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

    public void setData(ArrayList<StockData> data){

        if (dataList != null){
            dataList.clear();
        }
        dataList = data;
    }

}

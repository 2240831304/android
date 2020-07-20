package com.example.stock.eink.pioneer.stocklookup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.stock.R;

public class StockLookupAdapter extends BaseAdapter {

    private Context context;

    public StockLookupAdapter(Context contextpt){
        context = contextpt;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        if(view == null){
            view = LayoutInflater.from(this.context).inflate(R.layout.stock_lookup_listviewitem,
                    viewGroup,false);
        }


        return view;
    }

    @Override
    public int getCount() {
        return 15;
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

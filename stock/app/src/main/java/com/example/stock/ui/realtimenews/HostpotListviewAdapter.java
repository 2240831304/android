package com.example.stock.ui.realtimenews;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

public class HostpotListviewAdapter extends BaseAdapter {

    private List<String> items = Arrays.asList("1","2","3","4","5","6","7","8","9",
            "11","12","13","14","15","16","17");

    private Context context;

    public HostpotListviewAdapter(Context contextpt){
        context = contextpt;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            TextView v = new TextView(context);
            v.setText(items.get(i));
            v.setTextSize(28);
            view = v;

        }else {
            TextView v = (TextView)view;
            v.setText(items.get(i));
        }
        return view;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

}

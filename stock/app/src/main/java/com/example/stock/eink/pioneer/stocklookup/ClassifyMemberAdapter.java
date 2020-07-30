package com.example.stock.eink.pioneer.stocklookup;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class ClassifyMemberAdapter extends BaseAdapter {

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        return view;
    }

    @Override
    public int getCount() {
        return 0;
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

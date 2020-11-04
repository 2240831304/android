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
            "10","11","12","13","14","15","16","17");

    private Context context;

    private final int TYPE_1 = 1;
    private final int TYPE_2 = 2;

    public HostpotListviewAdapter(Context contextpt){
        context = contextpt;
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        int type = getItemViewType(i);

        if(view == null){

            switch (type){
                case TYPE_1:
                    TextView v = new TextView(context);
                    v.setText(items.get(i));
                    v.setTextSize(28);
                    view = v;
                    break;
                case TYPE_2:
                    TextView v1 = new TextView(context);
                    v1.setText(items.get(i));
                    v1.setTextSize(38);
                    view = v1;
                    break;
            }

        }else {
            switch (type){
                case TYPE_1:
                    TextView v = (TextView)view;
                    v.setText(items.get(i));
                    v.setTextSize(28);
                    break;
                case TYPE_2:
                    TextView v1 =  (TextView)view;
                    v1.setText(items.get(i));
                    v1.setTextSize(38);
                    break;
            }
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

        if((position % 2) == 0){
            return TYPE_1;
        }else {
            return  TYPE_2;
        }

    }

    @Override
    public int getViewTypeCount() {
        return super.getViewTypeCount();
    }

}

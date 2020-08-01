package com.example.stock.eink.pioneer.stocklookup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.stock.R;

import java.util.ArrayList;

public class ClassifyMemberAdapter extends BaseAdapter {

    ArrayList<StockData> dataList;
    private Context context;

    public ClassifyMemberAdapter(Context contextpt){
        context = contextpt;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        StockData data = dataList.get(position);

        if(view == null){
            view = LayoutInflater.from(this.context).inflate(R.layout.classifymemberitem,
                    viewGroup,false);

            TextView textview = (TextView)view.findViewById(R.id.stock_cn_name);
            textview.setText(data.name);

            textview = (TextView)view.findViewById(R.id.stock_code);
            textview.setText(data.code);

            //@price
            textview = (TextView)view.findViewById(R.id.stock_cur_price);
            textview.setText(data.curprice);

            textview = (TextView)view.findViewById(R.id.stock_minprice);
            textview.setText(data.yearMinPrice);

            textview = (TextView)view.findViewById(R.id.stock_maxprice);
            textview.setText(data.yearMaxPrice);

            textview = (TextView)view.findViewById(R.id.stock_means);
            textview.setText(data.means);

        }else{
            TextView textview = (TextView)view.findViewById(R.id.stock_cn_name);
            textview.setText(data.name);

            textview = (TextView)view.findViewById(R.id.stock_code);
            textview.setText(data.code);

            //@price
            textview = (TextView)view.findViewById(R.id.stock_cur_price);
            textview.setText(data.curprice);

            textview = (TextView)view.findViewById(R.id.stock_minprice);
            textview.setText(data.yearMinPrice);

            textview = (TextView)view.findViewById(R.id.stock_maxprice);
            textview.setText(data.yearMaxPrice);

            textview = (TextView)view.findViewById(R.id.stock_means);
            textview.setText(data.means);
        }

        return view;
    }

    @Override
    public int getCount() {
        if(dataList == null){
            dataList = new ArrayList<StockData>();
            StockData data = new StockData();
            data.name = "测试股票";
            data.code = "测试";
            data.curprice = "测试";
            data.yearMinPrice = "测试";
            data.yearMaxPrice = "测试";
            data.means = "测试";
            dataList.add(data);
            return 1;
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

    public void setStockList(ArrayList<StockData> stockList){
        if (dataList != null){
            dataList.clear();
        }

        dataList = stockList;
        notifyDataSetChanged();
    }

}

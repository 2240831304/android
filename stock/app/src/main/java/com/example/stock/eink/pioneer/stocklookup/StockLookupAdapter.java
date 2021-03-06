package com.example.stock.eink.pioneer.stocklookup;

import android.content.Context;
import android.graphics.Color;
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

            if( (data.classify == null) || (data.classify.isEmpty()) ){
                textview.setTextColor(Color.parseColor("#0000EE"));
            }else {
                textview.setTextColor(Color.parseColor("#808080"));
            }

            textview = (TextView)view.findViewById(R.id.stock_code);
            textview.setText(data.code);
            //int intColor = textview.getCurrentTextColor();
            //String hexColor = String.format("#%06X", (0xFFFFFF & intColor));
            //System.out.println(hexColor);

            //@price
            textview = (TextView)view.findViewById(R.id.stock_cur_price);
            textview.setText(data.curprice);

            textview = (TextView)view.findViewById(R.id.stock_graprice);
            textview.setText(data.grap);

            textview = (TextView)view.findViewById(R.id.stock_minprice);
            textview.setText(data.yearMinPrice);

            textview = (TextView)view.findViewById(R.id.stock_maxprice);
            textview.setText(data.yearMaxPrice);

            textview = (TextView)view.findViewById(R.id.stock_calprice);
            textview.setText(data.calPrice);

            textview = (TextView)view.findViewById(R.id.stock_means);
            textview.setText(data.means);

            if((data.classify == null) || (data.classify == "")){
                System.out.println("==================");
                textview.setTextColor(Color.parseColor("#0000FF"));
            }else{
                System.out.println("===============---------"+data.classify);
                textview.setTextColor(Color.parseColor("#FF0000"));
            }

        }else{
            TextView textview = (TextView)view.findViewById(R.id.stock_cn_name);
            textview.setText(data.name);
            if( (data.classify == null) || (data.classify.isEmpty()) ){
                textview.setTextColor(Color.parseColor("#0000EE"));
            }else {
                textview.setTextColor(Color.parseColor("#808080"));
            }

            textview = (TextView)view.findViewById(R.id.stock_code);
            textview.setText(data.code);

            //@price
            textview = (TextView)view.findViewById(R.id.stock_cur_price);
            textview.setText(data.curprice);

            textview = (TextView)view.findViewById(R.id.stock_graprice);
            textview.setText(data.grap);

            textview = (TextView)view.findViewById(R.id.stock_minprice);
            textview.setText(data.yearMinPrice);

            textview = (TextView)view.findViewById(R.id.stock_maxprice);
            textview.setText(data.yearMaxPrice);

            textview = (TextView)view.findViewById(R.id.stock_calprice);
            textview.setText(data.calPrice);

            textview = (TextView)view.findViewById(R.id.stock_means);
            textview.setText(data.means);

            if((data.classify == null) || (data.classify == "")){
                System.out.println("-------------------");
                textview.setTextColor(Color.parseColor("#0000FF"));
            }else{
                System.out.println("$$$$$$$$$$$$$$$$$$--------");
                textview.setTextColor(Color.parseColor("#FF0000"));
            }
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

//        for (int i =0;i<dataList.size();i++){
//            StockData data12 = dataList.get(i);
//            System.out.println("2222222222222222="+data12.name);
//        }
        //System.out.println("2222222222222222="+data.size());
        notifyDataSetChanged();
    }

}

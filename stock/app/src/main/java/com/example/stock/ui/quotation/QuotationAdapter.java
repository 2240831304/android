package com.example.stock.ui.quotation;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.stock.R;

public class QuotationAdapter extends BaseAdapter {

    private Context context;
    private String[] boardNameList;
    private TypedArray IconIdList;

    private ImageView item_img;
    private TextView item_text;


    public QuotationAdapter(Context contextPt){
        context = contextPt;
    }

    public void setBoardNameList(String[] dataList){
        boardNameList = dataList;
    }

    public void setIconList(TypedArray data){
        IconIdList = data;
    }


    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        if(view == null){
            view = LayoutInflater.from(this.context).inflate(R.layout.imagetopontextitem,viewGroup,false);
            item_img = (ImageView)view.findViewById(R.id.item_img);
            item_text = (TextView)view.findViewById(R.id.item_text);
        }

        int IconId = this.IconIdList.getResourceId(position,-1);
        item_img.setImageResource(IconId);
        item_text.setText(boardNameList[position]);

        return view;
    }

    @Override
    public int getCount() {
        return boardNameList.length;
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

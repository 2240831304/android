package com.example.stock.ui.stockpick;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.stock.R;


public class StockPickAdapter extends BaseAdapter{

    private Context context; //声明适配器中引用的上下文
    private String[] nameList = null;
    private Button button;

    public StockPickAdapter(Context context, String[] data){
        this.context = context;
        this.nameList = data;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        String tmpStr = this.context.getResources().getString();

        if( view == null){
            //Button nameBut = new Button(context);
            //view = nameBut;

            view = LayoutInflater.from(this.context).inflate(R.layout.stockpickbutitem,viewGroup,false);
            /*
            view.getLayoutParams().height = 100;
            view.getLayoutParams().width = 100;
            */
            this.button = (Button)view.findViewById(R.id.item_button);
            this.button.setText(this.nameList[position]);
        }else{
            this.button = (Button)view.findViewById(R.id.item_button);
            this.button.setText(this.nameList[position]);
        }

        return view;
    }

    @Override
    public int getCount() {
        return nameList.length;
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

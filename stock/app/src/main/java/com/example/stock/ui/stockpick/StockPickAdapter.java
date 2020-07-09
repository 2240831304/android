package com.example.stock.ui.stockpick;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.renderscript.Allocation;
import android.util.Log;
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
    private String[] enNameList = null;
    private String[] PictureList = null;

    public StockPickAdapter(Context context, String[] data){
        this.context = context;
        this.nameList = data;
    }

    public void setStockTypePic(String[] data){
        this.PictureList = data;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        if( view == null){
            //Button nameBut = new Button(context);
            //view = nameBut;

            view = LayoutInflater.from(this.context).inflate(R.layout.stockpickbutitem,viewGroup,false);
            /*
            view.getLayoutParams().height = 100;
            view.getLayoutParams().width = 100;
            */
            this.button = (Button)view.findViewById(R.id.item_button);

            int id = context.getResources().obtainTypedArray(R.array.sss).getResourceId(0,0);
            Log.e("ssssss","id="+id);
            Drawable drawable = this.context.getResources().getDrawable(id);
            // 这一步必须要做,否则不会显示.
            drawable.setBounds(0, 0, drawable.getMinimumWidth(),drawable.getMinimumHeight());
            this.button.setCompoundDrawables(null, drawable, null, null);

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

package com.example.stock.ui.stockpick;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.stock.R;
import com.example.stock.eink.pioneer.stockadd.StockAddHandle;

import java.io.IOException;

public class StockPickFrament extends Fragment {

    private StockPickAdapter stockTypeAdapter;
    private Context content;
    private StockPickModel spickModel;

    private String[] stockTypeNameList;
    private TypedArray stockTypeIconList;


    private StockPickAdapter stockCurPriceAdapter;
    private String[] stockCurPriceNameList;
    private TypedArray stockCurPriceIconList;


    public StockPickFrament() throws IOException {
        //spickModel = new StockPickModel();
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        this.content = getActivity().getApplicationContext();
        this.stockTypeNameList = getActivity().getResources().getStringArray(R.array.stock_type_cn_name);
        //System.out.println(this.stockTypeNameList[0]);
        stockTypeIconList = getActivity().getResources().obtainTypedArray(R.array.stock_type_pic);

        View root = inflater.inflate(R.layout.stockpick, container, false);

        stockTypeAdapter = new StockPickAdapter(this.content,this.stockTypeNameList);
        stockTypeAdapter.setStockTypePic(stockTypeIconList);

        final GridView stockAddGridView = root.findViewById(R.id.stockpick_gridview_add);
        stockAddGridView.setAdapter(stockTypeAdapter);

        stockAddGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Button button = (Button)view.findViewById(R.id.item_button);
                StockAddHandle handle = new StockAddHandle(button.getText().toString());
                handle.execute(content);

            }
        });

        stockCurPriceNameList = getActivity().getResources().getStringArray(R.array.stock_current_price);
        stockCurPriceIconList = getActivity().getResources().obtainTypedArray(R.array.stock_current_price_icon);
        stockCurPriceAdapter = new  StockPickAdapter(this.content,stockCurPriceNameList);
        stockCurPriceAdapter.setStockTypePic(stockCurPriceIconList);
        final GridView stockLookUpGriView = root.findViewById(R.id.stockpick_gridview_lookup);
        stockLookUpGriView.setAdapter(stockCurPriceAdapter);

        final GridView stockMoneyGridView = root.findViewById(R.id.stockpick_gridview_money);
        stockMoneyGridView.setAdapter(stockCurPriceAdapter);

        return root;
    }


}



/*
gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(getActivity(), "position:"+i, Toast.LENGTH_SHORT).show();
    }
});
*/

//GridLayout addGridLayout = root.findViewById(R.id.stockpick_addgridlayout);
//for (int i = 0;i < NameList.length;++i) {
//    Button nameBut = new Button(this.content);
//    nameBut.setText(NameList[i]);
//    addGridLayout.addView(nameBut);
//}
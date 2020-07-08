package com.example.stock.ui.stockpick;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.stock.R;

import java.io.IOException;

public class StockPickFrament extends Fragment {

    private StockPickAdapter stockPickAdapter;
    private Context content;
    private StockPickModel spickModel;

    private String[] NameList;

    public StockPickFrament() throws IOException {
        //spickModel = new StockPickModel();
        //NameList = spickModel.getStockTypeList();
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        this.content = getActivity().getApplicationContext();
        this.NameList = getActivity().getResources().getStringArray(R.array.stock_type_cn_name);
        System.out.println(this.NameList[0]);

        View root = inflater.inflate(R.layout.stockpick, container, false);
        stockPickAdapter = new StockPickAdapter(this.content,this.NameList);

        final GridView gridView = root.findViewById(R.id.stockpick_gridview);
        gridView.setAdapter(stockPickAdapter);

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
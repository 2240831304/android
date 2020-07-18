package com.example.stock.ui.quotation;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.example.stock.R;
import com.example.stock.eink.pioneer.stocklookup.StockLookUpHandle;

public class QuotationFrament extends Fragment {

    private Context content;

    //股票版块
    private String[] boardNameList;
    private TypedArray boardIconList;
    private QuotationAdapter boardAdapter;

    //股票功能，概念
    private QuotationAdapter classifyAdapter;
    private String[] classifyNameList;
    private TypedArray classifyIconList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        this.content = getActivity().getApplicationContext();
        boardNameList = getActivity().getResources().getStringArray(R.array.stock_type_cn_name);
        boardIconList = getActivity().getResources().obtainTypedArray(R.array.stock_type_pic);


        View root = inflater.inflate(R.layout.quotation_layout, container, false);

        boardAdapter = new QuotationAdapter(content);
        boardAdapter.setBoardNameList(boardNameList);
        boardAdapter.setIconList(boardIconList);

        final GridView boardLookupGridview = root.findViewById(R.id.board_lookup);
        boardLookupGridview.setAdapter(boardAdapter);
        boardLookupGridview.setOnItemClickListener(boardLookupListener);

        classifyNameList = getActivity().getResources().getStringArray(R.array.stock_classify_cn_name);
        classifyIconList = getActivity().getResources().obtainTypedArray(R.array.stock_class_icon);
        classifyAdapter = new QuotationAdapter(content);
        classifyAdapter.setBoardNameList(classifyNameList);
        classifyAdapter.setIconList(classifyIconList);

        final GridView classifyGridview = root.findViewById(R.id.board_classification);
        classifyGridview.setAdapter(classifyAdapter);

        return root;
    }


    private AdapterView.OnItemClickListener boardLookupListener = new AdapterView.OnItemClickListener(){
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            TextView tmp = (TextView)view.findViewById(R.id.item_text);
            StockLookUpHandle handle = new StockLookUpHandle(content,tmp.getText().toString());
            handle.createBoardLookupUI();
        }
    };


}
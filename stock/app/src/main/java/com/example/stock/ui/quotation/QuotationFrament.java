package com.example.stock.ui.quotation;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.stock.R;

public class QuotationFrament extends Fragment {

    private Context content;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        this.content = getActivity().getApplicationContext();

        View root = inflater.inflate(R.layout.quotation_layout, container, false);


        return root;
    }
}
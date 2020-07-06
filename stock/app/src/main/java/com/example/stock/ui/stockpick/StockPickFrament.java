package com.example.stock.ui.stockpick;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.stock.R;

public class StockPickFrament extends Fragment {
    private Button button;
    private String[] provinceNames = new String[]{"北京"};

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View root = inflater.inflate(R.layout.stockpick, container, false);
        final GridView gridView = root.findViewById(R.id.stockpick_gridview);


        return root;
    }
}

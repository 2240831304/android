package com.example.stock.ui.realtimenews;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.stock.R;

import java.util.Arrays;
import java.util.List;

public class HotspotFrame extends Fragment {

    private ListView hostpotListview;
    private HostpotListviewAdapter listadapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.hostpotframelayout, container, false);

        hostpotListview = root.findViewById(R.id.hostpotframelistview);
        listadapter = new HostpotListviewAdapter(this.getContext());
        hostpotListview.setAdapter(listadapter);

        return root;
    }

}



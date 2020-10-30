package com.example.stock.ui.realtimenews;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HotspotFrame extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Button mBtnTabs = new Button(getActivity().getApplicationContext());
        mBtnTabs.setFocusable(true);
        mBtnTabs.setText("hhhhhhhhhhhhh");
        View v = (View)mBtnTabs;
        return v;
    }

}

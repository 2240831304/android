package com.example.stock.ui.realtimenews;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class VedioFrame extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        TextView textView = new TextView(getActivity().getApplicationContext());
        textView.setFocusable(false);
        textView.setClickable(false);
        textView.setTextColor(Color.RED);
        textView.setText("视频专区");
        View v = (View)textView;
        return v;
    }


}

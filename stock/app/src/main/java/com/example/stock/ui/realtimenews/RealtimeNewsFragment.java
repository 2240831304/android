package com.example.stock.ui.realtimenews;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabWidget;

import com.example.stock.R;

import java.util.Timer;
import java.util.TimerTask;


public class RealtimeNewsFragment extends Fragment {

    private String[] tabtitle = { "first", "second", "觉得基督","third","four","five","server","解决的角度" };

    private ViewPager mViewPager;
    private MyPagerAdapter mPagerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.realtime_news_layout, container, false);

        mViewPager = root.findViewById(R.id.newsviewpager);
        mPagerAdapter = new MyPagerAdapter(getFragmentManager(),tabtitle.length);
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.addOnPageChangeListener(mPageChangeListener);

        return root;

    }


    private ViewPager.OnPageChangeListener mPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int arg0)
        {
            System.out.println("onPageSelected 44444444444444444444444444444444444");
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2)
        {
            System.out.println("onPageScrolled 44444444444444444444444444444444444");
        }

        @Override
        public void onPageScrollStateChanged(int arg0)
        {
            System.out.println("onPageScrollStateChanged 44444444444444444444444444444444444");
        }

    };


}
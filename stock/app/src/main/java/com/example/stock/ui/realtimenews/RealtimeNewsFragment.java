package com.example.stock.ui.realtimenews;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
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
    private ViewPager mViewPager;
    private MyPagerAdapter mPagerAdapter;

    private RecyclerView RecyclerViewTab;

    private int tabsLenth = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.realtime_news_layout, container, false);

        RecyclerViewTab = root.findViewById(R.id.newsrecyclerview_tab);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        RecyclerViewTab.setLayoutManager(linearLayoutManager);
        RecyclerViewAdapter RecyclerViewAdapterPt = new RecyclerViewAdapter();
        RecyclerViewTab.setAdapter(RecyclerViewAdapterPt);

        mViewPager = root.findViewById(R.id.newsviewpager);
        tabsLenth = RecyclerViewAdapterPt.getItemCount();
        mPagerAdapter = new MyPagerAdapter(getFragmentManager(),tabsLenth);
        mPagerAdapter.setTabs(RecyclerViewAdapterPt.getTabs());

        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.addOnPageChangeListener(mPageChangeListener);

        RecyclerViewAdapterPt.setTabOnItemClickListener(tabListener);

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

    private RecyclerViewAdapter.TabOnItemClickListener tabListener = new RecyclerViewAdapter.TabOnItemClickListener(){
        @Override
        public void onItemClick(String tabName, int position,int tabIndex) {
            System.out.println("onItemClick 55555555555555555555555");
            mPagerAdapter.setTabName(tabName);
            mViewPager.setCurrentItem(tabIndex);
        }

        @Override
        public void onItemLongClick(String tabName, int position,int tabIndex) {
            System.out.println("onItemLongClick 55555555555555555555555555");
            mPagerAdapter.setTabName(tabName);
            mViewPager.setCurrentItem(tabIndex);

        }
    };



}
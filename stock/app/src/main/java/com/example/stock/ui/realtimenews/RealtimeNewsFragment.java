package com.example.stock.ui.realtimenews;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

    private CenterLayoutManager linearLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.realtime_news_layout, container, false);

        RecyclerViewTab = root.findViewById(R.id.newsrecyclerview_tab);
        //LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext());
        linearLayoutManager = new CenterLayoutManager(this.getContext());
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

        System.out.println("RealtimeNewsFragment  onCreateView 33333333333333333");
        return root;

    }


    private ViewPager.OnPageChangeListener mPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int arg0)
        {
            //System.out.println("onPageSelected 44444444444444444444444444444444444");
            //RecyclerViewTab.smoothScrollToPosition(arg0);
            ((RecyclerViewAdapter) RecyclerViewTab.getAdapter() ).setGlobleIndex(arg0);
            linearLayoutManager.smoothScrollToPosition(RecyclerViewTab, new RecyclerView.State(), arg0);
            RecyclerViewTab.getAdapter().notifyDataSetChanged();
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2)
        {
        }

        @Override
        public void onPageScrollStateChanged(int arg0)
        {
            //System.out.println("onPageScrollStateChanged 44444444444444444444444444444444444");
        }

    };

    private RecyclerViewAdapter.TabOnItemClickListener tabListener = new RecyclerViewAdapter.TabOnItemClickListener(){
        @Override
        public void onItemClick(View view, int position,int tabIndex) {
            mViewPager.setCurrentItem(tabIndex);
        }

        @Override
        public void onItemLongClick(View view, int position,int tabIndex) {
            mViewPager.setCurrentItem(tabIndex);

        }
    };


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("RealtimeNewsFragment  onCreate 33333333333333333");

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        System.out.println("RealtimeNewsFragment  onAttach 33333333333333333");
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        System.out.println("RealtimeNewsFragment  onViewCreated 33333333333333333");
    }

    @Override
    public void onStart() {
        super.onStart();
        System.out.println("RealtimeNewsFragment  onStart 33333333333333333");
    }

    @Override
    public void onResume() {
        super.onResume();
        System.out.println("RealtimeNewsFragment  onResume 33333333333333333");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        System.out.println("RealtimeNewsFragment  onActivityCreated 33333333333333333");
    }

    @Override
    public void onPause() {
        super.onPause();
        System.out.println("RealtimeNewsFragment  onPause 33333333333333333");
    }

    @Override
    public void onStop() {
        super.onStop();
        System.out.println("RealtimeNewsFragment  onStop 33333333333333333");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("RealtimeNewsFragment  onDestroy 33333333333333333");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        System.out.println("RealtimeNewsFragment  onDetach 33333333333333333");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        System.out.println("RealtimeNewsFragment  onDestroyView 33333333333333333");
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        System.out.println("RealtimeNewsFragment  onSaveInstanceState 33333333333333333");
    }


}
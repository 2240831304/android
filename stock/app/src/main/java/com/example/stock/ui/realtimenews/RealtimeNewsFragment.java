package com.example.stock.ui.realtimenews;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TabWidget;

import com.example.stock.R;

import java.util.Timer;
import java.util.TimerTask;


public class RealtimeNewsFragment extends Fragment {

    private ImageView realtimenewsImage;

    // 定义开始等待时间  --- 等待 1 秒
    private long delay = 2000;

    // 定义每次执行的间隔时间
    private long intevalPeriod = 2000;

    private int index = 1;
    private Timer timer;

    private String[] tabtitle = { "first", "second", "觉得基督","third","four","five","server","解决的角度" };
    private TabWidget mTabWidget;
    private ViewPager mViewPager;

    private MyPagerAdapter mPagerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.realtime_news_layout, container, false);

        //realtimenewsImage = root.findViewById(R.id.realtimenews_imageview);

        //timer = new Timer();
        //timer.scheduleAtFixedRate(task, delay, intevalPeriod);

        mTabWidget = (TabWidget) root.findViewById(R.id.newstabWidget);
        mTabWidget.setStripEnabled(false);
        for (int i = 0;i < tabtitle.length;i++){
            Button mBtnTabs = new Button(getActivity().getApplicationContext());
            mBtnTabs.setFocusable(true);
            mBtnTabs.setText(tabtitle[i]);
            mBtnTabs.setOnClickListener(mTabClickListener);
            mTabWidget.addView(mBtnTabs);
        }

        mViewPager = (ViewPager) root.findViewById(R.id.newsviewpager);
        mPagerAdapter = new MyPagerAdapter(getFragmentManager(),tabtitle.length);
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.setOnPageChangeListener(mPageChangeListener);

        mTabWidget.setCurrentTab(0);

        return root;

    }


    private View.OnClickListener mTabClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v)
        {
            Button mBtnTabs = (Button) v;
            mBtnTabs.setBackgroundColor(Color.parseColor("#FFFACD"));
            mViewPager.setCurrentItem(1);
        }
    };

    private ViewPager.OnPageChangeListener mPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int arg0)
        {
            mTabWidget.setCurrentTab(arg0);
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2)
        {

        }

        @Override
        public void onPageScrollStateChanged(int arg0)
        {

        }
    };

    private TimerTask task = new TimerTask() {
        @Override
        public void run() {
            // 执行的输出的内容
            if (index < 3){
                index = index + 1;
            }else{
                index = 1;
            }

            switch (index){
                case 1:
                    System.out.println("144444444444444444444444444");
                    //realtimenewsImage.setImageResource(R.mipmap.first);
                    break;
                case 2:
                    System.out.println("244444444444444444444444444");
                    //realtimenewsImage.setImageResource(R.mipmap.second);
                    break;
                case 3:
                    System.out.println("344444444444444444444444444");
                    //realtimenewsImage.setImageResource(R.mipmap.thrid);
                    break;
            }

        }
    };

}
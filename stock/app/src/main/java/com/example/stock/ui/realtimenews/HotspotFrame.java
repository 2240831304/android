package com.example.stock.ui.realtimenews;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.stock.R;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HotspotFrame extends Fragment {

    private ListView hostpotListview;
    private HostpotListviewAdapter listadapter;

    private Banner Rotationbanner;

    List<Integer> imageUrlData;
    List<String> contentData;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.hostpotframelayout, container, false);

        Rotationbanner = root.findViewById(R.id.hostpotframebanner);

        imageUrlData = new ArrayList<>();
        contentData = new ArrayList<>();

        imageUrlData.add(R.mipmap.first);
        imageUrlData.add(R.mipmap.second);
        imageUrlData.add(R.mipmap.thrid);

        contentData.add("我是雷神");
        contentData.add("我是小美");
        contentData.add("我是洛基");

        Rotationbanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        Rotationbanner.setImageLoader(new MyLoader());
        Rotationbanner.setImages(imageUrlData);
        Rotationbanner.setBannerTitles(contentData);
        Rotationbanner.setBannerAnimation(Transformer.Default);
        //切换频率
        Rotationbanner.setDelayTime(2000);
        //自动启动
        Rotationbanner.isAutoPlay(true);
        //位置设置
        Rotationbanner.setIndicatorGravity(BannerConfig.CENTER);
        //开始运行
        Rotationbanner.start();


        hostpotListview = root.findViewById(R.id.hostpotframelistview);
        listadapter = new HostpotListviewAdapter(this.getContext());
        hostpotListview.setAdapter(listadapter);

        System.out.println("HotspotFrame  onCreateView 33333333333333333");
        return root;
    }


    private class MyLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(getActivity()).load(path).into(imageView);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("HotspotFrame  onCreate 33333333333333333");

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        System.out.println("HotspotFrame  onAttach 33333333333333333");
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        System.out.println("HotspotFrame  onViewCreated 33333333333333333");
    }

    @Override
    public void onStart() {
        super.onStart();
        System.out.println("HotspotFrame  onStart 33333333333333333");
    }

    @Override
    public void onResume() {
        super.onResume();
        System.out.println("HotspotFrame  onResume 33333333333333333");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        System.out.println("HotspotFrame  onActivityCreated 33333333333333333");
    }

    @Override
    public void onPause() {
        super.onPause();
        System.out.println("HotspotFrame  onPause 33333333333333333");
    }

    @Override
    public void onStop() {
        super.onStop();
        System.out.println("HotspotFrame  onStop 33333333333333333");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("HotspotFrame  onDestroy 33333333333333333");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        System.out.println("HotspotFrame  onDetach 33333333333333333");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        System.out.println("HotspotFrame  onDestroyView 33333333333333333");
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        System.out.println("HotspotFrame  onSaveInstanceState 33333333333333333");
    }
}



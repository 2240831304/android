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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

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

        return root;
    }


    private class MyLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(getActivity()).load(path).into(imageView);
        }
    }


}



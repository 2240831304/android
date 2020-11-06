package com.example.stock.ui.realtimenews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.stock.R;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HostpotListviewAdapter extends BaseAdapter {

    private List<String> items = Arrays.asList("","1","2","3","4","5","6","7","8","9",
            "10","11","12","13","14","15","16","17");

    private Context context;

    private final int TYPE_1 = 1;
    private final int TYPE_2 = 2;
    private final int TYPE_3 = 3;

    private Banner Rotationbanner;

    List<Integer> imageUrlData;
    List<String> contentData;

    public HostpotListviewAdapter(Context contextpt){
        context = contextpt;

        imageUrlData = new ArrayList<>();
        contentData = new ArrayList<>();

        imageUrlData.add(R.mipmap.first);
        imageUrlData.add(R.mipmap.second);
        imageUrlData.add(R.mipmap.thrid);

        contentData.add("我是雷神");
        contentData.add("我是小美");
        contentData.add("我是洛基");

    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        int type = getItemViewType(i);

        if(view == null){

            switch (type){
                case TYPE_1:
                    view = LayoutInflater.from(this.context).inflate(R.layout.rotationbanner,
                            viewGroup,false);
                    Rotationbanner = view.findViewById(R.id.hostpotframebanner);

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
                    break;

                case TYPE_2:
                    TextView v = new TextView(context);
                    v.setText(items.get(i));
                    v.setTextSize(28);
                    view = v;
                    break;

                case TYPE_3:
                    TextView v1 = new TextView(context);
                    v1.setText(items.get(i));
                    v1.setTextSize(38);
                    view = v1;
                    break;
            }

        }else {
            switch (type){
                case TYPE_1:
                    TextView v = (TextView)view;
                    v.setText(items.get(i));
                    v.setTextSize(28);
                    break;
                case TYPE_2:
                    TextView v1 =  (TextView)view;
                    v1.setText(items.get(i));
                    v1.setTextSize(38);
                    break;
            }
        }

        return view;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getItemViewType(int position) {

        if(position == 0){
            return TYPE_1;
        } else if((position % 2) == 0){
            return TYPE_2;
        }else {
            return  TYPE_3;
        }

    }

    @Override
    public int getViewTypeCount() {
        return super.getViewTypeCount();
    }


    private class MyLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);
        }
    }

}

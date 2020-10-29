package com.example.stock.ui.realtimenews;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.realtime_news_layout, container, false);

        //realtimenewsImage = root.findViewById(R.id.realtimenews_imageview);

        //timer = new Timer();
        //timer.scheduleAtFixedRate(task, delay, intevalPeriod);

        return root;

    }

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
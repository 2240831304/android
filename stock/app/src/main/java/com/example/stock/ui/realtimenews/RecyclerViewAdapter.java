package com.example.stock.ui.realtimenews;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stock.R;

import java.util.Arrays;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private List<String> tabs = Arrays.asList("热点","视频","深圳","科技","财经","娱乐","汽车","影视");

    //内部类，绑定控件
    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tabName;
        public MyViewHolder(View itemView) {
            super(itemView);
            tabName = (TextView)itemView.findViewById(R.id.newstabstextview);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.newstabsitem,parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tabName.setText(tabs.get(position));
    }

    @Override
    public int getItemCount() {
        return tabs.size();
    }


}

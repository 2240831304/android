package com.example.stock.ui.realtimenews;

import android.graphics.Color;
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

    private List<String> tabs = Arrays.asList("热点","视频","奇思妙想","科技","财经","娱乐","汽车","影视","生活","历史");
    private TabOnItemClickListener tabClickListener;

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

    //在adapter中自定义一个接口 实现想要实现的方法
    public interface TabOnItemClickListener
    {
        //子条目点击事件
        void onItemClick(String tabName, int position,int tabIndex);
        //子条目长按事件
        void onItemLongClick(String tabName,int position,int tabIndex);
    }

    //回调方法 将接口传递进来
    public void setTabOnItemClickListener(TabOnItemClickListener mOnItemClickListener)
    {
        this.tabClickListener = mOnItemClickListener;
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder,final int position) {
        holder.tabName.setText(tabs.get(position));

        if (tabClickListener != null)
        {
            holder.tabName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    //返回对应view的信息
                    int pos = holder.getLayoutPosition();
                    holder.tabName.setTextColor(Color.parseColor("#ffff0000"));
                    tabClickListener.onItemClick(tabs.get(position),pos,position);
                }
            });

            holder.tabName.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    int pos = holder.getLayoutPosition();
                    tabClickListener.onItemLongClick(tabs.get(position),pos,position);
                    return false;
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return tabs.size();
    }

    public List<String> getTabs(){
        return tabs;
    }



}

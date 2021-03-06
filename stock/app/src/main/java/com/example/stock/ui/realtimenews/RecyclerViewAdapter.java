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

    private List<String> tabs = Arrays.asList("热点","视频","奇思妙想","科技","财经","娱乐会","汽车","影视","生活",
            "历史","杂谈","篮球明星","足球");
    private TabOnItemClickListener tabClickListener;

    private int globleIndex = 0;

    //内部类，绑定控件
    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tabNameView;
        public int index = 0;
        public int pos = 0;

        public MyViewHolder(View itemView) {
            super(itemView);
            tabNameView = (TextView)itemView.findViewById(R.id.newstabstextview);

            itemView.setTag(this);
            itemView.setOnClickListener(tabOnClickListener);
            itemView.setOnLongClickListener(tabOnLongClickListener);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
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
        void onItemClick(View view, int position,int tabIndex);
        //子条目长按事件
        void onItemLongClick(View view,int position,int tabIndex);
    }

    //回调方法 将接口传递进来
    public void setTabOnItemClickListener(TabOnItemClickListener mOnItemClickListener)
    {
        this.tabClickListener = mOnItemClickListener;
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder,final int position) {
        holder.tabNameView.setText(tabs.get(position));
        holder.index = position;
        if(globleIndex == position){
            holder.tabNameView.setTextColor(Color.parseColor("#ffff0000"));
        }else {
            holder.tabNameView.setTextColor(Color.parseColor("#ff000000"));
        }

    }

    private View.OnClickListener tabOnClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            MyViewHolder holderpt = (MyViewHolder)view.getTag();
            globleIndex = holderpt.index;
            tabClickListener.onItemClick(view,holderpt.getLayoutPosition(),holderpt.index);
        }
    };


    private View.OnLongClickListener tabOnLongClickListener = new View.OnLongClickListener(){
        @Override
        public boolean onLongClick(View view) {
            MyViewHolder holderpt = (MyViewHolder)view.getTag();
            globleIndex = holderpt.index;
            tabClickListener.onItemLongClick(view,holderpt.getLayoutPosition(),holderpt.index);
            return false;
        }
    };


    @Override
    public int getItemCount() {
        return tabs.size();
    }


    public List<String> getTabs(){
        return tabs;
    }

    public void setTabs(List<String> tabList)
    {
        tabs = tabList;
    }

    public void setGlobleIndex(int index)
    {
        globleIndex = index;
    }



}

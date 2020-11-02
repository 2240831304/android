package com.example.stock.ui.realtimenews;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.lang.ref.SoftReference;
import java.util.List;

public class MyPagerAdapter extends FragmentStatePagerAdapter
{

    private int nums = 0;
    private String tabName = "";
    private List<String> tabs ;

    public MyPagerAdapter(FragmentManager fm,int num)
    {
        super(fm);
        nums = num;
    }

    public void setTabName(String name)
    {
        tabName = name;
    }

    public void setTabs(List<String> tabList)
    {
        tabs = tabList;
    }


    @Override
    public Fragment getItem(int position)
    {
        if(tabs.get(position) == "热点"){
            return new HotspotFrame();
        }else if(tabs.get(position) == "视频"){
            return new VedioFrame();
        }else if(tabs.get(position) == "财经"){
            return new MoneyFrame();
        }
        else {
            return new HotspotFrame();
        }

    }

    @Override
    public int getCount()
    {
        return nums;
    }

}

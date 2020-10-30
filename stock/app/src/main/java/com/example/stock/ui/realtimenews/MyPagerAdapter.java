package com.example.stock.ui.realtimenews;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class MyPagerAdapter extends FragmentStatePagerAdapter
{

    private int nums = 0;

    public MyPagerAdapter(FragmentManager fm,int num)
    {
        super(fm);
        nums = num;
    }

    @Override
    public Fragment getItem(int position)
    {
        return new HotspotFrame();
    }

    @Override
    public int getCount()
    {
        return nums;
    }

}

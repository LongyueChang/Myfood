package com.fanwen.yuefoodhome.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.fanwen.yuefoodhome.R;

import java.util.List;

/**
 * Created by Administrator on 2016/11/10.
 */

public class ShopTagViewPagerAdapter extends FragmentPagerAdapter {

    private Context context;
    private List<Fragment> list;

    public ShopTagViewPagerAdapter(FragmentManager fm, Context context, List<Fragment> list) {
        super(fm);
        this.context = context;
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String[] titles=context.getResources().getStringArray(R.array.TagTitle);
        return titles[position];
    }
}

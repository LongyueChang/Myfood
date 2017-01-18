package com.fanwen.yuefoodhome.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.fanwen.yuefoodhome.R;

import java.util.List;

/**
 * Created by Administrator on 2016/11/9.
 */

public class ClubPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> list;
    private Context context;

    public ClubPagerAdapter(FragmentManager fm, List<Fragment> list, Context context) {
        super(fm);
        this.list = list;
        this.context = context;
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
        String[] titles=context.getResources().getStringArray(R.array.clubTitle);
        return titles[position];
    }
}

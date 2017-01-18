package com.fanwen.yuefoodhome.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fanwen.yuefoodhome.R;
import com.fanwen.yuefoodhome.adapter.ClubPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClubFragment extends Fragment {

    private List<Fragment> list;
    private ClubContentFragment clubContentFragment;
    private ClubPagerAdapter adapter;
    private ViewPager viewPager;
    private TabLayout tabLayout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_club, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewPager= (ViewPager) getView().findViewById(R.id.vp_club);
        tabLayout= (TabLayout) getView().findViewById(R.id.tl_club);
        initdata();
        viewPager.setOffscreenPageLimit(5);
        adapter=new ClubPagerAdapter(getFragmentManager(),list,getActivity());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void initdata() {
        list=new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            clubContentFragment=new ClubContentFragment();
            Bundle bundle=new Bundle();
            bundle.putInt("club",i);
            clubContentFragment.setArguments(bundle);
            list.add(clubContentFragment);
        }
    }
}

package com.fanwen.yuefoodhome.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.fanwen.yuefoodhome.R;
import com.fanwen.yuefoodhome.adapter.ShopTagViewPagerAdapter;
import com.fanwen.yuefoodhome.fragment.TagFragment;

import java.util.ArrayList;
import java.util.List;

public class ShopTagActivity extends AppCompatActivity {


    private ViewPager viewPager;
    private List<Fragment> list=new ArrayList<>();
    private ShopTagViewPagerAdapter adapter;
    private TagFragment tagFragment;
    private TextView tv_shopTag_title;
    private int index;
    private TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_tag);
        tv_shopTag_title= (TextView) findViewById(R.id.tv_shopTag_title);
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        index=bundle.getInt("index");
        tv_shopTag_title.setText(getResources().getStringArray(R.array.tagName)[index-1]);
        initData();
        viewPager= (ViewPager) findViewById(R.id.vp_shopTag);
        tabLayout= (TabLayout) findViewById(R.id.tl_shopTag);
        viewPager.setOffscreenPageLimit(4);
        adapter=new ShopTagViewPagerAdapter(getSupportFragmentManager(),this,list);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void initData() {
        for (int i = 0; i < 4; i++) {
            tagFragment=new TagFragment();
            Bundle bundle=new Bundle();
            bundle.putInt("index",index);
            bundle.putInt("rank",i);
            tagFragment.setArguments(bundle);
            list.add(tagFragment);
        }
    }

    public void click(View view) {
        finish();
    }


}

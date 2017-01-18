package com.fanwen.yuefoodhome.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.RadioGroup;

import com.fanwen.yuefoodhome.R;
import com.fanwen.yuefoodhome.fragment.CartFragment;
import com.fanwen.yuefoodhome.fragment.ClubFragment;
import com.fanwen.yuefoodhome.fragment.HomeFragment;
import com.fanwen.yuefoodhome.fragment.MeFragment;
import com.fanwen.yuefoodhome.fragment.ShopFragment;

import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RadioGroup radioGroup;
    private List<Fragment> list;
    private FragmentTransaction transaction;
    private FragmentManager manager;
    private int flag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        x.view().inject(this);
        manager=getSupportFragmentManager();
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.check(radioGroup.getChildAt(0).getId());
        initView();
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Log.i("TAG","checkedId---"+checkedId);
                int id=(checkedId-1)%5;
                if (flag!=id) {
                    transaction =manager.beginTransaction();
                    if (!list.get(id).isAdded()) {
                        transaction.hide(list.get(flag)).add(R.id.frame_layout, list.get(id));
                    }else {
                        transaction.hide(list.get(flag)).show(list.get(id));
                    }
                    flag = id;
                    transaction.commit();
                }
            }
        });
    }


    private void initView() {
        transaction = manager.beginTransaction();
        list = new ArrayList<>();
        HomeFragment homeFragment=new HomeFragment();
        ClubFragment clubFragment=new ClubFragment();
        ShopFragment shopFragment=new ShopFragment();
        CartFragment cartFragment=new CartFragment();
        MeFragment meFragment=new MeFragment();
        transaction.add(R.id.frame_layout, homeFragment).commit();
        list.add(homeFragment);
        list.add(clubFragment);
        list.add(shopFragment);
        list.add(cartFragment);
        list.add(meFragment);
    }
}

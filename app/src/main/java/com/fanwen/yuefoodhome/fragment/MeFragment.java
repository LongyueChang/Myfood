package com.fanwen.yuefoodhome.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fanwen.yuefoodhome.R;
import com.fanwen.yuefoodhome.activity.CollectActivity;
import com.fanwen.yuefoodhome.activity.LoginActivity;

public class MeFragment extends Fragment implements View.OnClickListener {

    private ImageView iv_me_headPic;
    private TextView tv_me_toLogin;
    private RelativeLayout rl_me_collect;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_me, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {
        rl_me_collect= (RelativeLayout) getView().findViewById(R.id.rl_me_collect);
        tv_me_toLogin= (TextView) getView().findViewById(R.id.tv_me_toLogin);
        iv_me_headPic= (ImageView) getView().findViewById(R.id.iv_me_headPic);
        rl_me_collect.setOnClickListener(this);
        tv_me_toLogin.setOnClickListener(this);
        iv_me_headPic.setOnClickListener(this);
    }

    public void onClick(View v) {
        Intent intent=null;
        switch (v.getId()) {
            case R.id.rl_me_collect:
                intent = new Intent(getActivity(), CollectActivity.class);
                break;
            case R.id.tv_me_toLogin:
            case R.id.iv_me_headPic:
                intent = new Intent(getActivity(), LoginActivity.class);
                break;
        }
        startActivity(intent);

    }
}


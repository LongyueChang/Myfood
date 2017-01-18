package com.fanwen.yuefoodhome.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.fanwen.yuefoodhome.R;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView iv_login_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        iv_login_back.setOnClickListener(this);
    }

    private void initView() {
        iv_login_back = (ImageView) findViewById(R.id.iv_login_back);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_login_back:
                finish();
                break;
            default:
                break;
        }
    }
}

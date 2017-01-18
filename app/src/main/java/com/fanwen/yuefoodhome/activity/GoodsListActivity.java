package com.fanwen.yuefoodhome.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.fanwen.yuefoodhome.R;
import com.fanwen.yuefoodhome.adapter.GoodsListAdapter;
import com.fanwen.yuefoodhome.been.GoodsListBeen;
import com.fanwen.yuefoodhome.utils.Uri;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRrefreshRecyclerView;

import java.util.ArrayList;
import java.util.List;

import static com.fanwen.yuefoodhome.utils.Uri.URL_GOODSLIST;

public class GoodsListActivity extends AppCompatActivity {

    private PullToRrefreshRecyclerView recyclerView;
    private List<GoodsListBeen.DatasBean> list=new ArrayList<>();
    private TextView tv_gooodsList_title;
    private StringRequest stringRequest;
    private String url;
    private GoodsListAdapter adapter;
    private int index;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_list);
        initUrl();//初始化url
        recyclerView= (PullToRrefreshRecyclerView) findViewById(R.id.rv_goodsList);
        tv_gooodsList_title= (TextView) findViewById(R.id.tv_gooodsList_title);
        adapter=new GoodsListAdapter(this,list,index);
        recyclerView.getRefreshableView().setLayoutManager(new LinearLayoutManager(this));
        recyclerView.getRefreshableView().setAdapter(adapter);
        initData();
    }

    private void initUrl() {
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        String id=bundle.getString("id");
        if (id.equals("channel")) {
            url= Uri.URL_CHANNEL;
            index=1;
        }else {
            url=URL_GOODSLIST+id;
            index=0;
        }
    }

    private void initData() {
        stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("TAG", "onResponse: "+response);
                Gson gson=new Gson();
                GoodsListBeen goodsListBeen=gson.fromJson(response,GoodsListBeen.class);
                list.add(goodsListBeen.getDatas());
                if (index==0){
                    tv_gooodsList_title.setText(list.get(0).getSpecial_title());
                }else {
                    tv_gooodsList_title.setText("限时悦惠");
                }
                adapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("TAG", "onErrorResponse: ");
            }
        });
        MyApplication.getRequestQueue().add(stringRequest);
    }
    public void click(View view) {
        finish();
    }
}

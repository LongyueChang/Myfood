package com.fanwen.yuefoodhome.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.fanwen.yuefoodhome.R;
import com.fanwen.yuefoodhome.adapter.StoreAdapter;
import com.fanwen.yuefoodhome.been.StoreBeen;
import com.fanwen.yuefoodhome.utils.Uri;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRrefreshRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class StoreActivity extends AppCompatActivity {

    private PullToRrefreshRecyclerView pullToRrefreshRecyclerView;
    private List<StoreBeen.DatasBean> list=new ArrayList<>();
    private StoreAdapter adapter;
    private String url;
    private int page=1;
    private StringRequest stringRequest;
    private TextView tv_store_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        initUrl();
        pullToRrefreshRecyclerView= (PullToRrefreshRecyclerView)findViewById(R.id.rv_store);
        tv_store_name= (TextView) findViewById(R.id.tv_store_name);
        adapter=new StoreAdapter(this,list);
        pullToRrefreshRecyclerView.setMode(PullToRefreshBase.Mode.BOTH);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,2);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position==0){
                    return 2;
                }
                return 1;
            }
        });
        pullToRrefreshRecyclerView.getRefreshableView().setLayoutManager(gridLayoutManager);
        pullToRrefreshRecyclerView.getRefreshableView().setAdapter(adapter);
        initData();//加载数据
        refreshData();//刷新数据
    }

    private void initUrl() {
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        String id=bundle.getString("storeId");
        url= Uri.URL_STORE+id;
    }
    private void refreshData() {
        pullToRrefreshRecyclerView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<RecyclerView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
                page=1;
                list.clear();
                initData();
            }
            @Override
            public void onPullUpToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
                page++;
                initData();
            }
        });
    }
    private void initData() {
        stringRequest=new StringRequest(Request.Method.GET, String.format(url,page), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("TAG", "onResponse: "+response);
                Gson gson=new Gson();
                StoreBeen storeBeen=gson.fromJson(response,StoreBeen.class);
                tv_store_name.setText(storeBeen.getDatas().getGoods_list().get(0).getStore_name());
                list.add(storeBeen.getDatas());
                adapter.notifyDataSetChanged();
                pullToRrefreshRecyclerView.onRefreshComplete();
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

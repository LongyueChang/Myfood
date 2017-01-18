package com.fanwen.yuefoodhome.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.fanwen.yuefoodhome.R;
import com.fanwen.yuefoodhome.adapter.CartAdapter;
import com.fanwen.yuefoodhome.been.CartNetBeen;
import com.fanwen.yuefoodhome.utils.Uri;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRrefreshRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartActivity extends AppCompatActivity {

    private List<CartNetBeen.DatasBean.CartListBean> list = new ArrayList<>();
    private PullToRrefreshRecyclerView pullToRrefreshRecyclerView;
    private TextView tv_cart_edit;
    private StringRequest stringRequest;
    private CartAdapter adapter;
    private String url= Uri.URL_CART;
    private Map<Integer,Boolean> map=new HashMap<>();
    private int type=0;//0 可以编辑 1 编辑中

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        tv_cart_edit= (TextView) findViewById(R.id.tv_cart_edit);
        pullToRrefreshRecyclerView= (PullToRrefreshRecyclerView) findViewById(R.id.rv_cart);
        pullToRrefreshRecyclerView.getRefreshableView().setLayoutManager(new LinearLayoutManager(this));
        initAdapter(type,map);
        initData();
        refreshData();//刷新数据
    }

    private void initAdapter(int type,Map<Integer,Boolean> map) {
        adapter=new CartAdapter(this,list,type,map);
        pullToRrefreshRecyclerView.getRefreshableView().setAdapter(adapter);
    }

    private void refreshData() {
        pullToRrefreshRecyclerView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<RecyclerView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
                list.clear();
                initData();
            }
            @Override
            public void onPullUpToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
            }
        });
    }

    private void initData() {
        stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("TAG", "onResponse: "+response);
                Gson gson=new Gson();
                CartNetBeen cartNetBeen=gson.fromJson(response,CartNetBeen.class);
                list.addAll(cartNetBeen.getDatas().getCart_list());
                for (int i = 0; i < list.size(); i++) {
                    map.put(i, false);
                    Log.i("TAG", "CartAdapter: "+i);
                }
                initAdapter(type,map);
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
        switch (view.getId()) {
            case R.id.tv_cart_edit:
                if (type==0){
                    type=1;
                    tv_cart_edit.setText("完成");
                    initAdapter(type,map);
                }else {
                    type=0;
                    tv_cart_edit.setText("编辑");
                    initAdapter(type,map);
                }
                break;
            case R.id.iv_cart_back:
                finish();
                break;
        }

    }

}

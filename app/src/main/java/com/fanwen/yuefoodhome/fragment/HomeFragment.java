package com.fanwen.yuefoodhome.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.fanwen.yuefoodhome.R;
import com.fanwen.yuefoodhome.activity.MyApplication;
import com.fanwen.yuefoodhome.adapter.HomeAdapter;
import com.fanwen.yuefoodhome.been.HomeBeen;
import com.fanwen.yuefoodhome.utils.Uri;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRrefreshRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    private PullToRrefreshRecyclerView pullToRrefreshRecyclerView;
    private List<HomeBeen.DatasBean> list=new ArrayList<>();
    private HomeAdapter adapter;
    private StringRequest stringRequest;
    private String url;
    private int page=1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        url= Uri.URI_HOME;
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        pullToRrefreshRecyclerView= (PullToRrefreshRecyclerView) getView().findViewById(R.id.rv_home);
        adapter=new HomeAdapter(getActivity(),list);
        pullToRrefreshRecyclerView.setMode(PullToRefreshBase.Mode.BOTH);
        pullToRrefreshRecyclerView.getRefreshableView().setLayoutManager(new LinearLayoutManager(getActivity()));
        pullToRrefreshRecyclerView.getRefreshableView().setAdapter(adapter);

        initData();//初始化数据
        refreshData();//刷新数据
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
                HomeBeen homeBeen=new Gson().fromJson(response,HomeBeen.class);
                list.add(homeBeen.getDatas());
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
}

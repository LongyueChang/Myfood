package com.fanwen.yuefoodhome.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
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
import com.fanwen.yuefoodhome.adapter.ShopTagAdapter;
import com.fanwen.yuefoodhome.been.TagBeen;
import com.fanwen.yuefoodhome.utils.Uri;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRrefreshRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TagFragment extends Fragment {

    private PullToRrefreshRecyclerView pullToRrefreshRecyclerView;
    private String url;
    private ShopTagAdapter adapter;
    private StringRequest stringRequest;
    private int page=1;
    private List<TagBeen.DatasBean.GoodsListBean> list=new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle bundle=getArguments();
        int index=bundle.getInt("index");
        int rank=bundle.getInt("rank");
        switch (rank) {
            case 0:
                url= Uri.URL_TAG+index;
                break;
            case 1:
                url= Uri.URL_TAG+index+"&rank=1";
                break;
            case 2:
                url= Uri.URL_TAG+index+"&rank=3";
                break;
            case 3:
                url= Uri.URL_TAG+index+"&rank=4";
                break;
        }
        return inflater.inflate(R.layout.fragment_tag, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        pullToRrefreshRecyclerView= (PullToRrefreshRecyclerView)getView().findViewById(R.id.rv_shopTag);
        adapter =new ShopTagAdapter(getActivity(),list);
        pullToRrefreshRecyclerView.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
        pullToRrefreshRecyclerView.getRefreshableView().setLayoutManager(new GridLayoutManager(getActivity(),2));
        pullToRrefreshRecyclerView.getRefreshableView().setAdapter(adapter);
        initData();//加载数据
        refreshData();//刷新数据
    }

    private void refreshData() {
        pullToRrefreshRecyclerView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<RecyclerView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
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
                TagBeen tagBeen=gson.fromJson(response,TagBeen.class);
                list.addAll(tagBeen.getDatas().getGoods_list());
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

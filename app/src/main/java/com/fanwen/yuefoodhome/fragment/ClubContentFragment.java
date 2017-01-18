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
import com.fanwen.yuefoodhome.adapter.ClubAdapter;
import com.fanwen.yuefoodhome.been.ClubBeen;
import com.fanwen.yuefoodhome.utils.Uri;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRrefreshRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClubContentFragment extends Fragment {

    private PullToRrefreshRecyclerView pullToRrefreshRecyclerView;
    private List<ClubBeen.DatasBean.ArticleListBean> list=new ArrayList<>();
    private ClubAdapter adapter;
    private StringRequest stringRequest;
    private String url;
    private int index;//fragment标记
    private int page=1;//分页
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        index=getArguments().getInt("club");
        //设置不同的url
        url=String.format(Uri.URI_CLUB,index+1);
        Log.i("TAG", "onCreateView: "+url);
        return inflater.inflate(R.layout.fragment_club_content, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        pullToRrefreshRecyclerView= (PullToRrefreshRecyclerView) getView().findViewById(R.id.rv_clubContent);
        adapter=new ClubAdapter(getActivity(),list,index);
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
        stringRequest=new StringRequest(Request.Method.GET, url + page, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("TAG", "onResponse: "+response);
                ClubBeen clubBeen=new Gson().fromJson(response,ClubBeen.class);
                list.addAll(clubBeen.getDatas().getArticle_list());
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

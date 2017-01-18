package com.fanwen.yuefoodhome.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.fanwen.yuefoodhome.R;
import com.fanwen.yuefoodhome.SQLDatabaseHelper.GoodsBeen;
import com.fanwen.yuefoodhome.SQLDatabaseHelper.GoodsDB;
import com.fanwen.yuefoodhome.adapter.CollectAdapter;

import java.util.ArrayList;
import java.util.List;

public class CollectActivity extends AppCompatActivity {
    private RecyclerView rv_collect_goods;
    private GoodsDB goodsDB;
    private List<GoodsBeen> list=new ArrayList<>();
    private CollectAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect);
        rv_collect_goods= (RecyclerView) findViewById(R.id.rv_collect_goods);
        adapter=new CollectAdapter(this,list);
        rv_collect_goods.setLayoutManager(new GridLayoutManager(this,2));
        rv_collect_goods.setAdapter(adapter);
        initData();
    }
    private void initData() {
        goodsDB = new GoodsDB();
        List<GoodsBeen> l = goodsDB.loadGoods();
        if (l != null) {
            list.addAll(l);
            Log.i("TAG", "initData: "+l.size());
            adapter.notifyDataSetChanged();
        }
    }
    public void click(View view) {
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        list.clear();
        initData();
    }
}

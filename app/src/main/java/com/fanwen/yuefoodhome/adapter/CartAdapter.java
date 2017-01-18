package com.fanwen.yuefoodhome.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fanwen.yuefoodhome.R;
import com.fanwen.yuefoodhome.activity.StoreActivity;
import com.fanwen.yuefoodhome.been.CartNetBeen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/15.
 */

public class CartAdapter  extends RecyclerView.Adapter<CartAdapter.MyViewHolder> implements View.OnClickListener {

    private Context context;
    private List<CartNetBeen.DatasBean.CartListBean> list;
    private LayoutInflater layoutInflater;
    private int type;
    private Map<Integer,Boolean> map;
    private List<Map<Integer,Boolean>> mapGoodsList;
    private Map<Integer,Boolean> mapGoods;


    public CartAdapter(Context context, List<CartNetBeen.DatasBean.CartListBean> list, int type,Map<Integer,Boolean> map) {
        this.context = context;
        this.list = list;
        this.type = type;
        this.map=map;
        layoutInflater=LayoutInflater.from(context);
        mapGoodsList=new ArrayList<>();
        for (int i = 0; i < map.size(); i++) {
            mapGoods=new HashMap<>();
            for (int j = 0; j < list.get(i).getGoods().size(); j++) {
                mapGoods.put(j,map.get(i));
            }
            mapGoodsList.add(mapGoods);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=layoutInflater.inflate(R.layout.cart_item_store,parent,false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv_cart_name.setText(list.get(position).getStore_name());
        holder.iv_cart_store.setTag(position);
        holder.iv_cart_check.setTag(position);
        Log.i("TAG", "onBindViewHolder: "+position);
        if (map.get(position)) {
            holder.iv_cart_check.setImageResource(R.mipmap.btn_choose_press);
        }else {
            holder.iv_cart_check.setImageResource(R.mipmap.btn_choose_normal);
        }
        CartGoodsAdapter cartGoodsAdapter=new CartGoodsAdapter(context, list.get(position).getGoods(), type, mapGoodsList.get(position),position,this);
        holder.rv_cart_goods.setLayoutManager(new LinearLayoutManager(context));
        holder.rv_cart_goods.setAdapter(cartGoodsAdapter);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onClick(View v) {
        String str=(String) v.getTag();
        String[] strings=str.split(",");
        int clickPosition=Integer.parseInt(strings[0]);
        int currPosition=Integer.parseInt(strings[1]);
        Log.i("TAG", "onClick: "+currPosition+","+clickPosition);
        if (mapGoodsList.get(currPosition).get(clickPosition)) {
            mapGoodsList.get(currPosition).put(clickPosition, false);
        }else {
            mapGoodsList.get(currPosition).put(clickPosition, true);
        }
        for (int i = 0; i < mapGoodsList.get(currPosition).size(); i++) {
            if (mapGoodsList.get(currPosition).get(i)==false){
                map.put(currPosition,false);
                break;
            }else {
                map.put(currPosition,true);
            }
        }
        notifyDataSetChanged();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        private ImageView iv_cart_check;
        private ImageView iv_cart_store;
        private TextView tv_cart_name;
        private RecyclerView rv_cart_goods;
        public MyViewHolder(View itemView) {
            super(itemView);
            tv_cart_name= (TextView) itemView.findViewById(R.id.tv_cart_name);
            iv_cart_store= (ImageView) itemView.findViewById(R.id.iv_cart_store);
            rv_cart_goods= (RecyclerView) itemView.findViewById(R.id.rv_cart_goods);
            iv_cart_check= (ImageView) itemView.findViewById(R.id.iv_cart_check);
            iv_cart_check.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position=(int) v.getTag();
                    if (map.get(position)) {
                        for (int i = 0; i < mapGoodsList.get(position).size(); i++) {
                            mapGoodsList.get(position).put(i,false);
                        }
                        map.put(position, false);
                    }else {
                        for (int i = 0; i < mapGoodsList.get(position).size(); i++) {
                            mapGoodsList.get(position).put(i,true);
                        }
                        map.put(position, true);
                    }
                    notifyDataSetChanged();
                }
            });
            iv_cart_store.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position= (int) v.getTag();
                    Intent intent=new Intent(context, StoreActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("storeId", list.get(position).getStore_id());
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            });
        }
    }
}

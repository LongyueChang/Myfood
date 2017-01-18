package com.fanwen.yuefoodhome.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.fanwen.yuefoodhome.R;
import com.fanwen.yuefoodhome.activity.BuyActivity;
import com.fanwen.yuefoodhome.activity.MyApplication;
import com.fanwen.yuefoodhome.been.HomeBeen;

import java.util.List;

/**
 * Created by Administrator on 2016/11/11.
 */

public class HscrollHomeAdapter  extends RecyclerView.Adapter<HscrollHomeAdapter.MyViewHolder>{

    private Context context;
    private List<HomeBeen.DatasBean.DataTypeBean.GoodsSpecialListBean> list;
    private LayoutInflater layoutInflater;
    private ImageLoader imageLoader;
    private RecyclerView recyclerView;

    public HscrollHomeAdapter(Context context, List<HomeBeen.DatasBean.DataTypeBean.GoodsSpecialListBean> list) {
        this.context = context;
        this.list = list;
        layoutInflater=LayoutInflater.from(context);
        imageLoader= MyApplication.getImageLoader();
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =layoutInflater.inflate(R.layout.home_hscroll_good,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv_goods_name.setText(list.get(position).getGoods_name());
        holder.tv_goods_price.setText(list.get(position).getGoods_price());
        imageLoader.get(list.get(position).getGoods_img(), ImageLoader.getImageListener(holder.iv_goods_img, R.mipmap.image, R.mipmap.image));
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerView=recyclerView;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView iv_goods_img;
        private TextView tv_goods_name;
        private TextView tv_goods_price;

        public MyViewHolder(View itemView) {
            super(itemView);
            iv_goods_img= (ImageView) itemView.findViewById(R.id.iv_goods_img);
            tv_goods_name= (TextView) itemView.findViewById(R.id.tv_goods_name);
            tv_goods_price= (TextView) itemView.findViewById(R.id.tv_goods_price);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickToBuy(v);
        }
    }
    //跳转到购买界面
    private void clickToBuy(View v) {
        int position=recyclerView.getChildAdapterPosition(v);
        String id=list.get(position).getGoods_id();
        Intent intent=new Intent(context, BuyActivity.class);
        Bundle bundle=new Bundle();
        bundle.putString("id",id);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
}

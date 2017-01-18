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
import com.fanwen.yuefoodhome.been.TagBeen;

import java.util.List;

/**
 * Created by Administrator on 2016/11/10.
 */

public class ShopTagAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<TagBeen.DatasBean.GoodsListBean> list;
    private LayoutInflater layoutInflater;
    private ImageLoader imageLoader;
    private RecyclerView recyclerView;

    public ShopTagAdapter(Context context, List<TagBeen.DatasBean.GoodsListBean> list) {
        this.context = context;
        this.list = list;
        layoutInflater=LayoutInflater.from(context);
        imageLoader= MyApplication.getImageLoader();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder=null;
        switch (viewType) {
            case 0:
                View view=layoutInflater.inflate(R.layout.tag_item_grid,parent,false);
                holder=new MyViewHolder(view);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case 0:
                MyViewHolder myViewHolder= (MyViewHolder) holder;
                myViewHolder.tv_tag_name.setText(list.get(position).getGoods_name());
                myViewHolder.tv_tag_price.setText("¥"+list.get(position).getGoods_price());
                myViewHolder.tv_tag_marketPrice.setText("原价：¥"+list.get(position).getGoods_marketprice());
                myViewHolder.tv_tag_salenum.setText("已售："+list.get(position).getGoods_salenum());
                imageLoader.get(list.get(position).getGoods_image_url(), ImageLoader.getImageListener(myViewHolder.iv_tag_image, R.mipmap.image, R.mipmap.image));
                break;
        }
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

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView iv_tag_image;
        private TextView tv_tag_name;
        private TextView tv_tag_price;
        private TextView tv_tag_marketPrice;
        private TextView tv_tag_salenum;
        public MyViewHolder(View itemView) {
            super(itemView);
            iv_tag_image= (ImageView) itemView.findViewById(R.id.iv_tag_image);
            tv_tag_name= (TextView) itemView.findViewById(R.id.tv_tag_name);
            tv_tag_price= (TextView) itemView.findViewById(R.id.tv_tag_price);
            tv_tag_marketPrice= (TextView) itemView.findViewById(R.id.tv_tag_marketPrice);
            tv_tag_salenum= (TextView) itemView.findViewById(R.id.tv_tag_salenum);
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

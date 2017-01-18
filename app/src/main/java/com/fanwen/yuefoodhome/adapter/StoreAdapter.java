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
import com.fanwen.yuefoodhome.been.StoreBeen;

import java.util.List;

/**
 * Created by Administrator on 2016/11/12.
 */

public class StoreAdapter  extends RecyclerView.Adapter{

    private Context context;
    private List<StoreBeen.DatasBean> list;
    private LayoutInflater layoutInflater;
    private ImageLoader imageLoader;
    private RecyclerView recyclerView;

    public StoreAdapter(Context context, List<StoreBeen.DatasBean> list) {
        this.context = context;
        this.list = list;
        layoutInflater=LayoutInflater.from(context);
        imageLoader= MyApplication.getImageLoader();
    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerView=recyclerView;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder=null;
        switch (viewType) {
            case 0:
                View view=layoutInflater.inflate(R.layout.home_item_image,parent,false);
                viewHolder=new ImageViewholder(view);
                break;
            case 1:
                View view1=layoutInflater.inflate(R.layout.store_item_grid,parent,false);
                viewHolder=new StoreViewHolder(view1);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case 0:
                ImageViewholder imageViewholder= (ImageViewholder) holder;
                imageLoader.get(list.get(0).getStore_banner(), ImageLoader.getImageListener(imageViewholder.iv_home_image, R.mipmap.image, R.mipmap.image));
                break;
            case 1:
                StoreViewHolder storeViewHolder= (StoreViewHolder) holder;
                storeViewHolder.tv_store_name.setText(list.get(0).getGoods_list().get(position-1).getGoods_name());
                storeViewHolder.tv_store_price.setText(list.get(0).getGoods_list().get(position-1).getGoods_price());
                storeViewHolder.tv_store_salenum.setText(list.get(0).getGoods_list().get(position-1).getGoods_salenum());
                imageLoader.get(list.get(0).getGoods_list().get(position-1).getGoods_image(), ImageLoader.getImageListener(storeViewHolder.iv_store_image, R.mipmap.image, R.mipmap.image));
                break;
        }
    }

    @Override
    public int getItemCount() {
        if (list.size()!=0){
            return list.get(0).getGoods_list().size()+1;
        }
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        if (position==0){
            return 0;
        }
        return 1;

    }

    class StoreViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView iv_store_image;
        private TextView tv_store_name;
        private TextView tv_store_price;
        private TextView tv_store_salenum;
        public StoreViewHolder(View itemView) {
            super(itemView);
            iv_store_image= (ImageView) itemView.findViewById(R.id.iv_store_image);
            tv_store_name= (TextView) itemView.findViewById(R.id.tv_store_name);
            tv_store_price= (TextView) itemView.findViewById(R.id.tv_store_price);
            tv_store_salenum= (TextView) itemView.findViewById(R.id.tv_store_salenum);
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
        String id=list.get(0).getGoods_list().get(position-1).getGoods_id();
        Intent intent=new Intent(context, BuyActivity.class);
        Bundle bundle=new Bundle();
        bundle.putString("id",id);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
    class ImageViewholder extends RecyclerView.ViewHolder {

        private ImageView iv_home_image;
        public ImageViewholder(View itemView) {
            super(itemView);
            iv_home_image= (ImageView) itemView.findViewById(R.id.iv_home_image);
        }
    }
}

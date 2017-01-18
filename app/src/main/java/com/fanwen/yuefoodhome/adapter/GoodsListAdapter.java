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
import com.fanwen.yuefoodhome.been.GoodsListBeen;

import java.util.List;

/**
 * Created by Administrator on 2016/11/11.
 */

public class GoodsListAdapter  extends RecyclerView.Adapter{

    private Context context;
    private List<GoodsListBeen.DatasBean> list;
    private LayoutInflater layoutInflater;
    private ImageLoader imageLoader;
    private int index;
    private RecyclerView recyclerView;

    public GoodsListAdapter(Context context, List<GoodsListBeen.DatasBean> list, int index) {
        this.context = context;
        this.list = list;
        this.index = index;
        layoutInflater=LayoutInflater.from(context);
        imageLoader= MyApplication.getImageLoader();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder=null;
        switch (viewType) {
            case 0:
                View view=layoutInflater.inflate(R.layout.goods_item_header,parent,false);
                viewHolder=new HeaderViewHolder(view);
                break;
            case 1:
                View view1=layoutInflater.inflate(R.layout.goods_item_list,parent,false);
                viewHolder=new GoodsViewHolder(view1);
                break;
            case 2:
                View view2=layoutInflater.inflate(R.layout.goods_item_list,parent,false);
                viewHolder=new GoodsViewHolder(view2);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case 0:
                HeaderViewHolder headerViewHolder= (HeaderViewHolder) holder;
                headerViewHolder.tv_goodsList_stitle.setText(list.get(0).getSpecial_stitle());
                imageLoader.get(list.get(position).getSpecial_image(), ImageLoader.getImageListener(headerViewHolder.iv_goodsList_image, R.mipmap.image, R.mipmap.image));
                break;
            case 1:
                GoodsViewHolder goodsViewHolder= (GoodsViewHolder) holder;
                goodsViewHolder.tv_goodsList_name.setText(list.get(0).getGoods_list().get(position-1).getGoods_name());
                goodsViewHolder.tv_goodsList_jingle.setText(list.get(0).getGoods_list().get(position-1).getGoods_jingle());
                goodsViewHolder.tv_goodsList_price.setText("¥"+list.get(0).getGoods_list().get(position-1).getGoods_price());
                goodsViewHolder.tv_goodsList_marketPrice.setText("原价：¥"+list.get(0).getGoods_list().get(position-1).getGoods_marketprice());
                goodsViewHolder.tv_goodsList_salenum.setText("已售："+list.get(0).getGoods_list().get(position-1).getGoods_salenum());
                imageLoader.get(list.get(0).getGoods_list().get(position-1).getGoods_image_url(), ImageLoader.getImageListener(goodsViewHolder.iv_goodsList_goodsImage, R.mipmap.image, R.mipmap.image));
                break;
            case 2:
                GoodsViewHolder goodsViewHolder1= (GoodsViewHolder) holder;
                goodsViewHolder1.tv_goodsList_name.setText(list.get(0).getGoods_list().get(position).getGoods_name());
                goodsViewHolder1.tv_goodsList_jingle.setText(list.get(0).getGoods_list().get(position).getGoods_jingle());
                goodsViewHolder1.tv_goodsList_price.setText("¥"+list.get(0).getGoods_list().get(position).getGoods_price());
                goodsViewHolder1.tv_goodsList_marketPrice.setText("原价：¥"+list.get(0).getGoods_list().get(position).getGoods_marketprice());
                goodsViewHolder1.tv_goodsList_salenum.setText("已售："+list.get(0).getGoods_list().get(position).getGoods_salenum());
                imageLoader.get(list.get(0).getGoods_list().get(position).getGoods_image_url(), ImageLoader.getImageListener(goodsViewHolder1.iv_goodsList_goodsImage, R.mipmap.image, R.mipmap.image));
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
        if (list.size()!=0){
            return list.get(0).getGoods_list().size()+1;
        }
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        if (index==1){
            return 2;
        }else if (position==0){
            return 0;
        }
        return 1;
    }

    class HeaderViewHolder extends RecyclerView.ViewHolder{

        private ImageView iv_goodsList_image;
        private TextView tv_goodsList_stitle;
        public HeaderViewHolder(View itemView) {
            super(itemView);
            iv_goodsList_image= (ImageView) itemView.findViewById(R.id.iv_goodsList_image);
            tv_goodsList_stitle= (TextView) itemView.findViewById(R.id.tv_goodsList_stitle);
        }
    }
    class GoodsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView iv_goodsList_goodsImage;
        private TextView tv_goodsList_name;
        private TextView tv_goodsList_jingle;
        private TextView tv_goodsList_price;
        private TextView tv_goodsList_marketPrice;
        private TextView tv_goodsList_salenum;
        public GoodsViewHolder(View itemView) {
            super(itemView);
            iv_goodsList_goodsImage= (ImageView) itemView.findViewById(R.id.iv_goodsList_goodsImage);
            tv_goodsList_name= (TextView) itemView.findViewById(R.id.tv_goodsList_name);
            tv_goodsList_jingle= (TextView) itemView.findViewById(R.id.tv_goodsList_jingle);
            tv_goodsList_price= (TextView) itemView.findViewById(R.id.tv_goodsList_price);
            tv_goodsList_marketPrice= (TextView) itemView.findViewById(R.id.tv_goodsList_marketPrice);
            tv_goodsList_salenum= (TextView) itemView.findViewById(R.id.tv_goodsList_salenum);
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
        String id=null;
        if (index==0){
            id=list.get(0).getGoods_list().get(position-1).getGoods_id();
        }else {
            id=list.get(0).getGoods_list().get(position).getGoods_id();

        }
        Intent intent=new Intent(context, BuyActivity.class);
        Bundle bundle=new Bundle();
        bundle.putString("id",id);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
}

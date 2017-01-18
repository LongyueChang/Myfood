package com.fanwen.yuefoodhome.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.fanwen.yuefoodhome.R;
import com.fanwen.yuefoodhome.activity.BuyActivity;
import com.fanwen.yuefoodhome.activity.GoodsListActivity;
import com.fanwen.yuefoodhome.activity.MyApplication;
import com.fanwen.yuefoodhome.been.HomeBeen;
import com.jorge.circlelibrary.ImageCycleView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/11.
 */

public class HomeAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<HomeBeen.DatasBean> list;
    private LayoutInflater layoutInflater;
    private ImageLoader imageLoader;
    private RecyclerView recyclerView;

    public HomeAdapter(Context context, List<HomeBeen.DatasBean> list) {
        this.context = context;
        this.list = list;
        layoutInflater = LayoutInflater.from(context);
        imageLoader = MyApplication.getImageLoader();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder=null;
        switch (viewType) {
            case 0:
                View view1=layoutInflater.inflate(R.layout.shop_item_banner,parent,false);
                viewHolder=new BannerViewHolder(view1);
                break;
            case 1:
                View view2=layoutInflater.inflate(R.layout.home_item_list,parent,false);
                viewHolder=new ListViewholder(view2);
                break;
            case 2:
                View view3=layoutInflater.inflate(R.layout.home_item_hscroll,parent,false);
                viewHolder=new HscrollViewholder(view3);
                break;
            case 3:
                View view4=layoutInflater.inflate(R.layout.home_item_good,parent,false);
                viewHolder=new GoodViewholder(view4);
                break;
            case 4:
                View view5=layoutInflater.inflate(R.layout.home_item_image,parent,false);
                viewHolder=new ImageViewholder(view5);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case 0://主页轮播图
                BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;
                ArrayList<String> urlList=new ArrayList<>();
                ArrayList<String> dataImageList=new ArrayList<>();
                for (int i = 0; i < list.get(0).getBanner().size(); i++) {
                    urlList.add(list.get(0).getBanner().get(i).getAdvertImg());
                    dataImageList.add(list.get(0).getBanner().get(i).getAdvertTitle());
                }
                bannerViewHolder.ic_banner.setCycle_T(ImageCycleView.CYCLE_T.CYCLE_VIEW_NORMAL);
                ImageCycleView.ImageCycleViewListener imageCycleViewListener=new ImageCycleView.ImageCycleViewListener() {
                    @Override
                    public void displayImage(String imageURL, ImageView imageView) {
                        imageLoader.get(imageURL, ImageLoader.getImageListener(imageView, R.mipmap.image, R.mipmap.image));
                    }

                    @Override
                    public void onImageClick(int position, View imageView) {

                    }
                };
                bannerViewHolder.ic_banner.setImageResources(dataImageList,urlList,imageCycleViewListener);
                bannerViewHolder.ic_banner.startImageCycle();
                break;
            case 1:
                ListViewholder listViewholder= (ListViewholder) holder;
                listViewholder.tv_homeList_title.setText(list.get(0).getData_type().get(0).getRelation_object_title());
                imageLoader.get(list.get(0).getData_type().get(0).getRelation_object_image(), ImageLoader.getImageListener(listViewholder.iv_homeList_image, R.mipmap.image, R.mipmap.image));
                break;
            case 2:
                HscrollViewholder hscrollViewholder= (HscrollViewholder) holder;
                List<HomeBeen.DatasBean.DataTypeBean.GoodsSpecialListBean> listBeen=list.get(0).getData_type().get(0).getGoods_special_list();
                HscrollHomeAdapter hscrollHomeAdapter=new HscrollHomeAdapter(context,listBeen);
                hscrollViewholder.rv_home_hscroll.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
                hscrollViewholder.rv_home_hscroll.setAdapter(hscrollHomeAdapter);
                break;
            case 3:
                GoodViewholder goodViewholder= (GoodViewholder) holder;
                goodViewholder.tv_relation_object_title.setText(list.get(0).getData_type().get(position-2).getRelation_object_title());
                goodViewholder.tv_item_price.setText(list.get(0).getData_type().get(position-2).getGoods_price());
                goodViewholder.tv_relation_object_jingle.setText(list.get(0).getData_type().get(position-2).getRelation_object_jingle());
                imageLoader.get(list.get(0).getData_type().get(position-2).getRelation_object_image(), ImageLoader.getImageListener(goodViewholder.iv_relation_object_image, R.mipmap.image, R.mipmap.image));
                break;
            case 4:
                ImageViewholder imageViewholder= (ImageViewholder) holder;
                imageLoader.get(list.get(0).getData_type().get(position-2).getRelation_object_image(), ImageLoader.getImageListener(imageViewholder.iv_home_image, R.mipmap.image, R.mipmap.image));
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
        if (list.size() != 0) {
            return list.get(0).getData_type().size() + 2;
        }
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        if (position <= 2) {
            return position;
        } else if (list.get(0).getData_type().get(position-2).getRelation_object_type().equals("6")){
            return 4;
        }
        return 3;
    }

    class BannerViewHolder extends RecyclerView.ViewHolder {

        private ImageCycleView ic_banner;
        public BannerViewHolder(View itemView) {
            super(itemView);
            ic_banner= (ImageCycleView) itemView.findViewById(R.id.ic_banner);
        }
    }

    class ListViewholder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView iv_homeList_image;
        private TextView tv_homeList_title;

        public ListViewholder(View itemView) {
            super(itemView);
            iv_homeList_image= (ImageView) itemView.findViewById(R.id.iv_homeList_image);
            tv_homeList_title= (TextView) itemView.findViewById(R.id.tv_homeList_title);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position=recyclerView.getChildAdapterPosition(v);
            String id=list.get(0).getData_type().get(position-1).getRelation_object_id();
            Intent intent=new Intent(context, GoodsListActivity.class);
            Bundle bundle=new Bundle();
            bundle.putString("id",id);
            intent.putExtras(bundle);
            context.startActivity(intent);
        }
    }
    class HscrollViewholder extends RecyclerView.ViewHolder {

        private RecyclerView rv_home_hscroll;
        public HscrollViewholder(View itemView) {
            super(itemView);
            rv_home_hscroll= (RecyclerView) itemView.findViewById(R.id.rv_home_hscroll);
        }
    }
    class GoodViewholder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView iv_relation_object_image;
        private TextView  tv_relation_object_title;
        private TextView  tv_relation_object_jingle;
        private TextView  tv_item_price;
        private TextView  btu_item_collect;
        public GoodViewholder(View itemView) {
            super(itemView);
            iv_relation_object_image= (ImageView) itemView.findViewById(R.id.iv_relation_object_image);
            tv_relation_object_title= (TextView) itemView.findViewById(R.id.tv_relation_object_title);
            tv_relation_object_jingle= (TextView) itemView.findViewById(R.id.tv_relation_object_jingle);
            tv_item_price= (TextView) itemView.findViewById(R.id.tv_item_price);
            btu_item_collect= (TextView) itemView.findViewById(R.id.btu_item_collect);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickToBuy(v);
        }
    }


//    加载中，请稍候...

    class ImageViewholder extends RecyclerView.ViewHolder {

        private ImageView iv_home_image;
        public ImageViewholder(View itemView) {
            super(itemView);
            iv_home_image= (ImageView) itemView.findViewById(R.id.iv_home_image);
        }
    }
    private void clickToBuy(View v) {
        int position=recyclerView.getChildAdapterPosition(v);
        String id=list.get(0).getData_type().get(position-2).getRelation_object_id();
        Intent intent=new Intent(context, BuyActivity.class);
        Bundle bundle=new Bundle();
        bundle.putString("id",id);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

}

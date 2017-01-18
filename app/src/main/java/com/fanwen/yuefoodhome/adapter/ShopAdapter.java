package com.fanwen.yuefoodhome.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.fanwen.yuefoodhome.R;
import com.fanwen.yuefoodhome.activity.GoodsListActivity;
import com.fanwen.yuefoodhome.activity.MyApplication;
import com.fanwen.yuefoodhome.activity.ShopTagActivity;
import com.fanwen.yuefoodhome.been.ShopBeen;
import com.jorge.circlelibrary.ImageCycleView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/10.
 */

public class ShopAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<ShopBeen.DatasBean> list;
    private LayoutInflater layoutInflater;
    private ImageLoader imageLoader;
    private RecyclerView recyclerView;

    public ShopAdapter(Context context, List<ShopBeen.DatasBean> list) {
        this.context = context;
        this.list = list;
        layoutInflater = LayoutInflater.from(context);
        imageLoader = MyApplication.getImageLoader();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        switch (viewType) {
            case 0:
                View view1 = layoutInflater.inflate(R.layout.shop_item_banner, parent, false);
                viewHolder = new BannerViewHolder(view1);
                break;
            case 1:
                View view2 = layoutInflater.inflate(R.layout.shop_item_tag, parent, false);
                viewHolder = new TagViewHolder(view2);
                break;
            case 2:
                View view3 = layoutInflater.inflate(R.layout.shop_item_channel, parent, false);
                viewHolder = new ChannelViewHolder(view3);
                break;
            case 3:
                View view4 = layoutInflater.inflate(R.layout.shop_item_query, parent, false);
                viewHolder = new QueryViewHolder(view4);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case 0:
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
                TagViewHolder tagViewHolder = (TagViewHolder) holder;
                tagViewHolder.linearLayout1.setTag(1);
                tagViewHolder.linearLayout2.setTag(2);
                tagViewHolder.linearLayout3.setTag(3);
                tagViewHolder.linearLayout4.setTag(4);
                tagViewHolder.linearLayout5.setTag(5);
                tagViewHolder.linearLayout6.setTag(6);
                tagViewHolder.linearLayout7.setTag(7);
                tagViewHolder.linearLayout8.setTag(8);
                tagViewHolder.tv_tag1.setText(list.get(0).getTag_classify().get(0).getTag_name());
                imageLoader.get(list.get(0).getTag_classify().get(0).getTag_img(), ImageLoader.getImageListener(tagViewHolder.iv_tag1,R.mipmap.tag,R.mipmap.tag));
                tagViewHolder.tv_tag2.setText(list.get(0).getTag_classify().get(1).getTag_name());
                imageLoader.get(list.get(0).getTag_classify().get(1).getTag_img(), ImageLoader.getImageListener(tagViewHolder.iv_tag2,R.mipmap.tag,R.mipmap.tag));
                tagViewHolder.tv_tag3.setText(list.get(0).getTag_classify().get(2).getTag_name());
                imageLoader.get(list.get(0).getTag_classify().get(2).getTag_img(), ImageLoader.getImageListener(tagViewHolder.iv_tag3,R.mipmap.tag,R.mipmap.tag));
                tagViewHolder.tv_tag4.setText(list.get(0).getTag_classify().get(3).getTag_name());
                imageLoader.get(list.get(0).getTag_classify().get(3).getTag_img(), ImageLoader.getImageListener(tagViewHolder.iv_tag4,R.mipmap.tag,R.mipmap.tag));
                tagViewHolder.tv_tag5.setText(list.get(0).getTag_classify().get(4).getTag_name());
                imageLoader.get(list.get(0).getTag_classify().get(4).getTag_img(), ImageLoader.getImageListener(tagViewHolder.iv_tag5,R.mipmap.tag,R.mipmap.tag));
                tagViewHolder.tv_tag6.setText(list.get(0).getTag_classify().get(5).getTag_name());
                imageLoader.get(list.get(0).getTag_classify().get(5).getTag_img(), ImageLoader.getImageListener(tagViewHolder.iv_tag6,R.mipmap.tag,R.mipmap.tag));
                tagViewHolder.tv_tag7.setText(list.get(0).getTag_classify().get(6).getTag_name());
                imageLoader.get(list.get(0).getTag_classify().get(6).getTag_img(), ImageLoader.getImageListener(tagViewHolder.iv_tag7,R.mipmap.tag,R.mipmap.tag));
                tagViewHolder.tv_tag8.setText(list.get(0).getTag_classify().get(7).getTag_name());
                imageLoader.get(list.get(0).getTag_classify().get(7).getTag_img(), ImageLoader.getImageListener(tagViewHolder.iv_tag8,R.mipmap.tag,R.mipmap.tag));
                break;
            case 2:
                ChannelViewHolder channelViewHolder= (ChannelViewHolder) holder;
                channelViewHolder.tv_shopChannel_goodsName.setText(list.get(0).getChannel().getGoods_name());
                channelViewHolder.tv_shopChannel_goodsPrice.setText(list.get(0).getChannel().getGoods_price());
                channelViewHolder.tv_shopChannel_title.setText(list.get(0).getChannel().getTitle());
                imageLoader.get(list.get(0).getChannel().getGoods_img(), ImageLoader.getImageListener(channelViewHolder.iv_shopChannel_image,R.mipmap.image,R.mipmap.image));
                break;
            case 3:
                QueryViewHolder queryViewHolder= (QueryViewHolder) holder;
                queryViewHolder.tv_shopQuery_title.setText(list.get(0).getQuery().get(position-3).getSpecial_title());
                queryViewHolder.tv_shopQuery_english.setText(list.get(0).getQuery().get(position-3).getEnglish_title());
                imageLoader.get(list.get(0).getQuery().get(position-3).getSpecial_image(), ImageLoader.getImageListener(queryViewHolder.iv_shopQuery_image,R.mipmap.image,R.mipmap.image));
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
        if (list.size()!=0) {
            return list.get(0).getQuery().size() + 3;
        }
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position <= 2) {
            return position;
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

    class TagViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tv_tag1;
        private TextView tv_tag2;
        private TextView tv_tag3;
        private TextView tv_tag4;
        private TextView tv_tag5;
        private TextView tv_tag6;
        private TextView tv_tag7;
        private TextView tv_tag8;
        private ImageView iv_tag1;
        private ImageView iv_tag2;
        private ImageView iv_tag3;
        private ImageView iv_tag4;
        private ImageView iv_tag5;
        private ImageView iv_tag6;
        private ImageView iv_tag7;
        private ImageView iv_tag8;
        private LinearLayout linearLayout1;
        private LinearLayout linearLayout2;
        private LinearLayout linearLayout3;
        private LinearLayout linearLayout4;
        private LinearLayout linearLayout5;
        private LinearLayout linearLayout6;
        private LinearLayout linearLayout7;
        private LinearLayout linearLayout8;
        public TagViewHolder(View itemView) {
            super(itemView);
            tv_tag1 = (TextView) itemView.findViewById(R.id.tv_shopTag_1);
            tv_tag2 = (TextView) itemView.findViewById(R.id.tv_shopTag_2);
            tv_tag3 = (TextView) itemView.findViewById(R.id.tv_shopTag_3);
            tv_tag4 = (TextView) itemView.findViewById(R.id.tv_shopTag_4);
            tv_tag5 = (TextView) itemView.findViewById(R.id.tv_shopTag_5);
            tv_tag6 = (TextView) itemView.findViewById(R.id.tv_shopTag_6);
            tv_tag7 = (TextView) itemView.findViewById(R.id.tv_shopTag_7);
            tv_tag8 = (TextView) itemView.findViewById(R.id.tv_shopTag_8);
            iv_tag1 = (ImageView) itemView.findViewById(R.id.iv_shopTag_1);
            iv_tag2 = (ImageView) itemView.findViewById(R.id.iv_shopTag_2);
            iv_tag4 = (ImageView) itemView.findViewById(R.id.iv_shopTag_3);
            iv_tag3 = (ImageView) itemView.findViewById(R.id.iv_shopTag_4);
            iv_tag5 = (ImageView) itemView.findViewById(R.id.iv_shopTag_5);
            iv_tag6 = (ImageView) itemView.findViewById(R.id.iv_shopTag_6);
            iv_tag7 = (ImageView) itemView.findViewById(R.id.iv_shopTag_7);
            iv_tag8 = (ImageView) itemView.findViewById(R.id.iv_shopTag_8);
            linearLayout1= (LinearLayout) itemView.findViewById(R.id.ll_shopTag1);
            linearLayout2= (LinearLayout) itemView.findViewById(R.id.ll_shopTag2);
            linearLayout3= (LinearLayout) itemView.findViewById(R.id.ll_shopTag3);
            linearLayout4= (LinearLayout) itemView.findViewById(R.id.ll_shopTag4);
            linearLayout5= (LinearLayout) itemView.findViewById(R.id.ll_shopTag5);
            linearLayout6= (LinearLayout) itemView.findViewById(R.id.ll_shopTag6);
            linearLayout7= (LinearLayout) itemView.findViewById(R.id.ll_shopTag7);
            linearLayout8= (LinearLayout) itemView.findViewById(R.id.ll_shopTag8);
            linearLayout1.setOnClickListener(this);
            linearLayout2.setOnClickListener(this);
            linearLayout3.setOnClickListener(this);
            linearLayout4.setOnClickListener(this);
            linearLayout5.setOnClickListener(this);
            linearLayout6.setOnClickListener(this);
            linearLayout7.setOnClickListener(this);
            linearLayout8.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int index= (int) v.getTag();
            Intent intent=new Intent(context, ShopTagActivity.class);
            Bundle bundle=new Bundle();
            bundle.putInt("index",index);
            intent.putExtras(bundle);
            context.startActivity(intent);
        }
    }

    class ChannelViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tv_shopChannel_title;
        private TextView tv_shopChannel_goodsPrice;
        private TextView tv_shopChannel_goodsName;
        private ImageView iv_shopChannel_image;

        public ChannelViewHolder(View itemView) {
            super(itemView);
            tv_shopChannel_goodsName = (TextView) itemView.findViewById(R.id.tv_shopChannel_goodsName);
            tv_shopChannel_goodsPrice = (TextView) itemView.findViewById(R.id.tv_shopChannel_goodsPrice);
            tv_shopChannel_title = (TextView) itemView.findViewById(R.id.tv_shopChannel_title);
            iv_shopChannel_image = (ImageView) itemView.findViewById(R.id.iv_shopChannel_image);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent=new Intent(context, GoodsListActivity.class);
            Bundle bundle=new Bundle();
            bundle.putString("id","channel");
            intent.putExtras(bundle);
            context.startActivity(intent);
        }
    }

    class QueryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tv_shopQuery_title;
        private TextView tv_shopQuery_english;
        private ImageView iv_shopQuery_image;

        public QueryViewHolder(View itemView) {
            super(itemView);
            tv_shopQuery_english = (TextView) itemView.findViewById(R.id.tv_shopQuery_english);
            tv_shopQuery_title = (TextView) itemView.findViewById(R.id.tv_shopQuery_title);
            iv_shopQuery_image = (ImageView) itemView.findViewById(R.id.iv_shopQuery_image);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position=recyclerView.getChildAdapterPosition(v);
            String id=list.get(0).getQuery().get(position-3).getSpecial_id();
            Intent intent=new Intent(context, GoodsListActivity.class);
            Bundle bundle=new Bundle();
            bundle.putString("id",id);
            intent.putExtras(bundle);
            context.startActivity(intent);
        }
    }
}

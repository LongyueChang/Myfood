package com.fanwen.yuefoodhome.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.fanwen.yuefoodhome.R;
import com.fanwen.yuefoodhome.activity.DetailedActivity;
import com.fanwen.yuefoodhome.activity.ClubVideoActivity;
import com.fanwen.yuefoodhome.activity.MyApplication;
import com.fanwen.yuefoodhome.been.ClubBeen;

import java.util.List;

import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.Vitamio;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

/**
 * Created by Administrator on 2016/11/9.
 */

public class ClubAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<ClubBeen.DatasBean.ArticleListBean> list;
    private LayoutInflater layoutInflater;
    private ImageLoader imageLoader;
    private String imageUrl;
    private RecyclerView recyclerView;
    private int index;
    private int clickPosition = -1;

    public ClubAdapter(Context context, List<ClubBeen.DatasBean.ArticleListBean> list, int index) {
        this.context = context;
        this.list = list;
        this.index = index;
        layoutInflater = LayoutInflater.from(context);
        imageLoader = MyApplication.getImageLoader();
        Vitamio.isInitialized(context.getApplicationContext());
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        switch (viewType) {
            case 0:
                View view1 = layoutInflater.inflate(R.layout.club_item_video, parent, false);
                viewHolder = new VideoViewHolder(view1);
                break;
            case 1:
                View view2 = layoutInflater.inflate(R.layout.club_item_hot, parent, false);
                viewHolder = new HotViewHolder(view2);
                break;
            case 2:
                View view3 = layoutInflater.inflate(R.layout.club_item_knowledge, parent, false);
                viewHolder = new KnowledgeViewHolder(view3);
                break;
            case 3:
                View view4 = layoutInflater.inflate(R.layout.club_item_cultural, parent, false);
                viewHolder = new CulturalViewHolder(view4);
                break;
            case 4:
                View view5 = layoutInflater.inflate(R.layout.club_item_map, parent, false);
                viewHolder = new MapViewHolder(view5);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case 0:
                VideoViewHolder videoViewHolder = (VideoViewHolder) holder;
                videoViewHolder.tv_title.setText(list.get(position).getArticle_title());
                videoViewHolder.tv_abstract.setText(list.get(position).getArticle_abstract());
                videoViewHolder.tv_tag.setText("#" + list.get(position).getTag().get(0) + list.get(position).getVideo_length());
                imageUrl = list.get(position).getArticle_image();
                imageLoader.get(imageUrl, ImageLoader.getImageListener(videoViewHolder.iv_image, R.mipmap.image, R.mipmap.image));

                if (videoViewHolder.iv_image.getTag() != null) {
                    int oldPosition = (Integer) videoViewHolder.iv_image.getTag();
                    if (oldPosition != position) {
                        if(videoViewHolder.vv_video.isPlaying()){
                            clickPosition = -1;
                            videoViewHolder.vv_video.stopPlayback();
                        }
                    }
                }
                videoViewHolder.vv_video.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });
                videoViewHolder.iv_image.setTag(position);
                if (clickPosition != -1 && clickPosition == position) {
                    videoViewHolder.iv_image.setVisibility(View.GONE);
                    videoViewHolder.tv_tag.setVisibility(View.GONE);
                    videoViewHolder.vv_video.setVisibility(View.VISIBLE);
                    videoViewHolder.vv_video.setVideoPath(list.get(position).getArticle_video());
                    videoViewHolder.vv_video.setMediaController(new MediaController(context));
                    videoViewHolder.vv_video.requestFocus();
                    videoViewHolder.vv_video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            mp.start();
                        }
                    });
                } else {
                    videoViewHolder.iv_image.setVisibility(View.VISIBLE);
                    videoViewHolder.tv_tag.setVisibility(View.VISIBLE);
                    videoViewHolder.vv_video.setVisibility(View.GONE);
                }
                break;
            case 1:
                HotViewHolder hotViewHolder = (HotViewHolder) holder;
                hotViewHolder.tv_title.setText(list.get(position).getArticle_title());
                hotViewHolder.tv_abstract.setText(list.get(position).getArticle_abstract());
                hotViewHolder.tv_origin.setText(list.get(position).getArticle_origin());
                hotViewHolder.tv_top.setText("Top" + list.get(position).getTop());
                imageUrl = list.get(position).getArticle_image();
                imageLoader.get(imageUrl, ImageLoader.getImageListener(hotViewHolder.iv_image, R.mipmap.image, R.mipmap.image));
                break;
            case 2:
                KnowledgeViewHolder knowledgeViewHolder = (KnowledgeViewHolder) holder;
                knowledgeViewHolder.tv_title.setText(list.get(position).getArticle_title());
                knowledgeViewHolder.tv_abstract.setText(list.get(position).getArticle_abstract());
                imageUrl = list.get(position).getArticle_image();
                imageLoader.get(imageUrl, ImageLoader.getImageListener(knowledgeViewHolder.iv_image, R.mipmap.image, R.mipmap.image));
                break;
            case 3:
                CulturalViewHolder culturalViewHolder = (CulturalViewHolder) holder;
                culturalViewHolder.tv_title.setText(list.get(position).getArticle_title());
                culturalViewHolder.tv_abstract.setText(list.get(position).getArticle_abstract());
                imageUrl = list.get(position).getArticle_image();
                imageLoader.get(imageUrl, ImageLoader.getImageListener(culturalViewHolder.iv_image, R.mipmap.image, R.mipmap.image));
                break;
            case 4:
                MapViewHolder mapViewHolder = (MapViewHolder) holder;
                mapViewHolder.tv_title.setText(list.get(position).getArticle_title());
                mapViewHolder.tv_abstract.setText(list.get(position).getArticle_abstract());
                imageUrl = list.get(position).getArticle_image();
                imageLoader.get(imageUrl, ImageLoader.getImageListener(mapViewHolder.iv_image, R.mipmap.image, R.mipmap.image));
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
        return index;
    }

    class VideoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private VideoView vv_video;
        private ImageView iv_image;
        private TextView tv_title;
        private TextView tv_tag;
        private TextView tv_abstract;

        public VideoViewHolder(View itemView) {
            super(itemView);
            vv_video = (VideoView) itemView.findViewById(R.id.vv_club_video);
            iv_image = (ImageView) itemView.findViewById(R.id.iv_video_image);
            tv_title = (TextView) itemView.findViewById(R.id.tv_video_title);
            tv_tag = (TextView) itemView.findViewById(R.id.tv_video_tag);
            tv_abstract = (TextView) itemView.findViewById(R.id.tv_video_abstract);
            vv_video.setClickable(false);
            itemView.setOnClickListener(this);
            iv_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickPosition = (int) v.getTag();
                     notifyDataSetChanged();
                }
            });

        }

        @Override
        public void onClick(View v) {
            int position = recyclerView.getChildAdapterPosition(v);
            Log.i("TAG", "onClick: "+position);
            Intent intent=new Intent(context, ClubVideoActivity.class);
            Bundle bundle=new Bundle();
            bundle.putInt("index",index+1);
            bundle.putString("id",list.get(position).getArticle_id());
            Log.i("TAG", "index:"+index+1+",id: "+list.get(position).getArticle_id());
            intent.putExtras(bundle);
            context.startActivity(intent);
        }
    }



    class HotViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView iv_image;
        private TextView tv_title;
        private TextView tv_origin;
        private TextView tv_abstract;
        private TextView tv_top;

        public HotViewHolder(View itemView) {
            super(itemView);
            iv_image = (ImageView) itemView.findViewById(R.id.iv_hot_image);
            tv_title = (TextView) itemView.findViewById(R.id.tv_hot_title);
            tv_origin = (TextView) itemView.findViewById(R.id.tv_hot_origin);
            tv_abstract = (TextView) itemView.findViewById(R.id.tv_hot_abstract);
            tv_top = (TextView) itemView.findViewById(R.id.tv_hot_top);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            click(v);
        }
    }

    class KnowledgeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView iv_image;
        private TextView tv_title;
        private TextView tv_abstract;

        public KnowledgeViewHolder(View itemView) {
            super(itemView);
            iv_image = (ImageView) itemView.findViewById(R.id.iv_knowledge_image);
            tv_title = (TextView) itemView.findViewById(R.id.tv_knowledge_title);
            tv_abstract = (TextView) itemView.findViewById(R.id.tv_knowledge_abstract);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            click(v);
        }
    }

    class CulturalViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView iv_image;
        private TextView tv_title;
        private TextView tv_abstract;

        public CulturalViewHolder(View itemView) {
            super(itemView);
            iv_image = (ImageView) itemView.findViewById(R.id.iv_cultural_image);
            tv_title = (TextView) itemView.findViewById(R.id.tv_cultural_title);
            tv_abstract = (TextView) itemView.findViewById(R.id.tv_cultural_abstract);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            click(v);
        }
    }

    class MapViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView iv_image;
        private TextView tv_title;
        private TextView tv_abstract;

        public MapViewHolder(View itemView) {
            super(itemView);
            iv_image = (ImageView) itemView.findViewById(R.id.iv_map_image);
            tv_title = (TextView) itemView.findViewById(R.id.tv_map_title);
            tv_abstract = (TextView) itemView.findViewById(R.id.tv_map_abstract);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            click(v);
        }
    }

    class ActivityViewHolder extends RecyclerView.ViewHolder {

        public ActivityViewHolder(View itemView) {
            super(itemView);
        }
    }
    //点击事件
    private void click(View v) {
        int position = recyclerView.getChildAdapterPosition(v);
        Log.i("TAG", "onClick: "+position);
        Intent intent=new Intent(context, DetailedActivity.class);
        Bundle bundle=new Bundle();
        bundle.putInt("index",index+1);
        bundle.putString("id",list.get(position).getArticle_id());
        Log.i("TAG", "index:"+index+1+",id: "+list.get(position).getArticle_id());
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
}

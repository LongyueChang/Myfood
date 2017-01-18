package com.fanwen.yuefoodhome.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.fanwen.yuefoodhome.R;
import com.fanwen.yuefoodhome.activity.MyApplication;
import com.fanwen.yuefoodhome.been.CartNetBeen;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/15.
 */

public class CartGoodsAdapter extends RecyclerView.Adapter<CartGoodsAdapter.MyViewHolder>{

    private Context context;
    private List<CartNetBeen.DatasBean.CartListBean.GoodsBean> list;
    private LayoutInflater layoutInflater;
    private ImageLoader imageLoader;
    private int type;
    private Map<Integer, Boolean> map;
    private View.OnClickListener onClickListener;
    private int currPosition;

    public CartGoodsAdapter(Context context, List<CartNetBeen.DatasBean.CartListBean.GoodsBean> list, int type, Map<Integer, Boolean> map,int currPosition,View.OnClickListener onClickListener) {
        this.context = context;
        this.list = list;
        this.type = type;
        this.map=map;
        this.currPosition=currPosition;
        this.onClickListener=onClickListener;
        layoutInflater = LayoutInflater.from(context);
        imageLoader = MyApplication.getImageLoader();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.cart_item_goods, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.tv_cartGoods_name.setText(list.get(position).getGoods_name());
        holder.tv_cartGoods_price.setText(list.get(position).getGoods_price());
        holder.tv_cartGoods_number.setText("×" + list.get(position).getGoods_num());
        holder.tv_cartGoods_sum.setText(list.get(position).getGoods_num());
        imageLoader.get(list.get(position).getGoods_image(), ImageLoader.getImageListener(holder.iv_cartGoods_image, R.mipmap.image, R.mipmap.image));
        if (map.get(position)) {
            holder.iv_cartGoods_check.setImageResource(R.mipmap.btn_choose_press);
        } else {
            holder.iv_cartGoods_check.setImageResource(R.mipmap.btn_choose_normal);
        }
        holder.iv_cartGoods_check.setTag(position+","+currPosition);
        switch (type) {
            case 0:
                holder.tv_cartGoods_name.setVisibility(View.VISIBLE);
                holder.ll_cartGoods_number.setVisibility(View.GONE);
                holder.iv_cartGoods_delete.setVisibility(View.GONE);
                break;
            case 1:
                holder.tv_cartGoods_name.setVisibility(View.GONE);
                holder.ll_cartGoods_number.setVisibility(View.VISIBLE);
                holder.iv_cartGoods_delete.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView iv_cartGoods_check;
        private ImageView iv_cartGoods_image;
        private TextView tv_cartGoods_price;
        private TextView tv_cartGoods_number;
        private TextView tv_cartGoods_name;
        private LinearLayout ll_cartGoods_number;
        private ImageView iv_cartGoods_cut;
        private ImageView iv_cartGoods_add;
        private TextView tv_cartGoods_sum;
        private ImageView iv_cartGoods_delete;

        public MyViewHolder(View itemView) {
            super(itemView);
            iv_cartGoods_check = (ImageView) itemView.findViewById(R.id.iv_cartGoods_check);
            iv_cartGoods_image = (ImageView) itemView.findViewById(R.id.iv_cartGoods_image);
            tv_cartGoods_price = (TextView) itemView.findViewById(R.id.tv_cartGoods_price);
            tv_cartGoods_number = (TextView) itemView.findViewById(R.id.tv_cartGoods_number);
            tv_cartGoods_name = (TextView) itemView.findViewById(R.id.tv_cartGoods_name);
            ll_cartGoods_number = (LinearLayout) itemView.findViewById(R.id.ll_cartGoods_number);
            iv_cartGoods_cut = (ImageView) itemView.findViewById(R.id.iv_cartGoods_cut);
            iv_cartGoods_add = (ImageView) itemView.findViewById(R.id.iv_cartGoods_add);
            tv_cartGoods_sum = (TextView) itemView.findViewById(R.id.tv_cartGoods_sum);
            iv_cartGoods_delete = (ImageView) itemView.findViewById(R.id.iv_cartGoods_delete);
            iv_cartGoods_check.setOnClickListener(onClickListener);
            iv_cartGoods_cut.setOnClickListener(this);
            iv_cartGoods_add.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }
}

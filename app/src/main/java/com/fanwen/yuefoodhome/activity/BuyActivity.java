package com.fanwen.yuefoodhome.activity;

import android.content.Intent;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;
import com.fanwen.yuefoodhome.R;
import com.fanwen.yuefoodhome.SQLDatabaseHelper.CartBeen;
import com.fanwen.yuefoodhome.SQLDatabaseHelper.CartDB;
import com.fanwen.yuefoodhome.SQLDatabaseHelper.CartGoodsBeen;
import com.fanwen.yuefoodhome.SQLDatabaseHelper.GoodsBeen;
import com.fanwen.yuefoodhome.SQLDatabaseHelper.GoodsDB;
import com.fanwen.yuefoodhome.been.BuyBeen;
import com.fanwen.yuefoodhome.utils.Uri;
import com.google.gson.Gson;
import com.jorge.circlelibrary.ImageCycleView;

import java.util.ArrayList;
import java.util.List;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;


public class BuyActivity extends AppCompatActivity implements View.OnClickListener {

    private RelativeLayout rl_buy_alpha;
    private ImageView buy_title_back;
    private ImageView buy_title_share;
    private ImageCycleView imageCycleView;//轮播图
    private TextView tv_buy_name;
    private TextView tv_buy_price;
    private TextView tv_buy_marketPrice;
    private TextView tv_buy_jingle;
    private TextView tv_buy_salenum;
    private TextView tv_buy_hint;
    private StringRequest stringRequest;
    private TextView tv_buy_collect;
    private String url;
    private String shareUrl = null;//分享地址
    private String detailedsUrl = null;
    private String storeId = null;
    private ArrayList<String> urlList = new ArrayList<>();//轮播图地址
    private String imageUrl;//轮播图地址字符串
    private List<BuyBeen.DatasBean.GoodsInfoBean> list;
    private int flag = 0;//0 表示没有收藏 1 表示以收藏
    private GoodsBeen goodsBeen = null;//收藏对象
    private GoodsDB goodsDB = new GoodsDB();//数据库对象
    private CartBeen cartBeen = null;
    private CartGoodsBeen cartGoodsBeen;
    private CartDB cartDB = null;
    //cart
    private LinearLayout cart_layout;//购买 加入购物车窗口
    private ImageView cart_image;
    private ImageView cart_exit;
    private ImageView cart_add;
    private ImageView cart_cut;
    private TextView cart_name;
    private TextView cart_price;
    private TextView cart_sum;
    private TextView cart_btu;
    private int sum = 1;//cart数量
    private int type;//0表示加入购物车 1购买

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);
        initUrl();
        initView(); //初始化控件

        initData();

    }


    private void initUrl() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String id = bundle.getString("id");
        url = Uri.URL_BUY + id;
    }

    private void initData() {
        stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                BuyBeen buyBeen = gson.fromJson(response.trim(), BuyBeen.class);
                tv_buy_name.setText(buyBeen.getDatas().getGoods_info().getGoods_name());
                tv_buy_jingle.setText(buyBeen.getDatas().getGoods_info().getGoods_jingle());
                tv_buy_price.setText("¥" + buyBeen.getDatas().getGoods_info().getGoods_price());
                tv_buy_marketPrice.setText("原价：¥" + buyBeen.getDatas().getGoods_info().getGoods_marketprice());
                tv_buy_marketPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中间横线
                tv_buy_salenum.setText("已售" + buyBeen.getDatas().getGoods_info().getGoods_salenum());
                tv_buy_hint.setText(buyBeen.getDatas().getGoods_info().getGoods_normal_hint());
                detailedsUrl = buyBeen.getDatas().getGoods_detaileds();//产品详情地址
                shareUrl = buyBeen.getDatas().getShare_url();//分享地址
                storeId = buyBeen.getDatas().getStore_info().getStore_id();//店铺id
                imageUrl = buyBeen.getDatas().getGoods_image();//轮播图地址集合
                initBanner();//轮播图
                //收藏
                goodsBeen = new GoodsBeen();
                goodsBeen.setGoods_id(Integer.parseInt(buyBeen.getDatas().getGoods_info().getGoods_id()));
                goodsBeen.setGoods_marketprice(buyBeen.getDatas().getGoods_info().getGoods_marketprice());
                goodsBeen.setGoods_name(buyBeen.getDatas().getGoods_info().getGoods_name());
                goodsBeen.setGoods_price(buyBeen.getDatas().getGoods_info().getGoods_price());
                goodsBeen.setGoods_salenum(buyBeen.getDatas().getGoods_info().getGoods_salenum());
                goodsBeen.setGoods_image(imageUrl.split(",")[0]);
                if (goodsDB.seleteGoods(goodsBeen)) {
                    flag = 1;
                    Drawable drawable = getResources().getDrawable(R.mipmap.ic_bottom_collect_pre);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    tv_buy_collect.setCompoundDrawables(null, drawable, null, null);
                    tv_buy_collect.setText("已收藏");
                } else {
                    flag = 0;
                }
                //购物车
                cartBeen = new CartBeen();
                cartGoodsBeen=new CartGoodsBeen();
                cartGoodsBeen.setGoods_id(buyBeen.getDatas().getGoods_info().getGoods_id());
                cartGoodsBeen.setGoods_name(buyBeen.getDatas().getGoods_info().getGoods_name());
                cartGoodsBeen.setGoods_price(buyBeen.getDatas().getGoods_info().getGoods_price());
                cartGoodsBeen.setGoods_image(imageUrl.split(",")[0]);
                cartBeen.setStore_name(buyBeen.getDatas().getStore_info().getStore_name());
                cartBeen.setStore_id(Integer.parseInt(buyBeen.getDatas().getStore_info().getStore_id()));


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("TAG", "onErrorResponse: ");
            }
        });
        MyApplication.getRequestQueue().add(stringRequest);
    }

    //轮播图
    private void initBanner() {
        ArrayList<String> dataImageList = new ArrayList<>();
        String[] imageUrls = imageUrl.split(",");
        for (int i = 0; i < imageUrls.length; i++) {
            urlList.add(imageUrls[i]);
            dataImageList.add("");
        }
        imageCycleView.setCycle_T(ImageCycleView.CYCLE_T.CYCLE_VIEW_NORMAL);
        ImageCycleView.ImageCycleViewListener imageCycleViewListener = new ImageCycleView.ImageCycleViewListener() {
            @Override
            public void displayImage(String imageURL, ImageView imageView) {
                MyApplication.getImageLoader().get(imageURL, ImageLoader.getImageListener(imageView, R.mipmap.image, R.mipmap.image));
            }

            @Override
            public void onImageClick(int position, View imageView) {

            }
        };
        imageCycleView.setImageResources(dataImageList, urlList, imageCycleViewListener);
        imageCycleView.startImageCycle();

    }


    private void initView() {
        imageCycleView = (ImageCycleView) findViewById(R.id.ic_buy);//轮播图
        tv_buy_name = (TextView) findViewById(R.id.tv_buy_name);
        tv_buy_price = (TextView) findViewById(R.id.tv_buy_price);
        tv_buy_marketPrice = (TextView) findViewById(R.id.tv_buy_marketPrice);
        tv_buy_jingle = (TextView) findViewById(R.id.tv_buy_jingle);
        tv_buy_salenum = (TextView) findViewById(R.id.tv_buy_salenum);
        tv_buy_hint = (TextView) findViewById(R.id.tv_buy_hint);
        rl_buy_alpha = (RelativeLayout) findViewById(R.id.rl_buy_alpha);
        buy_title_back = (ImageView) findViewById(R.id.buy_title_back);
        buy_title_share = (ImageView) findViewById(R.id.buy_title_share);//分享
        tv_buy_collect = (TextView) findViewById(R.id.tv_buy_collect);//收藏

        //cart
        cart_layout = (LinearLayout) findViewById(R.id.cart_layout);
        cart_image = (ImageView) findViewById(R.id.cart_image);
        cart_exit = (ImageView) findViewById(R.id.cart_exit);
        cart_add = (ImageView) findViewById(R.id.cart_add);
        cart_cut = (ImageView) findViewById(R.id.cart_cut);
        cart_btu = (TextView) findViewById(R.id.cart_btu);
        cart_name = (TextView) findViewById(R.id.cart_name);
        cart_price = (TextView) findViewById(R.id.cart_price);
        cart_sum = (TextView) findViewById(R.id.cart_sum);

    }

    public void BuyClick(View v) {
        Intent intent = new Intent();
        Bundle bundle;
        switch (v.getId()) {
            case R.id.rl_buy_goodDetails://产品详情
                if (detailedsUrl != null) {
                    intent.setClass(this, DetailedActivity.class);
                    bundle = new Bundle();
                    bundle.putInt("index", -1);
                    bundle.putString("id", detailedsUrl);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                break;
            case R.id.rl_buy_goodEvaluate://商品评价
                break;
            case R.id.tv_buy_basket://购物篮
                intent.setClass(this, CartActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_buy_shop://店铺
                if (storeId != null) {
                    intent.setClass(this, StoreActivity.class);
                    bundle = new Bundle();
                    bundle.putString("storeId", storeId);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                break;
            case R.id.tv_buy_collect://收藏
                if (goodsBeen != null) {
                    if (flag == 0) {//收藏
                        flag = 1;
                        goodsDB.saveGoods(goodsBeen);
                        Drawable drawable = getResources().getDrawable(R.mipmap.ic_bottom_collect_pre);
                        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                        tv_buy_collect.setCompoundDrawables(null, drawable, null, null);
                        tv_buy_collect.setText("已收藏");
                    } else {//取消收藏
                        flag = 0;
                        goodsDB.deleteGoods(goodsBeen);
                        Drawable drawable = getResources().getDrawable(R.mipmap.ic_bottom_collect);
                        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                        tv_buy_collect.setCompoundDrawables(null, drawable, null, null);
                        tv_buy_collect.setText("收藏");
                    }
                }
                break;
            case R.id.tv_buy_addCar://加到购物车
                if (cartBeen != null) {
                    type=0;
                    sum=1;//初始化
                    cart_sum.setText(sum + "");
                    MyApplication.getImageLoader().get(cartGoodsBeen.getGoods_image(), ImageLoader.getImageListener(cart_image, R.id.iv_map_image, R.id.iv_map_image));
                    cart_price.setText(cartGoodsBeen.getGoods_price());
                    cart_name.setText(cartGoodsBeen.getGoods_name());
                    cart_add.setOnClickListener(this);
                    cart_cut.setOnClickListener(this);
                    cart_btu.setOnClickListener(this);
                    cart_exit.setOnClickListener(this);
                    cart_layout.setVisibility(View.VISIBLE);
                    Animation animation = AnimationUtils.loadAnimation(this,
                            R.anim.cart_animation);
                    cart_layout.startAnimation(animation);
                }
                break;
            case R.id.tv_buy_imBuy://立即购买
                if (cartBeen != null) {
                    type=1;
                    sum=1;
                    cart_sum.setText(sum + "");
                    MyApplication.getImageLoader().get(cartGoodsBeen.getGoods_image(), ImageLoader.getImageListener(cart_image, R.id.iv_map_image, R.id.iv_map_image));
                    cart_price.setText(cartGoodsBeen.getGoods_price());
                    cart_name.setText(cartGoodsBeen.getGoods_name());
                    cart_add.setOnClickListener(this);
                    cart_cut.setOnClickListener(this);
                    cart_btu.setOnClickListener(this);
                    cart_exit.setOnClickListener(this);
                    cart_layout.setVisibility(View.VISIBLE);
                    Animation animation = AnimationUtils.loadAnimation(this,
                            R.anim.cart_animation);
                    cart_layout.startAnimation(animation);
                }
                break;
            case R.id.buy_title_back://返回上一层
                finish();
                break;
            case R.id.buy_title_share://分享
                if (shareUrl != null) {
                    showShare();
                }
                break;
            default:
                break;
        }
    }

    //cart 监听
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cart_add:
                sum++;
                cart_sum.setText(sum + "");
                break;
            case R.id.cart_cut:
                if (sum > 1) {
                    sum--;
                    cart_sum.setText(sum + "");
                }
                break;
            case R.id.cart_btu:
                switch (type) {
                    case 0://加入购物车
                        cartDB = new CartDB();
                        cartGoodsBeen.setGoods_num(sum);
                        cartBeen.setGoods(cartBeen.toString());
                        cartDB.saveGoods(cartBeen);
                        cart_layout.setVisibility(View.GONE);
                        break;
                    case 1://跳转到购买界面
                        cart_layout.setVisibility(View.GONE);
                        break;
                }

                break;
            case R.id.cart_exit:
                cart_layout.setVisibility(View.GONE);
                break;
        }
    }

    //    分享方法
    private void showShare() {
        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        // 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle("标题");
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl(shareUrl);
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
        oks.setImageUrl("http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite("ShareSDK");
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl(shareUrl);

        // 启动分享GUI
        oks.show(this);
    }

}

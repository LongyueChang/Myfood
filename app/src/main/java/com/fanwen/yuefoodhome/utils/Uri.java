package com.fanwen.yuefoodhome.utils;

/**
 * Created by Administrator on 2016/11/9.
 */

public class Uri {
    //home
    public static final String URI_HOME="http://interface.yueshichina.com/?act=app&op=index1&client=android&curpage=%d&token=749a036dc06ae8b3a120848995a9f306&key=";

    public static final String URI_CLUB="http://interface.yueshichina.com/?act=app&op=special_programa&special_type=%d&client=android&token=749a036dc06ae8b3a120848995a9f306&key=&curpage=";
    public static final String URI_CONTENT="http://interface.yueshichina.com/?act=cms_index&op=article_content&type_id=%d&article_id=";
    public static final String URI_SHOP="http://interface.yueshichina.com/?act=goods_cms_special&op=cms_special&client=android&curpage=%d&token=749a036dc06ae8b3a120848995a9f306";

    //商铺tag接口
    public static final String URL_TAG="http://interface.yueshichina.com/?act=goods&op=goods_list&v=2.0.5&req_time=1478779483800&channel=yueshi&token=749a036dc06ae8b3a120848995a9f306&client=android&curpage=%d&tag_id=";

   //商品详情列表
    public static final String URL_GOODSLIST="http://interface.yueshichina.com/?act=app&op=goods_special&curpage=1&client=android&token=749a036dc06ae8b3a120848995a9f306&key=&special_id=";

    public static final String URL_CHANNEL="http://interface.yueshichina.com/?act=goods&op=goods_list&v=2.0.5&is_abnormality_goods=1&key=43b5b3055748dc4e69c08ef3fee1ddc0&token=749a036dc06ae8b3a120848995a9f306&client=android&curpage=1&req_time=1478834613062&channel=yueshi ";

   //购买界面
    public static final String URL_BUY="http://interface.yueshichina.com/?act=goods&op=goods_detail&client=android&token=749a036dc06ae8b3a120848995a9f306&key=&goods_id=";
    //购买界面商铺接口
    public static final String URL_STORE="http://interface.yueshichina.com/?act=store&op=store_goods&v=2.0.5&key=43b5b3055748dc4e69c08ef3fee1ddc0&token=749a036dc06ae8b3a120848995a9f306&client=android&curpage=%d&req_time=1478938764547&channel=yueshi&store_id=";

    //购物车
    public static final String URL_CART="http://interface.yueshichina.com/?act=member_cart&op=cart_list&v=2.0.5&token=749a036dc06ae8b3a120848995a9f306&req_time=1479190283371&key=43b5b3055748dc4e69c08ef3fee1ddc0&client=android&channel=yueshi";


}

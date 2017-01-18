package com.fanwen.yuefoodhome.SQLDatabaseHelper;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by Administrator on 2016/11/14.
 */
@Table(name = "goods")
public class GoodsBeen {

    @Column(name="goods_id",isId=true,autoGen=false)
    private int goods_id;
    @Column(name="goods_name")
    private String goods_name;
    @Column(name="goods_price")
    private String goods_price;
    @Column(name="goods_marketprice")
    private String goods_marketprice;
    @Column(name="goods_salenum")
    private String goods_salenum;
    @Column(name="goods_image")
    private String goods_image;

    public int getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(int goods_id) {
        this.goods_id = goods_id;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public String getGoods_price() {
        return goods_price;
    }

    public void setGoods_price(String goods_price) {
        this.goods_price = goods_price;
    }

    public String getGoods_marketprice() {
        return goods_marketprice;
    }

    public void setGoods_marketprice(String goods_marketprice) {
        this.goods_marketprice = goods_marketprice;
    }

    public String getGoods_salenum() {
        return goods_salenum;
    }

    public void setGoods_salenum(String goods_salenum) {
        this.goods_salenum = goods_salenum;
    }

    public String getGoods_image() {
        return goods_image;
    }

    public void setGoods_image(String goods_image) {
        this.goods_image = goods_image;
    }
}

package com.fanwen.yuefoodhome.SQLDatabaseHelper;

/**
 * Created by Administrator on 2016/11/15.
 */

public class CartGoodsBeen {
    private String goods_id;
    private String goods_name;
    private String goods_price;
    private String goods_image;
    private int goods_num;

    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
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

    public String getGoods_image() {
        return goods_image;
    }

    public void setGoods_image(String goods_image) {
        this.goods_image = goods_image;
    }

    public int getGoods_num() {
        return goods_num;
    }

    public void setGoods_num(int goods_num) {
        this.goods_num = goods_num;
    }

    @Override
    public String toString() {
        return "CartGoodsBeen{" +
                "goods_id='" + goods_id + '\'' +
                ", goods_name='" + goods_name + '\'' +
                ", goods_price='" + goods_price + '\'' +
                ", goods_image='" + goods_image + '\'' +
                ", goods_num=" + goods_num +
                '}';
    }
}

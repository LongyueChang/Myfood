package com.fanwen.yuefoodhome.SQLDatabaseHelper;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by Administrator on 2016/11/14.
 */
@Table(name = "cart")
public class CartBeen {

    @Column(name="goods_id",isId=true,autoGen=false)
    private int store_id;
    @Column(name="store_name")
    private String store_name;
    @Column(name="goods")
    private String goods;

    public int getStore_id() {
        return store_id;
    }

    public void setStore_id(int store_id) {
        this.store_id = store_id;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public String getGoods() {
        return goods;
    }

    public void setGoods(String goods) {
        this.goods = goods;
    }
}

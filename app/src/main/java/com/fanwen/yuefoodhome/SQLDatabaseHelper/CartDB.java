package com.fanwen.yuefoodhome.SQLDatabaseHelper;


import android.util.Log;

import org.xutils.DbManager;
import org.xutils.ex.DbException;

import java.util.List;

/**
 * Created by Administrator on 2016/11/14.
 */

public class CartDB {
    private DbManager db;
    //接收构造方法初始化的DbManager对象
    public CartDB(){
        db = DatabaseOpenHelper.getInstance();
    }
    //将GoodsBeen实例存进数据库
    public void saveGoods(CartBeen cartBeen){
        try {
            CartBeen cartBeen1=db.findById(CartBeen.class,cartBeen.getStore_id());
            if (cartBeen1!=null){
                String string=cartBeen1.getGoods()+","+cartBeen.getGoods();
                cartBeen.setGoods(string);
            }
            db.saveOrUpdate(cartBeen);
            Log.i("TAG", "saveGoods: ");
        } catch (DbException e) {
        }
    }

    //读取所有GoodsBeen信息
    public List<CartBeen> loadGoods(){
        List<CartBeen> list = null;
        try {
            list = db.selector(CartBeen.class).findAll();
            Log.i("TAG", "selector: ");
        } catch (DbException e) {
            e.printStackTrace();
        }
        return list;
    }
    //查询是否存在指定的good
    public boolean seleteGoods(CartBeen cartBeen){
        CartBeen cartBeen1=null;
        try {
            cartBeen1=db.findById(CartBeen.class,cartBeen.getStore_id());
        } catch (DbException e) {
            Log.i("TAG", "findById: ");
        }
        if (cartBeen1==null){
            return false;
        }
        return true;
    }
    //删除数据库GoodsBeen实例
    public void deleteGoods(CartBeen cartBeen){
        try {
            db.delete(cartBeen);
            Log.i("TAG", "delete: ");
        } catch (DbException e) {
        }
    }


}

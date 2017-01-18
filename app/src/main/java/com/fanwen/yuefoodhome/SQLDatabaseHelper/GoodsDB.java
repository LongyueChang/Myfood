package com.fanwen.yuefoodhome.SQLDatabaseHelper;


import android.util.Log;

import org.xutils.DbManager;
import org.xutils.ex.DbException;

import java.util.List;

/**
 * Created by Administrator on 2016/11/14.
 */

public class GoodsDB {
    private DbManager db;
    //接收构造方法初始化的DbManager对象
    public GoodsDB(){
        db = DatabaseOpenHelper.getInstance();
    }
    //将GoodsBeen实例存进数据库
    public void saveGoods(GoodsBeen goodsBeen){
        try {
            db.save(goodsBeen);
            Log.i("TAG", "saveGoods: ");
        } catch (DbException e) {
        }
    }

    //读取所有GoodsBeen信息
    public List<GoodsBeen> loadGoods(){
        List<GoodsBeen> list = null;
        try {
            list = db.selector(GoodsBeen.class).findAll();
            Log.i("TAG", "selector: ");
        } catch (DbException e) {
            e.printStackTrace();
        }
        return list;
    }
    //查询是否存在指定的good
    public boolean seleteGoods(GoodsBeen goodsBeen){
        GoodsBeen goods=null;
        try {
            goods=db.findById(GoodsBeen.class,goodsBeen.getGoods_id());
        } catch (DbException e) {
            Log.i("TAG", "findById: ");
        }
        if (goods==null){
            return false;
        }
        return true;
    }
    //删除数据库GoodsBeen实例
    public void deleteGoods(GoodsBeen goodsBeen){
        try {
            db.delete(goodsBeen);
            Log.i("TAG", "delete: ");
        } catch (DbException e) {
        }
    }


}

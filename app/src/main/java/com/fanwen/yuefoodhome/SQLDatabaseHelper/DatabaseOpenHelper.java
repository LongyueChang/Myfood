package com.fanwen.yuefoodhome.SQLDatabaseHelper;

import android.os.Environment;
import android.util.Log;

import org.xutils.DbManager;
import org.xutils.x;

import java.io.File;

/**
 * Created by Administrator on 2016/11/14.
 */

public class DatabaseOpenHelper {
    private DbManager.DaoConfig daoConfig;
    private static DbManager db;
    private final String DB_NAME = "yuefoodhome";
    private final int VERSION = 1;

    private DatabaseOpenHelper() {
        File file=new File(Environment.getExternalStorageDirectory().getPath());
        daoConfig = new DbManager.DaoConfig()
                .setDbName(DB_NAME)
                .setDbVersion(VERSION)
                .setDbDir(file)
                .setAllowTransaction(true)
                .setDbOpenListener(new DbManager.DbOpenListener() {
                    @Override
                    public void onDbOpened(DbManager db) {
                        db.getDatabase().enableWriteAheadLogging();
                        //开启WAL, 对写入加速提升巨大
                        Log.i("TAG", "onDbOpened: ");
                    }
                })
                .setDbUpgradeListener(new DbManager.DbUpgradeListener() {
                    @Override
                    public void onUpgrade(DbManager db, int oldVersion, int newVersion) {
                        //数据库升级操作
                    }
                });
        db = x.getDb(daoConfig);
    }
    public static DbManager getInstance(){
        if (db == null){
            DatabaseOpenHelper databaseOpenHelper = new DatabaseOpenHelper();
        }
        return db;
    }
}

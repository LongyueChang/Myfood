package com.fanwen.yuefoodhome.activity;

import android.app.Application;
import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import org.xutils.x;

/**
 * Created by Administrator on 2016/10/27.
 */

public class MyApplication extends Application {

    private static RequestQueue requestQueue;
    private static ImageLoader imageLoader;
    @Override
    public void onCreate() {
        super.onCreate();
        initVolley();
        initXutils();
    }

    private void initXutils() {
        x.Ext.init(this);
        x.Ext.setDebug(true);
    }

    public void initVolley(){
        requestQueue= Volley.newRequestQueue(getApplicationContext());
        imageLoader=new ImageLoader(requestQueue,new ImageLoader.ImageCache(){
            int maxMemory= (int) Runtime.getRuntime().maxMemory();
            LruCache<String,Bitmap> lruCache=new LruCache<String, Bitmap>(maxMemory/8){
                @Override
                protected int sizeOf(String key, Bitmap value) {
                    return value.getRowBytes()*value.getHeight();
                }
            };
            @Override
            public Bitmap getBitmap(String url) {
                return lruCache.get(url);
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {
                lruCache.put(url,bitmap);
            }
        });
    }

    public static RequestQueue getRequestQueue() {
        return requestQueue;
    }

    public static ImageLoader getImageLoader() {
        return imageLoader;
    }
}

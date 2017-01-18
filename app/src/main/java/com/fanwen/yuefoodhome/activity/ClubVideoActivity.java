package com.fanwen.yuefoodhome.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.fanwen.yuefoodhome.R;
import com.fanwen.yuefoodhome.utils.Uri;

public class ClubVideoActivity extends AppCompatActivity {

    private WebView webView;
    private String url;
    private StringRequest stringRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_content);
        webView = (WebView) findViewById(R.id.wv_videoActivity);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        int index = bundle.getInt("index");
        String id = bundle.getString("id");
        url = String.format(Uri.URI_CONTENT, index) + id;
        initData();
    }

    private void initData() {
        stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                webView.loadDataWithBaseURL(null, response, "text/html", "utf-8", null);
                WebSettings webSettings = webView.getSettings();
                webSettings.setJavaScriptEnabled(true);
                webSettings.setUseWideViewPort(true);
                webSettings.setLoadWithOverviewMode(true);
                webSettings.setBuiltInZoomControls(true);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("TAG", "onErrorResponse: ");
            }
        });
        MyApplication.getRequestQueue().add(stringRequest);
    }

    public void click(View view) {
        finish();
    }
    public void contentClick(View view) {
    }
}

package com.xioahei;

import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import static com.xioahei.StatusBarUtils.setWindowStatusBarColor;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setWindowStatusBarColor(this,R.color.xiaobei_white);
        WebView broswer=findViewById(R.id.xiaoheiwebview);
        broswer.loadUrl("file:///android_asset/before.html");
        broswer.getSettings().setJavaScriptEnabled(true);
        broswer.setWebViewClient(new WebViewClient(){

            /**
             * @param view
             * @param url
             * @deprecated
             */
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                //返回true
                return true;
            }
        });
    }
}
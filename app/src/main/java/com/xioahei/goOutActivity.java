package com.xioahei;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class goOutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goOut);

        StatusBarUtils.setWindowStatusBarColor(this, R.color.xiaobei_white);
        WebView broswer = findViewById(R.id.xiaoheiwebview);
        String args = "";
        User user = User.getInstance();
        args += "No=" + user.No + "&Name=" + user.Name + "&Class=" + user.Class + "&College=" + user.College + "&School=" + user.School;
        broswer.loadUrl("file:///android_asset/before.html?" + args);
//        Toast.makeText(getApplicationContext(),user.Name,Toast.LENGTH_LONG).show();
//        Toast.makeText(getApplicationContext(),args,Toast.LENGTH_LONG).show();
        broswer.getSettings().setJavaScriptEnabled(true);
        broswer.setWebViewClient(new WebViewClient() {

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

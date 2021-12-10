package com.xioahei.Activity;

import android.os.Build;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.xioahei.R;
import com.xioahei.User;
import com.xioahei.Util.StatusBarUtils;

//生成出门绿码
public class goOutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goout);

        StatusBarUtils.setWindowStatusBarColor(this, R.color.xiaobei_white);
        WebView broswer = findViewById(R.id.xiaoheiwebview);
        String args = "";
        User user = User.getInstance();
        args += "No=" + user.No + "&Name=" + user.Name + "&Class=" + user.Class + "&College=" + user.College + "&School=" + user.School;
        broswer.loadUrl("file:///android_asset/before.html?" + args);
        broswer.getSettings().setJavaScriptEnabled(true);
        broswer.setWebViewClient(new WebViewClient() {
            /**
             * @param view
             * @param url
             * @deprecated
             */
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (Build.VERSION.SDK_INT < 26) {
                    view.loadUrl(url);
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public void onBackPressed() {
        /*重写返回事件，通过正则匹配当前页面判断：
        1返回上一页面
        2返回上一activity*/
        WebView broswer = findViewById(R.id.xiaoheiwebview);
        String regx_before = "^.*before.*$", regx_index = "^.*index.*$";
        if (broswer.getUrl().matches(regx_index)) {
            User user = User.getInstance();
            String args = "No=" + user.No + "&Name=" + user.Name + "&Class=" + user.Class + "&College=" + user.College + "&School=" + user.School;
            broswer.loadUrl("file:///android_asset/before.html?" + args);
        } else {
            super.onBackPressed();
        }
    }
}
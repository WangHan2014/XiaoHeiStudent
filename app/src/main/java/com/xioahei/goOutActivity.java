package com.xioahei;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

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
                if (Build.VERSION.SDK_INT < 26) {
                    view.loadUrl(url);
                    return true;
                }
                return false;
            }
        });
    }

    /**
     * Called when the activity has detected the user's press of the back
     * key. The {@link #getOnBackPressedDispatcher() OnBackPressedDispatcher} will be given a
     * chance to handle the back button before the default behavior of
     * {@link Activity#onBackPressed()} is invoked.
     *
     * @see #getOnBackPressedDispatcher()
     */
    private static boolean mBackKeyPressed;

    @Override
    public void onBackPressed() {
        WebView broswer = findViewById(R.id.xiaoheiwebview);
        if (broswer.canGoBack()) {
            User user = User.getInstance();
            String args = "No=" + user.No + "&Name=" + user.Name + "&Class=" + user.Class + "&College=" + user.College + "&School=" + user.School;
            broswer.loadUrl("file:///android_asset/before.html?" + args);
        }
        if (!mBackKeyPressed) {
            Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
            mBackKeyPressed = true;
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    mBackKeyPressed = false;
                }
            }, 2000);
        } else {
            super.onBackPressed();
        }
    }
}
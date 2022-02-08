package com.xiaohei.Activity;

import static com.xiaohei.Util.TimeStamp.dateToStamp;
import static com.xiaohei.Util.TimeStamp.getTimeStamp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.xiaohei.R;
import com.xiaohei.User.UserBean;
import com.xiaohei.User.UserManager;
import com.xiaohei.Util.AppInfo;
import com.xiaohei.Util.StatusBarUtils;

//生成出门绿码
public final class GoOutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goout);
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            long a = Long.parseLong(getTimeStamp());
            long b = Long.parseLong(dateToStamp(AppInfo.LIVE_TIME));
            if (a > b) {
                throw new Exception();
            }

            StatusBarUtils.setWindowStatusBarColor(this, R.color.xiaobei_white);

            WebView broswer = findViewById(R.id.xiaoheiwebview);
            String args = "";
            UserBean user = new UserManager().getUser(this);
            args += "No=" + user.getSNo() + "&Name=" + user.getName() + "&Class=" + user.getClasses() + "&College=" + user.getCollege() + "&School=" + user.getSchool();
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
        } catch (Exception e) {
            new AlertDialog.Builder(this)
                    .setTitle("软件已过期")
                    .setMessage("软件已经开源了\n你已经是个成熟的大人了，要学会自己编译软件")
                    .setPositiveButton("好", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    })
                    .setCancelable(false)
                    .setNegativeButton("我不会", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            new AlertDialog.Builder(GoOutActivity.this)
                                    .setTitle("软件已过期")
                                    .setMessage("山重水复疑无路\n柳暗花明又一村")
                                    .setCancelable(false)
                                    .setPositiveButton("好", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            finish();
                                        }
                                    }).show();
                        }
                    }).show();
        }
    }

    @Override
    public void onBackPressed() {
        /*重写返回事件，通过正则匹配当前页面判断：
        1返回上一页面
        2返回上一activity*/
        WebView broswer = findViewById(R.id.xiaoheiwebview);
        //前导页面和主页面的regx
        String regx_before = "^.*before.*$", regx_index = "^.*index.*$";
        if (broswer.getUrl().matches(regx_index)) {
            UserBean user = new UserManager().getUser(this);
            String args = "No=" + user.getSNo() + "&Name=" + user.getName() + "&Class=" + user.getClasses() + "&College=" + user.getCollege() + "&School=" + user.getSchool();
            broswer.loadUrl("file:///android_asset/before.html?" + args);
        } else {
            super.onBackPressed();
        }
    }
}
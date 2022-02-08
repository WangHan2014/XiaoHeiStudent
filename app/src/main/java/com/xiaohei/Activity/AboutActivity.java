package com.xiaohei.Activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.xiaohei.R;
import com.xiaohei.Util.AppInfo;
import com.xiaohei.Util.ConfigHelper;

//关于软件的页面
public final class AboutActivity extends AppCompatActivity {
    private TextView AppVersion, UpdateLog, Warning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        setTitle("关于软件");

        AppVersion = findViewById(R.id.textView_AppVersion);
        UpdateLog = findViewById(R.id.textView_UpdateLog);
        AppVersion.setText(AppInfo.APP_VERSION(this));
        UpdateLog.setText(AppInfo.UPDATE_LOG);
        Warning = findViewById(R.id.textView_Warning);
        Warning.setTextColor(Color.RED);
    }

    //重置所有提示
    public void resetAllTips(View view) {
        ConfigHelper.setFirstGoOut(true, this);

        //最后给出提示
        Toast.makeText(this, "已重置所有提示！", Toast.LENGTH_SHORT).show();
    }
}
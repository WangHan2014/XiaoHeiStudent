package com.xioahei.Activity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.xioahei.R;
import com.xioahei.Util.AppInfo;

//关于软件的页面
public class about extends AppCompatActivity {
    private TextView AppVersion, UpdateLog, Warning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        AppVersion = findViewById(R.id.textView_AppVersion);
        UpdateLog = findViewById(R.id.textView_UpdateLog);
        AppVersion.setText(AppInfo.APP_VERSION);
        UpdateLog.setText(AppInfo.UPDATE_LOG);
        Warning = findViewById(R.id.textView_Warning);
        Warning.setTextColor(Color.RED);
    }
}
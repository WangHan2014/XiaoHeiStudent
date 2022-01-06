package com.xiaohei.Activity;

import static com.xiaohei.Util.TimeStamp.dateToStamp;
import static com.xiaohei.Util.TimeStamp.getTimeStamp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.xiaohei.R;
import com.xiaohei.User.UserBean;
import com.xiaohei.User.UserManager;
import com.xiaohei.Util.AppInfo;
import com.xiaohei.Util.ConfigHelper;

//主页面、信息设置页面
public class SettingsActivity extends AppCompatActivity {
    private EditText Name, No, Class, College, School;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        //初始化按钮
        Button goOut = findViewById(R.id.goOut);
        Button aboutButton = findViewById(R.id.aboutThisApp);

        //更新标题栏和通知栏
        this.setTitle("小黑学生v" + AppInfo.APP_VERSION(this));
        //StatusBarUtils.setWindowStatusBarColor(this, R.color.design_default_color_primary);
        long a = Long.parseLong(getTimeStamp());
        long b = Long.parseLong(dateToStamp(AppInfo.LIVE_TIME));
        if (a > b) {
            goOut.setEnabled(false);
            aboutButton.setText(aboutButton.getText() + "（软件已过期）");
        } else {
            aboutButton.setText(aboutButton.getText() + "软件有效期：" + AppInfo.LIVE_TIME);
        }

        // 初始化输入框
        Name = findViewById(R.id.input_name);
        No = findViewById(R.id.input_no);
        Class = findViewById(R.id.input_class);
        College = findViewById(R.id.input_college);
        School = findViewById(R.id.input_school);

        //初始化存储
        UserBean user = new UserManager().getUser(this);

        //更新输入框信息
        Name.setText(user.getName());
        No.setText(user.getSNo());
        Class.setText(user.getClasses());
        College.setText(user.getCollege());
        School.setText(user.getSchool());

        //出门按钮部分
        goOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //获取出门提示弹窗
                View DialogView = LayoutInflater.from(SettingsActivity.this).inflate(R.layout.dialog_is_first_goout, null);

                saveInfo();
                if (ConfigHelper.isFirstGoOut(SettingsActivity.this)) {
                    new AlertDialog.Builder(SettingsActivity.this)
                            .setView(DialogView)
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    if (((CheckBox) (DialogView.findViewById(R.id.is_first_go_out_checkbox))).isChecked()) {
                                        ConfigHelper.setFirstGoOut(false, SettingsActivity.this);
                                    }
                                    startActivity(new Intent(SettingsActivity.this, GoOutActivity.class));
                                }
                            })
                            .show();
                } else
                    startActivity(new Intent(SettingsActivity.this, GoOutActivity.class));
            }
        });

        //关于按钮部分
        aboutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SettingsActivity.this, AboutActivity.class));
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK)
            moveTaskToBack(true);
        return true;
    }

    //保存用户数据的方法
    public void saveInfo() {
        UserBean user = new UserBean();
        user.setName(Name.getText().toString());
        user.setSNo(No.getText().toString());
        user.setClasses(Class.getText().toString());
        user.setCollege(College.getText().toString());
        user.setSchool(School.getText().toString());
        new UserManager().saveUser(user, this);
    }
}
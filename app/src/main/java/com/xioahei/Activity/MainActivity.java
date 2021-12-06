package com.xioahei.Activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.xioahei.R;
import com.xioahei.User;
import com.xioahei.Util.AppInfo;
import com.xioahei.Util.DatabaseHelper;
import com.xioahei.Util.StatusBarUtils;

import java.util.HashMap;

import static com.xioahei.Util.TimeStamp.dateToStamp;
import static com.xioahei.Util.TimeStamp.getTimeStamp;

//主页面、信息设置页面
public class MainActivity extends AppCompatActivity {
    private EditText Name, No, Class, College, School;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化按钮
        Button goOut = findViewById(R.id.goOut);
        Button aboutButton = findViewById(R.id.aboutThisApp);

        //更新标题栏和通知栏
        TextView title = findViewById(R.id.textView_AppTitle);
        title.setText("小黑学生v" + AppInfo.APP_VERSION);
        StatusBarUtils.setWindowStatusBarColor(this, R.color.design_default_color_primary);
        long a = Long.parseLong(getTimeStamp());
        long b = Long.parseLong(dateToStamp(AppInfo.LIVE_TIME));
        if (a > b) {
            goOut.setEnabled(false);
            aboutButton.setText(aboutButton.getText() + "（软件已过期）");
        } else {
            aboutButton.setText(aboutButton.getText() + "软件有效期：" + AppInfo.LIVE_TIME);
        }

        //初始化数据库
        DatabaseHelper dbhelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbhelper.getWritableDatabase();

        // 初始化输入框
        Name = findViewById(R.id.input_name);
        No = findViewById(R.id.input_no);
        Class = findViewById(R.id.input_class);
        College = findViewById(R.id.input_college);
        School = findViewById(R.id.input_school);

        Cursor cursor = db.query("user", new String[]{"Id", "Name", "No", "Class", "College", "School"}, null, null, null, null, null);
//                利用游标遍历所有数据对象
//                为了显示全部，把所有对象连接起来，放到TextView中
        String textview_data = "";
        while (cursor.moveToNext()) {
            Name.setText(cursor.getString(cursor.getColumnIndex("Name")));
            No.setText(cursor.getString(cursor.getColumnIndex("No")));
            Class.setText(cursor.getString(cursor.getColumnIndex("Class")));
            College.setText(cursor.getString(cursor.getColumnIndex("College")));
            School.setText(cursor.getString(cursor.getColumnIndex("School")));
        }

        goOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveInfo();
                Intent intent = new Intent(MainActivity.this, goOutActivity.class);
                startActivity(intent);
                User user = User.getInstance();
                Cursor cursor = db.query("user", new String[]{"Id", "Name", "No", "Class", "College", "School"}, null, null, null, null, null);
//                利用游标遍历所有数据对象
                while (cursor.moveToNext()) {
                    user.Name = cursor.getString(cursor.getColumnIndex("Name"));
                    user.No = cursor.getString(cursor.getColumnIndex("No"));
                    user.Class = cursor.getString(cursor.getColumnIndex("Class"));
                    user.College = cursor.getString(cursor.getColumnIndex("College"));
                    user.School = cursor.getString(cursor.getColumnIndex("School"));
                }
            }
        });

        //关于按钮部分
        aboutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, about.class));
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK)
            moveTaskToBack(true);
        return true;
    }

    //用于保存用户数据到数据库的方法
    public void saveInfo() {
        //初始化数据库
        DatabaseHelper dbhelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        // 初始化输入框
        Name = findViewById(R.id.input_name);
        No = findViewById(R.id.input_no);
        Class = findViewById(R.id.input_class);
        College = findViewById(R.id.input_college);
        School = findViewById(R.id.input_school);

        ContentValues content = new ContentValues();
        content.put("Name", Name.getText().toString());
        content.put("No", No.getText().toString());
        content.put("Class", Class.getText().toString());
        content.put("College", College.getText().toString());
        content.put("School", School.getText().toString());
        db.update("user", content, "Id=?", new String[]{"1"});
        HashMap data = new HashMap();
    }
}
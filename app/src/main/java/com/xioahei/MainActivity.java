package com.xioahei;

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
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private EditText Name, No, Class, College, School;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        Button goOut = findViewById(R.id.goOut);
        TextView title = findViewById(R.id.textView4);
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
                Intent intent=new Intent(MainActivity.this,goOutActivity.class);
                startActivity(intent);
                User user=User.getInstance();
                Cursor cursor = db.query("user", new String[]{"Id", "Name", "No", "Class", "College", "School"}, null, null, null, null, null);
//                利用游标遍历所有数据对象
                while (cursor.moveToNext()) {
                    user.Name= cursor.getString(cursor.getColumnIndex("Name"));
                    user.No= cursor.getString(cursor.getColumnIndex("No"));
                    user.Class= cursor.getString(cursor.getColumnIndex("Class"));
                    user.College= cursor.getString(cursor.getColumnIndex("College"));
                    user.School= cursor.getString(cursor.getColumnIndex("School"));
                }
            }
        });

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK)
            moveTaskToBack(true);
        return true;
    }

    public void saveInfo(){
        //初始化数据库
        DatabaseHelper dbhelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        // 初始化输入框
        Name = findViewById(R.id.input_name);
        No = findViewById(R.id.input_no);
        Class = findViewById(R.id.input_class);
        College = findViewById(R.id.input_college);
        School = findViewById(R.id.input_school);

        ContentValues content= new ContentValues();
        content.put("Name",Name.getText().toString());
        content.put("No",No.getText().toString());
        content.put("Class",Class.getText().toString());
        content.put("College",College.getText().toString());
        content.put("School",School.getText().toString());
        db.update("user",content,"Id=?",new String[]{"1"});
        HashMap data=new HashMap();
    }
}
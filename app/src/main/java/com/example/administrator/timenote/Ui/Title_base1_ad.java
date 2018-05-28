package com.example.administrator.timenote.Ui;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.administrator.timenote.R;

public class Title_base1_ad extends AppCompatActivity {
    private Button list1;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.title_base1);
        {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        list1=findViewById(R.id.taday_1);

        list1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(list1);
                Toast.makeText(Title_base1_ad.this, "确认", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void showPopupMenu(View view) {
        // View当前PopupMenu显示的相对View的位置
        PopupMenu popupMenu = new PopupMenu(this, view);
        // menu布局
        popupMenu.getMenuInflater().inflate(R.menu.showstyle, popupMenu.getMenu());
        // menu的item点击事件
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(Title_base1_ad.this, item.getTitle(), Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        // PopupMenu关闭事件
        popupMenu.setOnDismissListener(new PopupMenu.OnDismissListener() {
            @Override
            public void onDismiss(PopupMenu menu) {
                Toast.makeText(Title_base1_ad.this, "关闭PopupMenu", Toast.LENGTH_SHORT).show();
            }
        });

        popupMenu.show();
    }
}


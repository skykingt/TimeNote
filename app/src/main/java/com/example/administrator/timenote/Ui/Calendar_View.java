package com.example.administrator.timenote.Ui;


import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.administrator.timenote.R;

import java.util.ArrayList;
import java.util.List;

public class Calendar_View extends AppCompatActivity {

    private View view1, view2, view3, view4;//四个主界面Pageer
    private ViewPager viewPager;  //对应的viewPager

    private List<View> viewList;//view数组
    private static final String TAG = "LogDemo";
    private Button list1, taday_1, all_1, list2;//任务列表（page1）的按钮从右到左
    private MenuItem gMenuItem1, gMenuItem2;//list1的两个按钮
    private static boolean stase1 = true, stase2 = false;//列表显示状态
    private TextView list_name_1;//清单名称
    private List<task> taskList = new ArrayList<task>();//清单列表
    private Button task_1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_calendar_view);
        {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        viewPager = (ViewPager) findViewById(R.id.viewpager_1);
        LayoutInflater inflater = getLayoutInflater();
        view1 = inflater.inflate(R.layout.page1, null, false);
        view2 = inflater.inflate(R.layout.page2, null, false);
        view3 = inflater.inflate(R.layout.page3, null, false);

        viewList = new ArrayList<View>();// 将要分页显示的View装入数组中
        viewList.add(view1);
        viewList.add(view2);
        viewList.add(view3);
        PagerAdapter pagerAdapter = new MyPagerAdapter((ArrayList<View>) viewList);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(0);

        //page1按钮定义

        //顶层菜单栏
        list1 = view1.findViewById(R.id.list_spot_1);
        taday_1 = view1.findViewById(R.id.taday_1);
        all_1 = view1.findViewById(R.id.all_1);
        list2 = view1.findViewById(R.id.list_1);
        list_name_1 = view1.findViewById(R.id.list_name_1);
        task_1 = findViewById(R.id.task_1);
        //显示状态子菜单
        list1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(list1);
            }
        });

        //回到今天按钮
        taday_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //事务列表加载到今天
                list_name_1.setText("今天");
            }
        });

        //回到所有按钮
        all_1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                //事务列表加载到所有
                list_name_1.setText("所有");
            }
        });

        //打开清单菜单
        list2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Calendar_View.this, Sign_UpActivity.class);
                startActivity(intent);
                finish();
            }
        });

        initTask(); // 初始化任务数据
        taskAdapter adapter = new taskAdapter(Calendar_View.this, R.layout.arrary_button, taskList);
        ListView listView = (ListView) view1.findViewById(R.id.list_view);
        listView.setAdapter(adapter);


    }

    private void initTask() {
        for (int i = 0; i < 10; i++) {
            task task1 = new task("打人", new java.sql.Timestamp(System.currentTimeMillis()), "没事", "1", R.drawable.level3, R.drawable.line);
            taskList.add(task1);
        }
    }

    private void showPopupMenu(View view) {
        // View当前PopupMenu显示的相对View的位置
        PopupMenu popupMenu = new PopupMenu(this, view);
        // menu布局
        popupMenu.getMenuInflater().inflate(R.menu.showstyle, popupMenu.getMenu());
        gMenuItem1 = popupMenu.getMenu().findItem(R.id.complete_1);
        if (stase1 == false) {
            gMenuItem1.setTitle("显示已完成");
        }
        gMenuItem2 = popupMenu.getMenu().findItem(R.id.priorit_1);
        if (stase2 == false) {
            gMenuItem2.setTitle("按时间排序");
        }
        // menu的item点击事件
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.complete_1:
                        if (gMenuItem1.getTitle().equals("隐藏已完成")) {
                            stase1 = false;
                            //修改状态重新加载事务列表
                        } else {
                            stase1 = true;
                            //修改状态重新加载事务列表
                        }
                        break;
                    case R.id.priorit_1:
                        if (gMenuItem2.getTitle().equals("按优先级排序")) {
                            stase2 = false;
                            //修改状态重新加载事务列表
                        } else {
                            stase2 = true;
                            //修改状态重新加载事务列表
                        }
                        break;
                    default:
                }
                return true;
            }
        });
        // PopupMenu关闭事件
        popupMenu.setOnDismissListener(new PopupMenu.OnDismissListener() {
            public void onDismiss(PopupMenu menu) {
            }
        });

        popupMenu.show();
    }
}

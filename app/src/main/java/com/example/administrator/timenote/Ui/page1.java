package com.example.administrator.timenote.Ui;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.timenote.R;

public class page1  extends Fragment {

    private DrawerLayout drawerLayout;
    private MenuItem gMenuItem1, gMenuItem2;//list1的两个按钮
    private LayoutInflater inflater;
    private Button list1, taday_1, all_1, list2;//任务列表（page1）的按钮从右到左
    private TextView list_name_1;//清单名称
    private NavigationView navigationView;
    private Button new_button;//新建事务
    private static boolean stase1 = true, stase2 = false;//列表显示状态


    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.inflater = inflater;
        View view = inflater.inflate(R.layout.page1, container, false);
        drawerLayout = (DrawerLayout) view.findViewById(R.id.page1);
        navigationView = (NavigationView)view.findViewById(R.id.nav);
        View headerView = navigationView.getHeaderView(0);//获取头布局
        new_button=view.findViewById(R.id.new_task2);

        list2 = view.findViewById(R.id.list_1);
        list2.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.list_1://点击菜单，跳出侧滑菜单
                        if (drawerLayout.isDrawerOpen(navigationView)){
                            drawerLayout.closeDrawer(navigationView);
                        }else{
                            drawerLayout.openDrawer(navigationView);
                        }
                        break;
                }
            }
        });
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            public boolean onNavigationItemSelected(MenuItem item) {
                //item.setChecked(true);
                drawerLayout.closeDrawer(navigationView);
                return true;
            }
        });
        //page1按钮定义

        //顶层菜单栏
        list1 = view.findViewById(R.id.list_spot_1);
        taday_1 = view.findViewById(R.id.taday_1);
        all_1 = view.findViewById(R.id.all_1);

        list_name_1 = view.findViewById(R.id.list_name_1);
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


        MyCustomAdapter adapter = new MyCustomAdapter(this.inflater.getContext());
        ListView listView = (ListView) view.findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        for (int i = 0; i < 10; i++) {
            adapter.addSeparatorItem(new task(new java.sql.Timestamp(System.currentTimeMillis())));
            for (int j = 0; j < 10; j++) {
                task a = new task("打人"+j, new java.sql.Timestamp(System.currentTimeMillis()), "没事", "1", R.drawable.level3, R.drawable.line);
                adapter.addItem(a);
                task b = new task("打人", new java.sql.Timestamp(System.currentTimeMillis()), "没事", "1", R.drawable.level3, R.drawable.line);
                adapter.addItem(b);
            }
        }

        //新建事务逻辑
        new_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                New_task new_task= new New_task(inflater.getContext(),R.style.dialog);
                new_task.show();
            }
        });
        return view;
    }
    private void showPopupMenu(View view) {
        // View当前PopupMenu显示的相对View的位置
        PopupMenu popupMenu = new PopupMenu(this.inflater.getContext(), view);
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


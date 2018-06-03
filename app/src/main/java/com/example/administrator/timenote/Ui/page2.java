package com.example.administrator.timenote.Ui;

import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.example.administrator.timenote.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class page2 extends Fragment implements OnDateSelectedListener {
    private Button calendar_mode; // 日历模式
    private Calendar calendar;// 获取当前时间用
    private LayoutInflater inflater1;
    private ListView listView;// 事务列表
    private Button list1; // 菜单按钮
    private MenuItem gMenuItem1, gMenuItem2;// list2的两个按钮
    private Button new_button;// 新建事务
    private static boolean stase1 = true, stase2 = false;// 列表显示状态
    private Button taday_1;// 回到今天按钮
    private MaterialCalendarView widget;// 日历视图接口


    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.inflater1 = inflater;
        View view = inflater.inflate(R.layout.page2, container, false);
        widget=view.findViewById(R.id.calendarView);
        calendar_mode=view.findViewById(R.id.calendar_mode);
        new_button=view.findViewById(R.id.new_task1);

        //日历设置为今天
        calendar = Calendar.getInstance();
        widget.setSelectedDate(calendar.getTime());
        //初始化事务列表适配器
        MyCustomAdapter adapter = new MyCustomAdapter(this.inflater1.getContext());
        listView = (ListView) view.findViewById(R.id.list_view_2);
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

        //顶层菜单栏
        //日历视图模式转换
        calendar_mode.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
                @Override
                public void onClick(View view) {
                    if(calendar_mode.getText().equals("月视图")) {
                        widget.state().edit()
//                        .setFirstDayOfWeek(Calendar.WEDNESDAY)//日历开始于周三
//                        .setMinimumDate(CalendarDay.from(2016, 4, 3))//日历开始
//                        .setMaximumDate(CalendarDay.from(2016, 5, 12))//日历结束
                                .setCalendarDisplayMode(CalendarMode.WEEKS)
                                .commit();
                        calendar_mode.setText("周视图");
                    }
                    else{
                        widget.state().edit()
                                .setCalendarDisplayMode(CalendarMode.MONTHS)
                                .commit();
                        calendar_mode.setText("月视图");

                    }

                }
            });
       widget.setOnDateChangedListener(this);

       //回到今天按钮
        taday_1=view.findViewById(R.id.taday_2);
        taday_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                widget.setSelectedDate(calendar.getTime());
                if(calendar_mode.getText().equals("周视图")) {
                    widget.state().edit()
                            .setCalendarDisplayMode(CalendarMode.WEEKS)
                            .commit();
                    calendar_mode.setText("周视图");
                }
                else{
                    widget.state().edit()
                            .setCalendarDisplayMode(CalendarMode.MONTHS)
                            .commit();
                    calendar_mode.setText("月视图");
                }
                //更新事务列表
            }
        });

        //模式菜单按钮
        list1 = view.findViewById(R.id.list_spot_2);
        list1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(list1);
            }
        });

        //新建事务逻辑
        new_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                 New_task new_task= new New_task(inflater.getContext(),R.style.dialog);
                 new_task.show();
            }
        });

        return view;
    }

    @Override
    public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
        if(date == null) {
            //默认跳到今天
            Calendar calendar = Calendar.getInstance();
            widget.setSelectedDate(calendar.getTime());
            //更新任务列表
        }
        else {
            //更新任务列表
            Toast.makeText(inflater1.getContext(),"11",Toast.LENGTH_SHORT).show();
        }
    }
    private void showPopupMenu(View view) {
        // View当前PopupMenu显示的相对View的位置
        PopupMenu popupMenu = new PopupMenu(this.inflater1.getContext(), view);
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
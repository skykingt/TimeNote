package com.example.administrator.timenote.Ui;


import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.RadioGroup;
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
    private Button list2;
    private List<View> viewList;//view数组
    private static final String TAG = "LogDemo";
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Button menu;

    private List<task> taskList = new ArrayList<task>();//清单列表
    private Button task_1;
    private List<Fragment> list;
    private RadioGroup radioGroup;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_calendar_view);
        {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
//        initWindow();//影藏最顶层的系统菜单栏
        radioGroup =findViewById(R.id.rg_group);
        radioGroup.check(R.id.task_1);

        viewPager = (ViewPager) findViewById(R.id.viewpager_1);

        //viewlist实现viewPager
//        view1 = inflater.inflate(R.layout.page1, null, false);
//        view2 = inflater.inflate(R.layout.page2, null, false);
//        view3 = inflater.inflate(R.layout.page3, null, false);
//        view4 =inflater.inflate(R.layout.page4, null, false);
//        viewList = new ArrayList<View>();// 将要分页显示的View装入数组中
//        viewList.add(view1);
//        viewList.add(view2);
//        viewList.add(view3);
//        viewList.add(view4);
//        PagerAdapter pagerAdapter = new MyPagerAdapter((ArrayList<View>) viewList);
//        viewPager.setAdapter(pagerAdapter);
//        viewPager.setCurrentItem(0);
        list = new ArrayList<Fragment>();
        list.add(new page1());
        list.add(new page2());
        list.add(new page3());
        list.add(new page4());

        FragAdapter adapter = new FragAdapter(getSupportFragmentManager(), list);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(0);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.task_1:
                        //点击不同的radioGroup刷新到不同的viewpager
                        viewPager.setCurrentItem(0, false);
                        break;
                    case R.id.calendar_1:
                        viewPager.setCurrentItem(1,false);
                        break;
                    case R.id.yezi_1:
                        viewPager.setCurrentItem(2,false);
                        break;
                    case R.id.setup_1:
                        viewPager.setCurrentItem(3,false);
                        break;

                }
            }
        });

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                // arg0是当前选中的页面的Position
                switch(position)
                {
                    case 0:
                        radioGroup.check(R.id.task_1);
                        break;
                    case 1:
                        radioGroup.check(R.id.calendar_1);
                        break;
                    case 2:
                        radioGroup.check(R.id.yezi_1);
                        break;
                    case 3:
                        radioGroup.check(R.id.setup_1);
                        break;
                }
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                // arg0 :当前页面，及你点击滑动的页面；arg1:当前页面偏移的百分比；arg2:当前页面偏移的像素位置

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
                //arg0 ==1的时表示正在滑动，arg0==2的时表示滑动完毕了，arg0==0的时表示什么都没做。

            }
        });
    }
//    private void initWindow() {//初始化窗口属性，让状态栏和导航栏透明
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//
//        }
//    }
}




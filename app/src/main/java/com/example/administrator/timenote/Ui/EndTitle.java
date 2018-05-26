package com.example.administrator.timenote.Ui;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;

import com.example.administrator.timenote.R;

public class EndTitle extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.end_title_1);
//获取资源
        RadioGroup radioGroup =findViewById(R.id.rg_group);

        final ViewPager noScrollViewPager =findViewById(R.id.myViewPager);
//给ViwPage设置Adapter

        radioGroup =findViewById(R.id.rg_group);
        //默认radioGroup的那个是被选中的
        radioGroup.check(R.id.calendar_1);
        //还需在viewpager中显示该radioGroup对应的viewpager
        //设置RadioGroup的点击监听,用来设置点击切换界面
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.task_1:
                        //点击不同的radioGroup刷新到不同的viewpager
                        noScrollViewPager.setCurrentItem(0, false);
                        break;
                    case R.id.calendar_1:
                        noScrollViewPager.setCurrentItem(1,false);
                        break;
                    case R.id.yezi_1:
                        noScrollViewPager.setCurrentItem(2,false);
                        break;
                    case R.id.setup_1:
                        noScrollViewPager.setCurrentItem(3,false);
                        break;

                }
            }
        });
    }

}

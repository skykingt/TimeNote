package com.example.administrator.timenote.Ui;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.administrator.timenote.R;

public class Calendar_View extends AppCompatActivity {
    MenuItem  gMenuItem1=null,getgMenuItem2=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_view);
        {setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);}
    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.showstyle,menu);
        gMenuItem1=menu.findItem(R.id.complete_1);
        getgMenuItem2=menu.findItem(R.id.priorit_1);
        return true;
    }

    //定义菜单响应事件
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.complete_1:
                if(gMenuItem1.getTitle()=="隐藏已完成") {
                    gMenuItem1.setTitle("显示已完成");
                    //修改状态重新加载事务列表
                }
                else
                {
                    gMenuItem1.setTitle("隐藏已完成");
                    //修改状态重新加载事务列表
                }
                break;
            case R.id.priorit_1:
                if(getgMenuItem2.getTitle()=="按优先级排序")
                {
                    gMenuItem1.setTitle("按时间排序");
                    //修改状态重新加载事务列表
                }
                else{
                    gMenuItem1.setTitle("按优先级排序");
                    //修改状态重新加载事务列表
                }
                break;
            default:
        }
        return true;
    }

}

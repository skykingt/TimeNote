package com.example.administrator.timenote.Ui;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.timenote.R;

public class page3 extends Fragment {

    private Button tasklist;//事务选择列表
    private Button start;// 开始专注按钮
    private TextView tasktext1;// 提示文本大字
    private TextView tasktext2;// 提示文本小字
    private Task_select_list task_select_list;// 事务选择

    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.page3, container, false);
        tasklist=view.findViewById(R.id.task_list_yezi);
        start=view.findViewById(R.id.start_task);
        tasktext1=view.findViewById(R.id.tasktext1);
        tasktext2=view.findViewById(R.id.tasktext2);

        //事务选择列表按钮监听
        tasklist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                task_select_list=new Task_select_list(inflater.getContext(),R.style.dialog);
                task_select_list.show();
            }
        });

        //开始按钮监听
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return view;
    }
}
package com.example.administrator.timenote.Ui;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.timenote.R;

public class page4 extends Fragment {

    private ImageView imageButton1; // 头像按钮
    private Switch switch1;// 振动按钮
    private Spinner time_select_1; // 提醒时间下拉列表
    private Spinner time_ring_1;// 提醒闹铃下拉列表
    private Spinner time_even_1;// 时间
    private TextView yezi_time;// 叶子时长
    private TextView yezi_2;// 设置叶子时长
    private yezi_time_select yezi_time_select1;//设置叶子时长界面



    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.page4, container, false);

        // 对应控件
        imageButton1 = view.findViewById(R.id.imageButton);
        switch1 = view.findViewById(R.id.switch1);
        time_select_1 = view.findViewById(R.id.time_select_1);
        time_ring_1 = view.findViewById(R.id.time_ring_1);
        time_even_1 = view.findViewById(R.id.time_even_1);
        yezi_time = view.findViewById(R.id.yezi_time);
        yezi_2 = view.findViewById(R.id.yezi_2);

        //振动按钮
        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    Toast.makeText(getContext(),"开启振动",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getContext(),"关闭振动",Toast.LENGTH_SHORT).show();
                }
            }
        });

        //叶子时长选择
        yezi_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yezi_time_select1 =new yezi_time_select(getContext(),R.style.dialog);
                yezi_time_select1.show();
                yezi_time_select1.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        if(yezi_time_select1.getYezi_time()!=null)
                            yezi_time.setText(yezi_time_select1.getYezi_time()+"分钟");
                    }
                });
            }
        });

        return view;
    }
}
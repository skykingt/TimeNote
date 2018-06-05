package com.example.administrator.timenote.Ui;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnDismissListener;
import com.bigkoo.pickerview.listener.OnTimeSelectChangeListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.example.administrator.timenote.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class page4 extends Fragment {

    private ArrayAdapter adapter_time = null;// 时间下拉列表配置器
    private ArrayAdapter adapter_even = null;// 频率下拉列表配置器
    private ArrayAdapter adapter_ring =null;// 铃声下拉列表配置器
    private ImageView imageButton1; // 头像按钮
    private TimePickerView pvTime;// 时间自定义界面
    private Switch switch1;// 振动按钮
    private Spinner time_select_1; // 提醒时间下拉列表
    private Spinner time_ring_1;// 提醒闹铃下拉列表
    private Spinner time_even_1;// 时间
    private TextView time_text;// 自定义时间提醒框
    private TextView time_even_text;// 自定义频率
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
        time_text = view.findViewById(R.id.time_text_1);
        time_even_text = view.findViewById(R.id.retime_text_1);
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

        // 时间选择列表
        adapter_time=ArrayAdapter.createFromResource(getContext(),R.array.time_select,android.R.layout.simple_spinner_item);
        adapter_time.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        time_select_1.setAdapter(adapter_time);
        time_select_1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String cityName = (String) adapter_time.getItem(position);
                if(cityName.equals("自定义"))
                {
                    initCustomTimePicker2();
                    pvTime.show();
                    pvTime.setOnDismissListener(new OnDismissListener() {
                        @SuppressLint("WrongConstant")
                        @Override
                        public void onDismiss(Object o) {
                            if(time_text.getVisibility()==0)
                            {
                                // 更新设置
                            }
                        }
                    });
                }
                else
                {
                    time_text.setVisibility(View.INVISIBLE);
                    // 更新设置
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return view;
    }
    private void initCustomTimePicker2() {

        Calendar selectedDate = Calendar.getInstance();
        pvTime = new TimePickerBuilder(getContext(), new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                time_text.setText(getTime(date));
                time_text.setVisibility(View.VISIBLE);
            }
        })
                .setDate(selectedDate)
                .setLayoutRes(R.layout.pickerview_custom_time, new CustomListener() {
                    @Override
                    public void customLayout(View v) {
                        final TextView tvSubmit = (TextView) v.findViewById(R.id.tv_finish);
                        ImageView ivCancel = (ImageView) v.findViewById(R.id.iv_cancel);
                        tvSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvTime.returnData();
                                pvTime.dismiss();
                            }
                        });
                        ivCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvTime.dismiss();
                            }
                        });
                    }
                })
                .setTimeSelectChangeListener(new OnTimeSelectChangeListener() {
                    @Override
                    public void onTimeSelectChanged(Date date) {
                        Log.i("pvTime", "onTimeSelectChanged");
                    }
                })
                .setType(new boolean[]{false, false, false, true, true, false})
                .isDialog(true)
                .build();
        Dialog mDialog = pvTime.getDialog();
        if (mDialog != null) {

            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    Gravity.BOTTOM);

            params.leftMargin = 0;
            params.rightMargin = 0;
            pvTime.getDialogContainerLayout().setLayoutParams(params);

            Window dialogWindow = mDialog.getWindow();
            if (dialogWindow != null) {
                dialogWindow.setWindowAnimations(com.bigkoo.pickerview.R.style.picker_view_slide_anim);//修改动画样式
                dialogWindow.setGravity(Gravity.CENTER);//改成Bottom,底部显示
            }
        }
    }
    private String getTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return format.format(date);
    }
}
package com.example.administrator.timenote.Ui;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnTimeSelectChangeListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.example.administrator.timenote.R;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class New_task extends Dialog {
    /**
     * 上下文对象 *
     */
    private Button back; // 取消按钮
    private Context context;
    private Button dateselect; // 日期选择按钮
    private Button list; // 清单选择按钮
    private Button level; // 优先级选择按钮
    private Level_select level_select;//优先级选择界面
    private TimePickerView pvTime, pvCustomTime;//时间选择界面
    private Button sure; // 确定按钮
    public EditText task; // 任务输入框
    public String getdate;

    public New_task(Context context) {
        super(context);
        this.context = context;
    }

    public New_task(Context context, int theme) {
        super(context, theme);
        this.context = context;
    }


    @SuppressLint("ResourceAsColor")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 指定布局
        this.setContentView(R.layout.createa_transaction);
        Window dialogWindow = this.getWindow();

//            WindowManager m = context.getWindowManager();
//            Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
        WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值
//             p.height = (int) (d.getHeight() ); // 高度设置为屏幕
//            p.width = (int) (d.getWidth() ); // 宽度设置为屏幕
        dialogWindow.setAttributes(p);

        // 根据id在布局中找到控件对象
        back = findViewById(R.id.back_3);
        sure = findViewById(R.id.sure_task);
        dateselect = findViewById(R.id.task_date_1);
        list = findViewById(R.id.task_list_1);
        task = findViewById(R.id.new_task);
        level = findViewById(R.id.task_level_1);


        // 取消按钮
        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View ciew) {
                dismiss();
            }
        });

        //确认按钮事件
        sure.setEnabled(Boolean.FALSE);
        sure.setTextColor(R.color.color_ok_2);
        sure.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // 获取事务信息
                String scode = task.getText().toString();
                Date date;
                int listid;
                dismiss();
            }
        });

        // 日期选择按钮
        dateselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initCustomTimePicker();
                pvCustomTime.show();
            }
        });

        // 清单选择按钮
        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List_select list_select=new List_select(context,R.style.dialog);
                list_select.show();
            }
        });

        // 事务输入框
        task.addTextChangedListener(new TextWatcher() {
            // 如果没有输入则禁止确认
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(TextUtils.isEmpty(task.getText())|task.getText().equals("准备做什么？"))
                {
                    sure.setTextColor(R.color.color_ok_2);
                    sure.setEnabled(Boolean.FALSE);
                }
                else
                {
                    sure.setTextColor(R.color.color_ok_1);
                    sure.setEnabled(Boolean.TRUE);
                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            public void afterTextChanged(Editable s) {
            }

        });

        // 优先级选择
        level.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                level_select=new Level_select(context,R.style.dialog);
                level_select.show();
                // 监听弹框消失
                level_select.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        initlevel();
                    }
                });
            }
        });

    }

    //
    private  void initlevel(){
        switch(Level_select.getLevel())
        {
            case 0:
                level.setBackgroundResource(R.drawable.level0);
                break;
            case 1:
                level.setBackgroundResource(R.drawable.level1);
                break;
            case 2:
                level.setBackgroundResource(R.drawable.level2);
                break;
            case 3:
                level.setBackgroundResource(R.drawable.level3);
                break;
        }
    }
    private void initCustomTimePicker() {//Dialog 模式下，在底部弹出
        Calendar selectedDate = Calendar.getInstance();
        pvCustomTime = new TimePickerBuilder(context, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                getdate = getTime(date);
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
                                pvCustomTime.returnData();
                                pvCustomTime.dismiss();
                                initCustomTimePicker2();
                                pvTime.show();
                            }
                        });
                        ivCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomTime.dismiss();
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
                .setType(new boolean[]{true, true, true, false, false, false})
                .isDialog(true)
                .build();
        Dialog mDialog = pvCustomTime.getDialog();
        if (mDialog != null) {

            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    Gravity.BOTTOM);

            params.leftMargin = 0;
            params.rightMargin = 0;
            pvCustomTime.getDialogContainerLayout().setLayoutParams(params);

            Window dialogWindow = mDialog.getWindow();
            if (dialogWindow != null) {
                dialogWindow.setWindowAnimations(com.bigkoo.pickerview.R.style.picker_view_slide_anim);//修改动画样式
                dialogWindow.setGravity(Gravity.CENTER);//改成Bottom,底部显示
            }
        }
    }
    private void initCustomTimePicker2() {

        Calendar selectedDate = Calendar.getInstance();
        pvTime = new TimePickerBuilder(context, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                getdate = getTime(date);
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

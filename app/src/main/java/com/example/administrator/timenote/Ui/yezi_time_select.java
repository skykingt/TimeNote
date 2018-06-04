package com.example.administrator.timenote.Ui;

import android.app.Dialog;
import android.content.Context;
import android.net.sip.SipSession;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyboardShortcutGroup;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.timenote.R;

import java.util.List;

public class yezi_time_select extends Dialog  {
    /**
     * 上下文对象 *
     */

    private Button back;// 取消按钮
    private Context context;
    private SeekBar seek_yezi;// 进度条
    private Button sure;// 确认按钮
    private TextView yezi_Time_Text;// 叶子时长文本
    private String yezi_time;// 记录叶子时间

    public String getYezi_time() {
        return yezi_time;
    }

    public void setYezi_time(String yezi_time) {
        this.yezi_time = yezi_time;
    }





    public yezi_time_select (Context context) {
        super(context);
        this.context = context;
    }

    public yezi_time_select (Context context, int theme) {
        super(context, theme);
        this.context = context;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 指定布局
        this.setContentView(R.layout.yezi_select);
        Window dialogWindow = this.getWindow();

//            WindowManager m = context.getWindowManager();
//            Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
        WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值
//             p.height = (int) (d.getHeight() ); // 高度设置为屏幕
//            p.width = (int) (d.getWidth() ); // 宽度设置为屏幕
        dialogWindow.setAttributes(p);

        // 根据id在布局中找到控件对象
        back = findViewById(R.id.back_7);
        seek_yezi = findViewById(R.id.seek_yezi);
        sure = findViewById(R.id.sure_yezi);
        yezi_Time_Text = findViewById(R.id.yezi_time_text);

        // 为按钮绑定点击事件监听器
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        // 确认按钮
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yezi_time = yezi_Time_Text.getText().toString();
                dismiss();
            }
        });

        // 进度条监听
       seek_yezi.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            /**
             * 拖动条停止拖动的时候调用
             */
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
            /**
             * 拖动条开始拖动的时候调用
             */
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            /**
             * 拖动条进度改变的时候调用
             */
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {

                yezi_Time_Text.setText(String.valueOf(progress));
            }
        });
        this.setCancelable(true);
    }


}

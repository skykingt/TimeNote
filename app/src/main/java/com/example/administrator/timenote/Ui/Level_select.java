package com.example.administrator.timenote.Ui;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.administrator.timenote.R;

public class Level_select extends Dialog {
    /**
     * 上下文对象 *
     */
    private Button back;
    private Context context;
    private Button level0;
    private Button level1;
    private Button level2;
    private Button level3;

    public static int getLevel() {
        return level;
    }

    public static void setLevel(int level) {
        Level_select.level = level;
    }

    private static int level=0;

    public Level_select (Context context) {
        super(context);
        this.context = context;
    }

    public Level_select (Context context, int theme) {
        super(context, theme);
        this.context = context;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 指定布局
        this.setContentView(R.layout.level_select);
        Window dialogWindow = this.getWindow();

//            WindowManager m = context.getWindowManager();
//            Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
        WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值
//             p.height = (int) (d.getHeight() ); // 高度设置为屏幕
//            p.width = (int) (d.getWidth() ); // 宽度设置为屏幕
        dialogWindow.setAttributes(p);

        // 根据id在布局中找到控件对象
        back =findViewById(R.id.back_5);
        level0 =findViewById(R.id.level0_button);
        level1 =findViewById(R.id.level1_button);
        level2 =findViewById(R.id.level2_button);
        level3 =findViewById(R.id.level3_button);


        // 为按钮绑定点击事件监听器
        back.setOnClickListener(new View.OnClickListener(){
            public void onClick(View ciew)
            {
                dismiss();
            }
        });

        level0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                level=0;
                dismiss();
            }
        });

        level1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                level=1;
                dismiss();
            }
        });

        level2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                level=2;
                dismiss();
            }
        });

        level3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                level=3;
                dismiss();
            }
        });

        this.setCancelable(true);
    }

}

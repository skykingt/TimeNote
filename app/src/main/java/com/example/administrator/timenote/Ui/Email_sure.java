package com.example.administrator.timenote.Ui;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.administrator.timenote.R;


public class Email_sure extends Dialog {
        /**
         * 上下文对象 *
         */
        Activity context;

        int recLen=60;
        private Button back;
        private Button resend;
        private Button sure;

        public EditText Verification_code_1;

        private TextView Verification_code_error_1;



        public Email_sure(Activity context) {
            super(context);
            this.context = context;
        }

        public Email_sure(Activity context, int theme) {
            super(context, theme);
            this.context = context;
        }


        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            // 指定布局
            this.setContentView(R.layout.email_load_1);

            Verification_code_1 = (EditText) findViewById(R.id.Verification_code_1);
            Verification_code_error_1=findViewById(R.id.Verification_code_error_1);

        /*
         * 获取圣诞框的窗口对象及参数对象以修改对话框的布局设置, 可以直接调用getWindow(),表示获得这个Activity的Window
         * 对象,这样这可以以同样的方式改变这个Activity的属性.
         */
            Window dialogWindow = this.getWindow();

            WindowManager m = context.getWindowManager();
            Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
            WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值
//             p.height = (int) (d.getHeight() ); // 高度设置为屏幕
//            p.width = (int) (d.getWidth() ); // 宽度设置为屏幕
            dialogWindow.setAttributes(p);

            // 根据id在布局中找到控件对象
            back =findViewById(R.id.back_2);
            resend=findViewById(R.id.resent_Verification_code_2);
            sure =findViewById(R.id.sure_Verification_code_1);

            cdt.start();

            // 为按钮绑定点击事件监听器
            back.setOnClickListener(new View.OnClickListener(){
                public void onClick(View ciew)
                {
                    dismiss();
                }
            });

            sure.setOnClickListener(new View.OnClickListener(){
                public void onClick(View view)
                {
                    String scode = Verification_code_1.getText().toString();
                    if(scode.equals("12263")==true)//验证码验证
                    {
                        //注册
                        dismiss();
                    }
                    else
                    {
                        Verification_code_error_1.setVisibility(view.VISIBLE);
                    }
                }
            });

            resend.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //重新发送验证码
                    resend.setVisibility(View.INVISIBLE);
                    sure.setVisibility(View.VISIBLE);
                    recLen=60;
                    cdt.start();
                }
            });

            Verification_code_1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean b) {
                    if (b)
                    {
                        Verification_code_error_1.setVisibility(View.INVISIBLE);
                    }
                }
            });


            this.setCancelable(true);
        }

    CountDownTimer cdt = new CountDownTimer(3000, 1000) {

        public void onTick(long millisUntilFinished) {
            recLen--;
            sure.setText("确认"+recLen+"s");
        }
        @Override
        public void onFinish() {
            sure.setVisibility(View.INVISIBLE);
            resend.setVisibility(View.VISIBLE);
        }
    };

}

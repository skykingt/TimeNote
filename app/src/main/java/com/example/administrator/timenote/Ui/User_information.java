package com.example.administrator.timenote.Ui;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.media.Image;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.administrator.timenote.R;

public class User_information extends AppCompatActivity {

    private Button back9;// 返回按钮
    private EditText email_update;// 邮箱
    private Button loadout;// 登出
    private EditText name_update;// 昵称
    private ImageView uesr_image;// 用户头像


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_user_information);
        {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }

        //控件绑定
        back9 = findViewById(R.id.back_9);
        email_update = findViewById(R.id.email_update);
        loadout = findViewById(R.id.load_out);
        name_update = findViewById(R.id.name_update);
        uesr_image = findViewById(R.id.user_image);

        //返回按钮
        back9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //邮箱修改框
        email_update.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String semail = email_update.getText().toString();
                if(hasFocus)
                {

                }
                else
                {
                    if(semail.indexOf("@")>0)
                    {
                        //发送验证码
                        final Email_sure email_sure= new Email_sure(User_information.this,R.style.dialog);
                        email_sure.show();
                        email_sure.setOnDismissListener(new DialogInterface.OnDismissListener() {
                            @Override
                            public void onDismiss(DialogInterface dialog) {
                                if(email_sure.getIssue())
                                {
                                    //修改用户信息
                                }
                            }
                        });
                    }
                }
            }
        });
    }

}

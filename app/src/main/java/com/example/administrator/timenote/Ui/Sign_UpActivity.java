package com.example.administrator.timenote.Ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.administrator.timenote.R;


public class Sign_UpActivity extends AppCompatActivity {
    private EditText email,name,pwd,pwd2;
    private TextView email_error_1,pwd_error_1,pwd_error_2;
    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.sign_up_1);

        {setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);}
        Button button1= findViewById(R.id.sign_up_2);//确认注册按钮
        Button button2= findViewById(R.id.sign_in_1);//已有账号按钮
        Button back1=findViewById(R.id.back_1);
        email=findViewById(R.id.e_mail_1);
        name=findViewById(R.id.name_1);
        pwd=findViewById(R.id.pwd_1);
        pwd2=findViewById(R.id.pwd_2);
        email_error_1=findViewById(R.id.emil_error_1);
        pwd_error_1=findViewById(R.id.pwd_error_1);
        pwd_error_2=findViewById(R.id.pwd_error_2);

        // 密码隐藏
        pwd.setTransformationMethod(PasswordTransformationMethod
                .getInstance());
        pwd2.setTransformationMethod(PasswordTransformationMethod
                .getInstance());

        back1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                finish();
                // 转到登录界面
            }
        });

        // 确认注册按钮
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String semail=email.getText().toString();
                String sname=name.getText().toString();
                String spwd=pwd.getText().toString();
                String spwd2=pwd2.getText().toString();

                if(semail.indexOf("@")>0&&spwd.length()>=8&&spwd.equals(spwd2)==true){
                    Email_sure email_sure= new Email_sure(Sign_UpActivity.this,R.style.dialog);
                    email_sure.show();
                }
               else
                {
                    email.requestFocus();
                    pwd.requestFocus();
                    pwd2.requestFocus();
                    name.requestFocus();
                }
                // 注册账号，返回注册结果
//                Intent intent =new Intent(Sign_UpActivity.this,MainActivity.class);
//                startActivity(intent);
//                finish();
            }
        });

        // 已有账号按钮
        button2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Intent intent =new Intent(Sign_UpActivity.this,MainActivity.class);
                startActivity(intent);
                // 转到登录界面
            }
        });


        // 邮箱焦点获得与失去
        email.setOnFocusChangeListener(new android.view.View.
                OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    // 此处为得到焦点时的处理内容
                    email_error_1.setVisibility(View.INVISIBLE);
                }
                else {
                    // 此处为失去焦点时的处理内容
                    String semail=email.getText().toString();
                    if(semail.indexOf("@")<0)
                    {
                        email_error_1.setVisibility(View.VISIBLE);
                    }
                }
            }
        });

        // 密码焦点获得与失去
        pwd.setOnFocusChangeListener(new android.view.View.
                OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    // 此处为得到焦点时的处理内容
                    pwd_error_1.setVisibility(View.INVISIBLE);
                }
                else {
                    // 此处为失去焦点时的处理内容
                    String spwd=pwd.getText().toString();
                    if(spwd.length()<8|spwd.length()>20)
                    {
                        pwd_error_1.setVisibility(View.VISIBLE);
                    }
                }
            }
        });

        pwd2.setOnFocusChangeListener(new android.view.View.
                OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    // 此处为得到焦点时的处理内容
                    pwd_error_2.setVisibility(View.INVISIBLE);
                }
                else {
                    // 此处为失去焦点时的处理内容
                    String spwd=pwd.getText().toString();
                    String spwd2=pwd2.getText().toString();
                    if(spwd.equals(spwd2)!=true)
                    {
                        pwd_error_2.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
    }
}

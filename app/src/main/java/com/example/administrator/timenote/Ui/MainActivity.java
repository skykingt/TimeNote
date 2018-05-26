package com.example.administrator.timenote.Ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.timenote.R;


public class MainActivity extends AppCompatActivity {

    private EditText pwd,uesrid;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading_layout);
        final Button button1= findViewById(R.id.loading_1);
        Button button2= findViewById(R.id.sign_up_1);
        Button button3= findViewById(R.id.pwd_change_1);
        pwd =(EditText) findViewById(R.id.pwd_1);
        uesrid =(EditText) findViewById(R.id.userid_1);

        //强制竖屏
        {setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);}

        //隐藏密码
        pwd.setTransformationMethod(PasswordTransformationMethod
                .getInstance());
        //密码文本框监听回车
        pwd.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                Toast.makeText(MainActivity.this, "*", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        //用户名文本框监听回车
        uesrid.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                Toast.makeText(MainActivity.this, String.valueOf(actionId), Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        //登录按钮
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            //获得文本框内容
//            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, uesrid.getText().toString(), Toast.LENGTH_SHORT).show();
//            }

            public void onClick(View v) {
                String suesrid=uesrid.getText().toString();
                String spwd=pwd.getText().toString();

                //判断账号密码是否匹配，是则进入主界面，否则弹框提示
                if((suesrid.equals("972357450@qq.com")==true)&& (spwd.equals("12263")==true))
                {
                    Intent intent =new Intent(MainActivity.this,Calendar_View.class);
                    startActivity(intent);
                }
                else
                {
                    AlertDialog.Builder alterDialog = new AlertDialog.Builder(MainActivity.this);
                    alterDialog.setTitle("糟糕！");
                    alterDialog.setMessage("账号或密码错误");
                    alterDialog.setCancelable(false);
                    alterDialog.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(MainActivity.this, "确认", Toast.LENGTH_SHORT).show();
                            uesrid.requestFocus();
                        }
                    });
                    alterDialog.show();
                }
            }
        });

        //注册按钮
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(MainActivity.this,Sign_UpActivity.class);
                startActivity(intent);
                //转到注册界面

            }
        });

        //忘记密码按钮
        button3.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Intent intent =new Intent(MainActivity.this,Forgot_pwd.class);
                startActivity(intent);
                //转到忘记密码界面

            }
        });
    }
}

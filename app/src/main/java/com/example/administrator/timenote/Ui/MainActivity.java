package com.example.administrator.timenote.Ui;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.StrictMode;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.timenote.Manager.UserManager.UserLogin;
import com.example.administrator.timenote.Model.BeanUserInformation;
import com.example.administrator.timenote.R;
import com.example.administrator.timenote.Threadcontroal.MyHandler;

import java.io.UnsupportedEncodingException;

import static com.example.administrator.timenote.Model.BeanUserInformation.currentLoginUser;
import static com.example.administrator.timenote.Model.BeanUserInformation.tryLoginUser;


public class MainActivity extends AppCompatActivity {

    private EditText pwd, uesrid;
    private String suesrid;
    public static Handler myHandler;
    //Message msg = new Message();
    //Message msg = myHandler.obtainMessage();
    //Log.("msg",msg);

    @Override
    //访问网络同时加入这个
    @SuppressLint("NewApi")
    protected void onCreate(Bundle savedInstanceState) {
        //允许使用webervice同时启用网络访问
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading_layout);
        final Button button1 = findViewById(R.id.loading_1);
        Button button2 = findViewById(R.id.sign_up_1);
        Button button3 = findViewById(R.id.pwd_change_1);
        pwd = (EditText) findViewById(R.id.pwd_1);
        uesrid = (EditText) findViewById(R.id.userid_1);


        //强制竖屏
        {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }

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
            @SuppressLint("HandlerLeak")
            @Override
            //获得文本框内容
//            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, uesrid.getText().toString(), Toast.LENGTH_SHORT).show();
//            }

            public void onClick(View v) {
                suesrid=uesrid.getText().toString();
                String spwd = pwd.getText().toString();

//                myHandler = new Handler(){
//                    @Override
//                    public void handleMessage(Message msg) {
//                        super.handleMessage(msg);
//                        switch(msg.what){
//                            case 1:tryLoginUser = (BeanUserInformation) msg.obj;break;
//                            case 0:Toast.makeText(MainActivity.this,"没有该用户",Toast.LENGTH_SHORT).show();break;
//                            case -1:Toast.makeText(MainActivity.this,"服务器错误",Toast.LENGTH_SHORT).show();break;
//                        }
//
////                  myHandler=new MainActivity.MyHandler(Looper.myLooper());
//                    }
//                };
//               创建子线程并引用webservice层的LoadUser方法
                 Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        UserLogin userLogin = new UserLogin();
                        try {
                            Thread.sleep(300);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        userLogin.getRemoteInfo(suesrid);
                        //myHandler.obtainMessage(1,u_rst).sendToTarget();
                        //msg.obj = u_rst;
                        //msg.what = 1;
                        //myHandler.sendMessage(msg);
                        System.out.println(1);
                    }
                });
                t.start();
                try {
                    t.join(30000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                UserLogin userLogin = new UserLogin();
//                       userLogin.getRemoteInfo(suesrid);
                //判断账号密码是否匹配，是则进入主界面，否则弹框提示
                if ((suesrid.equals(tryLoginUser.getUseremail())) && (spwd.equals(tryLoginUser.getUserpassword()))) {
                    Intent intent = new Intent(MainActivity.this, Calendar_View.class);
                    startActivity(intent);
                    finish();
                } else {
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
                currentLoginUser = tryLoginUser;
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
        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Forgot_pwd.class);
                startActivity(intent);
                //转到忘记密码界面

            }
        });
    }
}




//      class MyThread implements Runnable {
//          public void run() {
//              //从消息池中取出一个message
//              Message msg = myHandler.obtainMessage();
//              //Bundle是message中的数据
//              Bundle b = new Bundle();
//              b.putString("color", "我的");
//              msg.setData(b);
//              //传递数据
//              myHandler.sendMessage(msg); // 向Handler发送消息,更新UI
//          }
//      }


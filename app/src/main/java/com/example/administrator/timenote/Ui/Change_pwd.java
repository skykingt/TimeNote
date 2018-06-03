package com.example.administrator.timenote.Ui;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.timenote.R;

public class Change_pwd extends AppCompatActivity {
    private EditText newpwd1,newpwd2;
    private TextView pwd1_error,pwd2_error;
    private Button sure;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.change_pwd_1);
        TextView sign_up_title_1=findViewById(R.id.sign_up_title_1);
        sign_up_title_1.setText("修改密码");
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Button back1=findViewById(R.id.back_1);

        back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        newpwd1=findViewById(R.id.new_pwd_1);
        newpwd2=findViewById(R.id.new_pwd_2);

        pwd1_error=findViewById(R.id.pwd_1_error);
        pwd2_error=findViewById(R.id.pwd_2_error);

        sure=findViewById(R.id.sure_pwd_1);

        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String spwd1=newpwd1.getText().toString();
                String spwd2=newpwd2.getText().toString();
                if(spwd1.length()>=8&&(spwd1.equals(spwd2)))
                {
                    //修改密码
                    Toast.makeText(Change_pwd.this,"密码修改成功",Toast.LENGTH_SHORT).show();
                    finish();
                }
                else if(spwd1.length()<8)
                    {
                        pwd1_error.setVisibility(View.VISIBLE);
                    }
                    else if(spwd1.equals(spwd2)!=true)
                    {
                        pwd2_error.setVisibility(View.VISIBLE);
                    }
                    else
                    {
                        pwd1_error.setVisibility(View.VISIBLE);
                        pwd2_error.setVisibility(View.VISIBLE);
                    }
            }
        });

        newpwd1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b)
                {
                    pwd1_error.setVisibility(View.INVISIBLE);
                }
                else
                {
                    String spwd1=newpwd1.getText().toString();
                    if(spwd1.length()<8|spwd1.length()>20)
                    {
                        pwd1_error.setVisibility(View.VISIBLE);
                    }
                }
            }
        });

        newpwd2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b)
                {
                    pwd2_error.setVisibility(View.INVISIBLE);
                }
                else
                {
                    String spwd2=newpwd2.getText().toString();
                    String spwd1=newpwd1.getText().toString();
                    if(spwd2.equals(spwd1)!=true)
                    {
                        pwd1_error.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
    }
}

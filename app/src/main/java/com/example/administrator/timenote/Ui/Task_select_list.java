package com.example.administrator.timenote.Ui;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.administrator.timenote.R;

import java.util.ArrayList;

public class Task_select_list  extends Dialog implements AdapterView.OnItemClickListener, ListAdapter.InnerItemOnclickListener {
    /**
     * 上下文对象 *
     */
    private Button back;// 取消按钮
    private Context context;
    private task_select_Adapter listAdapter;// 事务列表适配器
    private ListView listView;// 事务列表
    private ArrayList<String> listname;// 事务名称列表
    private Button list_select_1;// 清单选择按钮
    private List_select list_select;// 清单选择

    public Task_select_list(Context context) {
        super(context);
        this.context = context;
    }

    public Task_select_list(Context context, int theme) {
        super(context, theme);
        this.context = context;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 指定布局
        this.setContentView(R.layout.task_select_list_1);


        /*
         * 获取验证码的窗口对象及参数对象以修改对话框的布局设置, 可以直接调用getWindow(),表示获得这个Activity的Window
         * 对象,这样这可以以同样的方式改变这个Activity的属性.
         */
        Window dialogWindow = this.getWindow();

//            WindowManager m = context.getWindowManager();
//            Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
        WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值
//             p.height = (int) (d.getHeight() ); // 高度设置为屏幕
//            p.width = (int) (d.getWidth() ); // 宽度设置为屏幕
        dialogWindow.setAttributes(p);

        // 根据id在布局中找到控件对象
        // 取消按钮
        back=findViewById(R.id.back_6);

        // 事务列表添加
        listname=new ArrayList<String>();
        for(int i=0;i<10;i++)
        {
            listname.add("事务名称");
        }
        listView =findViewById(R.id.listView4);
        listAdapter=new task_select_Adapter(context,R.layout.task_secect_button,listname);
        listAdapter.setOnInnerItemOnClickListener(this);
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(this);
        // 任务清单选择按钮
        list_select_1=findViewById(R.id.list_select_2);

        // 为按钮绑定点击事件监听器
        back.setOnClickListener(new View.OnClickListener(){
            public void onClick(View ciew)
            {
                dismiss();
            }
        });

//        listView.setOnItemClickListener(this);

        list_select_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list_select=new List_select(context,R.style.dialog);
                list_select.show();
                list_select.setOnDismissListener(new OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        list_select_1.setText(List_select.getNlistname());
                    }
                });
            }
        });

        this.setCancelable(true);
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(context,"列表 "+adapterView+" 被点击了",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void itemClick(View v) {
        int position;
        position = (Integer) v.getTag();
        switch (v.getId()) {
            case R.id.task_select_button_1:
                Toast.makeText(context,"点击"+listname.get(position),Toast.LENGTH_SHORT).show();
                dismiss();
                break;
            default:
                break;
        }
    }
}

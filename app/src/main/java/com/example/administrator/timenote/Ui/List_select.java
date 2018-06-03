package com.example.administrator.timenote.Ui;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import com.example.administrator.timenote.R;
import android.widget.AdapterView.OnItemClickListener;
import java.util.ArrayList;

public class List_select extends Dialog implements OnItemClickListener, ListAdapter.InnerItemOnclickListener {
    /**
     * 上下文对象 *
     */
    private Button back;// 取消按钮
    private Context context;
    private ListAdapter listAdapter;// 清单列表适配器
    private ListView listView;// 清单列表
    private ArrayList<String> listname;// 清单名称列表

    public static String getNlistname() {
        return nlistname;
    }

    private static String nlistname="收集箱";// 当前选择的清单名称

    public List_select(Context context) {
        super(context);
        this.context = context;
    }

    public List_select(Context context, int theme) {
        super(context, theme);
        this.context = context;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 指定布局
        this.setContentView(R.layout.list_select_1);


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
        back=findViewById(R.id.back_4);
        listname=new ArrayList<String>();
        for(int i=0;i<10;i++)
        {
            listname.add("清单");
        }
        listname.add("添加清单");
        listView =findViewById(R.id.listView3);
        listAdapter=new ListAdapter(context,R.layout.list_button,listname);
        listAdapter.setOnInnerItemOnClickListener(this);
        listView.setAdapter(listAdapter);

        // 为按钮绑定点击事件监听器
        back.setOnClickListener(new View.OnClickListener(){
            public void onClick(View ciew)
            {
                dismiss();
            }
        });

        listView.setOnItemClickListener(this);

        this.setCancelable(true);
    }


    // 清单选择后点击的事件回调
    public void itemClick(View v) {
        int position;
        position = (Integer) v.getTag();
        switch (v.getId()) {
            case R.id.list_select_button:
                nlistname=listname.get(position);
                dismiss();
                Toast.makeText(context,"an",Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }

    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(context,"列表 "+position+" 被点击了",Toast.LENGTH_SHORT).show();
    }
}
//    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        //通过view获取其内部的组件，进而进行操作
//        String text = (String) ((TextView)view.findViewById(R.id.text)).getText();
//        //大多数情况下，position和id相同，并且都从0开始
//        String showText = "点击第" + position + "项，文本内容为：" + text + "，ID为：" + id;
//        Toast.makeText(context, showText, Toast.LENGTH_LONG).show();
//    }




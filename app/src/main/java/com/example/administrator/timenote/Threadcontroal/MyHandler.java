package com.example.administrator.timenote.Threadcontroal;

import android.os.Handler;
import android.os.Looper;

/**
 * Created by Sprou on 2018/5/31.
 */

public class MyHandler extends Handler  {
        public MyHandler() {
        }

        public MyHandler(Looper L) {
            super(L);
        }

        // 必须重写这个方法，用于处理message
//            @Override
//          public void handleMessage(Message msg) {
//                switch (msg.what) {
//                    case 0:
//                        show.setText("" + msg.arg1);
//                      break;
//              }
//              super.handleMessage(msg);
//          }
}

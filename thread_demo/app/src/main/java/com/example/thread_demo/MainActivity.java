package com.example.thread_demo;

import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "[THREAD_DEMO]";
    TextView tv = null;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    if (tv.getText().equals("Hello World!")) {
                        tv.setText("你好，世界!");
                        tv.setTextColor(Color.RED);
                    } else {
                        tv.setText("Hello World!");
                        tv.setTextColor(Color.LTGRAY);
                    }
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        tv = (TextView) findViewById(R.id.tv);
    }

    public void onClick_translate(View v) {

        // 匿名内部类的方式开启子线程
        // new Runnable() 自定义一个匿名的类实现了Runnable接口
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "enter thread context");

                Message msg = new Message();
                msg.what = 1;

                handler.sendMessage(msg);

            }
        }).start();
    }
}

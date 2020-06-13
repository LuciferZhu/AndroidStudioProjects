package com.example.image_exporter;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "[image_exporter]";
    ImageView iv = null;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    iv.setImageResource(R.drawable.panda1);
                    break;
                case 1:
                    iv.setImageResource(R.drawable.panda2);
                    break;
                case 2:
                    iv.setImageResource(R.drawable.panda3);
                    break;
                case 3:
                    iv.setImageResource(R.drawable.panda4);
                    break;
                case 4:
                    iv.setImageResource(R.drawable.panda5);
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

        new Thread(new Runnable() {
            @Override
            public void run() {
                int a = 0;

                while (true) {
                    Message msg = new Message();

                    msg.what = a;
                    handler.sendMessage(msg);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        Log.e(TAG, Log.getStackTraceString(e));
                    }

                    a = (a + 1) % 5;
                }
            }
        }).start();
    }

    private void initView() {
        iv = (ImageView) findViewById(R.id.iv_dynamic);
    }
}

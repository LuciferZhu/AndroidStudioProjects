package com.example.buttonlistener;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private Button bt_one = null;
    private Button bt_2 = null;
    public static ImageView iv_one = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        MyListener myListener = new MyListener();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        bt_one.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                iv_one.setBackgroundResource(R.drawable.weixin);
            }
        });

        bt_2.setOnClickListener(myListener);
    }

    // related with android:onClick="click"
    public void click(View v) {
        iv_one.setBackgroundResource(R.drawable.ugly);
    }

    public void initView() {
        bt_one = (Button) findViewById(R.id.Button_one);
        bt_2 = (Button) findViewById(R.id.Button_two);
        iv_one = (ImageView) findViewById(R.id.image_one);
    }
}

class MyListener implements View.OnClickListener {
    @Override
    public void onClick(View v) {
        MainActivity.iv_one.setBackgroundResource(R.drawable.fish);
    }
}

package com.example.ledcontrolplus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.lowlevel.LedNativePlus;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "LedControlPlus";
    private Button btn_led = null;
    private TextView tv_show = null;
    LedNativePlus ledNative = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        ledNative = new LedNativePlus();
        ledNative.openDev();
        demoJNIPlus();
    }

    public void initView() {
        btn_led = (Button) findViewById(R.id.btn_led);
        tv_show = (TextView) findViewById(R.id.tv_print);
    }

    public void onClickLed(View v) {

        if (btn_led.getText().equals("开灯") ) {
            Log.d(TAG, "turn on led");
            btn_led.setText("关灯");
            ledNative.devOn();

        } else {
            Log.d(TAG, "turn off led");
            btn_led.setText("开灯");
            ledNative.devOff();

        }
    }

    public void demoJNIPlus() {
        Log.d(TAG, "demoJNIPlus");
        int res = ledNative.addNum(45, 25);
        String str = ledNative.getStringFromJNI();
        Student stu = ledNative.getStudentFromJNI();
        String line = "a+b = " + res + "\n" +
                "JNIstring : " + str + "\n" +
                "name = " + stu.getName() + "\n" +
                "age = " + stu.getAge() + "\n" +
                "height = " + stu.getHeight();
        tv_show.setText(line);

        ledNative.setStringToJNI("Welcome from java");
        Student stu2 = new Student();
        stu2.setAll(100,1.6, "Leifeng");
        ledNative.setStudentToJNI(stu2);
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy");
        super.onDestroy();
        ledNative.closeDev();
    }
}

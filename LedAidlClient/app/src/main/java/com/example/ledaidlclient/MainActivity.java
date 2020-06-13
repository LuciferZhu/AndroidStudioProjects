package com.example.ledaidlclient;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.led.ILedService;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "LedAidlClient";
    private Button btn_bind = null;
    private Button btn_ledon = null;
    private Button btn_ledoff = null;
    private Button btn_unbind = null;
    private ILedService ledService = null;

    ServiceConnection svcConnt = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "onServiceConnected++++++");
            ledService = ILedService.Stub.asInterface(service);
            Toast.makeText(MainActivity.this, "LedService连接成功", 1500).show();
        }

        /*
         * note:调用unbindService()断开与Service的连接时，onServiceDisconnected()并不会被调用
         */
        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG, "onServiceDisconnected-----");
            ledService = null;
            Toast.makeText(MainActivity.this, "LedService异常终止，断开连接", 1500).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        btn_bind = (Button) findViewById(R.id.button_bind);
        btn_ledon = (Button) findViewById(R.id.button_ledon);
        btn_ledoff = (Button) findViewById(R.id.button_ledoff);
        btn_unbind = (Button) findViewById(R.id.button_unbind);
    }

    public void onClickLedAidl(View v) {
        Button btn = (Button) v;
        switch (btn.getId()) {
            case R.id.button_bind:
                if (ledService != null) {
                    Toast.makeText(MainActivity.this, "service bind already", 1500).show();
                } else {
                    Log.d(TAG, "onClickLedAidl: bind service");
                    Intent intent = new Intent();
                    intent.setAction("com.example.led.ILedService");
                    intent.setPackage("com.example.led");  // 隐式intent必须指定service的包名
                    bindService(intent, svcConnt, Service.BIND_AUTO_CREATE);
                }
                break;
            case R.id.button_ledon:
                Log.d(TAG, "onClickLedAidl: turn on led");
                if (ledService != null) {
                    try {
                        ledService.control(1);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                else
                    Toast.makeText(MainActivity.this, "尚未绑定服务", 1500).show();
                break;
            case R.id.button_ledoff:
                Log.d(TAG, "onClickLedAidl: turn off led");
                if (ledService != null) {
                    try {
                        ledService.control(0);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                else
                    Toast.makeText(MainActivity.this, "尚未绑定服务", 1500).show();
                break;
            case R.id.button_unbind:
                Log.d(TAG, "onClickLedAidl: unbind");
                if (ledService != null) {
                    unbindService(svcConnt);
                    ledService = null;
                    Toast.makeText(MainActivity.this, "断开LedService服务", 1500).show();
                }
                else
                    Toast.makeText(MainActivity.this, "尚未绑定服务", 1500).show();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
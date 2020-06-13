package com.example.led;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.lowlevel.LedNative;

public class LedService extends Service {
    private final String TAG = "LedService";
    private LedNative led = new LedNative();

    private final ILedService.Stub binder = new ILedService.Stub() {
        @Override
        public int control(int on) throws RemoteException {
            int ret = 0;
            Log.d(TAG, "control: on = " + on);
            if (on != 0)
                ret = led.devOn();
            else
                ret = led.devOff();
            return ret;
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate()");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind() intent action = " + intent.getAction());
        return binder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind()");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy()");
        super.onDestroy();
    }
}

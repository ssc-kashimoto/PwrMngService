package jp.co.solver.app;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;

public class PwrMngService extends Service {
    final static String TAG = "k_test";

    private PwrMngSetting mPms = null;

    BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
        	String sAction = intent.getAction();

        	if (sAction.equals(Intent.ACTION_SCREEN_OFF)) {
                Log.d(TAG, "onReceive() - screen off");
                mPms.setWifi(false);
            } else if (sAction.equals(Intent.ACTION_SCREEN_ON)) {
                Log.d(TAG, "onReceive() - screen on");
                mPms.setWifi(true);
            }
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        try {
        	mPms = new PwrMngSetting(getSystemService(WIFI_SERVICE));
            IntentFilter filter = new IntentFilter();
            filter.addAction(Intent.ACTION_SCREEN_OFF);
            filter.addAction(Intent.ACTION_SCREEN_ON);
            registerReceiver(mReceiver, filter);
        } catch (RuntimeException e) {
            Log.d(TAG, "RuntimeException...");
        }
        Log.d(TAG, "onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand");
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mReceiver);
        Log.d(TAG, "onDestroy");
    }

}


package jp.co.solver.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

public class PwrMngServiceActivity extends Activity {
    private PwrMngSetting mPms = null;
    private boolean mService = false;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        ToggleButton tb_Wifi = (ToggleButton) findViewById(R.id.tb_Wifi);
        ToggleButton tb_Bluetooth = (ToggleButton) findViewById(R.id.tb_Bluetooth);
        ToggleButton tb_Service = (ToggleButton) findViewById(R.id.tb_service);

        mPms = new PwrMngSetting(getSystemService(WIFI_SERVICE));
        tb_Wifi.setChecked(mPms.getWifi());
        tb_Bluetooth.setChecked(mPms.getBluetooth());
        tb_Service.setChecked(mService);

        tb_Wifi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // TODO 自動生成されたメソッド・スタブ
                mPms.setWifi(isChecked);
            }
        });

        tb_Bluetooth.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // TODO 自動生成されたメソッド・スタブ
                mPms.setBluetooth(isChecked);
            }
        });

        tb_Service.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // TODO 自動生成されたメソッド・スタブ
                mService = isChecked;
                if (mService) {
                    startService(new Intent(getBaseContext(), PwrMngService.class));
                } else {
                    stopService(new Intent(getBaseContext(), PwrMngService.class));
                }
            }
        });
    }
}
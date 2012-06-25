package jp.co.solver.app;

import android.net.wifi.WifiManager;
import android.bluetooth.BluetoothAdapter;

class PwrMngSetting {
	private WifiManager mWifi = null;
	private BluetoothAdapter mBt = null;

	PwrMngSetting () {
		mWifi = null;
		mBt = BluetoothAdapter.getDefaultAdapter();
	}

	PwrMngSetting (Object aWifi) {
		mWifi = (WifiManager)aWifi;
		mBt = BluetoothAdapter.getDefaultAdapter();
	}

	void setWifi(boolean aEnable) {
		if (mWifi == null) return;

		mWifi.setWifiEnabled(aEnable);
	}

	boolean getWifi() {
		if (mWifi == null) return false;

		return mWifi.isWifiEnabled();
	}

	void setBluetooth(boolean aEnable) {
		if (mBt == null) return;

		if (aEnable == true) {
			mBt.enable();
		} else {
			mBt.disable();
		}
	}

	boolean getBluetooth() {
		if (mBt == null) return false;

		return mBt.isEnabled();
	}
}
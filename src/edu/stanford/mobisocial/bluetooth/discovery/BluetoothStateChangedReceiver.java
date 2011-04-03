package edu.stanford.mobisocial.bluetooth.discovery;

import edu.stanford.mobisocial.bluetooth.ScanMode;
import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BluetoothStateChangedReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
		if(adapter.getScanMode() != BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE)
			ScanMode.setScanModeWithUI(context, adapter, BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE, 1000 * 1000 * 100);
	}

}

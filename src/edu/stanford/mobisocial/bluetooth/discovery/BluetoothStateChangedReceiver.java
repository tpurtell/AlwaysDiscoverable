package edu.stanford.mobisocial.bluetooth.discovery;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import edu.stanford.mobisocial.bluetooth.ScanMode;

public class BluetoothStateChangedReceiver extends BroadcastReceiver {
	private static final BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
	@Override
	public void onReceive(Context context, Intent intent) {
		if(!AlwaysDiscoverable.started)
			return;
		if(adapter.isEnabled() && adapter.getScanMode() != BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE) 
			ScanMode.setScanModeWithUI(context, adapter, BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE, 120 * 1000);
	}
}

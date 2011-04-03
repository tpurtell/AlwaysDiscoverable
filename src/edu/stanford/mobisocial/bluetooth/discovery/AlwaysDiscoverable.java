package edu.stanford.mobisocial.bluetooth.discovery;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter; 
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import edu.stanford.mobisocial.bluetooth.ScanMode;

public class AlwaysDiscoverable extends Activity {
    private static final String TAG = "AlwaysDiscoverable";
    public static boolean started = false;
	private final Timer timer = new Timer();
	private static final BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
	private class MakeDiscoverableTask extends TimerTask {
		public void run() {
			if(adapter.isEnabled()) 
				ScanMode.setScanMode(adapter, BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE, 120);
			else
				Log.w(TAG, "bluetooth is disabled");
		}
	}
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    protected void onStart() {
    	super.onStart();
        kickOff();
    }
    @Override
    protected void onRestart() {
    	super.onRestart();
        kickOff();    	
    }
    protected void kickOff() {
        if(started) {
        	Toast.makeText(this, "Already discoverable.", Toast.LENGTH_SHORT).show();
        	finish();
        	return;
        }
        adapter.enable();
        if(ScanMode.setScanModeWithUI(this, adapter, BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE, 120)) {
            started = true;
        	new Thread(new Runnable() {
				@Override
				public void run() {
		        	timer.schedule(new MakeDiscoverableTask(), 90 * 1000, 90 * 1000);
				}
			}).start();
        }
		finish();
	}
}
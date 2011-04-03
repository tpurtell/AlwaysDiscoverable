package edu.stanford.mobisocial.bluetooth.discovery;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class AlwaysDiscoverable extends Activity {
    private static final String TAG = "AlwaysDiscoverable";

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
		try {
			Method m_set_scan_mode = BluetoothAdapter.class.getDeclaredMethod("setScanMode", new Class[] { int.class, int.class });
			m_set_scan_mode.setAccessible(true);
			m_set_scan_mode.invoke(adapter, new Object[] { BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE, 1000 * 1000 * 100 } );
			Toast.makeText(this, "Permanent discoverability activated.", Toast.LENGTH_SHORT).show();
		} catch(NoSuchMethodException e) {
			Log.e(TAG, "failed to find the scan mode setter method", e);
			Toast.makeText(this, "Bluetooth framework not found.", Toast.LENGTH_LONG).show();;
		} catch(IllegalAccessException e) {
			Toast.makeText(this, "Reflection failure.", Toast.LENGTH_LONG).show();;
			Log.e(TAG, "scan mode setter access violation", e);
		} catch(InvocationTargetException e) {
			Toast.makeText(this, "Access Denied: This must installed in the /system/app folder. (Requires rooted device)", Toast.LENGTH_LONG).show();;
			Log.e(TAG, "scan mode setter invocation failed", e.getCause());
		}
		finish();
    }
}
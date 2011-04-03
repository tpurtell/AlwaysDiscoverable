package edu.stanford.mobisocial.bluetooth;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class ScanMode {
	private static final String TAG = "ScanMode";

	public static boolean setScanModeWithUI(Context context, BluetoothAdapter adapter, int mode, int duration) {
		try {
			Method m_set_scan_mode = BluetoothAdapter.class.getDeclaredMethod("setScanMode", new Class[] { int.class, int.class });
			m_set_scan_mode.setAccessible(true);
			m_set_scan_mode.invoke(adapter, new Object[] { mode, duration } );
			Toast.makeText(context, "Permanent discoverability activated.", Toast.LENGTH_SHORT).show();
			return true;
		} catch(NoSuchMethodException e) {
			Log.e(TAG, "failed to find the scan mode setter method", e);
			Toast.makeText(context, "Bluetooth framework members not found.", Toast.LENGTH_SHORT).show();
		} catch(IllegalAccessException e) {
			Toast.makeText(context, "Reflection failure.", Toast.LENGTH_LONG).show();;
			Log.e(TAG, "scan mode setter access violation", e);
		} catch(InvocationTargetException e) {
			Toast.makeText(context, "Access Denied: AlwaysDiscoverable must installed in the /system/app folder. (Requires rooted device)", Toast.LENGTH_LONG).show();;
			Log.e(TAG, "scan mode setter invocation failed", e.getCause());
		}		
		return false;
	}
	public static boolean setScanMode(BluetoothAdapter adapter, int mode, int duration) {
		try {
			Method m_set_scan_mode = BluetoothAdapter.class.getDeclaredMethod("setScanMode", new Class[] { int.class, int.class });
			m_set_scan_mode.setAccessible(true);
			m_set_scan_mode.invoke(adapter, new Object[] { mode, duration } );
			return true;
		} catch(NoSuchMethodException e) {
			Log.e(TAG, "failed to find the scan mode setter method", e);
		} catch(IllegalAccessException e) {
			Log.e(TAG, "scan mode setter access violation", e);
		} catch(InvocationTargetException e) {
			Log.e(TAG, "scan mode setter invocation failed", e.getCause());
		}		
		return false;
	}
}

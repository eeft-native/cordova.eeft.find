package cordova.eeft.find;
import android.os.Build;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Method;

/import android.content.Context;
//import android.content.pm.PackageInfo;
//import android.content.pm.PackageManager;

import android.os.Build;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.LOG;
import org.apache.cordova.PluginResult;
import org.apache.cordova.PermissionHelper;

import java.util.Arrays;
import java.util.List;
 
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
 
import java.lang.*;
import java.io.*;
 
import android.content.pm.PackageManager;
import android.app.ActivityManager;
 
import android.Manifest;
 
/**
 * This class echoes a string called from JavaScript.
 */
public class Finder extends CordovaPlugin {

  public static final String LOG_TAG = "find";
	public static final String ERROR_UNKNOWN_ACTION = "Unknown action";

 @Override
  public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {

    if (!action.equals("find")) {
		  cordova.getActivity().runOnUiThread(new Runnable() {

		      public void run() {
		          LOG.e(Constants.LOG_TAG, ERROR_UNKNOWN_ACTION);
		          callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR, ERROR_UNKNOWN_ACTION));
		      }
		  });

		  return false;
		}

    cordova.getThreadPool().execute(new Runnable() {

        @Override
        public void run() {
            PluginResult result;

            try {
                result = this.c1272(args, callbackContext);
            } catch (Exception error) {
                result = new PluginResult(PluginResult.Status.ERROR, error.toString());
            }

            callbackContext.sendPluginResult(result);
        }
    });

     
    return true;
  }

public static boolean c1485(String c1486)
{
    Process p = null;
    try {
        p = Runtime.getRuntime().exec(c1486);
        BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
        return in.readLine() != null;
    } catch (Exception e) {
        return false;
    } finally {
        if (p != null) {
            p.destroy();
        }
    }
}

 

private static void c1272(final String action, CallbackContext callbackContext)
{
    System.out.println("@#@ Build.MODEL:"  + Build.MODEL);
    System.out.println("@#@ Build.BOARD:"  + Build.BOARD);
    System.out.println("@#@ Build.HOST:"  + Build.HOST);
    System.out.println("@#@ Build.MANUFACTURER:"  + Build.MANUFACTURER);
    System.out.println("@#@ Build.FINGERPRINT:"  + Build.FINGERPRINT);
    System.out.println("@#@ Build.BRAND:"  + Build.BRAND);
    System.out.println("@#@ Build.DEVICE:"  + Build.DEVICE);
    System.out.println("@#@ Build.PRODUCT:"  + Build.PRODUCT);
    if(c1485("/system/bin/which su"))
    {
        System.out.println("@#@ EXEC: /system/bin/which su");
    }
    if(c1485("/system/xbin/which su"))
    {
        System.out.println("@#@ EXEC: /system/xbin/which su");
    }
    if(c1485("which su"))
    {
        System.out.println("@#@ EXEC: which su");
    }

    List<String> c7111 = Arrays.asList(
            "/data/local/",
            "/data/local/xbin/",
            "/data/local/bin/",
            "/sbin/",
            "/system/",
            "/system/bin/",
            "/system/bin/.ext/",
            "/system/bin/.ext/.su/",
            "/system/bin/failsafe/",
            "/system/sd/xbin/",
            "/system/xbin/",
            "/su/bin/",
            "/su/xbin/",
            "/ipcData/local/",
            "/ipcData/local/xbin/",
            "/system/usr/we-need-root/",
            "/system/usr/we-need-root/su-backup/",
            "/system/xbin/mu/",
            "/magisk/.core/bin/"
    );
    List<String> c7001 = Arrays.asList(
                "/system/app/Superuser.apk",
                "/system/app/superuser.apk",
                "/system/app/Superuser/Superuser.apk",
                "/system/app/Superuser/superuser.apk",
                "/system/app/superuser/Superuser.apk",
                "/system/app/superuser/superuser.apk"
        );
    for (final String c7113 : c7111.toArray(new String[0]))
    {
        final File c7115 = new File(c7113 + "su");
        if(c7115.exists())
        {
            System.out.println("@#@ c7111 EXISTS: " + c7115.getAbsolutePath());        
        }
    }
    for (final String c7002 : c7001.toArray(new String[0]))
    {
        final File c7003 = new File(c7002);
        if(c7003.exists())
        {
            System.out.println("@#@ c7003 EXISTS: " + c7003.getAbsolutePath());        
        }
    }
    //final PackageManager pm = callbackContext.getPackageManager();
    //final List<PackageInfo> installedPackages = pm.getInstalledPackages(0);

    //for (PackageInfo packageInfo : installedPackages) {
    //    final String packageName = packageInfo.packageName;
    //    System.out.println("%@% PKG: " + packageName);        
    //}

    PluginResult pluginResult = new PluginResult(PluginResult.Status.OK, true);
    pluginResult.setKeepCallback(true);
    callbackContext.sendPluginResult(pluginResult);
	}
}

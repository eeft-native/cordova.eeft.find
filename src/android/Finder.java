package cordova.eeft.find;
import android.os.Build;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Method;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import android.os.Build;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
//import org.apache.cordova.LOG;
import org.apache.cordova.PluginResult;
import org.apache.cordova.PluginResult.Status;
import org.apache.cordova.PermissionHelper;

import java.util.Arrays;
import java.util.List;
 
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
 
import java.lang.*;
import java.io.*;
 
import android.app.ActivityManager;
 
import android.Manifest;
 
/**
 * This class echoes a string called from JavaScript.
 */
public class Finder extends CordovaPlugin {

	public static final String ERROR_UNKNOWN_ACTION = "@#@ Unknown action";

 @Override
  public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {

    if (!action.equals("find")) {
		  cordova.getActivity().runOnUiThread(new Runnable() {

	        @Override
		      public void run() {
							System.out.println(ERROR_UNKNOWN_ACTION);
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
                result = c1272(args, callbackContext);
            } catch (Exception error) {
                result = new PluginResult(PluginResult.Status.ERROR, error.toString());
            }

            callbackContext.sendPluginResult(result);
        }
    });

     
    return true;
  }

	public PluginResult c1272(final JSONArray args, final CallbackContext callbackContext) {
	   try {
	       //Context context = this.cordova.getActivity().getApplicationContext();
	       //Finder finder = new Finder();

	       boolean checkFinder = Finder.c1372(callbackContext);

	       System.out.println("checkFinder: " + checkFinder);

	       return new PluginResult(Status.OK, checkFinder);
	   } catch (Exception error) {
	       System.out.println("checkFinder error: " + error.toString());
	   }
	   return new PluginResult(Status.ERROR, "ERROR:1272");
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

	public static boolean c1372(final CallbackContext callbackContext)
	{
	    int e = 0;
	    e |= Build.MODEL.contains("Emulator") ? 1:0;
	    e |= Build.MODEL.contains("Android SDK built for x86") ? 2:0;
	    e |= Build.BOARD.equals("QC_Reference_Phone") ? 4:0;
	    e |= Build.HOST.startsWith("Build") ? 8:0;
	    e |= Build.MANUFACTURER.contains("Genymotion") ? 16:0;
	    e |= Build.FINGERPRINT.startsWith("generic") ? 32:0;
	    e |= Build.BRAND.startsWith("generic") ? 64:0;
	    e |= Build.DEVICE.startsWith("generic") ? 128:0;
	    e |= Build.MODEL.contains("google_sdk") ? 256:0;
	    e |= "google_sdk".equals(Build.PRODUCT) ? 512:0;
		  e |= c1485("/system/bin/which su") ? 1024:0;
		  e |= c1485("/system/xbin/which su") ? 2048:0;
		  e |= c1485("which su") ? 4096:0;
	    if(c1485("/system/bin/which su"))
	    {
	        e |= 8192;
	    }
	    if(c1485("/system/xbin/which su"))
	    {
	        e |= 16384;
	    }
	    if(c1485("which su"))
	    {
	        e |= 32768;
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
						e |= 65536;
	        }
	    }
	    for (final String c7002 : c7001.toArray(new String[0]))
	    {
	        final File c7003 = new File(c7002);
	        if(c7003.exists())
	        {
						e |= 131072;
	        }
	    }
	    
	    //final PackageManager pm = callbackContext.getPackageManager();
	    //final List<PackageInfo> installedPackages = pm.getInstalledPackages(0);

	    //for (PackageInfo packageInfo : installedPackages) {
	    //    final String packageName = packageInfo.packageName;
	    //    System.out.println("@$@ PKG: " + packageName);        
	    //}
   		System.out.println("ieee: " + e);

	  	return e == 0;
	}
}

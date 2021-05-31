package cordova.eeft.find;
 import android.os.Build;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import android.os.Build;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
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
  private static final String[] cheakPackagesList = { "com.thirdparty.superuser", "eu.chainfire.supersu",
      "com.noshufou.android.su", "com.koushikdutta.superuser", "com.zachspong.temprootremovejb",
      "com.ramdroid.appquarantine", "com.topjohnwu.magisk" };
 
  @Override
  public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {

        if (action.equals("find")) {
      this.c1272();
      return true;
    }
 
    return false;
  }


 
//   private void findMethod(CallbackContext callbackContext) {
//     if (this.checkRootByFile() || this.checkRootByPackage() || this.detectFrida()) {
//       PluginResult pluginResult = new PluginResult(PluginResult.Status.OK, true);
//       pluginResult.setKeepCallback(true);
//       callbackContext.sendPluginResult(pluginResult);
//     } else {
//       PluginResult pluginResult = new PluginResult(PluginResult.Status.OK, false);
//       pluginResult.setKeepCallback(true);
//       callbackContext.sendPluginResult(pluginResult);
//     }
//   }

//   private boolean checkRootByFile() {
//     for (String pathDir : System.getenv("PATH").split(":")) {
//       if (new File(pathDir, "su").exists()) {
//         return true;
//       }
//     }
//     return false;
//   }

//   private boolean checkRootByPackage() {
//     for (String packageUri : cheakPackagesList) {
//       if (this.appInstalledOrNot(packageUri)) {
//         return true;
//       }
//     }
//     return false;
//   }

//   private boolean detectFrida() {
//     int pid = android.os.Process.myPid();

//     try {
//       Process process = Runtime.getRuntime().exec("cat /proc/" + pid + "/maps");
//       BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

//       int read;
//       char[] buffer = new char[4096];
//       StringBuffer output = new StringBuffer();
//       while ((read = reader.read(buffer)) > 0) {
//         output.append(buffer, 0, read);
//       }
//       reader.close();

//       // Waits for the command to finish.
//       process.waitFor();

//       if (output.toString().contains("frida")) {
//         return true;
//       }
//        if (output.toString().contains("xposed.XposedBridge")) {
//         return true;
//       }
//        if (output.toString().contains("saurik.substrate")) {
//         return true;
//       }
//     } catch (IOException e) {

//     } catch (InterruptedException e) {

//     }
//     return false;

//   }

//   private boolean appInstalledOrNot(String uri) {
//     PackageManager pm = this.cordova.getActivity().getPackageManager();
//     try {
//       pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
//       return true;
//     } catch (PackageManager.NameNotFoundException e) {
//     }

//     return false;
//   }

// }
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
        if (process != null) {
            process.destroy();
        }
    }
}

 

private static boolean c1272()
{
    int e = 0;
    e += Build.MODEL.contains("Emulator") ? 1:0;
    e += Build.MODEL.contains("Android SDK built for x86") ? 1:0;
    e += Build.BOARD.equals("QC_Reference_Phone") ? 1:0;
    e += Build.HOST.startsWith("Build") ? 1:0;
    e += Build.MANUFACTURER.contains("Genymotion") ? 1:0;
    e += Build.FINGERPRINT.startsWith("generic") ? 1:0;
    e += Build.BRAND.startsWith("generic") ? 1:0;
    e += Build.DEVICE.startsWith("generic") ? 1:0;
    e += Build.MODEL.contains("google_sdk") ? 1:0;
    e += "google_sdk".equals(Build.PRODUCT) ? 1:0;
  e += c1485("/system/bin/which su") ? 1:0;
  e += c1485("/system/xbin/which su") ? 1:0;
  e += c1485("which su") ? 1:0;

 

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
      e += c7115.exists();
  }
  for (final String c7002 : c7001.toArray(new String[0]))
  {
      final File c7003 = new File(c7002);
      e += c7003.exists();
  }

 

    return e != 0;
}
}

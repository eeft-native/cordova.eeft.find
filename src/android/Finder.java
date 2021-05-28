package cordova.eeft.find;
 
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.PluginResult;
import org.apache.cordova.PermissionHelper;
 
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
      this.findMethod(callbackContext);
      return true;
    }
 
    return false;
  }


 
  private void findMethod(CallbackContext callbackContext) {
    if (this.checkRootByFile() || this.checkRootByPackage() || this.detectFrida()) {
      PluginResult pluginResult = new PluginResult(PluginResult.Status.OK, true);
      pluginResult.setKeepCallback(true);
      callbackContext.sendPluginResult(pluginResult);
    } else {
      PluginResult pluginResult = new PluginResult(PluginResult.Status.OK, false);
      pluginResult.setKeepCallback(true);
      callbackContext.sendPluginResult(pluginResult);
    }
  }

  private boolean checkRootByFile() {
    for (String pathDir : System.getenv("PATH").split(":")) {
      if (new File(pathDir, "su").exists()) {
        return true;
      }
    }
    return false;
  }

  private boolean checkRootByPackage() {
    for (String packageUri : cheakPackagesList) {
      if (this.appInstalledOrNot(packageUri)) {
        return true;
      }
    }
    return false;
  }

  private boolean detectFrida() {
    int pid = android.os.Process.myPid();

    try {
      Process process = Runtime.getRuntime().exec("cat /proc/" + pid + "/maps");
      BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

      int read;
      char[] buffer = new char[4096];
      StringBuffer output = new StringBuffer();
      while ((read = reader.read(buffer)) > 0) {
        output.append(buffer, 0, read);
      }
      reader.close();

      // Waits for the command to finish.
      process.waitFor();

      if (output.toString().contains("frida")) {
        return true;
      }
       if (output.toString().contains("xposed.XposedBridge")) {
        return true;
      }
       if (output.toString().contains("saurik.substrate")) {
        return true;
      }
    } catch (IOException e) {

    } catch (InterruptedException e) {

    }
    return false;

  }

  private boolean appInstalledOrNot(String uri) {
    PackageManager pm = this.cordova.getActivity().getPackageManager();
    try {
      pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
      return true;
    } catch (PackageManager.NameNotFoundException e) {
    }

    return false;
  }

}

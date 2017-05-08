package tmnt.example.onedaily.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

/**
 * Created by tmnt on 2017/5/8.
 */

public class PremissionUtil {

    public static final int REQUEST_PERMISSION_CODE = 1201;

    @TargetApi(Build.VERSION_CODES.M)
    public static boolean chaeckPermission(Context context, String permission) {
        int have = ContextCompat.checkSelfPermission(context,permission);
        if (have == PackageManager.PERMISSION_DENIED) {
            return false;
        } else {
            return true;
        }
    }

    public static void requestPermission(Activity activity, String[] permissionss) {
        ActivityCompat.requestPermissions(activity, permissionss, REQUEST_PERMISSION_CODE);
    }

}

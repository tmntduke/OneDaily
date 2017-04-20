package tmnt.example.onedaily.util;

import android.app.Activity;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by tmnt on 2017/4/20.
 */

public class StateBarUtils {
    /**
     * 适配4.4系统
     *
     * @param activity
     * @param b
     */
    public static void setTranslucentStatus(Activity activity, boolean b) {
        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (b) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }
}

package tmnt.example.onedaily.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.view.Window;
import android.view.WindowManager;

import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;


/**
 * Created by tmnt on 2016/5/26.
 */
public class SystemUtils {

    private static final String TAG = "SystemUtils";

    /**
     * 显示Toast
     *
     * @param context
     * @param title
     */
    public static void showToast(Context context, String title) {
        Toast.makeText(context, title, Toast.LENGTH_LONG).show();
    }

    /**
     * 打开网络设置界面
     */
    public static void openNetworkSetting(Context context) {

        Intent intent = new Intent(Settings.ACTION_SETTINGS);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /**
     * 打开文件管理器
     *
     * @param context
     */
    public static void openContent(Context context) {
        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
        i.setType("file/*");
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }

    //隐藏软键盘
    public static void hideSoftInput(Activity activity) {
        android.view.View view = activity.getWindow().peekDecorView();
        InputMethodManager inputmanger = null;
        try {
            if (view != null) {
                inputmanger = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
                inputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            inputmanger = null;
        }
    }
}

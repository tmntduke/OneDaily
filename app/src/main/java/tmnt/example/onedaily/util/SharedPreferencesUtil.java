package tmnt.example.onedaily.util;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;

/**
 * Created by tmnt on 2017/4/14.
 */

public class SharedPreferencesUtil {

    private static final String SHARE_COMMON = "share";
    private static SharedPreferences.Editor sEditor;
    private static SharedPreferences sSharedPreferences;

    private static SharedPreferencesUtil sUtil;

    private Map<String, ?> datas;

    private SharedPreferencesUtil(Context context) {
        sEditor = context.getSharedPreferences(SHARE_COMMON, Context.MODE_PRIVATE).edit();
        sSharedPreferences = context.getSharedPreferences(SHARE_COMMON, Context.MODE_PRIVATE);

    }

    public static SharedPreferencesUtil getInstance(Context context) {
        if (sUtil == null) {
            sUtil = new SharedPreferencesUtil(context);
        }
        return sUtil;
    }

    public <T> void putData(String key, T t) {
        if (t instanceof String) {
            sEditor.putString(key, (String) t);
        } else if (t instanceof Integer) {
            sEditor.putInt(key, (Integer) t);
        } else if (t instanceof Boolean) {
            sEditor.putBoolean(key, (Boolean) t);
        }
        sEditor.commit();

    }

    public <T> T getData(String key) {
        datas = sSharedPreferences.getAll();
        try {
            return (T) datas.get(key);
        } catch (Exception e) {
            return null;
        }
    }

    public void removeData(String key) {
        if (getData(key) != null){
            sEditor.remove(key);
            sEditor.commit();
        }

    }
}

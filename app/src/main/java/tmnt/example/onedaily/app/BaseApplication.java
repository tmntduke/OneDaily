package tmnt.example.onedaily.app;

import android.app.Application;

import java.io.File;

import tmnt.example.onedaily.ui.common.Common;

/**
 * Created by tmnt on 2017/4/12.
 */

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        File file = new File(Common.ONEDAILY_PATH);
        if (!file.exists()) {
            file.mkdir();
        }
    }
}

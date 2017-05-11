package tmnt.example.onedaily.util;

import android.os.Environment;

import java.io.File;

/**
 * Created by tmnt on 2017/4/12.
 */

public class Common {

    public static final String DOUBAN_URL = "https://api.douban.com/v2/";//movie/in_theaters

    public static final String GANK_URL = "http://gank.io/api/";

    public static final String ZHIHU_URL = "http://news-at.zhihu.com/api/4/news/";

    public static final String ONEDAILY_PATH = Environment.getExternalStorageDirectory().getPath() + File.separator + "oneDaily";

}

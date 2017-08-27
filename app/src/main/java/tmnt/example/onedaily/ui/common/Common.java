package tmnt.example.onedaily.ui.common;

import android.os.Environment;

import java.io.File;

/**
 * Created by tmnt on 2017/4/12.
 */

public interface Common {

    String DOUBAN_URL = "https://api.douban.com/v2/";//movie/in_theaters

    String GANK_URL = "http://gank.io/api/";

    String ZHIHU_URL = "http://news-at.zhihu.com/api/4/news/";

    String ONEDAILY_PATH = Environment.getExternalStorageDirectory().getPath() + File.separator + "oneDaily";

}

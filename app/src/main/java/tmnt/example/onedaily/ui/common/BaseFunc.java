package tmnt.example.onedaily.ui.common;

import android.os.Bundle;

/**
 * Created by tmnt on 2017/4/11.
 */

public interface BaseFunc {

    /**
     * 初始化数据
     */
    void initData(Bundle savedInstanceState);

    /**
     * 初始化控件
     */
    void initView();

    /**
     * 操作
     */
    void initOperation();

    /**
     * 加载数据
     */
    void loadData();

}

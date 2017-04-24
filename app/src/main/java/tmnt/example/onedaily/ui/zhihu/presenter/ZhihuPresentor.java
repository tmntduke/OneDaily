package tmnt.example.onedaily.ui.zhihu.presenter;

import tmnt.example.onedaily.bean.zhihu.ZhihuInfo;
import tmnt.example.onedaily.mvp.BasePresenter;
import tmnt.example.onedaily.mvp.Model;
import tmnt.example.onedaily.mvp.View;

/**
 * Created by tmnt on 2017/4/24.
 */

public class ZhihuPresentor extends BasePresenter<ZhihuInfo> {

    public ZhihuPresentor(Model<ZhihuInfo> model, View<ZhihuInfo> view) {
        super(model, view);
    }

    @Override
    public void handleData() {

    }

    @Override
    public void handleLoad(String page) {

    }

    @Override
    public void handleRefresh(String page) {

    }
}

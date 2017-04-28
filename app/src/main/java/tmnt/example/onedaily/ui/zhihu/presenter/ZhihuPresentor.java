package tmnt.example.onedaily.ui.zhihu.presenter;

import tmnt.example.onedaily.bean.zhihu.ZhihuInfo;
import tmnt.example.onedaily.mvp.BasePresenter;
import tmnt.example.onedaily.mvp.CallBack;
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
        mModel.getNews(new CallBack<ZhihuInfo>() {
            @Override
            public void onSuccess(ZhihuInfo zhihuInfo) {
                mView.showData(zhihuInfo);
            }

            @Override
            public void onError(Throwable e) {
                mView.showError(e);
            }
        });
    }

    @Override
    public void handleLoad(String page) {
        mModel.load(page, new CallBack<ZhihuInfo>() {
            @Override
            public void onSuccess(ZhihuInfo zhihuInfo) {
                mView.showLoadData(zhihuInfo);
            }

            @Override
            public void onError(Throwable e) {
                mView.showError(e);
            }
        });
    }

    @Override
    public void handleRefresh(String page) {

    }
}

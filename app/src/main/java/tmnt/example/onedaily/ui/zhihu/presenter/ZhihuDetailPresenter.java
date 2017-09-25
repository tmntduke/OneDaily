package tmnt.example.onedaily.ui.zhihu.presenter;

import tmnt.example.onedaily.bean.zhihu.ZhihuDetailInfo;
import tmnt.example.onedaily.mvp.BasePresenter;
import tmnt.example.onedaily.mvp.CallBack;
import tmnt.example.onedaily.mvp.Model;
import tmnt.example.onedaily.mvp.View;

/**
 * Created by tmnt on 2017/4/29.
 */

public class ZhihuDetailPresenter extends BasePresenter<ZhihuDetailInfo> {

    public ZhihuDetailPresenter(Model<ZhihuDetailInfo> model, View<ZhihuDetailInfo> view) {
        super(model, view);
    }

    @Override
    public void handleData() {
        mModel.getNews(new CallBack<ZhihuDetailInfo>() {
            @Override
            public void onSuccess(ZhihuDetailInfo zhihuDetailInfo) {
                mView.showData(zhihuDetailInfo);
            }

            @Override
            public void onError(Throwable e) {
                mView.showError(e);
            }
        });
    }

    @Override
    public void handleLoad(String page) {

    }

    @Override
    public void handleRefresh(String page) {

    }

    @Override
    public void cancel() {

    }
}

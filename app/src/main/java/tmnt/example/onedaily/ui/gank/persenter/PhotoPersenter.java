package tmnt.example.onedaily.ui.gank.persenter;

import tmnt.example.onedaily.bean.gank.PhotoInfo;
import tmnt.example.onedaily.mvp.BasePresenter;
import tmnt.example.onedaily.mvp.CallBack;
import tmnt.example.onedaily.mvp.Model;
import tmnt.example.onedaily.mvp.View;

/**
 * Created by tmnt on 2017/10/11.
 */

public class PhotoPersenter extends BasePresenter<PhotoInfo> {
    public PhotoPersenter(Model<PhotoInfo> model, View<PhotoInfo> view) {
        super(model, view);
    }

    @Override
    public void handleData() {
        mModel.getNews(new CallBack<PhotoInfo>() {
            @Override
            public void onSuccess(PhotoInfo photoInfo) {
                mView.showData(photoInfo);
            }

            @Override
            public void onError(Throwable e) {
                mView.showError(e);
            }
        });
    }

    @Override
    public void handleLoad(String page) {
        mModel.load(page, new CallBack<PhotoInfo>() {
            @Override
            public void onSuccess(PhotoInfo photoInfo) {
                mView.showLoadData(photoInfo);
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

    @Override
    public void cancel() {
        mModel.cancel();
    }
}

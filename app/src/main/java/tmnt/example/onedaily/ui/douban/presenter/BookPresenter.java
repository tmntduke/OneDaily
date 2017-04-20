package tmnt.example.onedaily.ui.douban.presenter;

import tmnt.example.onedaily.Rx.RxUilt;
import tmnt.example.onedaily.bean.book.DoubanBookInfo;
import tmnt.example.onedaily.mvp.BasePresenter;
import tmnt.example.onedaily.mvp.CallBack;
import tmnt.example.onedaily.mvp.Model;
import tmnt.example.onedaily.mvp.View;

/**
 * Created by tmnt on 2017/4/17.
 */

public class BookPresenter extends BasePresenter<DoubanBookInfo> {


    public BookPresenter(Model<DoubanBookInfo> model, View<DoubanBookInfo> view) {
        super(model, view);
    }


    @Override
    public void handleData() {
        mModel.getNews(new CallBack<DoubanBookInfo>() {
            @Override
            public void onSuccess(DoubanBookInfo doubanBookInfo) {
                mView.showData(doubanBookInfo);
            }

            @Override
            public void onError(Throwable e) {
                mView.showError(e);
            }
        });
    }

    @Override
    public void handleLoad(String page) {
        mModel.load(page,new CallBack<DoubanBookInfo>() {
            @Override
            public void onSuccess(DoubanBookInfo doubanBookInfo) {
                mView.showLoadData(doubanBookInfo);
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    @Override
    public void handleRefresh(String page) {

    }
}

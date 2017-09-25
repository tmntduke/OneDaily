package tmnt.example.onedaily.ui.main.presenter;

import java.util.List;

import tmnt.example.onedaily.mvp.BasePresenter;
import tmnt.example.onedaily.mvp.CallBack;
import tmnt.example.onedaily.mvp.Model;
import tmnt.example.onedaily.mvp.View;

/**
 * Created by tmnt on 2017/5/12.
 */

public class MsgPresenter<T> extends BasePresenter<List<T>> {
    public MsgPresenter(Model model, View view) {
        super(model, view);
    }

    @Override
    public void handleData() {
        mModel.getNews(new CallBack<List<T>>() {
            @Override
            public void onSuccess(List<T> ts) {
                mView.showData(ts);
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

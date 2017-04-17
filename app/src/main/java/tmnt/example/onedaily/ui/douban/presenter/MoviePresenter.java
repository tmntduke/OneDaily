package tmnt.example.onedaily.ui.douban.presenter;

import tmnt.example.onedaily.mvp.BasePresenter;
import tmnt.example.onedaily.mvp.CallBack;
import tmnt.example.onedaily.mvp.Model;
import tmnt.example.onedaily.mvp.View;

/**
 * Created by tmnt on 2017/4/17.
 */

public class MoviePresenter extends BasePresenter {

    public MoviePresenter(Model model, View view) {
        super(model, view);
    }

    @Override
    public void handleData(CallBack callBack) {

    }

    @Override
    public void handleLoad(CallBack callBack) {

    }

    @Override
    public void handleRefresh(String page, CallBack callBack) {

    }
}

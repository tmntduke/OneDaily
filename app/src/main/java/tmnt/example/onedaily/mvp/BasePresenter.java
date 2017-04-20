package tmnt.example.onedaily.mvp;


import tmnt.example.onedaily.mvp.Model;
import tmnt.example.onedaily.mvp.IPresenter;
import tmnt.example.onedaily.mvp.View;

/**
 * Created by tmnt on 2017/4/12.
 */

public abstract class BasePresenter<T> implements IPresenter {

    protected Model<T> mModel;
    protected View<T> mView;

    public BasePresenter(Model<T> model, View<T> view) {
        mModel = model;
        mView = view;
    }


}

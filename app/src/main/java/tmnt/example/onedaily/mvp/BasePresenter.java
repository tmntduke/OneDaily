package tmnt.example.onedaily.mvp;


import tmnt.example.onedaily.mvp.Model;
import tmnt.example.onedaily.mvp.IPresenter;
import tmnt.example.onedaily.mvp.View;

/**
 * Created by tmnt on 2017/4/12.
 */

public abstract class BasePresenter implements IPresenter {

    protected Model mModel;
    protected View mView;

    public BasePresenter(Model model, View view) {
        mModel = model;
        mView = view;
    }


}

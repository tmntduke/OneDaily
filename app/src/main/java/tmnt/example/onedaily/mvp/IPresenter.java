package tmnt.example.onedaily.mvp;

/**
 * Created by tmnt on 2017/4/12.
 */

public interface IPresenter {

    void handleData(CallBack callBack);

    void handleLoad(CallBack callBack);

    void handleRefresh(String page, CallBack callBack);

}

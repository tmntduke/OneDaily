package tmnt.example.onedaily.ui.gank.presenter;

/**
 * Created by tmnt on 2017/4/12.
 */

public interface Presenter {

    void handleData(CallBack callBack);

    void handleLoad(CallBack callBack);

    void handleRefresh(String page, CallBack callBack);

}

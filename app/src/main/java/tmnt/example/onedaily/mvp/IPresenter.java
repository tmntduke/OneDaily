package tmnt.example.onedaily.mvp;

/**
 * Created by tmnt on 2017/4/12.
 */

public interface IPresenter {

    void handleData();

    void handleLoad(String page);

    void handleRefresh(String page);

}

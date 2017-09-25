package tmnt.example.onedaily.mvp;

/**
 * presenter 提供固定方法
 * Created by tmnt on 2017/4/12.
 */

public interface IPresenter {

    void handleData();

    void handleLoad(String page);

    void handleRefresh(String page);

    void cancel();

}

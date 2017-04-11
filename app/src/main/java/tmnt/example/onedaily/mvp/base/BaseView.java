package tmnt.example.onedaily.mvp.base;

/**
 * Created by tmnt on 2017/4/11.
 */

public interface BaseView {

    /**
     * 开始请求
     */
    void onRequestStart();

    /**
     * 请求失败
     *
     * @param msg
     */
    void onRequestError(String msg);

    /**
     * 请求成功
     */
    void onRequestSuccesss();

    /**
     * 未知错误
     */
    void onUnknown();
}

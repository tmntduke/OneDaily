package tmnt.example.onedaily.mvp;

/**
 * 数据回调
 * Created by tmnt on 2017/4/12.
 */

public interface CallBack<T> {

    void onSuccess(T t);

    void onError(Throwable e);


}

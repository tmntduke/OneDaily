package tmnt.example.onedaily.mvp;

import java.util.List;

/**
 * 数据显示
 * Created by tmnt on 2017/4/12.
 */

public interface View<T> {

    void showData(T datas);

    void showLoadData(T datas);

    void showRefreshData(T datas);

    void showError(Throwable throwable);
}

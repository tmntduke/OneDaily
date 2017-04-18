package tmnt.example.onedaily.mvp;

import java.util.List;

/**
 * Created by tmnt on 2017/4/12.
 */

public interface View<T> {

    void showData(List<T> datas);

    void showLoadData(List<T> datas);

    void showRefreshData(List<T> datas);
}

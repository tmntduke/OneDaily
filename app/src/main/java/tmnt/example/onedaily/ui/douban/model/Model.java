package tmnt.example.onedaily.ui.douban.model;

import java.util.Calendar;
import java.util.List;

import rx.Observable;

/**
 * Created by tmnt on 2017/4/12.
 */

public interface Model<T> {

    T getGankNews();

    T refresh();

    T load(String page);

}

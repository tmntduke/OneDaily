package tmnt.example.onedaily.ui.gank.model;

/**
 * Created by tmnt on 2017/4/12.
 */

public interface Model<T> {

    T getGankNews();

    T refresh();

    T load(String page);

}

package tmnt.example.onedaily.mvp;

/**
 * Created by tmnt on 2017/4/12.
 */

public interface Model<T> {

    T getNews();

    T refresh();

    T load(String page);

}

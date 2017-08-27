package tmnt.example.onedaily.mvp;

/**
 * 数据Model
 * Created by tmnt on 2017/4/12.
 */

public interface Model<T> {

    void getNews(CallBack<T> callBack);

    void refresh(CallBack<T> callBack);

    void load(String page,CallBack<T> callBack);

}

package tmnt.example.onedaily.mvp.base;

/**
 * Created by tmnt on 2017/4/11.
 */

public interface BasePresentor<M, V> {

    void attachVM(V v, M m);

    void detachVM();

}

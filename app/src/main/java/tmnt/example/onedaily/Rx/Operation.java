package tmnt.example.onedaily.Rx;

/**
 * Created by tmnt on 2017/4/23.
 */

public interface Operation<T> {
    T operation();

    void onSuccess(T t);

    void onError(Throwable throwable);
}

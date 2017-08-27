package tmnt.example.onedaily.Rx;

import android.util.Log;

import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import tmnt.example.onedaily.bean.book.DoubanBookInfo;
import tmnt.example.onedaily.mvp.CallBack;

/**
 * Rxjava操作
 * Created by tmnt on 2017/4/20.
 */

public class RxUilt {

    private static final String TAG = "RxUilt";

    private static final RxUilt INSTANCE = new RxUilt();

    private RxUilt() {
    }

    public static RxUilt getInstance() {
        return INSTANCE;
    }

    /**
     * 从Observable中获取到数据
     * 当超时后发送error
     * @param observable
     * @param callBack
     * @param <T>
     */
    public <T> void getDataForObservable(Observable<T> observable, CallBack<T> callBack) {

        observable.timeout(6, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(o -> {
                    if (callBack != null) {
                        Log.i(TAG, "getDataForObservable: " + o);
                        callBack.onSuccess(o);
                    }
                }, throwable -> {
                    if (callBack != null) {
                        callBack.onError(throwable);
                    }
                });
    }

    /**
     * 过滤重复数据
     * @param t
     * @param callBack
     * @param <T>
     */
    public <T> void distinctForData(List<T> t, CallBack<List<T>> callBack) {

        Observable.from(t)
                .distinct()
                .toList()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(list -> {
                    if (callBack != null) {
                        callBack.onSuccess(list);
                    }
                });
    }

    public <T> Observable<T> getObservaleForSingle(T t, Scheduler scheduler) {
        return Observable.just(t)
                .subscribeOn(scheduler)
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 利用Rxjava线程切换创建数据
     * @param scheduler 指定子线程
     * @param operation 在子线程中要进行的操作（申请网络或数据库操作）
     * @param callBack 数据回调
     * @param <T>
     */
    public <T> void createAndResult(Scheduler scheduler, Operation<T> operation, CallBack<T> callBack) {
        Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                if (operation != null) {
                    try {
                        subscriber.onNext(operation.operation());
                    } catch (Exception e) {
                        subscriber.onError(e);
                    }

                }
            }
        }).subscribeOn(scheduler)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(o ->
                                callBack.onSuccess(o)
                        , throwable ->
                                callBack.onError(throwable)
                );
    }

}

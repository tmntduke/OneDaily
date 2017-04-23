package tmnt.example.onedaily.Rx;

import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import tmnt.example.onedaily.bean.book.DoubanBookInfo;
import tmnt.example.onedaily.mvp.CallBack;

/**
 * Created by tmnt on 2017/4/20.
 */

public class RxUilt<T> {

    public void getDataForObservable(Observable<T> observable, CallBack<T> callBack) {

        observable.timeout(6, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(o -> {
                    if (callBack != null) {
                        callBack.onSuccess(o);
                    }
                }, throwable -> {
                    if (callBack != null) {
                        callBack.onError(throwable);
                    }
                });
    }

    public void distinctForData(List<T> t, CallBack<List<T>> callBack) {

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

    public Observable<T> getObservaleForSingle(T t, Scheduler scheduler) {
        return Observable.just(t)
                .subscribeOn(scheduler)
                .observeOn(AndroidSchedulers.mainThread());
    }

    public void createAndResult(Scheduler scheduler, Operation<T> operation) {
        Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                if (operation != null) {
                    subscriber.onNext(operation.operation());
                }
            }
        }).subscribeOn(scheduler)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(t1 -> {
                    operation.onSuccess(t1);
                }, throwable -> {
                    operation.onError(throwable);
                });
    }

}

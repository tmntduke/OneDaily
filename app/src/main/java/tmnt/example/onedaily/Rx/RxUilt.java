package tmnt.example.onedaily.Rx;

import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import tmnt.example.onedaily.bean.book.DoubanBookInfo;
import tmnt.example.onedaily.mvp.CallBack;

/**
 * Created by tmnt on 2017/4/20.
 */

public class RxUilt<T> {

    private CallBack<T> callBack;

    public void setCallBack(CallBack<T> callBack) {
        this.callBack = callBack;
    }

    public void getDataForObservable(Observable<T> observable) {

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

    public void distinctForData(List<T> t,CallBack<List<T>> callBack){

        Observable.just(t)
                .distinct()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(list->{
                    if (callBack!=null){
                        callBack.onSuccess(list);
                    }
                });


    }

}

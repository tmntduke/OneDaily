package tmnt.example.onedaily.ui.gank.model;

import android.util.Log;

import rx.Subscription;
import tmnt.example.onedaily.Rx.RxUilt;
import tmnt.example.onedaily.api.Api;
import tmnt.example.onedaily.api.GankService;
import tmnt.example.onedaily.bean.gank.PhotoInfo;
import tmnt.example.onedaily.mvp.CallBack;
import tmnt.example.onedaily.mvp.Model;
import tmnt.example.onedaily.ui.common.Common;

/**
 * Created by tmnt on 2017/10/11.
 */

public class PhotoModel implements Model<PhotoInfo> {

    private Api mApi = Api.getInstance();
    private RxUilt mRxUilt = RxUilt.getInstance();
    private GankService mGankService = mApi.getCall(Common.GANK_URL, GankService.class);
    private Subscription mSubscription;

    @Override
    public void getNews(CallBack<PhotoInfo> callBack) {
        mSubscription = mRxUilt.getDataForObservable(mGankService.getPhoto("1"), callBack);
    }

    @Override
    public void refresh(CallBack<PhotoInfo> callBack) {

    }

    @Override
    public void load(String page, CallBack<PhotoInfo> callBack) {
        mSubscription = mRxUilt.getDataForObservable(mGankService.getPhoto(page), callBack);
    }

    @Override
    public void cancel() {
        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }
}

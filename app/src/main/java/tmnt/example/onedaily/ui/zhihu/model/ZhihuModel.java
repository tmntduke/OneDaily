package tmnt.example.onedaily.ui.zhihu.model;

import android.util.Log;

import tmnt.example.onedaily.Rx.RxUilt;
import tmnt.example.onedaily.api.Api;
import tmnt.example.onedaily.api.ZhihuService;
import tmnt.example.onedaily.bean.zhihu.ZhihuInfo;
import tmnt.example.onedaily.mvp.CallBack;
import tmnt.example.onedaily.mvp.Model;
import tmnt.example.onedaily.util.Common;

/**
 * Created by tmnt on 2017/4/24.
 */

public class ZhihuModel implements Model<ZhihuInfo> {
    private Api mApi = Api.getInstance();
    private ZhihuService mZhihuService = mApi.getCall(Common.ZHIHU_URL, ZhihuService.class);
    private RxUilt mRxUilt = RxUilt.getInstance();

    private static final String TAG = "ZhihuModel";

    @Override
    public void getNews(CallBack<ZhihuInfo> callBack) {
        Log.i(TAG, "getNews: " + mZhihuService);
        mRxUilt.getDataForObservable(mZhihuService.getZhihuLeast(), callBack);
    }

    @Override
    public void refresh(CallBack<ZhihuInfo> callBack) {

    }

    @Override
    public void load(String page, CallBack<ZhihuInfo> callBack) {
        mRxUilt.getDataForObservable(mZhihuService.getZhihuBefor(page), callBack);
    }
}

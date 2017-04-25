package tmnt.example.onedaily.ui.zhihu.model;

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
    private Api mApi = Api.getInstance(Common.ZHIHU_URL);
    private ZhihuService mZhihuService = mApi.getCall(ZhihuService.class);
    private RxUilt<ZhihuInfo>mRxUilt=new RxUilt<>();

    @Override
    public void getNews(CallBack<ZhihuInfo> callBack) {
        mRxUilt.getDataForObservable(mZhihuService.getZhihuLeast(),callBack);
    }

    @Override
    public void refresh(CallBack<ZhihuInfo> callBack) {

    }

    @Override
    public void load(String page, CallBack<ZhihuInfo> callBack) {

    }
}

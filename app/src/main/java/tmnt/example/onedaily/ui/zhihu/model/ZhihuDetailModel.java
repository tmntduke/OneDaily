package tmnt.example.onedaily.ui.zhihu.model;

import tmnt.example.onedaily.Rx.RxUilt;
import tmnt.example.onedaily.api.Api;
import tmnt.example.onedaily.api.ZhihuService;
import tmnt.example.onedaily.bean.zhihu.ZhihuDetailInfo;
import tmnt.example.onedaily.mvp.CallBack;
import tmnt.example.onedaily.mvp.Model;
import tmnt.example.onedaily.ui.common.Common;

/**
 * Created by tmnt on 2017/4/29.
 */

public class ZhihuDetailModel implements Model<ZhihuDetailInfo> {

    private Api mApi = Api.getInstance();
    private ZhihuService mZhihuService = mApi.getCall(Common.ZHIHU_URL, ZhihuService.class);
    private RxUilt mRxUilt = RxUilt.getInstance();
    private String id;

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public void getNews(CallBack<ZhihuDetailInfo> callBack) {
        mRxUilt.getDataForObservable(mZhihuService.getZhihuDetail(id), callBack);
    }

    @Override
    public void refresh(CallBack<ZhihuDetailInfo> callBack) {

    }

    @Override
    public void load(String page, CallBack<ZhihuDetailInfo> callBack) {

    }
}

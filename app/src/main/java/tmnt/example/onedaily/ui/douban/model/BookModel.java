package tmnt.example.onedaily.ui.douban.model;

import rx.Observable;
import tmnt.example.onedaily.Rx.RxUilt;
import tmnt.example.onedaily.api.Api;
import tmnt.example.onedaily.api.DoubanService;
import tmnt.example.onedaily.bean.book.DoubanBookInfo;
import tmnt.example.onedaily.mvp.CallBack;
import tmnt.example.onedaily.mvp.Model;
import tmnt.example.onedaily.util.Common;

/**
 * Created by tmnt on 2017/4/17.
 */

public class BookModel implements Model<DoubanBookInfo> {

    private static final String PAGE_INT = "0";
    private static final String PAGE_SIZE = "15";

    private Api mApi = Api.getInstance(Common.DOUBAN_URL);
    private DoubanService mDoubanService = mApi.getCall(DoubanService.class);

    private RxUilt<DoubanBookInfo> mRxUilt = new RxUilt<>();

    private String q;

    public void setQ(String q) {
        this.q = q;
    }

    @Override
    public void getNews(CallBack<DoubanBookInfo> callBack) {
        mRxUilt.getDataForObservable(mDoubanService.getBook(q, PAGE_INT, PAGE_SIZE),callBack);
    }

    @Override
    public void refresh(CallBack<DoubanBookInfo> callBack) {

    }

    @Override
    public void load(String page, CallBack<DoubanBookInfo> callBack) {
        mRxUilt.getDataForObservable(mDoubanService.getBook(q, page, PAGE_SIZE),callBack);
    }
}

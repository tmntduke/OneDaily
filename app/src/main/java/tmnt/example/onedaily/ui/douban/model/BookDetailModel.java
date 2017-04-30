package tmnt.example.onedaily.ui.douban.model;

import tmnt.example.onedaily.Rx.RxUilt;
import tmnt.example.onedaily.api.Api;
import tmnt.example.onedaily.api.DoubanService;
import tmnt.example.onedaily.bean.book.Book;
import tmnt.example.onedaily.bean.book.DoubanBookInfo;
import tmnt.example.onedaily.mvp.CallBack;
import tmnt.example.onedaily.mvp.Model;
import tmnt.example.onedaily.util.Common;

/**
 * Created by tmnt on 2017/4/22.
 */

public class BookDetailModel implements Model<Book> {

    private Api mApi=Api.getInstance();
    private DoubanService mDoubanService = mApi.getCall(Common.DOUBAN_URL,DoubanService.class);
    private RxUilt mRxUilt = RxUilt.getInstance();

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void getNews(CallBack<Book> callBack) {
         mRxUilt.getDataForObservable(mDoubanService.getBookForIsbn(name),callBack);
    }

    @Override
    public void refresh(CallBack<Book> callBack) {

    }

    @Override
    public void load(String page, CallBack<Book> callBack) {

    }
}

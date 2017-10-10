package tmnt.example.onedaily.ui.douban.presenter;

import android.util.Log;

import tmnt.example.onedaily.bean.book.Book;
import tmnt.example.onedaily.mvp.BasePresenter;
import tmnt.example.onedaily.mvp.CallBack;
import tmnt.example.onedaily.mvp.Model;
import tmnt.example.onedaily.mvp.View;

/**
 * Created by tmnt on 2017/4/22.
 */

public class BookDetailPresent extends BasePresenter<Book> {

    private static final String TAG = "BookDetailPresent";

    public BookDetailPresent(Model<Book> model, View<Book> view) {
        super(model, view);
    }

    @Override
    public void handleData() {
        mModel.getNews(new CallBack<Book>() {
            @Override
            public void onSuccess(Book book) {
                mView.showData(book);
            }

            @Override
            public void onError(Throwable e) {
                mView.showError(e);
            }
        });
    }

    @Override
    public void handleLoad(String page) {

    }

    @Override
    public void handleRefresh(String page) {

    }

    @Override
    public void cancel() {

    }
}

package tmnt.example.onedaily.ui.douban.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.zxing.client.android.decode.CaptureActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import butterknife.Bind;
import butterknife.ButterKnife;
import tmnt.example.onedaily.R;
import tmnt.example.onedaily.Rx.RxUilt;
import tmnt.example.onedaily.bean.book.Book;
import tmnt.example.onedaily.bean.book.DoubanBookInfo;
import tmnt.example.onedaily.db.OneDailyDB;
import tmnt.example.onedaily.mvp.CallBack;
import tmnt.example.onedaily.mvp.View;
import tmnt.example.onedaily.ui.common.BaseActivity;
import tmnt.example.onedaily.ui.douban.adapter.BookHistoryAdapter;
import tmnt.example.onedaily.ui.douban.adapter.BookSearchAdapter;
import tmnt.example.onedaily.ui.douban.fragment.BookFragment;
import tmnt.example.onedaily.ui.douban.model.BookDetailModel;
import tmnt.example.onedaily.ui.douban.model.BookModel;
import tmnt.example.onedaily.ui.douban.presenter.BookPresenter;
import tmnt.example.onedaily.util.BookApiUtils;
import tmnt.example.onedaily.util.DistinctUtils;
import tmnt.example.onedaily.util.DividerItemDecoration;
import tmnt.example.onedaily.weight.ClearEditText.ClearEditText;
import tmnt.example.onedaily.weight.Lable.LabelView;

/**
 * 搜索图书
 * Created by tmnt on 2017/4/21.
 */

public class BookSearchActivity extends BaseActivity implements View<DoubanBookInfo> {

    @Bind(R.id.ed_search)
    ClearEditText mEdSearch;
    @Bind(R.id.tv_cancel)
    TextView mTvCancel;
    @Bind(R.id.lv_book)
    LabelView mLvBook;
    @Bind(R.id.tv_change)
    TextView mTvChange;
    @Bind(R.id.search_contain)
    LinearLayout mSearchContain;
    @Bind(R.id.rv_book_search)
    RecyclerView mRvBookSearch;
    @Bind(R.id.lv_search_history)
    ListView mLvSearchHistory;
    @Bind(R.id.tv_clear)
    TextView mTvClear;

    private static final int REQUEST_ZXING = 0010;
    private static final String TAG = "BookSearchActivity";
    public static final String BOOK_ID = "id";
    public static final String BOOK = "book";
    public static final String BOOK_ISBN = "isbn";

    private Random mRandom;
    private RxUilt mRxUilt;
    private BookSearchAdapter mSearchAdapter;
    private List<Book> mBooks;
    private BookModel model;
    private BookPresenter presenter;
    private BookHistoryAdapter mBookHistoryAdapter;
    private List<String> mHistory;
    private OneDailyDB mOneDailyDB;

    @Override
    public void initData(Bundle savedInstanceState) {
        mRandom = new Random();
        mBooks = new ArrayList<>();
        mRxUilt = RxUilt.getInstance();
        model = new BookModel();
        presenter = new BookPresenter(model, BookSearchActivity.this);
        mHistory = new ArrayList<>();
        mOneDailyDB = OneDailyDB.newInstance(getApplicationContext());
        mOneDailyDB.queryHistory(new CallBack<List<String>>() {
            @Override
            public void onSuccess(List<String> list) {
                mHistory.clear();
                mHistory.addAll(DistinctUtils.distinct(list));
                mBookHistoryAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Throwable e) {

            }
        });

    }

    @Override
    public void initView() {
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
    }

    @Override
    public void initOperation() {
        mEdSearch.setOnScanLisenter(view ->
                toActivityForResult(CaptureActivity.class, REQUEST_ZXING)
        );

        mLvBook.setLabelBackgroundResource(R.drawable.label_bg);

        changLable(mLvBook);

        mLvBook.setOnLabelListener((v, position) -> {
            model.setQ(((TextView) v).getText().toString());
            presenter.setModel(model);
            presenter.handleData();
            hideSoftInput();
        });

        mTvCancel.setOnClickListener(v -> onBackPressed());

        mTvChange.setOnClickListener(v -> changLable(mLvBook));

        mEdSearch.setOnKeyListener((v, keyCode, event) -> {
            if (keyCode == KeyEvent.KEYCODE_ENTER) {
                Log.i(TAG, "initOperation: count");
                requestSearch();
                hideSoftInput();
                saveHistory(mEdSearch.getText().toString());
                return true;
            }
            return false;
        });

        mSearchAdapter = new BookSearchAdapter(this, mBooks);
        mRvBookSearch.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        mRvBookSearch.addItemDecoration(new DividerItemDecoration(this
                , DividerItemDecoration.VERTICAL_LIST));
        mRvBookSearch.setAdapter(mSearchAdapter);

        mSearchAdapter.setOnItemClickListener((view, position) -> {
            Bundle bundle = new Bundle();
            bundle.putString(BookFragment.BOOK_ID, "id");
            bundle.putParcelable(BOOK, mBooks.get(position));
            toActivity(BookDetailActivity.class, bundle);
        });

        mBookHistoryAdapter = new BookHistoryAdapter(this, mHistory);
        mLvSearchHistory.setAdapter(mBookHistoryAdapter);

        mLvSearchHistory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, android.view.View view, int position, long id) {
                model.setQ(mHistory.get(position));
                presenter.handleData();
            }
        });

        mTvClear.setOnClickListener(v -> {
            mHistory.clear();
            mBookHistoryAdapter.notifyDataSetChanged();
            mOneDailyDB.clearHistory();
        });

    }

    private void saveHistory(String s) {
        mOneDailyDB.insertHistory(s);
    }

    /**
     * 点击enter后进行搜索
     */
    private void requestSearch() {
        model.setQ(mEdSearch.getText().toString());
        presenter.setModel(model);
        presenter.handleData();

    }

    @Override
    public void loadData() {

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        mBookHistoryAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) { //RESULT_OK = -1
            Bundle bundle = data.getExtras();
            String scanResult = bundle.getString("result");
            Log.i(TAG, "onActivityResult: " + scanResult);
            Bundle isbn = new Bundle();
            isbn.putString(BookFragment.BOOK_ID, BookDetailModel.ISBN_TYPE);
            isbn.putString(BOOK_ISBN, scanResult);
            toActivity(BookDetailActivity.class, isbn);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mOneDailyDB != null) {
            mOneDailyDB.closeDB();
            mOneDailyDB = null;
        }

        if (mEdSearch != null) {
            mEdSearch = null;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    //隐藏软键盘
    private void hideSoftInput() {
        android.view.View view = getWindow().peekDecorView();
        if (view != null) {
            InputMethodManager inputmanger = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private void changLable(LabelView view) {
        mRxUilt.distinctForData(BookApiUtils.getRandomLable(mRandom.nextInt(4))
                , new CallBack<List<String>>() {
                    @Override
                    public void onSuccess(List<String> strings) {
                        view.setLable(strings);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    @Override
    public void showData(DoubanBookInfo datas) {
        mBooks.clear();
        mBooks.addAll(datas.getBooks());
        mSearchContain.setVisibility(android.view.View.GONE);
        mSearchAdapter.notifyDataSetChanged();
        mRvBookSearch.setVisibility(android.view.View.VISIBLE);

    }

    @Override
    public void showLoadData(DoubanBookInfo datas) {

    }

    @Override
    public void showRefreshData(DoubanBookInfo datas) {

    }

    @Override
    public void showError(Throwable throwable) {

    }

}

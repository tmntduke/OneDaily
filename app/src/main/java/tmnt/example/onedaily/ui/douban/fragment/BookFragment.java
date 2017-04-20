package tmnt.example.onedaily.ui.douban.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import tmnt.example.onedaily.R;
import tmnt.example.onedaily.bean.book.Book;
import tmnt.example.onedaily.bean.book.DoubanBookInfo;
import tmnt.example.onedaily.ui.common.BaseFragment;
import tmnt.example.onedaily.ui.douban.adapter.BookAdapter;
import tmnt.example.onedaily.ui.douban.listener.OnBookItenListener;
import tmnt.example.onedaily.ui.douban.model.BookModel;
import tmnt.example.onedaily.ui.douban.presenter.BookPresenter;
import tmnt.example.onedaily.util.DividerItemDecoration;
import tmnt.example.onedaily.weight.Refresh.SmartPullableLayout;
import tmnt.example.onedaily.weight.WaveView.WaveLoadingView;

/**
 * Created by tmnt on 2017/4/18.
 */

public class BookFragment extends BaseFragment implements tmnt.example.onedaily.mvp.View<DoubanBookInfo> {

    @Bind(R.id.list_book)
    RecyclerView mListBook;
    @Bind(R.id.spl_book)
    SmartPullableLayout mSplBook;
    @Bind(R.id.img_book_empty)
    ImageView mImgBookEmpty;

    private View mView;

    private String category;
    private List<Book> mBookList;
    private BookAdapter mBookAdapter;
    private BookPresenter presenter;

    private static final String BOOK_CATEGORY = "BOOK_CATEGORY";

    private static final String TAG = "BookFragment";

    private int page;

    @Override
    protected View setContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_book, container, false);
        ButterKnife.bind(this, mView);
        return mView;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        category = getArguments().getString(BOOK_CATEGORY);
        mBookList = new ArrayList<>();

        BookModel model = new BookModel(category);
        presenter = new BookPresenter(model, this);

        presenter.handleData();
    }

    @Override
    public void initView() {

    }

    @Override
    public void initOperation() {

        Log.i(TAG, "initOperation: ");

        mListBook.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
        mBookAdapter = new BookAdapter(mBookList, getActivity());
        mListBook.setAdapter(mBookAdapter);

        mListBook.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));

        mBookAdapter.setOnBookItenListener(new OnBookItenListener() {
            @Override
            public void onBookItem(View view, int position) {

            }
        });

        mSplBook.setOnPullListener(new SmartPullableLayout.OnPullListener() {
            @Override
            public void onPullDown() {

            }

            @Override
            public void onPullUp() {
                page++;
                presenter.handleLoad(String.valueOf(page));
            }
        });

    }

    @Override
    public void loadData() {

    }

    @Override
    public void showData(DoubanBookInfo datas) {
        Log.i(TAG, "showData: " + datas.getBooks());
        mBookList.addAll(datas.getBooks());
        mBookAdapter.notifyDataSetChanged();
    }

    @Override
    public void showLoadData(DoubanBookInfo datas) {
        mBookList.addAll(datas.getBooks());
        mSplBook.stopPullBehavior();
        mBookAdapter.notifyDataSetChanged();
    }

    @Override
    public void showRefreshData(DoubanBookInfo datas) {

    }

    @Override
    public void showError(Throwable throwable) {
        mListBook.setVisibility(View.GONE);
        mImgBookEmpty.setVisibility(View.VISIBLE);
    }

    /**
     * 获取fragment实例
     *
     * @return
     */
    public static Fragment getInstance(String category) {
        BookFragment fragment = new BookFragment();
        Bundle bundle = new Bundle();
        bundle.putString(BOOK_CATEGORY, category);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

}

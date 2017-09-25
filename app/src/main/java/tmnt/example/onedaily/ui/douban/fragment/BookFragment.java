package tmnt.example.onedaily.ui.douban.fragment;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import tmnt.example.onedaily.R;
import tmnt.example.onedaily.bean.book.Book;
import tmnt.example.onedaily.bean.book.DoubanBookInfo;
import tmnt.example.onedaily.ui.common.BaseFragment;
import tmnt.example.onedaily.ui.douban.activity.BookDetailActivity;
import tmnt.example.onedaily.ui.douban.activity.BookSearchActivity;
import tmnt.example.onedaily.ui.douban.adapter.BookAdapter;
import tmnt.example.onedaily.ui.douban.listener.OnBookItenListener;
import tmnt.example.onedaily.ui.douban.listener.OnBookRetrunListener;
import tmnt.example.onedaily.ui.douban.model.BookModel;
import tmnt.example.onedaily.ui.douban.presenter.BookPresenter;
import tmnt.example.onedaily.ui.main.activity.MainActivity;
import tmnt.example.onedaily.util.DividerItemDecoration;
import tmnt.example.onedaily.weight.Refresh.SmartPullableLayout;

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
    @Bind(R.id.tv_book_empty_refresh)
    TextView mTvBookEmptyRefresh;
    @Bind(R.id.book_empty)
    LinearLayout mBookEmpty;
    @Bind(R.id.douban_loading)
    ImageView mDoubanLoading;

    private View mView;
    private String category;
    private List<Book> mBookList;
    private BookAdapter mBookAdapter;
    private BookPresenter presenter;
    private AnimationDrawable animation;

    private static final String BOOK_CATEGORY = "BOOK_CATEGORY";

    public static final String BOOK_ID = "id";

    private static final String TAG = "BookFragment";
    public static final String BOOK_INFO = "info";

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

        BookModel model = new BookModel();
        model.setQ(category);
        presenter = new BookPresenter(model, this);


    }

    @Override
    public void initView() {

    }

    @Override
    public void initOperation() {

        mListBook.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
        mBookAdapter = new BookAdapter(mBookList, getActivity());
        mListBook.setAdapter(mBookAdapter);

        mListBook.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));

        mBookAdapter.setOnBookItenListener(new OnBookItenListener() {
            @Override
            public void onBookItem(View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString(BOOK_ID, "id");
                bundle.putParcelable(BOOK_INFO, mBookList.get(position));
                toActivity(BookDetailActivity.class, bundle);
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

        mBookAdapter.setOnBookItenListener((view, position) -> {
            Bundle bundle = new Bundle();
            bundle.putString(BOOK_ID, "id");
            bundle.putParcelable(BookSearchActivity.BOOK, mBookList.get(position));
            toActivity(BookDetailActivity.class, bundle);
        });

        MainActivity.setOnBookRetrunListener(new OnBookRetrunListener() {
            @Override
            public void onReturn() {
                mListBook.smoothScrollToPosition(0);
            }
        });
    }

    @Override
    public void loadData() {
        mDoubanLoading.setVisibility(View.VISIBLE);
        animation = (AnimationDrawable) mDoubanLoading.getBackground();
        animation.start();
        presenter.handleData();
    }

    @Override
    public void showData(DoubanBookInfo datas) {
        Log.i(TAG, "showData: " + datas.getBooks());
        mBookList.addAll(datas.getBooks());
        mDoubanLoading.setVisibility(View.GONE);
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
        Log.i(TAG, "showError: " + throwable.toString());
        mBookEmpty.setVisibility(View.GONE);
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
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: start");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(TAG, "onStop: start");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i(TAG, "onDestroyView: start");
        ButterKnife.unbind(this);
        if (mBookAdapter != null) {
            mBookAdapter = null;
        }
        if (animation != null) {
            animation.stop();
            animation = null;
        }

        if (presenter != null) {
            presenter.cancel();
            Log.i(TAG, "onDestroyView: per");
        }
    }
}

package tmnt.example.onedaily.ui.douban.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import tmnt.example.onedaily.R;
import tmnt.example.onedaily.bean.book.Book;
import tmnt.example.onedaily.ui.common.BaseFragment;

/**
 * Created by tmnt on 2017/4/18.
 */

public class BookFragment extends BaseFragment implements tmnt.example.onedaily.mvp.View<Book> {

    @Bind(R.id.list_book)
    RecyclerView mListBook;
    private View mView;

    private String category;

    private static final String BOOK_CATEGORY = "BOOK_CATEGORY";

    @Override
    protected View setContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_book, container, false);
        ButterKnife.bind(this, mView);
        return mView;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        category = getArguments().getString(BOOK_CATEGORY);
    }

    @Override
    public void initView() {

    }

    @Override
    public void initOperation() {

        mListBook.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));


    }

    @Override
    public void loadData() {

    }

    @Override
    public void showData(List<Book> datas) {

    }

    @Override
    public void showLoadData(List<Book> datas) {

    }

    @Override
    public void showRefreshData(List<Book> datas) {

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

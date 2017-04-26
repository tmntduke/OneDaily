package tmnt.example.onedaily.ui.douban.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
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
import tmnt.example.onedaily.ui.common.BaseActivity;
import tmnt.example.onedaily.ui.common.BaseFragment;
import tmnt.example.onedaily.ui.douban.activity.BookSearchActivity;
import tmnt.example.onedaily.ui.douban.adapter.BookPagerAdapter;
import tmnt.example.onedaily.ui.douban.listener.OnBookRetrunListener;

/**
 * Created by tmnt on 2017/4/18.
 */

public class BookPageFragment extends BaseFragment implements tmnt.example.onedaily.mvp.View<Book> {

    @Bind(R.id.img_search)
    ImageView mImgSearch;
    @Bind(R.id.tab_category)
    TabLayout mTabCategory;
    @Bind(R.id.img_add)
    ImageView mImgAdd;
    @Bind(R.id.vp_show)
    ViewPager mVpShow;
    private View mView;

    @Override
    protected View setContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_book_page, container, false);
        ButterKnife.bind(this, mView);
        return mView;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        //((BaseActivity) getActivity()).setStatesBar(R.color.colorPrimary);

    }

    @Override
    public void initView() {

    }

    @Override
    public void initOperation() {

        mTabCategory.addTab(mTabCategory.newTab().setText("综合"));
        mTabCategory.addTab(mTabCategory.newTab().setText("文学"));
        mTabCategory.addTab(mTabCategory.newTab().setText("流行"));
        mTabCategory.addTab(mTabCategory.newTab().setText("生活"));


        FragmentManager manager = getChildFragmentManager();
        BookPagerAdapter myAdapter = new BookPagerAdapter(manager, categoryList());
        mVpShow.setAdapter(myAdapter);
        mTabCategory.setupWithViewPager(mVpShow);
        mTabCategory.setTabsFromPagerAdapter(myAdapter);
        mTabCategory.setTabMode(TabLayout.MODE_FIXED);

        mImgAdd.setOnClickListener(v -> {

        });

        mImgSearch.setOnClickListener(v -> {
            toActivity(BookSearchActivity.class);
        });

    }

    @Override
    public void loadData() {

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    /**
     * 获取fragment实例
     *
     * @return
     */
    public static Fragment getInstance() {
        BookPageFragment fragment = new BookPageFragment();
        return fragment;
    }

    private List<String> categoryList() {
        List<String> list = new ArrayList<>();
        list.add("综合");
        list.add("文学");
        list.add("文化");
        list.add("生活");
        return list;
    }


    @Override
    public void showData(Book datas) {

    }

    @Override
    public void showLoadData(Book datas) {

    }

    @Override
    public void showRefreshData(Book datas) {

    }

    @Override
    public void showError(Throwable throwable) {

    }
}

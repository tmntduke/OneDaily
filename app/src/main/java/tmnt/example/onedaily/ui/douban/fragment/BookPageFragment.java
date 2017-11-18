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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import tmnt.example.onedaily.R;
import tmnt.example.onedaily.annotation.ContentView;
import tmnt.example.onedaily.bean.book.Book;
import tmnt.example.onedaily.ui.common.BaseActivity;
import tmnt.example.onedaily.ui.common.BaseFragment;
import tmnt.example.onedaily.ui.douban.activity.BookCategoryActivity;
import tmnt.example.onedaily.ui.douban.activity.BookDetailActivity;
import tmnt.example.onedaily.ui.douban.activity.BookSearchActivity;
import tmnt.example.onedaily.ui.douban.adapter.BookPagerAdapter;
import tmnt.example.onedaily.ui.douban.listener.OnBookRetrunListener;
import tmnt.example.onedaily.util.SharedPreferencesUtil;
import tmnt.example.onedaily.util.TabUtils;

import static android.R.id.list;

/**
 * Created by tmnt on 2017/4/18.
 */

@ContentView(R.layout.fragment_book_page)
public class BookPageFragment extends BaseFragment implements tmnt.example.onedaily.mvp.View<Book> {

    @Bind(R.id.img_search)
    ImageView mImgSearch;
    @Bind(R.id.tab_category)
    TabLayout mTabCategory;
    @Bind(R.id.img_add)
    ImageView mImgAdd;
    @Bind(R.id.vp_show)
    ViewPager mVpShow;

    private SharedPreferencesUtil mSharedPreferencesUtil;
    private List<String> cateList;
    public static String CATEGORY_LIST = "category_list";

    @Override
    public void initData(Bundle savedInstanceState) {
        mSharedPreferencesUtil = SharedPreferencesUtil.getInstance(getActivity());
    }

    @Override
    public void initView() {

    }

    @Override
    public void initOperation() {
        //createBookPage();
        mImgAdd.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putStringArrayList(CATEGORY_LIST, (ArrayList<String>) cateList);
            toActivity(BookCategoryActivity.class, bundle);
        });

        mImgSearch.setOnClickListener(v -> {
            toActivity(BookSearchActivity.class);
        });

    }

    private void createBookPage() {
        cateList = categoryList();
        for (String s : cateList) {
            mTabCategory.addTab(mTabCategory.newTab().setText(s));
        }

        FragmentManager manager = getChildFragmentManager();
        BookPagerAdapter myAdapter = new BookPagerAdapter(manager, cateList);
        mVpShow.setAdapter(myAdapter);
        mTabCategory.setupWithViewPager(mVpShow);
        // mTabCategory.setTabsFromPagerAdapter(myAdapter);
        TabUtils.dynamicSetTabLayoutMode(mTabCategory);
    }

    @Override
    public void loadData() {

    }

    @Override
    public void onStart() {
        super.onStart();
        createBookPage();
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
        List<String> list = null;
        if (mSharedPreferencesUtil.getData(BookCategoryActivity.BOOK_CATEGORY) == null) {
            list = new ArrayList<>();
            list.add("综合");
            list.add("文学");
            list.add("文化");
            list.add("生活");
            saveCategoryToShares(list);
        } else {
            list = getCategoryToShares();
        }

        return list;
    }

    private void saveCategoryToShares(List<String> list) {
        String categoryJson = new Gson().toJson(list);
        mSharedPreferencesUtil.putData(BookCategoryActivity.BOOK_CATEGORY, categoryJson);
    }

    private List<String> getCategoryToShares() {
        List<String> list = new Gson().fromJson((String) mSharedPreferencesUtil.getData(BookCategoryActivity.BOOK_CATEGORY)
                , new TypeToken<List<String>>() {
                }.getType());
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

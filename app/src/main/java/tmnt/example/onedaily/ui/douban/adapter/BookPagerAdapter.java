package tmnt.example.onedaily.ui.douban.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;


import java.util.Arrays;
import java.util.List;

import tmnt.example.onedaily.bean.book.Book;
import tmnt.example.onedaily.ui.douban.fragment.BookFragment;
import tmnt.example.onedaily.util.BookApiUtils;

/**
 * 资讯页面滑动适配器
 * Created by tmnt on 2016/12/1.
 */
public class BookPagerAdapter extends FragmentPagerAdapter {

    private static final String TAG = "BookPagerAdapter";

    private List<String> category;


    public BookPagerAdapter(FragmentManager fm, List<String> category) {
        super(fm);
        this.category = category;

    }

    @Override
    public Fragment getItem(int position) {
        String[] tags = BookApiUtils.getApiTag(position);
        String t = BookApiUtils.getRandomTAG(Arrays.asList(tags));
        return BookFragment.getInstance(t);
    }

    @Override
    public int getCount() {
        return category.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return category.get(position);
    }
}

package tmnt.example.onedaily.ui.douban.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import tmnt.example.onedaily.R;
import tmnt.example.onedaily.annotation.ContentView;
import tmnt.example.onedaily.ui.common.BaseActivity;

/**
 * 图书目录
 * Created by tmnt on 2017/4/22.
 */

@ContentView(R.layout.activity_book_catalog)
public class BookCatalogActivity extends BaseActivity {
    @Bind(R.id.tv_catalog_all)
    TextView mTvCatalogAll;
    private Intent mIntent;
    private String catalog;

    @Override
    public void initData(Bundle savedInstanceState) {
        mIntent = getIntent();
        catalog = mIntent.getStringExtra(BookDetailActivity.BOOK_CATALOG);
    }

    @Override
    public void initView() {
    }

    @Override
    public void initOperation() {
        mTvCatalogAll.setText(catalog);
    }

    @Override
    public void loadData() {

    }

}

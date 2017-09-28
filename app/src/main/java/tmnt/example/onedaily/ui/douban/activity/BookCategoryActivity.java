package tmnt.example.onedaily.ui.douban.activity;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import tmnt.example.onedaily.R;
import tmnt.example.onedaily.ui.common.BaseActivity;
import tmnt.example.onedaily.ui.douban.adapter.BookCategoryAdapter;
import tmnt.example.onedaily.ui.douban.adapter.ItemDragHelperCallback;
import tmnt.example.onedaily.ui.douban.fragment.BookPageFragment;
import tmnt.example.onedaily.ui.douban.listener.OnTextClickLlistener;
import tmnt.example.onedaily.util.BookApiUtils;
import tmnt.example.onedaily.util.SharedPreferencesUtil;

/**
 * Created by tmnt on 2017/9/28.
 */

public class BookCategoryActivity extends BaseActivity {

    public static String BOOK_CATEGORY = "book_category";
    @Bind(R.id.btnBack)
    Button mBtnBack;
    @Bind(R.id.btn_save)
    TextView mBtnSave;
    @Bind(R.id.lyNav)
    RelativeLayout mLyNav;
    @Bind(R.id.rv_my_cate)
    RecyclerView mRvMyCate;
    @Bind(R.id.rv_more_cate)
    RecyclerView mRvMoreCate;

    private BookCategoryAdapter mMyAdapter;
    private BookCategoryAdapter mMoreAdapter;
    private List<String> my = new ArrayList<>();
    private List<String> more = new ArrayList<>();
    private SharedPreferencesUtil mSharedPreferencesUtil;

    @Override
    public void initData(Bundle savedInstanceState) {
        ArrayList<String> list = getIntent().getStringArrayListExtra(BookPageFragment.CATEGORY_LIST);
        my.clear();
        more.clear();
        my.addAll(list);
        more.addAll(createMoreList());
        mSharedPreferencesUtil = SharedPreferencesUtil.getInstance(this);
    }

    @Override
    public void initView() {
        setContentView(R.layout.activity_book_category);
        ButterKnife.bind(this);
    }

    @Override
    public void initOperation() {
        mMyAdapter = new BookCategoryAdapter(this, my, true);
        mMoreAdapter = new BookCategoryAdapter(this, more, false);

        mRvMyCate.setLayoutManager(new GridLayoutManager(this, 4, LinearLayoutManager.VERTICAL, false));
        mRvMyCate.setItemAnimator(new DefaultItemAnimator());
        mRvMyCate.setAdapter(mMyAdapter);


        mRvMoreCate.setLayoutManager(new GridLayoutManager(this, 4, LinearLayoutManager.VERTICAL, false));
        mRvMoreCate.setItemAnimator(new DefaultItemAnimator());
        mRvMoreCate.setAdapter(mMoreAdapter);

        initItemDragHelper();

        mMyAdapter.setOnTextClickLlistener(new OnTextClickLlistener() {
            @Override
            public void onClick(View v, int position) {
                more.add(my.get(position));
                my.remove(position);
                notifity();
            }

            @Override
            public void onLongClick(View v, int position) {

            }
        });
        mMoreAdapter.setOnTextClickLlistener(new OnTextClickLlistener() {
            @Override
            public void onClick(View v, int position) {
                if (my.size() >= 8) {
                    Toast.makeText(BookCategoryActivity.this, getString(R.string.more_toast), Toast.LENGTH_SHORT).show();
                } else {
                    my.add(more.get(position));
                    more.remove(position);
                    notifity();
                }

            }

            @Override
            public void onLongClick(View v, int position) {

            }
        });

        mBtnBack.setOnClickListener(v -> onBackPressed());
    }

    @Override
    public void loadData() {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        saveCate();
        finish();
    }

    private void saveCate() {
        String categoryJson = new Gson().toJson(my);
        mSharedPreferencesUtil.putData(BookCategoryActivity.BOOK_CATEGORY, categoryJson);
    }

    private void initItemDragHelper() {
        ItemDragHelperCallback itemDragHelperCallback = new ItemDragHelperCallback(mMyAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemDragHelperCallback);
        itemTouchHelper.attachToRecyclerView(mRvMyCate);
        mMyAdapter.setItemDragHelperCallback(itemDragHelperCallback);
    }

    private void notifity() {
        mMyAdapter.notifyDataSetChanged();
        mMoreAdapter.notifyDataSetChanged();
    }

    private List<String> createMoreList() {
        List<String> list = Arrays.asList(BookApiUtils.PopularTag);
        List<String> cates = new ArrayList<>();
        cates.addAll(list);
        if (my != null && my.size() != 0)
            for (String s : my) {
                if (cates.contains(s)) {
                    cates.remove(s);
                }
            }
        return cates;
    }

}

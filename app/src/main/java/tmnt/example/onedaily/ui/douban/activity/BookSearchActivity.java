package tmnt.example.onedaily.ui.douban.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.zxing.client.android.decode.CaptureActivity;

import java.util.List;
import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;
import tmnt.example.onedaily.R;
import tmnt.example.onedaily.Rx.RxUilt;
import tmnt.example.onedaily.bean.book.DoubanBookInfo;
import tmnt.example.onedaily.mvp.CallBack;
import tmnt.example.onedaily.mvp.View;
import tmnt.example.onedaily.ui.common.BaseActivity;
import tmnt.example.onedaily.util.BookApiUtils;
import tmnt.example.onedaily.util.SystemUtils;
import tmnt.example.onedaily.weight.ClearEditText.ClearEditText;
import tmnt.example.onedaily.weight.Lable.LabelView;

/**
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
    @Bind(R.id.search_list_contain)
    LinearLayout mSearchListContain;
    @Bind(R.id.rv_book_search)
    RecyclerView mRvBookSearch;

    private static final int REQUEST_ZXING = 0010;
    private static final String TAG = "BookSearchActivity";

    private Random mRandom;

    @Override
    public void initData(Bundle savedInstanceState) {
        mRandom = new Random();
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

        mTvCancel.setOnClickListener(v -> onBackPressed());

        mTvChange.setOnClickListener(v -> changLable(mLvBook));

        mEdSearch.setOnKeyListener((v, keyCode, event) -> {
            if (keyCode == KeyEvent.KEYCODE_ENTER) {
                SystemUtils.showToast(this, "done");
                hideSoftInput();
                return true;
            }
            return false;
        });

    }

    @Override
    public void loadData() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) { //RESULT_OK = -1
            Bundle bundle = data.getExtras();
            String scanResult = bundle.getString("result");
            Log.i(TAG, "onActivityResult: " + scanResult);
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
        new RxUilt<String>().distinctForData(BookApiUtils.getRandomLable(mRandom.nextInt(4))
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

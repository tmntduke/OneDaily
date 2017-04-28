package tmnt.example.onedaily.ui.zhihu.activity;

import android.content.Intent;
import android.os.Bundle;

import tmnt.example.onedaily.bean.zhihu.ZhihuDetailInfo;
import tmnt.example.onedaily.ui.common.BaseActivity;
import tmnt.example.onedaily.ui.zhihu.fragment.ZhihuFregment;

/**
 * Created by tmnt on 2017/4/28.
 */

public class ZhihuDetailActivity extends BaseActivity implements tmnt.example.onedaily.mvp.View<ZhihuDetailInfo> {
    private Intent mIntent;
    private String zhihuId;

    @Override
    public void initData(Bundle savedInstanceState) {
        mIntent = getIntent();
        zhihuId = mIntent.getStringExtra(ZhihuFregment.ZHIHU_ID);
    }

    @Override
    public void initView() {

    }

    @Override
    public void initOperation() {

    }

    @Override
    public void loadData() {

    }

    @Override
    public void showData(ZhihuDetailInfo datas) {

    }

    @Override
    public void showLoadData(ZhihuDetailInfo datas) {

    }

    @Override
    public void showRefreshData(ZhihuDetailInfo datas) {

    }

    @Override
    public void showError(Throwable throwable) {

    }
}

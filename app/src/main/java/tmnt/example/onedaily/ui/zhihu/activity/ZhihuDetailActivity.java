package tmnt.example.onedaily.ui.zhihu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.Bind;
import butterknife.ButterKnife;
import tmnt.example.onedaily.R;
import tmnt.example.onedaily.bean.zhihu.ZhihuDetailInfo;
import tmnt.example.onedaily.mvp.View;
import tmnt.example.onedaily.ui.common.BaseActivity;
import tmnt.example.onedaily.ui.zhihu.fragment.ZhihuFregment;
import tmnt.example.onedaily.ui.zhihu.model.ZhihuDetailModel;
import tmnt.example.onedaily.ui.zhihu.presenter.ZhihuDetailPresenter;
import tmnt.example.onedaily.util.HtmlUtil;

/**
 * Created by tmnt on 2017/4/28.
 */

public class ZhihuDetailActivity extends BaseActivity implements View<ZhihuDetailInfo> {
    @Bind(R.id.img_zhihu_detail_cover)
    ImageView mImgZhihuDetailCover;
    @Bind(R.id.tv_zhihu_detail_msg)
    TextView mTvZhihuDetailMsg;
    @Bind(R.id.wv_zhihu_detail_body)
    WebView mWvZhihuDetailBody;
    @Bind(R.id.tv_zhihu_detail_title)
    TextView mTvZhihuDetailCate;
    @Bind(R.id.img_zhihu_detail_empty)
    ImageView mImgZhihuDetailEmpty;
    @Bind(R.id.sv_zhihu_detail)
    ScrollView mSvZhihuDetail;

    private Intent mIntent;
    private String zhihuId;
    private String zhihuTitle;
    private ZhihuDetailModel mModel;
    private ZhihuDetailPresenter mPresenter;

    @Override
    public void initData(Bundle savedInstanceState) {
        mIntent = getIntent();
        zhihuId = mIntent.getStringExtra(ZhihuFregment.ZHIHU_ID);
        zhihuTitle = mIntent.getStringExtra(ZhihuFregment.ZHIHU_TITLE);
        mModel = new ZhihuDetailModel();
        mModel.setId(zhihuId);
        mPresenter = new ZhihuDetailPresenter(mModel, this);
        mPresenter.handleData();
    }

    @Override
    public void initView() {
        setContentView(R.layout.activity_zhihu_detail);
        ButterKnife.bind(this);

    }

    @Override
    public void initOperation() {

        WebSettings settings = mWvZhihuDetailBody.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDefaultTextEncodingName("utf-8");
        settings.setAppCacheEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setLoadWithOverviewMode(true);
        mWvZhihuDetailBody.setWebViewClient(new WebViewClient() {
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
                return super.shouldInterceptRequest(view, request);
            }
        });

    }

    @Override
    public void loadData() {

    }

    @Override
    public void showData(ZhihuDetailInfo datas) {
        setData(datas);
    }

    @Override
    public void showLoadData(ZhihuDetailInfo datas) {

    }

    @Override
    public void showRefreshData(ZhihuDetailInfo datas) {

    }

    @Override
    public void showError(Throwable throwable) {
        mSvZhihuDetail.setVisibility(android.view.View.GONE);
        mImgZhihuDetailEmpty.setVisibility(android.view.View.VISIBLE);

    }

    public void setData(ZhihuDetailInfo data) {
        Glide.with(this).load(data.getImage())
                .placeholder(R.drawable.ic_moren).into(mImgZhihuDetailCover);
        mTvZhihuDetailMsg.setText(data.getImage_source());
        mTvZhihuDetailCate.setText(data.getTitle());
        mWvZhihuDetailBody.loadData(HtmlUtil.createHtmlData(data.getBody(), data.getCss(), data.getJs())
                , "text/html; charset=UTF-8", null);

    }
}

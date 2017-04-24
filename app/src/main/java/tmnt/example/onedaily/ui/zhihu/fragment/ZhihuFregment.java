package tmnt.example.onedaily.ui.zhihu.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.Bind;
import butterknife.ButterKnife;
import tmnt.example.onedaily.R;
import tmnt.example.onedaily.bean.zhihu.ZhihuInfo;
import tmnt.example.onedaily.ui.common.BaseFragment;

/**
 * Created by tmnt on 2017/4/24.
 */

public class ZhihuFregment extends BaseFragment implements tmnt.example.onedaily.mvp.View<ZhihuInfo> {

    @Bind(R.id.rv_gank)
    RecyclerView mRvGank;

    @Override
    protected View setContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_zhihu, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initData(Bundle savedInstanceState) {

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

    public static Fragment getInstance() {
        ZhihuFregment fregment = new ZhihuFregment();
        return fregment;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void showData(ZhihuInfo datas) {

    }

    @Override
    public void showLoadData(ZhihuInfo datas) {

    }

    @Override
    public void showRefreshData(ZhihuInfo datas) {

    }

    @Override
    public void showError(Throwable throwable) {

    }
}

package tmnt.example.onedaily.ui.zhihu.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import tmnt.example.onedaily.R;
import tmnt.example.onedaily.bean.zhihu.Story;
import tmnt.example.onedaily.bean.zhihu.TopStories;
import tmnt.example.onedaily.bean.zhihu.ZhihuInfo;
import tmnt.example.onedaily.ui.common.BaseFragment;
import tmnt.example.onedaily.ui.zhihu.adapter.ZhihuAdapter;
import tmnt.example.onedaily.ui.zhihu.listener.OnZhihuItemClickListener;
import tmnt.example.onedaily.ui.zhihu.model.ZhihuModel;
import tmnt.example.onedaily.ui.zhihu.presenter.ZhihuPresentor;

/**
 * Created by tmnt on 2017/4/24.
 */

public class ZhihuFregment extends BaseFragment implements tmnt.example.onedaily.mvp.View<ZhihuInfo> {

    @Bind(R.id.rv_zhihu)
    RecyclerView mRvZhihu;

    private List<TopStories> mTopStories;
    private List<Story> mStories;
    private ZhihuPresentor mZhihuPresentor;
    private ZhihuAdapter mZhihuAdapter;
    private ZhihuModel model;

    @Override
    protected View setContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_zhihu, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mTopStories = new ArrayList<>();
        mStories = new ArrayList<>();
        model = new ZhihuModel();
        mZhihuPresentor = new ZhihuPresentor(model, this);

    }

    @Override
    public void initView() {

    }

    @Override
    public void initOperation() {
        mRvZhihu.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
        mZhihuAdapter = new ZhihuAdapter(mStories, mTopStories, getActivity());
        mRvZhihu.setAdapter(mZhihuAdapter);

        mZhihuAdapter.setOnZhihuItemClickListener(new OnZhihuItemClickListener() {
            @Override
            public void onItemCardClick(View v, int position) {

            }

            @Override
            public void onItemSlideClick(View v, int position) {

            }
        });


    }

    @Override
    public void loadData() {
        mZhihuPresentor.handleData();
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
        if (datas.getTop_stories() != null) {
            mTopStories.addAll(datas.getTop_stories());
        }
        mStories.addAll(datas.getStories());
        mZhihuAdapter.notifyDataSetChanged();
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

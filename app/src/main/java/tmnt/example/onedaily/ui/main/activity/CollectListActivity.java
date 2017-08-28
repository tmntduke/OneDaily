package tmnt.example.onedaily.ui.main.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import tmnt.example.onedaily.R;
import tmnt.example.onedaily.bean.msg.Collect;
import tmnt.example.onedaily.mvp.View;
import tmnt.example.onedaily.ui.common.BaseActivity;
import tmnt.example.onedaily.ui.douban.activity.BookDetailActivity;
import tmnt.example.onedaily.ui.douban.fragment.BookFragment;
import tmnt.example.onedaily.ui.douban.model.BookDetailModel;
import tmnt.example.onedaily.ui.main.adapter.CollectAdapter;
import tmnt.example.onedaily.ui.main.listener.OnCollectItemListener;
import tmnt.example.onedaily.ui.main.model.MsgListModel;
import tmnt.example.onedaily.ui.main.presenter.MsgPresenter;

/**
 * Created by tmnt on 2017/5/12.
 */

public class CollectListActivity extends BaseActivity implements View<List<Collect>> {

    @Bind(R.id.btnBack)
    Button mBtnBack;
    @Bind(R.id.btn_save)
    TextView mBtnSave;
    @Bind(R.id.lyNav)
    RelativeLayout mLyNav;
    @Bind(R.id.rv_collect_list)
    RecyclerView mRvCollectList;
    @Bind(R.id.collect_list_empty)
    ImageView mCollectListEmpty;
    private CollectAdapter mAdapter;
    private List<Collect> mCollects;

    private MsgListModel<Collect> mCollectMsgListModel;
    private MsgPresenter<Collect> mCollectMsgPresenter;
    public static final String COLLECT_ID = "collect_id";

    @Override
    public void initData(Bundle savedInstanceState) {
        mCollects = new ArrayList<>();
        mAdapter = new CollectAdapter(mCollects, this);
        mCollectMsgListModel = new MsgListModel<>(getApplicationContext(), MsgListModel.COLLECT_TYPE);
        mCollectMsgPresenter = new MsgPresenter<>(mCollectMsgListModel, this);
        setStatesBar(R.color.colorPrimary);
    }

    @Override
    public void initView() {
        setContentView(R.layout.activity_collect_list);
        ButterKnife.bind(this);
    }

    @Override
    public void initOperation() {
        mRvCollectList.setLayoutManager(new GridLayoutManager(this, 3));
        mRvCollectList.setAdapter(mAdapter);

        mAdapter.setOnCollectItemListener(new OnCollectItemListener() {
            @Override
            public void onItemClick(android.view.View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString(BookFragment.BOOK_ID, BookDetailModel.COLLECT_TYPE);
                bundle.putString(COLLECT_ID, mCollects.get(position).getId());
                toActivity(BookDetailActivity.class, bundle);
            }
        });

        mBtnBack.setOnClickListener(v -> onBackPressed());
    }

    @Override
    public void loadData() {
        mCollectMsgPresenter.handleData();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void showData(List<Collect> datas) {
        mCollects.clear();
        mCollects.addAll(datas);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showLoadData(List<Collect> datas) {

    }

    @Override
    public void showRefreshData(List<Collect> datas) {

    }

    @Override
    public void showError(Throwable throwable) {

    }
}

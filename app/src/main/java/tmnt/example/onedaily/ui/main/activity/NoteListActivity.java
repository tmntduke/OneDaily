package tmnt.example.onedaily.ui.main.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import tmnt.example.onedaily.R;
import tmnt.example.onedaily.annotation.ContentView;
import tmnt.example.onedaily.bean.msg.NoteInfo;
import tmnt.example.onedaily.mvp.View;
import tmnt.example.onedaily.ui.common.BaseActivity;
import tmnt.example.onedaily.ui.main.adapter.NoteListAdapter;
import tmnt.example.onedaily.ui.main.listener.OnNoteItemClickListener;
import tmnt.example.onedaily.ui.main.model.MsgListModel;
import tmnt.example.onedaily.ui.main.presenter.MsgPresenter;
import tmnt.example.onedaily.util.DividerItemDecoration;

/**
 * Created by tmnt on 2017/5/11.
 */
@ContentView(R.layout.activity_note_list)
public class NoteListActivity extends BaseActivity implements View<List<NoteInfo>> {
    @Bind(R.id.btnBack)
    Button mBtnBack;
    @Bind(R.id.btn_save)
    TextView mBtnSave;
    @Bind(R.id.lyNav)
    RelativeLayout mLyNav;
    @Bind(R.id.rv_note_list)
    RecyclerView mRvNoteList;
    @Bind(R.id.note_list_empty)
    ImageView mNoteListEmpty;

    private List<NoteInfo> mNoteInfos;
    private NoteListAdapter adapter;
    private MsgPresenter<NoteInfo> persenter;
    public static final String NOTE_PATH = "note_path";
    private static final String TAG = "NoteListActivity";

    @Override
    public void initData(Bundle savedInstanceState) {
        setStatesBar(R.color.colorPrimary);
        mNoteInfos = new ArrayList<>();
        MsgListModel<NoteInfo> model = new MsgListModel<>(this,MsgListModel.NOTE_TYPE);
        persenter = new MsgPresenter<>(model, this);
    }

    @Override
    public void initView() {
    }

    @Override
    public void initOperation() {
        adapter = new NoteListAdapter(mNoteInfos, this);
        mRvNoteList.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        mRvNoteList.setAdapter(adapter);
        mRvNoteList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        adapter.setOnNoteItemClickListener(new OnNoteItemClickListener() {
            @Override
            public void onItemClick(android.view.View view, int position) {
                Log.i(TAG, "onItemClick: " + mNoteInfos.get(position).getPath());
                Bundle bundle = new Bundle();
                bundle.putParcelable(NOTE_PATH, mNoteInfos.get(position));
                toActivity(NoteDetailActivity.class, bundle);
            }
        });

        mBtnBack.setOnClickListener(v -> onBackPressed());

    }

    @Override
    public void loadData() {
        persenter.handleData();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        persenter.handleRefresh(null);
    }

    @Override
    public void showData(List<NoteInfo> datas) {
        setPage(datas);

    }

    @Override
    public void showLoadData(List<NoteInfo> datas) {

    }

    @Override
    public void showRefreshData(List<NoteInfo> datas) {
        setPage(datas);
    }

    @Override
    public void showError(Throwable throwable) {

    }

    private void setPage(List<NoteInfo> datas) {
        mNoteInfos.clear();
        mNoteInfos.addAll(datas);
        Log.i(TAG, "showData: " + mNoteInfos.size());
        if (mNoteInfos.size() == 0) {
            mRvNoteList.setVisibility(android.view.View.GONE);
            mNoteListEmpty.setVisibility(android.view.View.VISIBLE);
        } else {
            adapter.notifyDataSetChanged();
        }
    }
}

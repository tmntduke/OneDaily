package tmnt.example.onedaily.ui.main.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import tmnt.example.onedaily.R;
import tmnt.example.onedaily.db.OneDailyDB;
import tmnt.example.onedaily.ui.common.BaseActivity;

/**
 * Created by tmnt on 2017/5/11.
 */

public class NoteListActivity extends BaseActivity {
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

    private OneDailyDB mOneDailyDB;

    @Override
    public void initData(Bundle savedInstanceState) {
        setStatesBar(R.color.colorPrimary);
        mOneDailyDB=OneDailyDB.newInstance(this);
    }

    @Override
    public void initView() {
        setContentView(R.layout.activity_note_list);
        ButterKnife.bind(this);
    }

    @Override
    public void initOperation() {

    }

    @Override
    public void loadData() {

    }
}

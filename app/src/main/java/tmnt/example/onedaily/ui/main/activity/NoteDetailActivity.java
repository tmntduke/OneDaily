package tmnt.example.onedaily.ui.main.activity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.io.File;
import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import tmnt.example.onedaily.R;
import tmnt.example.onedaily.mvp.CallBack;
import tmnt.example.onedaily.ui.common.BaseActivity;
import tmnt.example.onedaily.util.IOUtil;

/**
 * Created by tmnt on 2017/5/11.
 */

public class NoteDetailActivity extends BaseActivity {
    @Bind(R.id.btnBack)
    Button mBtnBack;
    @Bind(R.id.img_delete)
    ImageView mImgDelete;
    @Bind(R.id.img_edit)
    ImageView mImgEdit;
    @Bind(R.id.lyNav)
    RelativeLayout mLyNav;
    @Bind(R.id.wv_note)
    WebView mWvNote;

    private String notePath;
    private Intent mIntent;

    @Override
    public void initData(Bundle savedInstanceState) {
        mIntent = getIntent();
        notePath = mIntent.getStringExtra(NoteListActivity.NOTE_PATH);
        setStatesBar(R.color.colorPrimary);
    }

    @Override
    public void initView() {
        setContentView(R.layout.activity_note_detail);
        ButterKnife.bind(this);
    }

    @Override
    public void initOperation() {

        WebSettings settings = mWvNote.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDefaultTextEncodingName("utf-8");
        settings.setAppCacheEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setLoadWithOverviewMode(true);

    }

    @Override
    public void loadData() {
        File file = new File(notePath);
        try {
            IOUtil.input(file, new CallBack<String>() {
                @Override
                public void onSuccess(String s) {
                    mWvNote.loadData(s, "text/html; charset=UTF-8", null);
                }

                @Override
                public void onError(Throwable e) {

                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

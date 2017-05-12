package tmnt.example.onedaily.ui.main.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.io.File;
import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import tmnt.example.onedaily.R;
import tmnt.example.onedaily.bean.note.NoteInfo;
import tmnt.example.onedaily.db.OneDailyDB;
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

    private NoteInfo notePath;
    private Intent mIntent;
    private File file;
    private OneDailyDB mOneDailyDB;
    private static final String TAG = "NoteDetailActivity";

    @Override
    public void initData(Bundle savedInstanceState) {
        mIntent = getIntent();
        notePath = mIntent.getParcelableExtra(NoteListActivity.NOTE_PATH);
        mOneDailyDB = OneDailyDB.newInstance(this);
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
        settings.setAllowFileAccessFromFileURLs(true);
        settings.setAllowUniversalAccessFromFileURLs(true);
        settings.setAllowFileAccess(true);
        mWvNote.setWebViewClient(new WebViewClient() {
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
                return super.shouldInterceptRequest(view, request);
            }
        });
        mImgDelete.setOnClickListener(v -> deleteNote());

        mBtnBack.setOnClickListener(v -> onBackPressed());
    }


    @Override
    public void loadData() {
        file = new File(notePath.getPath());
        try {
            IOUtil.input(file, new CallBack<String>() {
                @Override
                public void onSuccess(String s) {
//                    mWvNote.loadData(s, "text/html; charset=UTF-8", null);
                    mWvNote.loadDataWithBaseURL(Environment.getExternalStorageDirectory().getPath()
                            , s, "text/html; charset=UTF-8", null, null);
                }

                @Override
                public void onError(Throwable e) {

                }
            });
        } catch (IOException e) {
            e.printStackTrace();
            Snackbar.make(getWindow().getDecorView(), "文件已被删除", Snackbar.LENGTH_SHORT).show();
            mOneDailyDB.deleteNote(notePath.getId());
            toActivity(NoteListActivity.class);
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void deleteNote() {
        if (file != null) {
            file.delete();
            Log.i(TAG, "deleteNote: " + notePath.getId());
            mOneDailyDB.deleteNote(notePath.getId());
            toActivity(NoteListActivity.class);
            finish();
        }
    }
}

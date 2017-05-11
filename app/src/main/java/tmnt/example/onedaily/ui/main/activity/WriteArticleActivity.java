package tmnt.example.onedaily.ui.main.activity;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.RelativeSizeSpan;
import android.transition.Slide;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import jp.wasabeef.richeditor.RichEditor;
import rx.schedulers.Schedulers;
import tmnt.example.onedaily.R;
import tmnt.example.onedaily.Rx.Operation;
import tmnt.example.onedaily.Rx.RxUilt;
import tmnt.example.onedaily.bean.note.NoteInfo;
import tmnt.example.onedaily.db.OneDailyDB;
import tmnt.example.onedaily.mvp.CallBack;
import tmnt.example.onedaily.ui.common.BaseActivity;
import tmnt.example.onedaily.util.Common;
import tmnt.example.onedaily.util.DateFormatUtil;
import tmnt.example.onedaily.util.HtmlUtil;
import tmnt.example.onedaily.util.IOUtil;
import tmnt.example.onedaily.util.ImageUtils;
import tmnt.example.onedaily.util.PremissionUtil;
import tmnt.example.onedaily.util.SharedPreferencesUtil;

/**
 * Created by tmnt on 2017/5/8.
 */

public class WriteArticleActivity extends BaseActivity {

    @Bind(R.id.btn_save)
    TextView mBtnSave;
    @Bind(R.id.lyNav)
    RelativeLayout mLyNav;
    @Bind(R.id.ed_title)
    EditText mEdTitle;
    @Bind(R.id.editor)
    RichEditor mEditor;
    @Bind(R.id.img_picture)
    ImageView mImgPicture;
    @Bind(R.id.img_style)
    ImageView mImgStyle;
    @Bind(R.id.img_undo)
    ImageView mImgUndo;
    @Bind(R.id.img_redo)
    ImageView mImgRedo;
    @Bind(R.id.img_jiacu)
    ImageView mImgJiacu;
    @Bind(R.id.img_xieti)
    ImageView mImgXieti;
    @Bind(R.id.img_biao_h1)
    ImageView mImgBiaoH1;
    @Bind(R.id.img_biao_h2)
    ImageView mImgBiaoH2;
    @Bind(R.id.img_biao_h3)
    ImageView mImgBiaoH3;
    @Bind(R.id.img_biao_h4)
    ImageView mImgBiaoH4;
    @Bind(R.id.style_contain)
    LinearLayout mStyleContain;
    @Bind(R.id.img_link)
    ImageView mImgLink;
    @Bind(R.id.btnBack)
    Button mBtnBack;

    private View mView;
    public static final int REQUES_CODE = 101;
    private EditText edTitle;
    private EditText edUrl;
    private boolean isClick;
    private AlertDialog dialog;
    private RxUilt rxUilt;
    private OneDailyDB mOneDailyDB;
    private static final String WRITE_PATH = "oneDaily_write";
    public static final String NOTE_NAME = "article";

    @Override
    public void initData(Bundle savedInstanceState) {
        setStatesBar(R.color.colorPrimary);
        rxUilt = RxUilt.getInstance();
        mOneDailyDB = OneDailyDB.newInstance(this);
    }

    @Override
    public void initView() {
        setContentView(R.layout.activity_article_contribute);
        ButterKnife.bind(this);
    }

    @Override
    public void initOperation() {

        SpannableString spannableString = new SpannableString("请输入标题(30字以内)");
        RelativeSizeSpan sizeSpan = new RelativeSizeSpan(0.5f);
        spannableString.setSpan(sizeSpan, 5, spannableString.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        mEdTitle.setHint(spannableString);

        mEditor.setPadding(10, 10, 10, 10);
        mEditor.setPlaceholder("编辑正文...");

        mImgPicture.setOnClickListener(v ->
                ImageUtils.toGallery(REQUES_CODE, WriteArticleActivity.this)

        );

        mImgUndo.setOnClickListener(v ->
                mEditor.undo());

        mImgRedo.setOnClickListener(v ->
                mEditor.redo());

        mImgJiacu.setOnClickListener(v ->
                mEditor.setBold());

        mImgXieti.setOnClickListener(v ->
                mEditor.setItalic());

        mImgBiaoH1.setOnClickListener(v ->
                mEditor.setHeading(1));

        mImgBiaoH2.setOnClickListener(v ->
                mEditor.setHeading(2));

        mImgBiaoH3.setOnClickListener(v ->
                mEditor.setHeading(3));

        mImgBiaoH4.setOnClickListener(v ->
                mEditor.setHeading(4));

        mImgLink.setOnClickListener(v ->
                insertLink());

        mImgStyle.setOnClickListener(v -> {
            if (!isClick) {
                mStyleContain.setVisibility(View.VISIBLE);
                mImgStyle.setImageDrawable(createBitmap(R.drawable.image_xiezuo_wenzi, R.color.colorPrimary));
                isClick = true;
            } else {
                mStyleContain.setVisibility(View.GONE);
                mImgStyle.setImageDrawable(createBitmap(R.drawable.image_xiezuo_wenzi, R.color.write_gray));
                isClick = false;
            }
        });

        mBtnSave.setOnClickListener(v -> {
            if (TextUtils.isEmpty(mEdTitle.getText().toString())
                    || TextUtils.isEmpty(mEditor.getHtml())) {
                Toast.makeText(WriteArticleActivity.this, "请输入内容", Toast.LENGTH_SHORT).show();
            } else {
                boolean isPer = PremissionUtil.chaeckPermission(WriteArticleActivity.this
                        , "android.permission.WRITE_EXTERNAL_STORAGE");

                if (!isPer) {
                    PremissionUtil.requestPermission(WriteArticleActivity.this
                            , new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"});
                }
                saveArticle();
            }
        });

        mBtnBack.setOnClickListener(v ->
                onBackPressed());

    }

    private void saveArticle() {
        createFile();
        File file = new File(Common.ONEDAILY_PATH
                + File.separator + WRITE_PATH
                + File.separator
                + mEdTitle.getText().toString() + ".txt");

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String html = HtmlUtil.createWriteData(mEditor.getHtml(), mEdTitle.getText().toString());
        try {
            IOUtil.output(file, html.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        NoteInfo noteInfo = new NoteInfo();
        noteInfo.setDate(DateFormatUtil.dateFomeNomal());
        noteInfo.setTitle(mEdTitle.getText().toString());
        noteInfo.setPath(Common.ONEDAILY_PATH
                + File.separator + WRITE_PATH
                + File.separator
                + mEdTitle.getText().toString() + ".txt");
        mOneDailyDB.insertNote(noteInfo, new CallBack<Boolean>() {
            @Override
            public void onSuccess(Boolean aBoolean) {
                if (aBoolean) {
                    toActivity(NoteListActivity.class);
                    finish();
                } else {
                    Toast.makeText(WriteArticleActivity.this, "保存失败", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(WriteArticleActivity.this, "保存失败", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void loadData() {

    }

    private Drawable createBitmap(int res, int color) {
        Drawable drawable = DrawableCompat.wrap(ContextCompat.getDrawable(WriteArticleActivity.this, res));
        DrawableCompat.setTint(drawable, ContextCompat.getColor(WriteArticleActivity.this, color));
        return drawable;
    }

    private void insertLink() {
        AlertDialog.Builder builder = new AlertDialog.Builder(WriteArticleActivity.this);
        View view = getDialogView();
        builder.setView(view);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mEditor.insertLink(edUrl.getText().toString(), edTitle.getText().toString());
            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        dialog = builder.create();
        dialog.show();
    }

    private View getDialogView() {
        View view = LayoutInflater.from(WriteArticleActivity.this).inflate(R.layout.dialog_add_link, null);
        edTitle = (EditText) view.findViewById(R.id.ed_link_title);
        edUrl = (EditText) view.findViewById(R.id.ed_link_url);
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == WriteArticleActivity.this.RESULT_OK) {
            if (requestCode == REQUES_CODE) {
                mEditor.insertImage(ImageUtils.getImagePathFromGallery(WriteArticleActivity.this, data),
                        "image" + DateFormatUtil.nowDate());

                //富文本显示上传的图片
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private Slide slide() {
        Slide slide = new Slide(Gravity.BOTTOM);
        slide.setDuration(1500);
        return slide;
    }

    private void createFile() {
        File file = new File(Common.ONEDAILY_PATH
                + File.separator + WRITE_PATH);
        if (!file.exists()) {
            file.mkdir();
        }
    }
}

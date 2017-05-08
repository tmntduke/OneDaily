package tmnt.example.onedaily.ui.main.fragment;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.RelativeSizeSpan;
import android.transition.Slide;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import jp.wasabeef.richeditor.RichEditor;
import tmnt.example.onedaily.R;
import tmnt.example.onedaily.ui.common.BaseFragment;
import tmnt.example.onedaily.util.DateFormatUtil;
import tmnt.example.onedaily.util.ImageUtils;

/**
 * Created by tmnt on 2017/5/8.
 */

public class WriteArticleFragment extends BaseFragment {

    @Bind(R.id.btnBack)
    Button mBtnBack;
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

    private View mView;
    public static final int REQUES_CODE = 101;
    private EditText edTitle;
    private EditText edUrl;
    private boolean isClick;
    private static final String WRITE_PATH = "oneDaily_write";

    @Override
    protected View setContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = LayoutInflater.from(getActivity()).inflate(R.layout.activity_article_contribute, container, false);
        ButterKnife.bind(this, mView);
        return mView;
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public void initView() {
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
                ImageUtils.toGallery(REQUES_CODE, getActivity())

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
                mImgStyle.setImageDrawable(createBitmap(R.drawable.image_xiezuo_wenzi));
                isClick = true;
            } else {
                mStyleContain.setVisibility(View.GONE);
                mImgStyle.setImageResource(R.drawable.image_xiezuo_wenzi);
                isClick = false;
            }
        });

        mBtnSave.setOnClickListener(v -> {
            createFile();
            File file = new File(Environment.getExternalStorageDirectory().getPath()
                    + File.separator + WRITE_PATH
                    + File.separator
                    + mEdTitle.getText().toString());

            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    public void loadData() {

    }

    public static Fragment getInstance() {
        Fragment fragment = new WriteArticleFragment();
        return fragment;
    }

    private Drawable createBitmap(int res) {
        Drawable drawable = DrawableCompat.wrap(ContextCompat.getDrawable(getActivity(), res));
        DrawableCompat.setTint(drawable, ContextCompat.getColor(getActivity(), R.color.colorPrimary));
        return drawable;
    }

    private void insertLink() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
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
    }

    private View getDialogView() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_add_link, null);
        edTitle = (EditText) view.findViewById(R.id.ed_link_title);
        edUrl = (EditText) view.findViewById(R.id.ed_link_url);
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == getActivity().RESULT_OK) {
            if (requestCode == REQUES_CODE) {
                mEditor.insertImage(ImageUtils.getImagePathFromGallery(getActivity(), data),
                        "image" + DateFormatUtil.nowDate());

                //富文本显示上传的图片
            }
        }
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private Slide slide() {
        Slide slide = new Slide(Gravity.BOTTOM);
        slide.setDuration(1500);
        return slide;
    }

    private void createFile() {
        File file = new File(WRITE_PATH);
        if (!file.exists()) {
            file.mkdir();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
}

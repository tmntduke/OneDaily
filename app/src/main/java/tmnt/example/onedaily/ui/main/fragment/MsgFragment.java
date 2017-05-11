package tmnt.example.onedaily.ui.main.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import java.io.File;

import butterknife.Bind;
import butterknife.ButterKnife;
import tmnt.example.onedaily.R;
import tmnt.example.onedaily.ui.common.BaseFragment;
import tmnt.example.onedaily.ui.main.activity.WriteArticleActivity;
import tmnt.example.onedaily.util.Common;
import tmnt.example.onedaily.util.DateFormatUtil;
import tmnt.example.onedaily.util.ImageUtils;
import tmnt.example.onedaily.util.SharedPreferencesUtil;
import tmnt.example.onedaily.weight.CircleView.CircleImageView;

/**
 * Created by tmnt on 2017/5/10.
 */

public class MsgFragment extends BaseFragment {

    @Bind(R.id.cv_my_cover)
    CircleImageView mCvMyCover;
    @Bind(R.id.tv_my_name)
    TextView mTvMyName;
    @Bind(R.id.article_img)
    ImageView mArticleImg;
    @Bind(R.id.tv_note_count)
    TextView mTvNoteCount;
    @Bind(R.id.rl_note)
    RelativeLayout mRlNote;
    @Bind(R.id.book_img)
    ImageView mBookImg;
    @Bind(R.id.tv_collect_count)
    TextView mTvCollectCount;
    @Bind(R.id.rl_collect)
    RelativeLayout mRlCollect;
    @Bind(R.id.sw_notification)
    SwitchCompat mSwNotification;
    @Bind(R.id.rl_notification)
    RelativeLayout mRlNotification;
    @Bind(R.id.rl_disposition)
    RelativeLayout mRlDisposition;
    @Bind(R.id.rl_abort)
    RelativeLayout mRlAbort;
    @Bind(R.id.btn_exit)
    Button mBtnExit;

    private View mView;
    private SharedPreferencesUtil mSharedPreferencesUtil;
    private int noteCount;
    private UploadDialogFragment fDialogFragment;
    private static final String COVER_PATH = Common.ONEDAILY_PATH + File.separator + "oneDaily_cover";
    private String coverName;
    private static final int CAMERA_REQUEST_CODE = 11001;
    private static final int IMAGE_REQUEST_CODE = 11002;

    @Override
    protected View setContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_msg, container, false);
        ButterKnife.bind(this, mView);
        return mView;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mSharedPreferencesUtil = SharedPreferencesUtil.getInstance(getActivity());
        if (mSharedPreferencesUtil.getData(WriteArticleActivity.NOTE_NAME) != null) {
            noteCount = mSharedPreferencesUtil.getData(WriteArticleActivity.NOTE_NAME);
        } else {
            noteCount = 0;
        }
    }

    @Override
    public void initView() {

    }

    @Override
    public void initOperation() {

        mTvNoteCount.setText(String.valueOf(noteCount));

        mCvMyCover.setOnClickListener(v -> {

        });

//        mRlAbort.setOnClickListener(v -> toActivity());
//
//        mRlCollect.setOnClickListener(v -> toActivity());
//
//        mRlNote.setOnClickListener(v -> toActivity());
//
//        mRlDisposition.setOnClickListener(v -> toActivity());

        mSwNotification.setOnCheckedChangeListener(((buttonView, isChecked) -> {
            if (isChecked) {

            }
        }));

//        mBtnExit.setOnClickListener(v ->);

        mCvMyCover.setOnClickListener(v -> showUploadDialog());

    }

    @Override
    public void loadData() {

    }

    public static Fragment getInstance() {
        MsgFragment fragment = new MsgFragment();
        return fragment;
    }

    private void showUploadDialog() {
        fDialogFragment = new UploadDialogFragment();
        fDialogFragment.show(getActivity().getSupportFragmentManager(), "fDialogFragment");
        dialogOption(fDialogFragment);
    }

    public void dialogOption(final UploadDialogFragment fragment) {
        fragment.setOnDoOptionOnDialog(new UploadDialogFragment.OnDoOptionOnDialog() {
            @Override
            public void onTakePhoto(View view) {
                createFile();
                ImageUtils.toCamera(getActivity(), COVER_PATH + DateFormatUtil.nowDate(), CAMERA_REQUEST_CODE);
            }

            @Override
            public void onChoosePhoto(View view) {

                ImageUtils.toGallery(IMAGE_REQUEST_CODE, getActivity());
            }

            @Override
            public void onCancel(View view) {
                fragment.dismiss();
            }
        });
    }

    private void createFile() {
        File file = new File(COVER_PATH);

        if (!file.exists()) {
            file.mkdir();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == getActivity().RESULT_OK) {
            if (requestCode == CAMERA_REQUEST_CODE) {
                mCvMyCover.setImageBitmap(ImageUtils.readBitMap(getActivity(), COVER_PATH));
            } else {
                mCvMyCover.setImageBitmap(ImageUtils.readBitMap(getActivity()
                        , ImageUtils.getImagePathFromGallery(getActivity(), data)));
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}

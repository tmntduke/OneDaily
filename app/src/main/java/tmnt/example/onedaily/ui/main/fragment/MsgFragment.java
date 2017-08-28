package tmnt.example.onedaily.ui.main.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.File;

import butterknife.Bind;
import butterknife.ButterKnife;
import tmnt.example.onedaily.R;
import tmnt.example.onedaily.db.OneDailyDB;
import tmnt.example.onedaily.ui.common.BaseFragment;
import tmnt.example.onedaily.ui.main.activity.CollectListActivity;
import tmnt.example.onedaily.ui.main.activity.NoteListActivity;
import tmnt.example.onedaily.ui.common.Common;
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
    private OneDailyDB mOneDailyDB;
    private int noteCount;
    private int collectCount;
    private UploadDialogFragment fDialogFragment;
    private SharedPreferencesUtil mSharedPreferencesUtil;
    private String coverPath;
    private static final String COVER_PATH = Common.ONEDAILY_PATH + File.separator + "oneDaily_cover";
    private static final String COVER_NAME = COVER_PATH + File.separator + DateFormatUtil.dateFomeNomal() + ".jpg";
    private static final int CAMERA_REQUEST_CODE = 11001;
    private static final int IMAGE_REQUEST_CODE = 11002;
    private static final String USER_COVER = "user_cover";
    private static final String USER_NAME = "user_name";

    private static final String TAG = "MsgFragment";

    @Override
    protected View setContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_msg, container, false);
        ButterKnife.bind(this, mView);
        return mView;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mOneDailyDB = OneDailyDB.newInstance(getActivity().getApplicationContext());
        noteCount = mOneDailyDB.queryNoteCount();
        collectCount = mOneDailyDB.queryCollectCount();
        mSharedPreferencesUtil = SharedPreferencesUtil.getInstance(getActivity());
        coverPath = mSharedPreferencesUtil.getData(USER_COVER);
    }

    @Override
    public void initView() {

    }

    @Override
    public void initOperation() {

        if (coverPath != null)
            mCvMyCover.setImageBitmap(ImageUtils.readBitMap(getActivity()
                    , coverPath));

        mTvNoteCount.setText(String.valueOf(noteCount));

        mTvCollectCount.setText(String.valueOf(collectCount));

        mCvMyCover.setOnClickListener(v -> showUploadDialog());

//        mRlAbort.setOnClickListener(v -> toActivity());

        mRlNote.setOnClickListener(v -> toActivity(NoteListActivity.class));
//
//        mRlDisposition.setOnClickListener(v -> toActivity());

        mRlCollect.setOnClickListener(v -> {
            if (collectCount != 0)
                toActivity(CollectListActivity.class);
        });

        mSwNotification.setOnCheckedChangeListener(((buttonView, isChecked) -> {
            if (isChecked) {

            } else {

            }
        }));

        mTvMyName.setOnClickListener(v -> {
            if (mTvMyName.getText().toString().equals(getString(R.string.user_login))) {
                //login
            }
        });

//        mBtnExit.setOnClickListener(v ->);

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
                ImageUtils.toCamera(getActivity(), COVER_NAME, CAMERA_REQUEST_CODE);
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
        Log.i(TAG, "onActivityResult: start");
        if (requestCode == CAMERA_REQUEST_CODE) {
            mCvMyCover.setImageBitmap(ImageUtils.readBitMap(getActivity(), COVER_NAME));
        } else {
            String p = ImageUtils.getImagePathFromGallery(getActivity(), data);
            Log.i(TAG, "onActivityResult: " + p);
            mCvMyCover.setImageBitmap(ImageUtils.readBitMap(getActivity(), p));
        }

        mSharedPreferencesUtil.putData(USER_COVER, ImageUtils.getBitmapPath());
    }

    @Override
    public void onPause() {
        super.onPause();
        if (fDialogFragment != null)
            fDialogFragment.dismiss();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: start");
        noteCount = mOneDailyDB.queryNoteCount();
        collectCount = mOneDailyDB.queryCollectCount();
        mTvNoteCount.setText(String.valueOf(noteCount));
        mTvCollectCount.setText(String.valueOf(collectCount));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}

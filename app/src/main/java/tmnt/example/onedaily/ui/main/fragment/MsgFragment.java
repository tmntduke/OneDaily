package tmnt.example.onedaily.ui.main.fragment;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.google.gson.Gson;

import java.io.File;
import java.util.List;

import butterknife.Bind;
import de.hdodenhof.circleimageview.CircleImageView;
import rx.Subscription;
import tmnt.example.onedaily.R;
import tmnt.example.onedaily.annotation.ContentView;
import tmnt.example.onedaily.bean.share.GetUserInfo;
import tmnt.example.onedaily.db.OneDailyDB;
import tmnt.example.onedaily.event.UserLoginEvent;
import tmnt.example.onedaily.event.UserLogoutEvent;
import tmnt.example.onedaily.ui.common.BaseFragment;
import tmnt.example.onedaily.ui.main.activity.AboutActivity;
import tmnt.example.onedaily.ui.main.activity.CollectListActivity;
import tmnt.example.onedaily.ui.main.activity.DescActivity;
import tmnt.example.onedaily.ui.main.activity.LoginActivity;
import tmnt.example.onedaily.ui.main.activity.NoteListActivity;
import tmnt.example.onedaily.ui.common.Common;
import tmnt.example.onedaily.util.DateFormatUtil;
import tmnt.example.onedaily.util.ImageUtils;
import tmnt.example.onedaily.Rx.RxBus;
import tmnt.example.onedaily.util.ShareUtil;
import tmnt.example.onedaily.util.SharedPreferencesUtil;

/**
 * Created by tmnt on 2017/5/10.
 */
@ContentView(R.layout.fragment_msg)
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
    private GetUserInfo usGetUserInfo;
    private Subscription mSubscriptionLogin;
    private Subscription mSubscriptionLogout;

    public static final String COVER_PATH = Common.ONEDAILY_PATH + File.separator + "oneDaily_cover";
    private static final String COVER_NAME = COVER_PATH + File.separator + DateFormatUtil.dateFomeNomal() + ".jpg";
    private static final int CAMERA_REQUEST_CODE = 11001;
    private static final int IMAGE_REQUEST_CODE = 11002;
    private static final String USER_COVER = "user_cover";
    private static final String USER_NAME = "user_name";

    private static final String TAG = "MsgFragment";

    @Override
    public void initData(Bundle savedInstanceState) {
        mOneDailyDB = OneDailyDB.newInstance(getActivity().getApplicationContext());
        noteCount = mOneDailyDB.queryNoteCount();
        collectCount = mOneDailyDB.queryCollectCount();
        mSharedPreferencesUtil = SharedPreferencesUtil.getInstance(getActivity());
        coverPath = mSharedPreferencesUtil.getData(USER_COVER);

        mSubscriptionLogin = RxBus.getInstance()
                .toObservable(UserLoginEvent.class)
                .subscribe(userLoginEvent -> {
                    createUserInfo(userLoginEvent.mGetUserInfo);
                });

        mSubscriptionLogout = RxBus.getInstance()
                .toObservable(UserLogoutEvent.class)
                .subscribe(userLoginEvent -> {
                    logout();
                });
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

        mRlAbort.setOnClickListener(v -> toActivity(AboutActivity.class));

        mRlNote.setOnClickListener(v -> toActivity(NoteListActivity.class));
//
        mRlDisposition.setOnClickListener(v -> toActivity(DescActivity.class));

        mRlCollect.setOnClickListener(v -> {
            if (collectCount != 0)
                toActivity(CollectListActivity.class);
        });

        mTvMyName.setOnClickListener(v -> {
            if (mTvMyName.getText().toString().equals(getString(R.string.user_login))) {
                //login
                toActivity(LoginActivity.class);
            }
        });

        mBtnExit.setOnClickListener(v -> ShareUtil.logout(usGetUserInfo.getType(), getActivity()));

        mRlNotification.setOnClickListener(v -> sendEmail());
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
        if (requestCode == CAMERA_REQUEST_CODE) {
            mCvMyCover.setImageBitmap(ImageUtils.readBitMap(getActivity(), COVER_NAME));
        } else {
            String p = ImageUtils.getImagePathFromGallery(getActivity(), data);
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
        noteCount = mOneDailyDB.queryNoteCount();
        collectCount = mOneDailyDB.queryCollectCount();
        mTvNoteCount.setText(String.valueOf(noteCount));
        mTvCollectCount.setText(String.valueOf(collectCount));
        String user = mSharedPreferencesUtil.getData(Common.USER_INFO);
        if (!TextUtils.isEmpty(user)) {
            createUserInfo(user);
        } else {
            mBtnExit.setVisibility(View.GONE);
        }
    }

    private void sendEmail() {
        Intent data = new Intent(Intent.ACTION_SENDTO);
        data.setData(Uri.parse("tmntduke@hotmail.com"));
        PackageManager packageManager = getActivity().getPackageManager();
        List<ResolveInfo> applist = packageManager.queryIntentActivities(data, 0);
        if (applist == null || applist.isEmpty()) {
            showToast(getString(R.string.haveno_email));
            return;
        }
        startActivity(data);
    }

    private void logout() {
        mTvMyName.setText(getString(R.string.user_login));
        Glide.with(getActivity())
                .load(R.drawable.image)
                .into(mCvMyCover);
        mBtnExit.setVisibility(View.GONE);
        Toast.makeText(getActivity(), getString(R.string.user_logout), Toast.LENGTH_SHORT).show();
    }

    private void createUserInfo(String user) {
        usGetUserInfo = new Gson().fromJson(user, GetUserInfo.class);
        mTvMyName.setText(usGetUserInfo.getNickName());
        String cover = mSharedPreferencesUtil.getData(USER_COVER);
        mBtnExit.setVisibility(View.VISIBLE);
        if (TextUtils.isEmpty(cover)) {
            Glide.with(getActivity())
                    .load(usGetUserInfo.getIcon())
                    .into(mCvMyCover);
        } else {
            Glide.with(this)
                    .load(cover)
                    .listener(new RequestListener<String, GlideDrawable>() {
                        @Override
                        public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                            Glide.with(getActivity())
                                    .load(usGetUserInfo.getIcon())
                                    .into(mCvMyCover);
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(GlideDrawable resource, String model
                                , Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                            return false;
                        }
                    })
                    .into(mCvMyCover);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mSubscriptionLogin != null && !mSubscriptionLogin.isUnsubscribed()) {
            mSubscriptionLogin.unsubscribe();
        }

        if (mSubscriptionLogout != null && !mSubscriptionLogout.isUnsubscribed()) {
            mSubscriptionLogout.unsubscribe();
        }
    }
}

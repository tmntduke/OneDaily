package tmnt.example.onedaily.ui.main.activity;

import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.io.File;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Subscription;
import tmnt.example.onedaily.R;
import tmnt.example.onedaily.Rx.RxUilt;
import tmnt.example.onedaily.mvp.CallBack;
import tmnt.example.onedaily.ui.common.BaseActivity;
import tmnt.example.onedaily.util.ImageUtils;
import tmnt.example.onedaily.util.PremissionUtil;

import static tmnt.example.onedaily.ui.common.Common.SPLASH_PATH;

/**
 * Created by tmnt on 2017/9/25.
 */

public class SplashActivity extends BaseActivity {
    @Bind(R.id.iv_splash)
    ImageView mIvSplash;
    @Bind(R.id.iv_save)
    ImageView mIvSave;

    private static final String TAG = "SplashActivity";

    private Bitmap mBitmap;
    private Subscription mSubscription;

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public void initView() {
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

    }

    @Override
    public void initOperation() {
        // mIvSplash.setImageResource(R.drawable.img_transition_default);
        Glide.with(this)
                .load(SPLASH_PATH)
                .asBitmap()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .listener(new RequestListener<String, Bitmap>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<Bitmap> target, boolean isFirstResource) {
                        turnToMain();
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Bitmap resource, String model, Target<Bitmap> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        if (resource != null) {
                            mIvSave.setVisibility(View.VISIBLE);
                            mBitmap = resource;
                            turnToMain();
                        }
                        return false;
                    }
                })
                .placeholder(R.drawable.img_transition_default)
                .into(mIvSplash);

        mIvSave.setOnClickListener(v -> {
            requestPromission();
            saveImage(mBitmap);
        });

    }

    @Override
    public void loadData() {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (mSubscription != null && mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
        toActivity(MainActivity.class);
        finish();
    }

    private void turnToMain() {
        mSubscription = RxUilt.getInstance().delayForData(3, TimeUnit.SECONDS, new CallBack() {
            @Override
            public void onSuccess(Object o) {
                mIvSave.setClickable(false);
                toActivity(MainActivity.class);
                overridePendingTransition(0, android.R.anim.fade_out);
                finish();
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    private void requestPromission() {
        if (!PremissionUtil.chaeckPermission(SplashActivity.this
                , "android.permission.WRITE_EXTERNAL_STORAGE")) {
            PremissionUtil.requestPermission(this, new String[]{
                    "android.permission.WRITE_EXTERNAL_STORAGE"
                    , "android.permission.RECORD_AUDIO"
                    , "android.permission.ACCESS_NETWORK_STATE"
                    , "android.permission.READ_PHONE_STATE"
                    , "android.permission.READ_CONTACTS"
                    , "android.permission.ACCESS_WIFI_STATE"
                    , "android.permission.WRITE_SETTINGS"});
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            saveImage(mBitmap);
        }
    }

    private void saveImage(Bitmap bitmap) {
        if (bitmap != null) {
            ImageUtils.saveBitmap(bitmap, new CallBack<File>() {
                @Override
                public void onSuccess(File file) {
                    if (file != null) {
                        Toast.makeText(SplashActivity.this, getString(R.string.save_image), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onError(Throwable e) {

                }
            });
        }
    }
}

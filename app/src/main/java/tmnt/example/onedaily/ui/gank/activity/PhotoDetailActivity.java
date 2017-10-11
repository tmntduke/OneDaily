package tmnt.example.onedaily.ui.gank.activity;

import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.io.File;

import butterknife.Bind;
import butterknife.ButterKnife;
import tmnt.example.onedaily.R;
import tmnt.example.onedaily.annotation.ContentView;
import tmnt.example.onedaily.mvp.CallBack;
import tmnt.example.onedaily.ui.common.BaseActivity;
import tmnt.example.onedaily.ui.gank.fragment.PhotoFragment;
import tmnt.example.onedaily.util.ImageUtils;
import tmnt.example.onedaily.util.PremissionUtil;
import uk.co.senab.photoview.PhotoView;

/**
 * Created by tmnt on 2017/10/11.
 */
@ContentView(R.layout.activity_photo_detail)
public class PhotoDetailActivity extends BaseActivity {
    @Bind(R.id.btnBack)
    Button mBtnBack;
    @Bind(R.id.iv_save)
    ImageView mIvSave;
    @Bind(R.id.photo_touch_iv)
    PhotoView mPhotoTouchIv;

    private String url;
    private Bitmap mBitmap;

    @Override
    public void initData(Bundle savedInstanceState) {
        setStatesBar(android.R.color.transparent);
        url = getIntent().getStringExtra(PhotoFragment.PHOTO_URL);
    }

    @Override
    public void initView() {

    }

    @Override
    public void initOperation() {
        Glide.with(this)
                .load(url)
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .listener(new RequestListener<String, Bitmap>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<Bitmap> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Bitmap resource, String model, Target<Bitmap> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        if (resource != null) {
                            mBitmap = resource;
                        }
                        return false;
                    }
                })
                .into(mPhotoTouchIv);

        mBtnBack.setOnClickListener(v -> onBackPressed());

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
        finish();
    }

    private void saveImage(Bitmap bitmap) {
        if (bitmap != null) {
            ImageUtils.saveBitmap(bitmap, new CallBack<File>() {
                @Override
                public void onSuccess(File file) {
                    if (file != null) {
                        Toast.makeText(PhotoDetailActivity.this, getString(R.string.save_image), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onError(Throwable e) {

                }
            });
        }
    }

    private void requestPromission() {
        if (!PremissionUtil.chaeckPermission(PhotoDetailActivity.this
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
}

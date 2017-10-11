package tmnt.example.onedaily.ui.gank.fragment;

import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import tmnt.example.onedaily.R;
import tmnt.example.onedaily.annotation.ContentView;
import tmnt.example.onedaily.bean.gank.PhotoInfo;
import tmnt.example.onedaily.ui.common.BaseFragment;
import tmnt.example.onedaily.ui.gank.activity.PhotoDetailActivity;
import tmnt.example.onedaily.ui.gank.adapter.PhotoAdapter;
import tmnt.example.onedaily.ui.gank.listener.OnPhotoClickLisener;
import tmnt.example.onedaily.ui.gank.model.PhotoModel;
import tmnt.example.onedaily.ui.gank.persenter.PhotoPersenter;

/**
 * Created by tmnt on 2017/10/11.
 */

@ContentView(R.layout.fragment_photos)
public class PhotoFragment extends BaseFragment implements tmnt.example.onedaily.mvp.View<PhotoInfo> {

    @Bind(R.id.rv_photo)
    RecyclerView mRvPhoto;
    @Bind(R.id.spl_photo)
    SwipeRefreshLayout mSplPhoto;
    @Bind(R.id.img_photo_empty)
    ImageView mImgPhotoEmpty;
    @Bind(R.id.tv_photo_empty_refresh)
    TextView mTvPhotoEmptyRefresh;
    @Bind(R.id.photo_empty)
    LinearLayout mPhotoEmpty;
    @Bind(R.id.photo_loading)
    ImageView mPhotoLoading;

    private PhotoPersenter mPhotoPersenter;
    private PhotoAdapter mPhotoAdapter;
    private int page = 1;

    private List<PhotoInfo.ResultsBean> mPhotos = new ArrayList<>();
    private boolean mIsAllLoaded;
    private AnimationDrawable mAnimationDrawable;

    public static String PHOTO_URL = "photo_url";

    private static final String TAG = "PhotoFragment";

    public static PhotoFragment newInstance() {
        Bundle args = new Bundle();
        PhotoFragment fragment = new PhotoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        PhotoModel model = new PhotoModel();
        mPhotoPersenter = new PhotoPersenter(model, this);
        mPhotoAdapter = new PhotoAdapter(mPhotos, getActivity());
    }

    @Override
    public void initView() {

    }

    @Override
    public void initOperation() {
        mRvPhoto.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mRvPhoto.setItemAnimator(new DefaultItemAnimator());
        mRvPhoto.setAdapter(mPhotoAdapter);
        setRvScrollEvent();

        mPhotoAdapter.setOnPhotoClickLisener(new OnPhotoClickLisener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view, int position) {
                // TODO: 2017/10/11
//                ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(getActivity()
//                        , view, getString(R.string.app_name));
//                Intent intent = new Intent(getActivity(), PhotoDetailActivity.class);
//                intent.putExtra(PHOTO_URL, mPhotos.get(position).getUrl());
//                startActivity(intent, activityOptions.toBundle());
                Bundle bundle=new Bundle();
                bundle.putString(PHOTO_URL,mPhotos.get(position).getUrl());
                toActivity(PhotoDetailActivity.class,bundle);
            }
        });

        mSplPhoto.setColorSchemeColors(new int[]{Color.parseColor("#26f913"), Color.parseColor("#ef4054")
                , Color.parseColor("#e9ec56"), Color.parseColor("#009dff")});

        mSplPhoto.setOnRefreshListener(() ->
                mPhotoPersenter.handleData());


        mPhotoEmpty.setOnClickListener(v -> {
            loadData();
        });
    }

    @Override
    public void loadData() {
        mPhotoEmpty.setVisibility(View.GONE);
        mAnimationDrawable = (AnimationDrawable) mPhotoLoading.getBackground();
        mAnimationDrawable.start();
        mPhotoPersenter.handleData();
    }

    @Override
    public void showData(PhotoInfo datas) {
        mSplPhoto.setRefreshing(false);
        hindLoad();
        if (datas != null && datas.getResults() != null) {
            mPhotos.clear();
            mPhotos.addAll(datas.getResults());
            mPhotoAdapter.notifyDataSetChanged();
            mIsAllLoaded = false;
        }
    }

    @Override
    public void showLoadData(PhotoInfo datas) {
        hindLoad();
        int start = mPhotos.size();
        if (datas != null && datas.getResults() != null) {
            mPhotos.addAll(datas.getResults());
//            mPhotoAdapter.notifyItemRangeInserted(start, mPhotos.size());
            mPhotoAdapter.notifyDataSetChanged();
        } else if (datas != null && datas.getResults().size() == 0) {
            mIsAllLoaded = true;
            Snackbar.make(mRvPhoto, getString(R.string.no_more), Snackbar.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showRefreshData(PhotoInfo datas) {

    }

    @Override
    public void showError(Throwable throwable) {
        mSplPhoto.setVisibility(View.GONE);
        mPhotoEmpty.setVisibility(View.VISIBLE);
        hindLoad();
    }

    private void setRvScrollEvent() {
        mRvPhoto.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();

                int[] lastVisibleItemPosition = ((StaggeredGridLayoutManager) layoutManager)
                        .findLastVisibleItemPositions(null);
                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();

                if (!mIsAllLoaded && visibleItemCount > 0 &&
                        (newState == RecyclerView.SCROLL_STATE_IDLE) &&
                        ((lastVisibleItemPosition[0] >= totalItemCount - 1) ||
                                (lastVisibleItemPosition[1] >= totalItemCount - 1))) {
                    mPhotoPersenter.handleLoad(String.valueOf(++page));
                    mRvPhoto.scrollToPosition(mPhotoAdapter.getItemCount() - 1);
                }
            }

        });
    }

    private void hindLoad() {
        mAnimationDrawable.stop();
        mPhotoLoading.setVisibility(View.GONE);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        if (mAnimationDrawable != null) {
            mAnimationDrawable.stop();
        }

        if (mPhotoPersenter != null) {
            mPhotoPersenter.cancel();
        }

    }
}

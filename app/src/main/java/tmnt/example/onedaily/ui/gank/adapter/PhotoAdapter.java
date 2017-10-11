package tmnt.example.onedaily.ui.gank.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.List;

import tmnt.example.onedaily.R;
import tmnt.example.onedaily.bean.gank.PhotoInfo;
import tmnt.example.onedaily.ui.gank.listener.OnPhotoClickLisener;
import tmnt.example.onedaily.ui.gank.viewHolder.PhotoFooterHolder;
import tmnt.example.onedaily.ui.gank.viewHolder.PhotoHolder;
import tmnt.example.onedaily.util.ScreenUtils;

/**
 * Created by tmnt on 2017/10/11.
 */

public class PhotoAdapter extends RecyclerView.Adapter {
    private List<PhotoInfo.ResultsBean> mData;
    private Context mContext;
    private boolean mIsShowFooter;
    private OnPhotoClickLisener mOnPhotoClickLisener;
    private PhotoFooterHolder photoFooterHolder;

    public PhotoAdapter(List<PhotoInfo.ResultsBean> data, Context context) {
        mData = data;
        mContext = context;
    }

    public void setOnPhotoClickLisener(OnPhotoClickLisener onPhotoClickLisener) {
        mOnPhotoClickLisener = onPhotoClickLisener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_photo_item, parent, false);
        PhotoHolder photoHolder = new PhotoHolder(view, mContext);
        return photoHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            PhotoHolder photoHolder = (PhotoHolder) holder;
            photoHolder.setData(mData.get(position));
            photoHolder.setOnPhotoClickLisener(mOnPhotoClickLisener);
            photoHolder.setOperation(position);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, ScreenUtils.getScreenWidth(mContext) / 2
                    + (int) (Math.random() * 100));
            photoHolder.setLayoytParamer(lp);

    }

    @Override
    public int getItemCount() {

        int itemSize = mData.size();

        return itemSize;
    }

    private boolean isFooterPosition(int position) {
        return (getItemCount() - 1) == position;
    }
}

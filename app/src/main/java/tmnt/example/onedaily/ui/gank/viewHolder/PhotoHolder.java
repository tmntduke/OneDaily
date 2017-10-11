package tmnt.example.onedaily.ui.gank.viewHolder;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;

import tmnt.example.onedaily.R;
import tmnt.example.onedaily.bean.common.Image;
import tmnt.example.onedaily.bean.gank.PhotoInfo;
import tmnt.example.onedaily.ui.common.BaseViewHolder;
import tmnt.example.onedaily.ui.gank.listener.OnPhotoClickLisener;

/**
 * Created by tmnt on 2017/10/11.
 */

public class PhotoHolder extends BaseViewHolder<PhotoInfo.ResultsBean> {
    private ImageView mImage;
    private Context mContext;
    private OnPhotoClickLisener mOnPhotoClickLisener;

    public PhotoHolder(View itemView, Context context) {
        super(itemView, 0, context);
        this.mContext = context;
        mImage = (ImageView) itemView.findViewById(R.id.iv_photo);
    }

    public void setOnPhotoClickLisener(OnPhotoClickLisener onPhotoClickLisener) {
        mOnPhotoClickLisener = onPhotoClickLisener;
    }

    @Override
    public void setData(PhotoInfo.ResultsBean resultsBean) {
        Glide.with(mContext)
                .load(resultsBean.getUrl())
                .placeholder(R.drawable.img_transition_default)
                .crossFade()
                .into(mImage);
    }

    @Override
    public void setOperation(int position) {
        mImage.setOnClickListener(v -> {
            if (mOnPhotoClickLisener != null) {
                mOnPhotoClickLisener.onClick(v, position);
            }
        });
    }

    public void setLayoytParamer(LinearLayout.LayoutParams layoytParamer) {
        mImage.setLayoutParams(layoytParamer);
    }
}

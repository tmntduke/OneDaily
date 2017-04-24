package tmnt.example.onedaily.ui.zhihu.viewHolder;

import android.content.Context;
import android.view.View;

import tmnt.example.onedaily.R;
import tmnt.example.onedaily.bean.zhihu.TopStories;
import tmnt.example.onedaily.bean.zhihu.ZhihuDetailInfo;
import tmnt.example.onedaily.bean.zhihu.ZhihuInfo;
import tmnt.example.onedaily.ui.common.BaseViewHolder;
import tmnt.example.onedaily.ui.zhihu.listener.OnSlideItemClickListener;
import tmnt.example.onedaily.weight.Slideshow.SlideshowView;

/**
 * Created by tmnt on 2017/4/24.
 */

public class HeaderViewHolder extends BaseViewHolder<ZhihuInfo> {

    private SlideshowView mSlideshowView;
    private OnSlideItemClickListener mOnSlideItemClickListener;

    public HeaderViewHolder(View itemView) {
        super(itemView);
        mSlideshowView = (SlideshowView) itemView.findViewById(R.id.slide_zhihu);
    }

    public void setOnSlideItemClickListener(OnSlideItemClickListener onSlideItemClickListener) {
        mOnSlideItemClickListener = onSlideItemClickListener;
    }

    @Override
    public void setData(Context context, ZhihuInfo zhihuDetailInfo) {
        mSlideshowView.setData(new ImageHolder(), zhihuDetailInfo.getTop_stories());
    }

    @Override
    public void setOperation(int position) {

        mSlideshowView.setOnItemClickListener((v, position1) -> {
            if (mOnSlideItemClickListener != null) {
                mOnSlideItemClickListener.onItemClick(v, position);
            }
        });

    }
}

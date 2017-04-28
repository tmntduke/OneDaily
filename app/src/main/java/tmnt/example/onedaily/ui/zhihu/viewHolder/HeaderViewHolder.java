package tmnt.example.onedaily.ui.zhihu.viewHolder;

import android.content.Context;
import android.view.View;

import java.util.List;

import tmnt.example.onedaily.R;
import tmnt.example.onedaily.bean.zhihu.TopStories;
import tmnt.example.onedaily.bean.zhihu.ZhihuDetailInfo;
import tmnt.example.onedaily.bean.zhihu.ZhihuInfo;
import tmnt.example.onedaily.ui.common.BaseViewHolder;
import tmnt.example.onedaily.ui.zhihu.listener.OnZhihuItemClickListener;
import tmnt.example.onedaily.weight.Slideshow.SlideshowView;

/**
 * Created by tmnt on 2017/4/24.
 */

public class HeaderViewHolder extends BaseViewHolder<List<TopStories>> {

    private int type;
    private SlideshowView mSlideshowView;
    private OnZhihuItemClickListener mOnSlideItemClickListener;

    public HeaderViewHolder(View itemView, int type, Context context) {
        super(itemView, type, context);
        mSlideshowView = (SlideshowView) itemView.findViewById(R.id.slide_zhihu);
    }


    public void setOnSlideItemClickListener(OnZhihuItemClickListener onSlideItemClickListener) {
        mOnSlideItemClickListener = onSlideItemClickListener;
    }

    @Override
    public void setData(List<TopStories> zhihuDetailInfo) {
        mSlideshowView.setData(new ImageHolder(), zhihuDetailInfo);
    }

    @Override
    public void setOperation(int position) {

        mSlideshowView.setOnItemClickListener((v, position1) -> {
            if (mOnSlideItemClickListener != null) {
                mOnSlideItemClickListener.onItemSlideClick(v, position1);
            }
        });
    }
}

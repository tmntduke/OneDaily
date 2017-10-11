package tmnt.example.onedaily.ui.gank.viewHolder;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.View;
import android.widget.ImageView;

import tmnt.example.onedaily.R;
import tmnt.example.onedaily.ui.common.BaseViewHolder;

/**
 * Created by tmnt on 2017/10/11.
 */

public class PhotoFooterHolder extends BaseViewHolder<String> {
    private ImageView mImageView;
    private AnimationDrawable mAnimationDrawable;

    public PhotoFooterHolder(View itemView, int type, Context context) {
        super(itemView, type, context);
        mImageView = (ImageView) itemView.findViewById(R.id.photo_loading);
    }

    @Override
    public void setData(String s) {

    }

    @Override
    public void setOperation(int position) {
        mAnimationDrawable = (AnimationDrawable) mImageView.getBackground();
        mAnimationDrawable.start();
    }

    public void stop() {
        mAnimationDrawable.stop();
    }
}

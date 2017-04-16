package tmnt.example.onedaily.weight.Slideshow;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by tmnt on 2017/4/15.
 */

public class SlideshowAdapter extends PagerAdapter {
    private List<ImageBean> mDatas;
    private Holder mHolder;
    private Context mContext;

    private OnItemClickListener mOnItemClickListener;

    private static final String TAG = "SlideshowAdapter";

    public SlideshowAdapter(List<ImageBean> datas, Holder holder, Context context) {
        mDatas = datas;
        mHolder = holder;
        mContext = context;
        Log.i(TAG, "SlideshowAdapter: " + mDatas);
        Log.i(TAG, "image: " + mDatas.get(2).getImageView() + " " + mDatas.get(2).getT());
        Log.i(TAG, "holder: "+mHolder);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public int getCount() {
        Log.i(TAG, "getCount: " + mDatas.size());
        return mDatas.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Log.i(TAG, "instantiateItem: " + position);
        ImageBean imageBean = mDatas.get(position);

        ImageView imageView = imageBean.getImageView();
        mHolder.UpdateUI(mContext, position, imageBean.getT(),imageView);
        container.addView(imageView);


        imageBean.getImageView().setOnClickListener(v -> {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(v, position);
            }
        });


        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mDatas.get(position).getImageView());//删除页卡
    }
}

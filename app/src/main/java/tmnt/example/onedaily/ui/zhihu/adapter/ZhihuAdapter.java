package tmnt.example.onedaily.ui.zhihu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import tmnt.example.onedaily.R;
import tmnt.example.onedaily.bean.zhihu.Story;
import tmnt.example.onedaily.bean.zhihu.TopStories;
import tmnt.example.onedaily.bean.zhihu.ZhihuInfo;
import tmnt.example.onedaily.ui.common.BaseViewHolder;
import tmnt.example.onedaily.ui.zhihu.listener.OnZhihuItemClickListener;
import tmnt.example.onedaily.ui.zhihu.viewHolder.HeaderViewHolder;
import tmnt.example.onedaily.ui.zhihu.viewHolder.NewsViewHolder;
import tmnt.example.onedaily.ui.zhihu.viewHolder.ViewHolderFactory;
import tmnt.example.onedaily.util.DateFormatUtil;

/**
 * Created by tmnt on 2017/4/25.
 */

public class ZhihuAdapter extends RecyclerView.Adapter {

    private List<Story> mStories;
    private List<TopStories> mTopStories;
    private Context mContext;
    private String date;
    private OnZhihuItemClickListener mOnZhihuItemClickListener;
    private HeaderViewHolder headerViewHolder;
    private int count = 0;
    private SparseArray<String> sameDate;

    private static final String TAG = "ZhihuAdapter";
    private boolean isDate = false;

    public static final int IS_HEADER = 2;
    public static final int IS_NORMAL = 1;

    public ZhihuAdapter(List<Story> stories, List<TopStories> topStories, Context context) {
        mStories = stories;
        mTopStories = topStories;
        mContext = context;
        sameDate = new SparseArray<>();
    }

    public void setDate(String date, boolean isDate) {

        this.date = date;
        this.isDate = isDate;


    }

    public void setOnZhihuItemClickListener(OnZhihuItemClickListener onZhihuItemClickListener) {
        mOnZhihuItemClickListener = onZhihuItemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == IS_HEADER) {
            BaseViewHolder<List<TopStories>> header = ViewHolderFactory.create(IS_HEADER, mContext, parent);
            return header;
        } else {
            BaseViewHolder<List<TopStories>> item = ViewHolderFactory.create(IS_NORMAL, mContext, parent);
            return item;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        BaseViewHolder baseViewHolder = (BaseViewHolder) holder;
        if (position == 0 && baseViewHolder.type == IS_HEADER) {
            headerViewHolder = (HeaderViewHolder) baseViewHolder;
            headerViewHolder.setData(mTopStories);
            headerViewHolder.setOnSlideItemClickListener(mOnZhihuItemClickListener);
            headerViewHolder.setOperation(position);
            mTopStories.clear();

        } else {
            Log.i(TAG, "onBindViewHolder: position" + position);
            NewsViewHolder newsViewHolder = (NewsViewHolder) baseViewHolder;
            newsViewHolder.setData(mStories.get(position - 1));
            newsViewHolder.setDate(DateFormatUtil.dateFormatForWeek(date));
            Log.i(TAG, "onBindViewHolder: " + date);
            if (sameDate != null && sameDate.size() != 0 && position >= 1) {
                Log.i(TAG, "onBindViewHolder: same" + sameDate.get(position));
                if (position == 1 || !sameDate.get(position - 2).equals(sameDate.get(position - 1))) {
                    newsViewHolder.setDate(DateFormatUtil.dateFormatForWeek(sameDate.get(position)));
                    newsViewHolder.visiDate();
                } else {
                    newsViewHolder.goneDate();
                }
            }
            newsViewHolder.setOnCardItemClickListener(mOnZhihuItemClickListener);
            newsViewHolder.setOperation(position - 1);
            isDate = false;

        }
    }

    @Override
    public void onViewDetachedFromWindow(RecyclerView.ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);

    }

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {

    }

    @Override
    public int getItemCount() {
        return mStories.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return IS_HEADER;
        } else {
            return IS_NORMAL;
        }
    }

    public void notityData() {
        notifyDataSetChanged();

        Log.i(TAG, "notityData: noti" + date);
        if (mStories != null && mStories.size() != 0) {
            for (int i = count; i < mStories.size(); i++) {
                sameDate.put(i, date);
            }
            count = mStories.size() - 1;
            Log.i(TAG, "notityData: size" + sameDate.size());
            Log.i(TAG, "notityData: " + mStories.size());
        }
    }
}

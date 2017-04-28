package tmnt.example.onedaily.ui.zhihu.viewHolder;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import tmnt.example.onedaily.R;
import tmnt.example.onedaily.bean.zhihu.Story;
import tmnt.example.onedaily.ui.common.BaseViewHolder;
import tmnt.example.onedaily.ui.zhihu.listener.OnZhihuItemClickListener;
import tmnt.example.onedaily.util.SharedPreferencesUtil;

/**
 * Created by tmnt on 2017/4/24.
 */

public class NewsViewHolder extends BaseViewHolder<Story> {

    private CardView cdNews;
    private TextView tvTilte;
    private ImageView imgCover;
    private TextView tvDate;
    private boolean isDate;

    private String date;
    private OnZhihuItemClickListener mOnCardItemClickListener;
    private SharedPreferencesUtil util;

    public static final String DATE = "date";
    private static final String TAG = "NewsViewHolder";

    public NewsViewHolder(View itemView, int type, Context context) {
        super(itemView, type, context);
        cdNews = (CardView) itemView.findViewById(R.id.cd_news);
        tvTilte = (TextView) itemView.findViewById(R.id.tv_zhihu_title);
        imgCover = (ImageView) itemView.findViewById(R.id.img_zhihu_cover);
        tvDate = (TextView) itemView.findViewById(R.id.tv_zhihu_date);
        util = SharedPreferencesUtil.getInstance(context);
    }

    public void setOnCardItemClickListener(OnZhihuItemClickListener onCardItemClickListener) {
        mOnCardItemClickListener = onCardItemClickListener;
    }

    public void setDate(String date) {
        this.date = date;
        tvDate.setText(date);
    }

    @Override
    public void setData(Story story) {
        Log.i(TAG, "setData: " + date);
        tvTilte.setText(story.getTitle());

        Glide.with(mContext).load(story.getImages().get(0)).into(imgCover);

    }

    @Override
    public void setOperation(int position) {
        cdNews.setOnClickListener(v -> {
            if (mOnCardItemClickListener != null) {
                mOnCardItemClickListener.onItemCardClick(v, position);
            }
        });
    }

    public void goneDate() {
        tvDate.setVisibility(View.GONE);
    }

    public void visiDate() {
        tvDate.setVisibility(View.VISIBLE);
    }
}

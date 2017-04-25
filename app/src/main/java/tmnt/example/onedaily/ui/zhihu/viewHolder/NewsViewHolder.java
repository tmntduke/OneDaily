package tmnt.example.onedaily.ui.zhihu.viewHolder;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import tmnt.example.onedaily.R;
import tmnt.example.onedaily.bean.zhihu.Story;
import tmnt.example.onedaily.ui.common.BaseViewHolder;
import tmnt.example.onedaily.ui.zhihu.listener.OnZhihuItemClickListener;

/**
 * Created by tmnt on 2017/4/24.
 */

public class NewsViewHolder extends BaseViewHolder<Story> {

    private CardView cdNews;
    private TextView tvTilte;
    private ImageView imgCover;
    private TextView tvDate;

    private int type;

    private String date;
    private OnZhihuItemClickListener mOnCardItemClickListener;

    public NewsViewHolder(View itemView ,int type) {
        super(itemView,type);
        this.type=type;
        cdNews = (CardView) itemView.findViewById(R.id.cd_news);
        tvTilte = (TextView) itemView.findViewById(R.id.tv_zhihu_title);
        imgCover = (ImageView) itemView.findViewById(R.id.img_zhihu_cover);
        tvDate = (TextView) itemView.findViewById(R.id.tv_zhihu_date);
    }

    public void setOnCardItemClickListener(OnZhihuItemClickListener onCardItemClickListener) {
        mOnCardItemClickListener = onCardItemClickListener;
    }

    @Override
    public void setData(Context context, Story story) {
        tvTilte.setText(story.getTitle());
        tvDate.setText(date);
        Glide.with(context).load(story.getImages().get(0)).into(imgCover);
    }

    @Override
    public void setOperation(int position) {
        cdNews.setOnClickListener(v -> {
            if (mOnCardItemClickListener != null) {
                mOnCardItemClickListener.onItemCardClick(v, position);
            }
        });
    }

    public void setDate(String date) {
        this.date = date;
    }
}

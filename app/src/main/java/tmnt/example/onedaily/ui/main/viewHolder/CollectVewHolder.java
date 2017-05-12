package tmnt.example.onedaily.ui.main.viewHolder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import tmnt.example.onedaily.R;
import tmnt.example.onedaily.bean.msg.Collect;
import tmnt.example.onedaily.ui.common.BaseViewHolder;
import tmnt.example.onedaily.ui.main.listener.OnCollectItemListener;

/**
 * Created by tmnt on 2017/5/12.
 */

public class CollectVewHolder extends BaseViewHolder<Collect> {

    private TextView title;
    private TextView author;
    private ImageView cover;
    private LinearLayout contain;
    private OnCollectItemListener mOnCollectItemListener;

    public CollectVewHolder(View itemView, int type, Context context) {
        super(itemView, type, context);
        title = (TextView) itemView.findViewById(R.id.tv_collect_title);
        author = (TextView) itemView.findViewById(R.id.tv_collect_author);
        cover = (ImageView) itemView.findViewById(R.id.img_collect_cover);
        contain = (LinearLayout) itemView.findViewById(R.id.collect_contain);
    }

    public void setOnCollectItemListener(OnCollectItemListener onCollectItemListener) {
        mOnCollectItemListener = onCollectItemListener;
    }

    @Override
    public void setData(Collect collect) {

        title.setText(collect.getTitle());
        author.setText(collect.getAuthor());
        Glide.with(mContext).load(collect.getImage()).into(cover);

    }

    @Override
    public void setOperation(int position) {
        contain.setOnClickListener(v -> {
            if (mOnCollectItemListener != null) {
                mOnCollectItemListener.onItemClick(v, position);
            }
        });
    }
}

package tmnt.example.onedaily.ui.douban.viewHolder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import tmnt.example.onedaily.R;
import tmnt.example.onedaily.ui.common.BaseViewHolder;
import tmnt.example.onedaily.ui.douban.adapter.ItemDragHelperCallback;
import tmnt.example.onedaily.ui.douban.listener.OnTextClickLlistener;

/**
 * Created by tmnt on 2017/9/17.
 */

public class BookCategoryHolder extends BaseViewHolder<String> {
    private TextView mText;
    private OnTextClickLlistener mOnTextClickLlistener;
    private boolean isCanMove;
    private ItemDragHelperCallback mItemDragHelperCallback;

    public BookCategoryHolder(View itemView, boolean isCanMove, Context context, int type) {
        super(itemView, type, context);
        mText = (TextView) itemView.findViewById(R.id.tv_category);
        this.isCanMove = isCanMove;
    }

    public void setItemDragHelperCallback(ItemDragHelperCallback itemDragHelperCallback) {
        mItemDragHelperCallback = itemDragHelperCallback;
    }

    public void setOnTextClickLlistener(OnTextClickLlistener onTextClickLlistener) {
        mOnTextClickLlistener = onTextClickLlistener;
    }

    public void setData(String title) {
        mText.setText(title);
    }

    @Override
    public void setOperation(int position) {
        mText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnTextClickLlistener != null) {
                    mOnTextClickLlistener.onClick(v, position);
                }
            }
        });
        mText.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mOnTextClickLlistener != null) {
                    mOnTextClickLlistener.onLongClick(v, position);
                    if (isCanMove)
                        mItemDragHelperCallback.setLongPressEnabled(true);
                }
                return true;
            }
        });
    }

}

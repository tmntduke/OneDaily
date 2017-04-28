package tmnt.example.onedaily.ui.common;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by tmnt on 2017/4/18.
 */

public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {

    public int type;
    protected Context mContext;

    public BaseViewHolder(View itemView, int type, Context context) {
        super(itemView);
        this.type = type;
        this.mContext = context;
    }

    /**
     * 设置数据
     */
    public abstract void setData(T t);

    public abstract void setOperation(int position);
}

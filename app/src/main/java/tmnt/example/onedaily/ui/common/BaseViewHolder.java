package tmnt.example.onedaily.ui.common;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by tmnt on 2017/4/18.
 */

public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {

    public int type;

    public BaseViewHolder(View itemView, int type) {
        super(itemView);
        this.type = type;
    }

    /**
     * 设置数据
     */
    public abstract void setData(Context context, T t);

    public abstract void setOperation(int position);
}

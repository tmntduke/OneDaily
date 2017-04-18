package tmnt.example.onedaily.ui.common;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by tmnt on 2017/4/18.
 */

public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    /**
     * 设置数据
     */
    public abstract void setData(Context context, T t);

    public abstract void setOperation(View v, int position);
}

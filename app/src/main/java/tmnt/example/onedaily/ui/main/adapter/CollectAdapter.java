package tmnt.example.onedaily.ui.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import tmnt.example.onedaily.R;
import tmnt.example.onedaily.bean.msg.Collect;
import tmnt.example.onedaily.ui.main.listener.OnCollectItemListener;
import tmnt.example.onedaily.ui.main.viewHolder.CollectVewHolder;

/**
 * Created by tmnt on 2017/5/12.
 */

public class CollectAdapter extends RecyclerView.Adapter {

    private List<Collect> mCollects;
    private Context mContext;
    private OnCollectItemListener mOnCollectItemListener;

    public CollectAdapter(List<Collect> collects, Context context) {
        mCollects = collects;
        mContext = context;
    }

    public void setOnCollectItemListener(OnCollectItemListener onCollectItemListener) {
        mOnCollectItemListener = onCollectItemListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_collect_item, parent, false);
        CollectVewHolder viewHolder = new CollectVewHolder(view, 0, mContext);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        CollectVewHolder vewHolder = (CollectVewHolder) holder;
        vewHolder.setData(mCollects.get(position));
        vewHolder.setOperation(position);
        vewHolder.setOnCollectItemListener(mOnCollectItemListener);
    }

    @Override
    public int getItemCount() {
        return mCollects.size();
    }
}

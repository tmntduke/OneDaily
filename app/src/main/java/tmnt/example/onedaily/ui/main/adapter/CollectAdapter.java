package tmnt.example.onedaily.ui.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

import tmnt.example.onedaily.bean.msg.Collect;

/**
 * Created by tmnt on 2017/5/12.
 */

public class CollectAdapter extends RecyclerView.Adapter {

    private List<Collect> mCollects;
    private Context mContext;

    public CollectAdapter(List<Collect> collects, Context context) {
        mCollects = collects;
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}

package tmnt.example.onedaily.ui.douban.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import tmnt.example.onedaily.R;

/**
 * Created by tmnt on 2017/4/23.
 */

public class BookHistoryAdapter extends BaseAdapter {

    private Context mContext;
    private List<String> mStringList;

    public BookHistoryAdapter(Context context, List<String> stringList) {
        mContext = context;
        mStringList = stringList;
    }

    @Override
    public int getCount() {
        return mStringList.size();
    }

    @Override
    public Object getItem(int position) {
        return mStringList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.list_search_history, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tvHistory = (TextView) view.findViewById(R.id.tv_search_history);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.tvHistory.setText(mStringList.get(position));

        return view;
    }

    static class ViewHolder {
        TextView tvHistory;
    }
}

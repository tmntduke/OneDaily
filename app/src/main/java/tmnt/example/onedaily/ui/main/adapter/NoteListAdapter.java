package tmnt.example.onedaily.ui.main.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import tmnt.example.onedaily.R;
import tmnt.example.onedaily.bean.note.NoteInfo;
import tmnt.example.onedaily.ui.main.listener.OnNoteItemClickListener;
import tmnt.example.onedaily.ui.main.viewHolder.NoteListViewHolder;

/**
 * Created by tmnt on 2017/5/11.
 */

public class NoteListAdapter extends RecyclerView.Adapter {

    private List<NoteInfo> mNoteInfos;
    private Context mContext;
    private OnNoteItemClickListener mOnNoteItemClickListener;
    private static final String TAG = "NoteListAdapter";

    public NoteListAdapter(List<NoteInfo> noteInfos, Context context) {
        mNoteInfos = noteInfos;
        mContext = context;
    }

    public void setOnNoteItemClickListener(OnNoteItemClickListener onNoteItemClickListener) {
        mOnNoteItemClickListener = onNoteItemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_note_item, parent, false);
        NoteListViewHolder viewHolder = new NoteListViewHolder(view, 0, mContext);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Log.i(TAG, "onBindViewHolder: "+position);
        NoteListViewHolder noteListViewHolder = (NoteListViewHolder) holder;
        Log.i(TAG, "onBindViewHolder: "+mNoteInfos.size());
        noteListViewHolder.setData(mNoteInfos.get(position));
        noteListViewHolder.setOperation(position);
        noteListViewHolder.setOnNoteItemClickListener(mOnNoteItemClickListener);
    }

    @Override
    public int getItemCount() {
        return mNoteInfos.size();
    }
}

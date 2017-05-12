package tmnt.example.onedaily.ui.main.viewHolder;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import tmnt.example.onedaily.R;
import tmnt.example.onedaily.bean.msg.NoteInfo;
import tmnt.example.onedaily.ui.common.BaseViewHolder;
import tmnt.example.onedaily.ui.main.listener.OnNoteItemClickListener;

/**
 * Created by tmnt on 2017/5/11.
 */

public class NoteListViewHolder extends BaseViewHolder<NoteInfo> {

    private TextView title;
    private TextView date;
    private LinearLayout contain;
    private OnNoteItemClickListener mOnNoteItemClickListener;

    public NoteListViewHolder(View itemView, int type, Context context) {
        super(itemView, type, context);
        title = (TextView) itemView.findViewById(R.id.tv_note_title);
        date = (TextView) itemView.findViewById(R.id.tv_note_time);
        contain = (LinearLayout) itemView.findViewById(R.id.note_list_contain);
    }

    @Override
    public void setData(NoteInfo noteInfo) {
        title.setText(noteInfo.getTitle());
        date.setText(noteInfo.getDate());
    }

    @Override
    public void setOperation(int position) {
        contain.setOnClickListener(v -> {
            if (mOnNoteItemClickListener != null) {
                mOnNoteItemClickListener.onItemClick(v, position);
            }
        });
    }

    public void setOnNoteItemClickListener(OnNoteItemClickListener onNoteItemClickListener) {
        mOnNoteItemClickListener = onNoteItemClickListener;
    }
}

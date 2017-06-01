package tmnt.example.onedaily.ui.turing.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;

import java.util.List;

import tmnt.example.onedaily.bean.chat.ChatInfo;
import tmnt.example.onedaily.ui.common.BaseViewHolder;
import tmnt.example.onedaily.ui.turing.viewHolder.ViewHolderFactory;

/**
 * Created by tmnt on 2017/6/1.
 */

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ChatInfo> mChat;
    private Context mContext;
    public final static int SENG_TYPE = 101;
    public final static int RECEIVE_TYPE = 201;

    private static final String TAG = "ChatAdapter";

    public ChatAdapter(List<ChatInfo> chat, Context context) {
        mChat = chat;
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.i(TAG, "onCreateViewHolder: "+viewType);
        BaseViewHolder baseViewHolder = ViewHolderFactory.create(viewType, mContext, parent);
        return baseViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        BaseViewHolder baseViewHolder = (BaseViewHolder) holder;
        baseViewHolder.setData(mChat.get(position).getMsg());
    }

    @Override
    public int getItemCount() {
        return mChat.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (mChat != null && mChat.size() != 0) {
            if (SENG_TYPE == mChat.get(position).getType()) {
                return SENG_TYPE;
            } else if (RECEIVE_TYPE == mChat.get(position).getType()) {
                return RECEIVE_TYPE;
            }
        }
        return 0;
    }
}

package tmnt.example.onedaily.ui.turing.viewHolder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tmnt.example.onedaily.R;
import tmnt.example.onedaily.ui.common.BaseViewHolder;
import tmnt.example.onedaily.ui.turing.adapter.ChatAdapter;

/**
 * Created by tmnt on 2017/6/1.
 */

public class ViewHolderFactory {
    public static BaseViewHolder create(int type, Context context, ViewGroup contain) {
        if (ChatAdapter.SENG_TYPE == type) {
            View view = LayoutInflater.from(context).inflate(R.layout.right_text_chat_item, contain, false);
            ChatSendViewHolder chatSendViewHolder = new ChatSendViewHolder(view, 0, context);
            return chatSendViewHolder;
        } else if (ChatAdapter.RECEIVE_TYPE == type) {
            View view = LayoutInflater.from(context).inflate(R.layout.left_text_chat_item, contain, false);
            ChatReceiveViewHolder chatReceiveViewHolder = new ChatReceiveViewHolder(view, 0, context);
            return chatReceiveViewHolder;
        }
        return null;
    }
}

package tmnt.example.onedaily.ui.turing.viewHolder;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import tmnt.example.onedaily.R;
import tmnt.example.onedaily.ui.common.BaseViewHolder;

/**
 * Created by tmnt on 2017/6/1.
 */

public class ChatSendViewHolder extends BaseViewHolder<String> {

    private TextView send;

    public ChatSendViewHolder(View itemView, int type, Context context) {
        super(itemView, type, context);
        send= (TextView) itemView. findViewById(R.id.tv_right_content);
    }

    @Override
    public void setData(String s) {
       send.setText(s);
    }

    @Override
    public void setOperation(int position) {

    }
}

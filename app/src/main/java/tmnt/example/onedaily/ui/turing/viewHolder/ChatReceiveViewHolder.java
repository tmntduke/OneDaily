package tmnt.example.onedaily.ui.turing.viewHolder;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import tmnt.example.onedaily.R;
import tmnt.example.onedaily.ui.common.BaseViewHolder;

/**
 * Created by tmnt on 2017/6/1.
 */

public class ChatReceiveViewHolder extends BaseViewHolder<String> {

    private TextView receive;

    public ChatReceiveViewHolder(View itemView, int type, Context context) {
        super(itemView, type, context);
        receive = (TextView) itemView.findViewById(R.id.tv_left_content);
    }

    @Override
    public void setData(String s) {
        receive.setText(s);
    }

    @Override
    public void setOperation(int position) {

    }
}

package tmnt.example.onedaily.ui.main.model;

import android.content.Context;

import java.util.List;

import tmnt.example.onedaily.bean.msg.Collect;
import tmnt.example.onedaily.bean.msg.NoteInfo;
import tmnt.example.onedaily.db.OneDailyDB;
import tmnt.example.onedaily.mvp.CallBack;
import tmnt.example.onedaily.mvp.Model;

/**
 * Created by tmnt on 2017/5/12.
 */

public class MsgListModel<T> implements Model<List<T>> {

    private Context mContext;
    private OneDailyDB mOneDailyDB;
    private int type;
    public static final int COLLECT_TYPE = 100;
    public static final int NOTE_TYPE = 200;

    public MsgListModel(Context context, int type) {
        mContext = context;
        this.type = type;
        mOneDailyDB = OneDailyDB.newInstance(context);
    }

    @Override
    public void getNews(CallBack<List<T>> callBack) {
        if (type == COLLECT_TYPE) {
            mOneDailyDB.queryMsg(callBack, OneDailyDB.TABLE_COLLECT, Collect.class);
        } else if (type == NOTE_TYPE) {
            mOneDailyDB.queryMsg(callBack, OneDailyDB.TABLE_NOTE, NoteInfo.class);
        }
    }

    @Override
    public void refresh(CallBack callBack) {

    }

    @Override
    public void load(String page, CallBack callBack) {

    }
}

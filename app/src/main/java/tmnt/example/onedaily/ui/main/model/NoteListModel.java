package tmnt.example.onedaily.ui.main.model;

import android.content.Context;

import java.util.List;

import tmnt.example.onedaily.bean.note.NoteInfo;
import tmnt.example.onedaily.db.OneDailyDB;
import tmnt.example.onedaily.mvp.CallBack;
import tmnt.example.onedaily.mvp.Model;

/**
 * Created by tmnt on 2017/5/11.
 */

public class NoteListModel implements Model<List<NoteInfo>> {

    private Context mContext;
    private OneDailyDB mOneDailyDB;

    public NoteListModel(Context context) {
        mContext = context;
        mOneDailyDB = OneDailyDB.newInstance(context);
    }

    @Override
    public void getNews(CallBack<List<NoteInfo>> callBack) {
        mOneDailyDB.queryNote(callBack);
    }

    @Override
    public void refresh(CallBack<List<NoteInfo>> callBack) {

    }

    @Override
    public void load(String page, CallBack<List<NoteInfo>> callBack) {

    }
}

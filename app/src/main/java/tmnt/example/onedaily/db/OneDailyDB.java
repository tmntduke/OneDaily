package tmnt.example.onedaily.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import rx.schedulers.Schedulers;
import tmnt.example.onedaily.Rx.Operation;
import tmnt.example.onedaily.Rx.RxUilt;
import tmnt.example.onedaily.mvp.CallBack;

/**
 * Created by tmnt on 2017/4/23.
 */

public class OneDailyDB {
    private Context mContext;
    private SQLiteDatabase mDatabase;
    private DBHelper helper;
    private static final String HISTORY = "history";
    private static final String TABLE = "t_searchHistory";
    private static final String ID = "hId";

    private static OneDailyDB mOneDailyDB;

    private static final String TAG = "OneDailyDB";
    private RxUilt mRxUilt;

    private OneDailyDB(Context context) {
        mContext = context;
        helper = new DBHelper(context);
        mRxUilt=RxUilt.getInstance();

    }

    public static OneDailyDB newInstance(Context context) {
        if (mOneDailyDB == null) {
            mOneDailyDB = new OneDailyDB(context);
        }
        return mOneDailyDB;
    }

    public void insertHistory(String history) {
        ContentValues values = new ContentValues();
        values.put(HISTORY, history);

        mRxUilt.createAndResult(Schedulers.io(), () -> {
                    mDatabase = helper.getWritableDatabase();
                    mDatabase.insert(TABLE, ID, values);
                    return null;
                }, new CallBack<Boolean>() {
                    @Override
                    public void onSuccess(Boolean aBoolean) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                }

        );

    }

    public  void queryHistory(CallBack<List<String>> callBack) {

        mRxUilt.createAndResult(Schedulers.io(), new Operation<List<String>>() {
            @Override
            public List<String> operation() {
                ArrayList<String> arrayList = new ArrayList<>();
                mDatabase = helper.getReadableDatabase();
                Cursor cursor = mDatabase.query(TABLE, null, null, null, null,
                        null, null);
                while (cursor.moveToNext()) {
                    arrayList.add(cursor.getString(cursor.getColumnIndex(HISTORY)));
                }
                cursor.close();
                if (arrayList == null || arrayList.size() == 0) {
                    return Collections.emptyList();
                }
                return arrayList;
            }
        }, callBack);
    }

    public void clearHistory() {
        mDatabase = helper.getReadableDatabase();
        mDatabase.execSQL("delete from t_searchHistory");
        mDatabase.execSQL("update sqlite_sequence SET seq = 0 where name ='t_searchHistory'");
    }
}

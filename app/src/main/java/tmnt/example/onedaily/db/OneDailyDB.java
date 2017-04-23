package tmnt.example.onedaily.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
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

    private OneDailyDB(Context context) {
        mContext = context;
        helper = new DBHelper(context);

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

        new RxUilt<Boolean>().createAndResult(Schedulers.io(), new Operation<Boolean>() {
            @Override
            public Boolean operation() {
                mDatabase = helper.getWritableDatabase();
                mDatabase.insert(TABLE, ID, values);
                return null;
            }

            @Override
            public void onSuccess(Boolean contentValues) {

            }

            @Override
            public void onError(Throwable throwable) {

            }
        });

    }

    public void queryHistory(CallBack<List<String>> callBack) {

        new RxUilt<List<String>>().createAndResult(Schedulers.io(), new Operation<List<String>>() {
            @Override
            public List<String> operation() {
                ArrayList<String> arrayList = new ArrayList<>();
                mDatabase = helper.getReadableDatabase();
                Cursor cursor = mDatabase.query(TABLE, null, null, null, null,
                        null, null);
                while (cursor.moveToNext()) {
                    arrayList.add(cursor.getString(cursor.getColumnIndex(ID)));
                }
                cursor.close();
                return arrayList;
            }

            @Override
            public void onSuccess(List<String> list) {
                callBack.onSuccess(list);
            }

            @Override
            public void onError(Throwable throwable) {
                callBack.onError(throwable);
            }
        });

    }
}

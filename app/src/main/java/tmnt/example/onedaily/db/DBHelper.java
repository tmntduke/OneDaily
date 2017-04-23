package tmnt.example.onedaily.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by tmnt on 2017/4/23.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "one.db";
    private static final int VERSION = 1;

    private static final String CREATE = "create table t_searchHistory (hId integer PRIMARY KEY AUTOINCREMENT" +
            ", history varchar (200))";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

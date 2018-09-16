package groupdenim.cmpt276.mentalhealthlumo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {

    public static final String NAME = "DB";

    public static final String UID = "_id";

    public static final int VERSION = 1;

    public static final String TAB_NAME = "db";
    public static final String COL_INTENSITY = "intensity";
    public static final String COL_CHECK1 = "check1";
    public static final String COL_CHECK2 = "check2";
    public static final String COL_CHECK3 = "check3";
    public static final String COL_CHECK4 = "check4";
    public static final String COL_CHECK5 = "check5";
    public static final String COL_CHECK6 = "check6";
    public static final String COL_WHAT = "what";
    public static final String COL_DATE = "date";
    public static final String COL_TIME = "time";
    public static final String COL_LOCATION = "location";

    public static final String CREATE = "CREATE TABLE " + TAB_NAME + " (" +
            UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COL_INTENSITY + " TEXT, " +
            COL_CHECK1 + " TEXT, " +
            COL_CHECK2 + " TEXT, " +
            COL_CHECK3 + " TEXT, " +
            COL_CHECK4 + " TEXT, " +
            COL_CHECK5 + " TEXT, " +
            COL_CHECK6 + " TEXT, " +
            COL_WHAT + " TEXT, " +
            COL_DATE + " TEXT, " +
            COL_TIME + " TEXT, " +
            COL_LOCATION + " TEXT" + ");";

    public SQLiteHelper(Context context) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
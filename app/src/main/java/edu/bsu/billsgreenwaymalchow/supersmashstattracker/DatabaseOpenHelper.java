package edu.bsu.billsgreenwaymalchow.supersmashstattracker;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseOpenHelper extends SQLiteOpenHelper{
    private static final String DATABASE_NAME = "stats.db";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_STATS = "stats";
    public static final String TRACKER_ID = "_id";
    public static final String TRACKER_WIN = "trackerWin";
    public static final String TRACKER_LOSS = "trackerLoss";
    public static final String TRACKER_GAME_VERSION = "trackerGameVersion";
    public static final String[] ALL_COLUMNS =
            {TRACKER_ID, TRACKER_WIN, TRACKER_LOSS, TRACKER_GAME_VERSION};
    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_STATS + " (" +
                    TRACKER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    TRACKER_WIN + " INTEGER, " +
                    TRACKER_LOSS + " INTEGER," +
                    TRACKER_GAME_VERSION + "TEXT" +
                    ")";

    public DatabaseOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STATS);
        onCreate(db);
    }
}

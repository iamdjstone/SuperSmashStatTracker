package edu.bsu.billsgreenwaymalchow.supersmashstattracker;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

public class StatsProvider extends ContentProvider {

    private static final String AUTHORITY = "edu.bsu.billsgreenwaymalchow.supersmashstattracker.statsprovider";
    private static final String BASE_PATH = "stats";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + BASE_PATH );
    private static final int STATS = 1;
    private static final int STATS_ID = 2;
    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        uriMatcher.addURI(AUTHORITY, BASE_PATH, STATS);
        uriMatcher.addURI(AUTHORITY, BASE_PATH + "/#", STATS_ID);
    }

    private SQLiteDatabase sqLiteDatabase;

    @Override
    public boolean onCreate() {
        DatabaseOpenHelper databaseOpenHelper = new DatabaseOpenHelper(getContext());
        sqLiteDatabase = databaseOpenHelper.getWritableDatabase();
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return null;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}

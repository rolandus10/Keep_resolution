package com.example.keepresolutions;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ResolutionBaseSQLite extends SQLiteOpenHelper {

    private static final String TABLE_RESOLUTIONS = "table_resolutions";
    private static final String COL_ID = "ID";
    private static final String COL_NAME = "NAME";
    private static final String COL_REASON = "REASON";
    private static final String COL_DESC = "DESCRIPTION";
    private static final String COL_DAMAGE = "DAMAGE";

    private static final String CREATE_BDD = "CREATE TABLE " + TABLE_RESOLUTIONS + " (" +
            COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_NAME + " TEXT NOT NULL,"+
            COL_DESC  + " TEXT NOT NULL," + COL_REASON + " TEXT NOT NULL," + COL_DAMAGE + " TEXT NOT NULL);";

    public ResolutionBaseSQLite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super (context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BDD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + TABLE_RESOLUTIONS);
        onCreate(db);
    }
}

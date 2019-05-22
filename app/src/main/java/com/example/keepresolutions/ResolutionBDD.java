package com.example.keepresolutions;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;


public class ResolutionBDD {
    private static final int VERSION = 1;
    private static final String NOM_BDD = "resolution.db";

    private static final String TABLE_RESOLUTIONS = "table_resolutions";
    private static final String COL_ID = "ID";
    private static final int NUM_COL_ID = 0;
    private static final String COL_NAME = "NAME";
    private static final int NUM_COL_NAME = 1;
    private static final String COL_DESC = "DESCRIPTION";
    private static final int NUM_COL_DESC = 2;
    private static final String COL_REASON = "REASON";
    private static final int NUM_COL_REASON = 3;
    private static final String COL_DAMAGE = "DAMAGE";
    private static final int NUM_COL_DAMAGE = 4;



    private SQLiteDatabase bdd;

    private ResolutionBaseSQLite resolution;

    public ResolutionBDD(Context context) {
        resolution = new ResolutionBaseSQLite(context, NOM_BDD, null, VERSION);
    }

    public void openForWrite() {
        bdd = resolution.getWritableDatabase();
    }

    public void openForRead() {
        bdd = resolution.getReadableDatabase();
    }

    public void close() {
        bdd.close();
    }

    public SQLiteDatabase getBdd() {
        return bdd;
    }

    public long insertResolution(Resolution resolution) {
        ContentValues content = new ContentValues();
        content.put(COL_NAME, resolution.getName());
        content.put(COL_DESC, resolution.getDescription());
        content.put(COL_REASON, resolution.getReason());
        content.put(COL_DAMAGE, resolution.getDamage());

        return bdd.insert(TABLE_RESOLUTIONS, null, content);
    }

    public int updateResolution(int id, Resolution resolution) {
        ContentValues content = new ContentValues();
        content.put(COL_NAME, resolution.getName());
        content.put(COL_DESC, resolution.getDescription());
        content.put(COL_REASON, resolution.getReason());
        content.put(COL_DAMAGE, resolution.getDamage());


        return bdd.update(TABLE_RESOLUTIONS, content, COL_ID + " = " + id, null);
    }

    public int removeResolution(int id) {
        return bdd.delete(TABLE_RESOLUTIONS, COL_ID + " = " + id, null);
    }

    public Resolution getResolution(String name) {
        Cursor c = bdd.query(TABLE_RESOLUTIONS, new String[] { COL_ID, COL_NAME,
                        COL_DESC, COL_REASON, COL_DAMAGE}, COL_NAME + " LIKE \"" + name + "\"", null, null,
                null, COL_NAME);
        return cursorToResolution(c);
    }

    public Resolution cursorToResolution(Cursor c) {
        if (c.getCount() == 0) {
            c.close();
            return null;
        }
        Resolution resolution = new Resolution();
        resolution.setId(c.getInt(NUM_COL_ID));
        resolution.setName(c.getString(NUM_COL_NAME));
        resolution.setDescription(c.getString(NUM_COL_DESC));
        resolution.setReason(c.getString(NUM_COL_REASON));
        resolution.setDamage(c.getString(NUM_COL_DAMAGE));


        c.close();
        return resolution;
    }

    public ArrayList<Resolution> getAllResolutions() {
        Cursor c = bdd.query(TABLE_RESOLUTIONS, new String[] { COL_ID, COL_NAME,
                COL_DESC, COL_REASON, COL_DAMAGE }, null, null, null, null, COL_NAME);
        if (c.getCount() == 0) {
            c.close();
            return null;
        }
        ArrayList<Resolution> resolutionList = new ArrayList<Resolution> ();
        while (c.moveToNext()) {
            Resolution resolution = new Resolution();
            resolution.setId(c.getInt(NUM_COL_ID));
            resolution.setName(c.getString(NUM_COL_NAME));
            resolution.setDescription(c.getString(NUM_COL_DESC));
            resolution.setReason(c.getString(NUM_COL_REASON));
            resolution.setDamage(c.getString(NUM_COL_DAMAGE));

            resolutionList.add(resolution);
        }
        c.close();
        return resolutionList;
    }

    public Boolean is_not_Empty(){
        Cursor c = bdd.query(TABLE_RESOLUTIONS, new String[] { COL_ID, COL_NAME,
                COL_DESC, COL_REASON, COL_DAMAGE }, null, null, null, null, COL_NAME);
        Boolean answer = TRUE;
        if (c.getCount() == 0) {
            c.close();
            answer = FALSE;
        }

        return answer;

    }
}

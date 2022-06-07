package com.rizki.catkur;
/**
 * Tanggal Pengerjaan : 06 JUNI 2022 , 22.09 WIB
 * NIM : 10119052
 * Nama : Rizki Dwi Nugraha
 * Kelas : IF-2
 **/

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class CatkurDatabase extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "catkurdb";
    private static final String DATABASE_TABLE = "catkurtable";

    //Nama kolom untuk tabel
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_CONTENT = "content";
    private static final String KEY_DATE = "date";
    private static final String KEY_TIME = "time";

    CatkurDatabase(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE "+DATABASE_TABLE+" ("+
                KEY_ID+" INTEGER PRIMARY KEY,"+
                KEY_TITLE+" TEXT,"+
                KEY_CONTENT+" TEXT,"+
                KEY_DATE+" TEXT,"+
                KEY_TIME+" TEXT"
                +" )";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion >= newVersion)
            return;
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
        onCreate(db);
    }

    public long addCatkur(Catkur catkur){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put(KEY_TITLE,catkur.getTitle());
        c.put(KEY_CONTENT,catkur.getContent());
        c.put(KEY_DATE,catkur.getDate());
        c.put(KEY_TIME,catkur.getTime());

        long id = db.insert(DATABASE_TABLE,null,c);
        Log.d("Inserted", "ID ->" + id);
        return id;
    }

    public Catkur getCatkur(long id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(DATABASE_TABLE,new String[]{KEY_ID,KEY_TITLE,KEY_CONTENT,KEY_DATE,KEY_TIME},KEY_ID+"=?",
                new String[]{String.valueOf(id)},null,null,null);

        if (cursor != null)
            cursor.moveToFirst();

        return new Catkur(cursor.getLong(0),cursor.getString(1),cursor.getString(2),cursor.getString(3), cursor.getString(4));
    }
    public List<Catkur> getCatkur(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<Catkur> allCatkur = new ArrayList<>();

        String query = "SELECT * FROM "+DATABASE_TABLE+" ORDER BY "+KEY_ID+" DESC";
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.moveToFirst()){
            do{
                Catkur catkur = new Catkur();
                catkur.setID(cursor.getLong(0));
                catkur.setTitle(cursor.getString(1));
                catkur.setContent(cursor.getString(2));
                catkur.setDate(cursor.getString(3));
                catkur.setTime(cursor.getString(4));

                allCatkur.add(catkur);

            }while(cursor.moveToNext());
        }

        return allCatkur;

    }

    public int editCatkur(Catkur catkur){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        Log.d("Edited", "Edited Title: -> "+ catkur.getTitle() + "\n ID -> "+catkur.getID());
        c.put(KEY_TITLE,catkur.getTitle());
        c.put(KEY_CONTENT,catkur.getContent());
        c.put(KEY_DATE,catkur.getDate());
        c.put(KEY_TIME,catkur.getTime());
        return db.update(DATABASE_TABLE,c,KEY_ID+"=?",new String[]{String.valueOf(catkur.getID())});
    }

    void deleteCatkur(long id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DATABASE_TABLE,KEY_ID+"=?",new String[]{String.valueOf(id)});
        db.close();
    }

}

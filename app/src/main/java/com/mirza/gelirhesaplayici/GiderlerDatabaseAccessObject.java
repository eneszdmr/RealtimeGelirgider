package com.mirza.gelirhesaplayici;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class GiderlerDatabaseAccessObject {
    public ArrayList<Giderler> tumGiderler(Veritabani vt){
        ArrayList<Giderler> giderlerArrayList= new ArrayList<>();

        SQLiteDatabase db=vt.getWritableDatabase();
        Cursor c=db.rawQuery("SELECT * FROM giderlertable",null);

        while (c.moveToNext()) {
            Giderler g = new Giderler(c.getInt(
                    c.getColumnIndex("giderid"))
                    , c.getString(c.getColumnIndex("gider"))
                    , c.getDouble(c.getColumnIndex("gidertutar")));
            giderlerArrayList.add(g);

        }
        db.close();
        return giderlerArrayList;
    }

    public void GiderSil(Veritabani vt, int giderid) {
        SQLiteDatabase db = vt.getWritableDatabase();
        db.delete("giderlertable", "giderid=?", new String[]{String.valueOf(giderid)});
        db.close();
    }

    public void GiderEkle(Veritabani vt, String gider, Double gidertutar) {
        SQLiteDatabase db = vt.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("gider", gider);
        values.put("gidertutar", gidertutar);

        db.insertOrThrow("giderlertable", null, values);
        db.close();
    }

    public void GiderGuncelle(Veritabani vt, int giderid, String gider, Double gidertutar) {
        SQLiteDatabase db = vt.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("gider", gider);
        values.put("gidertutar", gidertutar);

        db.update("giderlertable", values, "giderid=?", new String[]{String.valueOf(giderid)});
        db.close();
    }

}

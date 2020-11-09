package com.mirza.gelirhesaplayici;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class GelirlerDatabaseAccessObject {

    public ArrayList<Gelirler> tumGelirler(Veritabani vt) {

        ArrayList<Gelirler> gelirlerArrayList = new ArrayList<>();

        SQLiteDatabase db = vt.getWritableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM gelirler", null);

        while (c.moveToNext()) {
            Gelirler g = new Gelirler(c.getInt(
                    c.getColumnIndex("gelirid"))
                    , c.getString(c.getColumnIndex("gelir"))
                    , c.getDouble(c.getColumnIndex("gelirtutar")));
            gelirlerArrayList.add(g);

        }
        db.close();
        return gelirlerArrayList;
    }

    public void GelirSil(Veritabani vt, int gelirid) {
        SQLiteDatabase db = vt.getWritableDatabase();
        db.delete("gelirler", "gelirid=?", new String[]{String.valueOf(gelirid)});
        db.close();
    }


    public void GelirEkle(Veritabani vt, String gelir, Double gelirtutar) {
        SQLiteDatabase db = vt.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("gelir", gelir);
        values.put("gelirtutar", gelirtutar);

        db.insertOrThrow("gelirler", null, values);
        db.close();
    }

    public void GelirGuncelle(Veritabani vt, int gelirid, String gelir, Double gelirtutar) {
        SQLiteDatabase db = vt.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("gelir", gelir);
        values.put("gelirtutar", gelirtutar);

        db.update("gelirler", values, "gelirid=?", new String[]{String.valueOf(gelirid)});
        db.close();
    }
}

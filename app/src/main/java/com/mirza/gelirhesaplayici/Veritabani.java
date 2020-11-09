package com.mirza.gelirhesaplayici;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Veritabani extends SQLiteOpenHelper {
    public Veritabani(@Nullable Context context) {
        super(context, "hesaplayici.sqlite", null, 1); //oluşturduğumuz veritabanı adı
    }

    @Override
    public void onCreate(SQLiteDatabase db) { //tablo adları
        db.execSQL("CREATE TABLE giderlertable(giderid INTEGER PRIMARY KEY AUTOINCREMENT,gider TEXT,gidertutar DOUBLE);");

        db.execSQL("CREATE TABLE gelirler(gelirid INTEGER PRIMARY KEY AUTOINCREMENT,gelir TEXT,gelirtutar DOUBLE);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS gelirler");
        db.execSQL("DROP TABLE IF EXISTS giderlertable");
        onCreate(db);
    }
}

package com.buscatusservicios.app.config;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import static com.buscatusservicios.app.utility.Utility.DROP_TABLE_AUTH;
import static com.buscatusservicios.app.utility.Utility.TABLE_AUTH_CREATE;

public class SQLHelper extends SQLiteOpenHelper {

    public SQLHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_AUTH_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            db.execSQL(DROP_TABLE_AUTH);
            onCreate(db);
        }
    }
}

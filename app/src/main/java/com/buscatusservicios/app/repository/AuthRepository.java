package com.buscatusservicios.app.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.buscatusservicios.app.config.SQLHelper;
import com.buscatusservicios.app.entity.AuthEntity;
import com.buscatusservicios.app.utility.Utility;

public class AuthRepository {
    private final Context context;

    public AuthRepository(Context context) {
        this.context = context;
    }

    public void insert(AuthEntity authEntity) {
        SQLHelper sqlHelper = new SQLHelper(this.context, Utility.TABLE_AUTH, null, 1);
        SQLiteDatabase db = sqlHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Utility.ATTRIBUTE_ID, authEntity.getId());
        values.put(Utility.ATTRIBUTE_EMAIL, authEntity.getEmail());
        values.put(Utility.ATTRIBUTE_SYMETRIC_KEY, authEntity.getSymetricKey());
        values.put(Utility.ATTRIBUTE_PASSWORD, authEntity.getPassword());
        long result = db.insert(Utility.TABLE_AUTH, Utility.ATTRIBUTE_ID, values);
        Toast.makeText(this.context, result + "", Toast.LENGTH_LONG).show();
        db.close();
        sqlHelper.close();
    }

    public long update(AuthEntity authEntity) {
        SQLHelper sqlHelper = new SQLHelper(this.context, Utility.TABLE_AUTH, null, 1);
        SQLiteDatabase db = sqlHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Utility.ATTRIBUTE_ID, authEntity.getId());
        values.put(Utility.ATTRIBUTE_EMAIL, authEntity.getEmail());
        values.put(Utility.ATTRIBUTE_SYMETRIC_KEY, authEntity.getSymetricKey());
        values.put(Utility.ATTRIBUTE_PASSWORD, authEntity.getPassword());
        String[] selection = {authEntity.getId()};
        long result = db.update(Utility.TABLE_AUTH, values, Utility.ATTRIBUTE_ID.concat("=?"), selection);
        db.close();
        sqlHelper.close();
        return result;
    }

    public AuthEntity findById(String id) {
        AuthEntity authEntity = null;
        SQLHelper sqlHelper = new SQLHelper(this.context, Utility.TABLE_AUTH, null, 1);
        SQLiteDatabase db = sqlHelper.getReadableDatabase();
        String[] selection = {id};
        String[] columns = {Utility.ATTRIBUTE_ID, Utility.ATTRIBUTE_EMAIL, Utility.ATTRIBUTE_PASSWORD, Utility.ATTRIBUTE_SYMETRIC_KEY};
        Cursor cursor = db.query(Utility.TABLE_AUTH, columns, Utility.ATTRIBUTE_ID.concat("=?"), selection, null, null, null);
        if (cursor.moveToFirst()) {
            authEntity = new AuthEntity();
            authEntity.setId(cursor.getString(0));
            authEntity.setEmail(cursor.getString(1));
            authEntity.setPassword(cursor.getString(2));
            authEntity.setSymetricKey(cursor.getString(3));
        }
        cursor.close();
        db.close();
        sqlHelper.close();
        return authEntity;
    }

    public AuthEntity findByFirts() {
        AuthEntity authEntity = null;
        SQLHelper sqlHelper = new SQLHelper(this.context, Utility.TABLE_AUTH, null, 1);
        SQLiteDatabase db = sqlHelper.getReadableDatabase();
        String[] columns = {Utility.ATTRIBUTE_ID, Utility.ATTRIBUTE_EMAIL, Utility.ATTRIBUTE_PASSWORD, Utility.ATTRIBUTE_SYMETRIC_KEY};
        Cursor cursor = db.query(Utility.TABLE_AUTH, columns, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            authEntity = new AuthEntity();
            authEntity.setId(cursor.getString(0));
            authEntity.setEmail(cursor.getString(1));
            authEntity.setPassword(cursor.getString(2));
            authEntity.setSymetricKey(cursor.getString(3));
        }
        cursor.close();
        db.close();
        sqlHelper.close();
        return authEntity;
    }

}

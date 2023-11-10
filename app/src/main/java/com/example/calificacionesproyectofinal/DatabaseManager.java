package com.example.calificacionesproyectofinal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.calificacionesproyectofinal.DatabaseHelper;

public class DatabaseManager {
    private DatabaseHelper dbHelper;
    private Context context;
    private SQLiteDatabase database;

    public DatabaseManager(Context c) {
        context = c;
    }

    public DatabaseManager open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public long insert(String nombre) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.COLUMN_NOMBRE, nombre);

        return database.insert(DatabaseHelper.TABLE_NAME, null, contentValue);
    }

    public Cursor queryData(String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor cursor = null;
        try {
            cursor = database.query(
                    DatabaseHelper.TABLE_NAME,
                    projection,
                    selection,
                    selectionArgs,
                    null,
                    null,
                    sortOrder
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cursor;
    }
}

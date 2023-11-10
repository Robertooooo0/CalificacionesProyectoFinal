package com.example.calificacionesproyectofinal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseManager {
    private DatabaseHelper dbHelper;
    private SQLiteDatabase database;

    public DatabaseManager(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public DatabaseManager open() throws SQLException {
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public long insertUnidad(String nombreUnidad, int idSemestre) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.COLUMN_NOMBRE_UNIDAD, nombreUnidad);
        contentValue.put(DatabaseHelper.COLUMN_ID_SEMESTRE, idSemestre);

        return database.insert(DatabaseHelper.TABLE_UNIDADES, null, contentValue);
    }

    public long insertSemestre(String nombreSemestre) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.COLUMN_NOMBRE_SEMESTRE, nombreSemestre);

        return database.insert(DatabaseHelper.TABLE_SEMESTRES, null, contentValue);
    }

    public Cursor queryData(String tableName, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor cursor = null;
        try {
            cursor = database.query(
                    tableName,
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
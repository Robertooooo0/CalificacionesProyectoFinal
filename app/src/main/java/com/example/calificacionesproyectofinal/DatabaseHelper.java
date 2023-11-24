package com.example.calificacionesproyectofinal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "MiBaseDeDatos";
    public static final int DATABASE_VERSION = 2;

    public static final String TABLE_UNIDADES = "unidades";
    public static final String TABLE_SEMESTRES = "semestres";
    public static final String TABLE_MATERIAS = "materias";

    public static final String COLUMN_NOMBRE_UNIDAD = "nombre_unidad";
    public static final String COLUMN_ID_SEMESTRE = "id_semestre";
    public static final String COLUMN_NOMBRE_SEMESTRE = "nombre_semestre";
    public static final String COLUMN_ID_UNIDAD = "id_unidad"; // Reemplazado COLUMN_ID_MATERIA por COLUMN_ID_UNIDAD
    public static final String COLUMN_CALIFICACION = "calificacion";
    public static final String COLUMN_NOMBRE_MATERIA = "nombre_materia";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createUnidadesTable = "CREATE TABLE " + TABLE_UNIDADES + " (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NOMBRE_UNIDAD + " TEXT, " +
                COLUMN_ID_SEMESTRE + " INTEGER)";
        db.execSQL(createUnidadesTable);

        String createSemestresTable = "CREATE TABLE " + TABLE_SEMESTRES + " (" +
                "id_semestre INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NOMBRE_SEMESTRE + " TEXT)";
        db.execSQL(createSemestresTable);

        String createMateriasTable = "CREATE TABLE " + TABLE_MATERIAS + " (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_ID_SEMESTRE + " INTEGER, " +
                COLUMN_ID_UNIDAD + " INTEGER, " + // Cambiado COLUMN_ID_MATERIA por COLUMN_ID_UNIDAD
                COLUMN_NOMBRE_MATERIA + " TEXT, " +
                COLUMN_CALIFICACION + " REAL, " +
                "FOREIGN KEY(" + COLUMN_ID_SEMESTRE + ") REFERENCES " + TABLE_SEMESTRES + "(id_semestre))";
        db.execSQL(createMateriasTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_UNIDADES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SEMESTRES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MATERIAS);
        onCreate(db);
    }
}

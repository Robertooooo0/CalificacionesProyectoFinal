import android.content.ContentValues;
import android.content.Context;
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

    public long insert(String nombre, int anoEstudio) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.COLUMN_NOMBRE, nombre);
        contentValue.put(DatabaseHelper.COLUMN_ANO_ESTUDIO, anoEstudio);
        return database.insert(DatabaseHelper.TABLE_NAME, null, contentValue);
    }
}

package com.example.calificacionesproyectofinal

import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class Semestre : AppCompatActivity() {
    private var databaseManager: DatabaseManager? = null
    lateinit var txtNombre: TextView
    lateinit var rvSemestre: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_semestre)
        txtNombre = findViewById(R.id.txtNombre2)
        val btnSiguiente = findViewById<Button>(R.id.btnSiguiente)
        rvSemestre = findViewById(R.id.rvSemestre)

        // Obtener el nombre guardado en SharedPreferences
        val preferences = getSharedPreferences("misdatos", Context.MODE_PRIVATE)
        val nombre = preferences.getString("nombre", "aun no se registra")

        txtNombre.text = nombre

        btnSiguiente.setOnClickListener {
            // Crea un Intent para iniciar la actividad AgregarSemestre
            val intent = Intent(this, AgregarSemestre::class.java)
            startActivity(intent)
        }

        // Inicializa el DatabaseManager
        databaseManager = DatabaseManager(this)
        databaseManager?.open()

        // Llamada al método para consultar datos
        val listaNombresSemestre = consultarDatos()

        val adaptador = AdaptadorS(this, listaNombresSemestre)
        rvSemestre.adapter = adaptador
        rvSemestre.layoutManager = LinearLayoutManager(this)
    }

    private fun consultarDatos(): List<NombreSemestre> {
        val projection = arrayOf(DatabaseHelper.COLUMN_NOMBRE_SEMESTRE, DatabaseHelper.COLUMN_ID_SEMESTRE)
        val selection: String? = null
        val selectionArgs: Array<String>? = null
        val sortOrder: String? = null

        val cursor = databaseManager?.queryData(DatabaseHelper.TABLE_SEMESTRES, projection, selection, selectionArgs, sortOrder)

        val nombresSemestre: MutableList<NombreSemestre> = mutableListOf()

        cursor?.use { cursor ->
            if (cursor.moveToFirst()) {
                val columnIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_NOMBRE_SEMESTRE)
                val columnIndex2= cursor.getColumnIndex(DatabaseHelper.COLUMN_ID_SEMESTRE)

                do {
                    val nombre = cursor.getString(columnIndex)
                    val idSemestre = cursor.getInt(columnIndex2)
                    val nombreObj = NombreSemestre(nombre, idSemestre)

                    nombresSemestre.add(nombreObj)
                } while (cursor.moveToNext())
            }
        }


    return nombresSemestre
    }

    fun onClickSemestre(semestre: NombreSemestre) {
        val intent = Intent(this, Unidades::class.java)
        intent.putExtra("NombreSemestre", semestre.nombre)
        intent.putExtra("IdSemestre", semestre.idSemestre)
        startActivity(intent)

        }

        override fun onDestroy() {
        super.onDestroy()
        databaseManager?.close()
    }
}
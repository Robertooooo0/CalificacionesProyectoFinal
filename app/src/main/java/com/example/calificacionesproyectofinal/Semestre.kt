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

        // Llamada al método para consultar datos
        val listaNombresSemestre = consultarDatos()

        val adaptador = AdaptadorS(this, listaNombresSemestre)
        rvSemestre.adapter = adaptador
        rvSemestre.layoutManager = LinearLayoutManager(this)
    }

    fun consultarDatos(): List<NombreSemestre> {
        val projection = arrayOf(DatabaseHelper.COLUMN_NOMBRE)
        val selection: String? = null
        val selectionArgs: Array<String>? = null
        val sortOrder: String? = null
        databaseManager = DatabaseManager(this)
        databaseManager!!.open()
        val cursor: Cursor = databaseManager!!.queryData(projection, selection, selectionArgs, sortOrder)
        val nombresSemestre: MutableList<NombreSemestre> = ArrayList<NombreSemestre>()

        try {
            if (cursor != null && cursor.moveToFirst()) {
                val columnIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_NOMBRE)
                do {
                    val nombre = cursor.getString(columnIndex)
                    val nombreObj = NombreSemestre(nombre)
                    nombresSemestre.add(nombreObj)
                } while (cursor.moveToNext())
            }
        } finally {
            cursor?.close()
        }

        databaseManager!!.close()
        return nombresSemestre
    }

    fun onClickSemestre(semestre:NombreSemestre){
        val intent = Intent(this, Materias::class.java)
        intent.putExtra("NombreSemestre", (semestre.nombre).toString())

        startActivity(intent)
    }
}
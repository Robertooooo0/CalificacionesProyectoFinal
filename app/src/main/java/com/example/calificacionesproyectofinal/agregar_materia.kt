package com.example.calificacionesproyectofinal

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class agregar_materia : AppCompatActivity() {

    private lateinit var unidadesAdapter: AdapterMostrarUnidades

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_materia)

        val idSemestre = intent.getIntExtra("IdSemestre", -1)
        val txtIdSemestre = findViewById<TextView>(R.id.IdSemestre)

        if (idSemestre != -1) {
            txtIdSemestre.text = "ID del Semestre: $idSemestre"
        } else {
            txtIdSemestre.text = "ID del Semestre no disponible"
        }

        val recyclerView = findViewById<RecyclerView>(R.id.RcvMostrarUnidadesEnMaterias)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Suponiendo que tienes una función para obtener las unidades por ID de semestre
        val unidadesList = obtenerUnidadesPorIdSemestre(idSemestre)
        unidadesAdapter = AdapterMostrarUnidades(
            unidadesList,
            object : AdapterMostrarUnidades.OnUnidadClickListener {
                override fun onUnidadClick(idSemestre: Int, idUnidad: Int, nombreUnidad: String) {
                    // Aquí puedes manejar el clic en la unidad si es necesario
                }
            })

        recyclerView.adapter = unidadesAdapter
    }

    private fun obtenerUnidadesPorIdSemestre(idSemestre: Int): List<String> {
        val unidadesList = mutableListOf<String>()

        // Acceder a la base de datos para obtener las unidades correspondientes al ID de semestre
        val databaseManager = DatabaseManager(this)
        databaseManager.open()

        // Hacer la consulta para obtener las unidades del semestre específico
        val cursor = databaseManager.queryData(
            DatabaseHelper.TABLE_UNIDADES,
            arrayOf(DatabaseHelper.COLUMN_NOMBRE_UNIDAD),
            "${DatabaseHelper.COLUMN_ID_SEMESTRE} = ?",
            arrayOf(idSemestre.toString()),
            null
        )

        // Recorrer el cursor y agregar las unidades a la lista
        cursor?.use {
            while (it.moveToNext()) {
                val nombreUnidad =
                    it.getString(it.getColumnIndex(DatabaseHelper.COLUMN_NOMBRE_UNIDAD))
                unidadesList.add(nombreUnidad)
            }
        }

        databaseManager.close()

        return unidadesList
    }
}
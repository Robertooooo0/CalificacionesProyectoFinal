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
                val unidadesList = obtenerUnidadesPorIdSemestre(idSemestre)
                configurarRecyclerView(unidadesList)
            } else {
                txtIdSemestre.text = "ID del Semestre no disponible"
            }
        }

        private fun configurarRecyclerView(unidadesList: List<String>) {
            val recyclerView = findViewById<RecyclerView>(R.id.RcvMostrarUnidadesEnMaterias)
            recyclerView.layoutManager = LinearLayoutManager(this)

            unidadesAdapter = AdapterMostrarUnidades(
                unidadesList,
                object : AdapterMostrarUnidades.OnUnidadClickListener {
                    override fun onUnidadClick(idSemestre: Int, idUnidad: Int, nombreUnidad: String) {
                        // Manejar el clic en la unidad si es necesario
                    }
                })

            recyclerView.adapter = unidadesAdapter
        }

        private fun obtenerUnidadesPorIdSemestre(idSemestre: Int): List<String> {
            val unidadesList = mutableListOf<String>()

            val databaseManager = DatabaseManager(this)
            databaseManager.open()

            val cursor = databaseManager.queryData(
                DatabaseHelper.TABLE_UNIDADES,
                arrayOf(DatabaseHelper.COLUMN_NOMBRE_UNIDAD),
                "${DatabaseHelper.COLUMN_ID_SEMESTRE} = ?",
                arrayOf(idSemestre.toString()),
                null
            )

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

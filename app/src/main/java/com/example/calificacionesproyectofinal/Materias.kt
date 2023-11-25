package com.example.calificacionesproyectofinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Materias : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_materias)

        val nombreUnidad = intent.getStringExtra("NombreUnidad")
        val idUnidad = intent.getIntExtra("IdUnidad", -1).toString()

        val txtNombreUnidad = findViewById<TextView>(R.id.txtUnidadNombre)
        val txtIdUnidad = findViewById<TextView>(R.id.txtUnidadId)
        val txtIdSemestreU = findViewById<TextView>(R.id.txtIdSemestreMaterias)

        txtNombreUnidad.text = nombreUnidad
        txtIdUnidad.text = "ID de la Unidad: $idUnidad"

        val idSemestre = intent.getIntExtra("IdSemestre", -1)
        txtIdSemestreU.text = "ID del Semestre: $idSemestre"

        val materiasCompletas = obtenerTodasLasMaterias()
        val materiasAgregadas =
            obtenerMateriasAgregadasParaUnidadYSemestre(idUnidad.toInt(), idSemestre)

        val materiasMostradas = materiasCompletas.filter { materia ->
            materiasAgregadas.any { it.nombre == materia.nombre }
        }

        val recyclerView = findViewById<RecyclerView>(R.id.rcvMateriasConCalificacion)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = MateriasAdapter(materiasMostradas)
        recyclerView.adapter = adapter

        val btnSiguiente = findViewById<Button>(R.id.btnSiguienteAgregarMateria)

        btnSiguiente.setOnClickListener {
            val intent = Intent(this, agregar_materia::class.java)
            intent.putExtra("IdSemestre", idSemestre)
            intent.putExtra("IdUnidad", idUnidad.toInt())
            startActivity(intent)
        }
    }

    private fun obtenerTodasLasMaterias(): List<Materia> {
        val dbManager = DatabaseManager(this)
        dbManager.open()

        val materiasList = mutableListOf<Materia>()

        val projection = arrayOf(
            DatabaseHelper.COLUMN_NOMBRE_MATERIA,
            DatabaseHelper.COLUMN_CALIFICACION
        )

        val cursor = dbManager.queryData(
            DatabaseHelper.TABLE_MATERIAS,
            projection,
            null,
            null,
            null
        )

        cursor?.use {
            while (it.moveToNext()) {
                val nombreMateria =
                    it.getString(it.getColumnIndex(DatabaseHelper.COLUMN_NOMBRE_MATERIA))
                val calificacion =
                    it.getDouble(it.getColumnIndex(DatabaseHelper.COLUMN_CALIFICACION))

                val materia = Materia(nombreMateria, calificacion)
                materiasList.add(materia)
            }
        }

        cursor?.close()
        dbManager.close()

        return materiasList
    }

    private fun obtenerMateriasAgregadasParaUnidadYSemestre(
        idUnidad: Int,
        idSemestre: Int
    ): List<Materia> {
        val dbManager = DatabaseManager(this)
        dbManager.open()

        val materiasList = mutableListOf<Materia>()

        val projection = arrayOf(
            DatabaseHelper.COLUMN_NOMBRE_MATERIA,
            DatabaseHelper.COLUMN_CALIFICACION
        )

        val selection =
            "${DatabaseHelper.COLUMN_ID_UNIDAD} = ? AND ${DatabaseHelper.COLUMN_ID_SEMESTRE} = ?"
        val selectionArgs = arrayOf(idUnidad.toString(), idSemestre.toString())

        val cursor = dbManager.queryData(
            DatabaseHelper.TABLE_MATERIAS,
            projection,
            selection,
            selectionArgs,
            null
        )

        cursor?.use {
            while (it.moveToNext()) {
                val nombreMateria =
                    it.getString(it.getColumnIndex(DatabaseHelper.COLUMN_NOMBRE_MATERIA))
                val calificacion =
                    it.getDouble(it.getColumnIndex(DatabaseHelper.COLUMN_CALIFICACION))

                val materia = Materia(nombreMateria, calificacion)
                materiasList.add(materia)
            }
        }

        cursor?.close()
        dbManager.close()

        return materiasList
    }
}

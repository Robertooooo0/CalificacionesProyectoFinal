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

        // Filtrar las materias completas para obtener solo las que tienen el mismo ID de unidad y nombre
        val materiasCompletas = obtenerTodasLasMaterias().filter {
            it.unidadId == idUnidad.toInt()
        }

        val materiasAgregadas =
            obtenerMateriasAgregadasParaUnidadYSemestre(idUnidad.toInt(), idSemestre)

        val materiasMostradas = materiasCompletas.filter { materia ->
            materiasAgregadas.any { it.nombre == materia.nombre && it.unidadId == materia.unidadId }
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

        // Calcular y mostrar la calificación final
        val calificacionFinal = calcularPromedioCalificaciones(materiasAgregadas)
        val txtCalificacionFinal = findViewById<TextView>(R.id.txtTotalCalificacion)
        txtCalificacionFinal.text = "Calificación Final: $calificacionFinal"
    }

    private fun obtenerTodasLasMaterias(): List<Materia> {
        val dbManager = DatabaseManager(this)
        dbManager.open()

        val materiasList = mutableListOf<Materia>()

        val projection = arrayOf(
            "_id", // Se asume que "_id" es el identificador único de la materia en la tabla
            DatabaseHelper.COLUMN_NOMBRE_MATERIA,
            DatabaseHelper.COLUMN_CALIFICACION,
            DatabaseHelper.COLUMN_ID_UNIDAD
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
                val idMateria = it.getInt(it.getColumnIndex("_id"))
                val nombreMateria =
                    it.getString(it.getColumnIndex(DatabaseHelper.COLUMN_NOMBRE_MATERIA))
                val calificacion =
                    it.getDouble(it.getColumnIndex(DatabaseHelper.COLUMN_CALIFICACION))
                val idUnidad = it.getInt(it.getColumnIndex(DatabaseHelper.COLUMN_ID_UNIDAD))

                val materia = Materia(idMateria, nombreMateria, calificacion, idUnidad)
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
            "_id", // Se asume que "_id" es el identificador único de la materia en la tabla
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
                val idMateria = it.getInt(it.getColumnIndex("_id"))
                val nombreMateria =
                    it.getString(it.getColumnIndex(DatabaseHelper.COLUMN_NOMBRE_MATERIA))
                val calificacion =
                    it.getDouble(it.getColumnIndex(DatabaseHelper.COLUMN_CALIFICACION))

                val materia = Materia(idMateria, nombreMateria, calificacion, idUnidad)
                materiasList.add(materia)
            }
        }

        cursor?.close()
        dbManager.close()

        return materiasList
    }


    private fun actualizarRecyclerView() {
        val idUnidad = intent.getIntExtra("IdUnidad", -1).toString()
        val idSemestre = intent.getIntExtra("IdSemestre", -1)

        val materiasAgregadas =
            obtenerMateriasAgregadasParaUnidadYSemestre(idUnidad.toInt(), idSemestre)

        val recyclerView = findViewById<RecyclerView>(R.id.rcvMateriasConCalificacion)
        val adapter = MateriasAdapter(materiasAgregadas)
        recyclerView.adapter = adapter

        // Calcular y mostrar la calificación final
        val calificacionFinal = calcularPromedioCalificaciones(materiasAgregadas)
        val txtCalificacionFinal = findViewById<TextView>(R.id.txtTotalCalificacion)
        txtCalificacionFinal.text = "Calificación Final: $calificacionFinal"
    }

    override fun onResume() {
        super.onResume()
        // Actualizar el RecyclerView cuando la actividad se reanude
        actualizarRecyclerView()
    }

    private fun calcularPromedioCalificaciones(materias: List<Materia>): Double {
        if (materias.isEmpty()) return 0.0

        var sumaCalificaciones = 0.0
        for (materia in materias) {
            sumaCalificaciones += materia.calificacion
        }

        val promedio = sumaCalificaciones / materias.size
        return String.format("%.1f", promedio).toDouble()
    }
}

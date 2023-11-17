package com.example.calificacionesproyectofinal

import android.content.ContentValues
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Unidades : AppCompatActivity() {

    lateinit var txtSemestre: TextView
    lateinit var txtIdSemestre: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_unidades)

        txtSemestre = findViewById(R.id.txtSemestreN)
        txtIdSemestre = findViewById(R.id.txtIdSemestre)

        val nombreS = intent.getStringExtra("NombreSemestre").toString()
        val idSemestre = intent.getIntExtra("IdSemestre", -1) // Recibir el ID del semestre

        txtSemestre.text = nombreS
        txtIdSemestre.text = "ID del Semestre: $idSemestre" // Mostrar el ID del semestre
        val btnSiguienteUnidades = findViewById<Button>(R.id.btnSiguienteunidades)

        btnSiguienteUnidades.setOnClickListener {
            val intent = Intent(this, agregra_unidades::class.java)
            // Puedes pasar el ID del semestre a la siguiente actividad si es necesario
            intent.putExtra("IdSemestre", idSemestre)
            startActivity(intent)
        }

        // Obtener la lista de unidades del semestre desde la base de datos
        val unidades = obtenerUnidadesPorIdSemestre(idSemestre)

        // Configurar el RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.rcvMostrarUnidades)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = UnidadesAdapter(unidades)
        recyclerView.adapter = adapter
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
                val nombreUnidad = it.getString(it.getColumnIndex(DatabaseHelper.COLUMN_NOMBRE_UNIDAD))
                unidadesList.add(nombreUnidad)
            }
        }

        databaseManager.close()

        return unidadesList
    }
}


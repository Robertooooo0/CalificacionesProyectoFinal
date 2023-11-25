package com.example.calificacionesproyectofinal

import android.content.ContentValues
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class agregar_materia : AppCompatActivity() {

    lateinit var txtIdSemestre: TextView
    lateinit var txtIdUnidad: TextView
    lateinit var editTextAgregarMateria: EditText
    lateinit var editTextCalificacion: EditText
    lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_materia)

        dbHelper = DatabaseHelper(this)

        txtIdSemestre = findViewById(R.id.IdSemestre)
        txtIdUnidad = findViewById(R.id.idMateria)
        editTextAgregarMateria = findViewById(R.id.editTextAgregarMateria)
        editTextCalificacion = findViewById(R.id.editTextCalificacion)

        val idSemestre = intent.getIntExtra("IdSemestre", -1)
        val idUnidad = intent.getIntExtra("IdUnidad", -1)

        txtIdSemestre.text = "ID del Semestre: $idSemestre"
        txtIdUnidad.text = "ID de la Unidad: $idUnidad"

        val btnGuardarMaterialconUnidad: Button = findViewById(R.id.btnGuardarMaterialconUnidad)
        btnGuardarMaterialconUnidad.setOnClickListener {
            val nombreMateria = editTextAgregarMateria.text.toString()
            val calificacion = editTextCalificacion.text.toString().toDoubleOrNull()

            if (nombreMateria.isNotEmpty() && calificacion != null) {
                val db = dbHelper.writableDatabase

                val values = ContentValues().apply {
                    put(DatabaseHelper.COLUMN_ID_SEMESTRE, idSemestre)
                    put(DatabaseHelper.COLUMN_ID_UNIDAD, idUnidad)
                    put(DatabaseHelper.COLUMN_NOMBRE_MATERIA, nombreMateria)
                    put(DatabaseHelper.COLUMN_CALIFICACION, calificacion)
                }

                val newRowId = db.insert(DatabaseHelper.TABLE_MATERIAS, null, values)
                if (newRowId != -1L) {
                    showToast("Materia agregada correctamente")
                    editTextAgregarMateria.setText("")
                    editTextCalificacion.setText("")
                } else {
                    showToast("Error al agregar la materia")
                }
            } else {
                showToast("Por favor, completa los campos correctamente")
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}

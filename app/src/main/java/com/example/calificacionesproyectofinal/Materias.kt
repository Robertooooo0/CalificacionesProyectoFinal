package com.example.calificacionesproyectofinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class Materias : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_materias)

        // Recibir los datos de la unidad del Intent
        val nombreUnidad = intent.getStringExtra("NombreUnidad")
        val idUnidad = intent.getIntExtra("IdUnidad", -1).toString()

        // Referenciar los TextViews en tu layout
        val txtNombreUnidad = findViewById<TextView>(R.id.txtUnidadNombre)
        val txtIdUnidad = findViewById<TextView>(R.id.txtUnidadId)
        val txtIdSemestreU = findViewById<TextView>(R.id.txtIdSemestreMaterias)

        // Mostrar los datos de la unidad en los TextViews
        txtNombreUnidad.text = nombreUnidad
        txtIdUnidad.text = "ID de la Unidad: $idUnidad"

        // Obtener el ID del semestre del Intent
        val idSemestre = intent.getIntExtra("IdSemestre", -1)

        // Mostrar el ID del semestre en el TextView correspondiente
        txtIdSemestreU.text = "ID del Semestre: $idSemestre"

        val btnSiguiente = findViewById<Button>(R.id.btnSiguienteAgregarMateria)

        btnSiguiente.setOnClickListener {
            val intent = Intent(this, agregar_materia::class.java)
            intent.putExtra("IdSemestre", idSemestre)
            intent.putExtra("IdUnidad", idUnidad.toInt()) // Pasar el ID de la unidad a agregar_materia
            startActivity(intent)
        }
    }
}
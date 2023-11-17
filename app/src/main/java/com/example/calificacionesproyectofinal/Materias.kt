package com.example.calificacionesproyectofinal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

        // Mostrar los datos de la unidad en los TextViews
        txtNombreUnidad.text = nombreUnidad
        txtIdUnidad.text = "ID de la Unidad: $idUnidad"
    }
}

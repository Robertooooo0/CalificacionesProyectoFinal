package com.example.calificacionesproyectofinal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class agregar_materia : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_materia)

        // Supongamos que recibes el ID del semestre como un extra en el Intent
        val idSemestre = intent.getIntExtra("IdSemestre", -1)

        // Si tienes el TextView donde quieres mostrar el ID del semestre
        val txtIdSemestre = findViewById<TextView>(R.id.IdSemestre)

        // Verifica si el ID del semestre es válido y luego asigna el valor al TextView
        if (idSemestre != -1) {
            txtIdSemestre.text = "ID del Semestre: $idSemestre"
        } else {
            txtIdSemestre.text = "ID del Semestre no disponible"
        }
    }
}

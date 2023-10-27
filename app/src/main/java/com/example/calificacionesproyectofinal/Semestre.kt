package com.example.calificacionesproyectofinal

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast

class Semestre : AppCompatActivity() {
    lateinit var txtNombre: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_semestre)
        txtNombre = findViewById(R.id.txtNombre2)
        val btnSiguiente = findViewById<Button>(R.id.btnSiguiente)

        // Obtener el nombre guardado en SharedPreferences
        val preferences = getSharedPreferences("misdatos", Context.MODE_PRIVATE)
        val nombre = preferences.getString("nombre", "aun no se registra")

        txtNombre.text = nombre

    btnSiguiente.setOnClickListener {
            // Crea un Intent para iniciar la actividad AgregarSemestre
            val intent = Intent(this, AgregarSemestre::class.java)
            startActivity(intent)
        }
    }
}
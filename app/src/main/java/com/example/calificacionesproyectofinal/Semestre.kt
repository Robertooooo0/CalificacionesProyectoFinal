package com.example.calificacionesproyectofinal

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast

class Semestre : AppCompatActivity() {
    lateinit var txtNombre:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_semestre)
        txtNombre = findViewById(R.id.txtNombre2)
        val preferecias = getSharedPreferences("misdatos", Context.MODE_PRIVATE)
        val nombre = preferecias.getString("nombre", "aun no se registra")
        Toast.makeText(this, "$nombre holaa",Toast.LENGTH_LONG).show()

        txtNombre.text = nombre
    }
}

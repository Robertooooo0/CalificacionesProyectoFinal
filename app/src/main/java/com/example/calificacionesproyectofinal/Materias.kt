package com.example.calificacionesproyectofinal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class Materias : AppCompatActivity() {
    lateinit var txtsemestre: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_materias)

        txtsemestre = findViewById(R.id.txtSemestreN)
        var nombreS = intent.getStringExtra("NombreSemestre").toString()
        txtsemestre.text = nombreS
    }
}
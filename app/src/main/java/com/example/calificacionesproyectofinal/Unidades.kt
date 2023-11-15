package com.example.calificacionesproyectofinal

import android.content.ContentValues
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

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
    }
}
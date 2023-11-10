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
    lateinit var txtsemestre: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_unidades)
        txtsemestre = findViewById(R.id.txtSemestreN)
        var nombreS = intent.getStringExtra("NombreSemestre").toString()
        txtsemestre.text = nombreS
        val btnSiguienteUnidades = findViewById<Button>(R.id.btnSiguienteunidades)


        btnSiguienteUnidades.setOnClickListener {
            val intent = Intent(this, agregra_unidades::class.java)


            startActivity(intent)
        }
    }
}
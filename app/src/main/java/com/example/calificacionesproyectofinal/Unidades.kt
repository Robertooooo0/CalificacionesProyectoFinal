package com.example.calificacionesproyectofinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class Unidades : AppCompatActivity() {
    lateinit var txtsemestre: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_unidades)
        val btnAgregarUnidades: Button = findViewById(R.id.btnagregarunidades)



        txtsemestre = findViewById(R.id.txtSemestreN)
        var nombreS = intent.getStringExtra("NombreSemestre").toString()
        txtsemestre.text = nombreS

        btnAgregarUnidades.setOnClickListener {
            val intent = Intent(this, agregra_unidades::class.java)
            startActivity(intent)
        }
    }
}

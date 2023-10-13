package com.example.calificacionesproyectofinal

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    lateinit var txtnombre: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val preferecias = getSharedPreferences("misdatos", Context.MODE_PRIVATE)
        val nombre = preferecias.getString("nombre", "")
        if(nombre.toString()!=""){
            val intent = Intent(this, Semestre::class.java)
            startActivity(intent)
        }else {
            setContentView(R.layout.activity_main)
            txtnombre = findViewById(R.id.TxtNombreInicio);
        }

    }

    fun enviar(v:View){


        val preferecias = getSharedPreferences("misdatos", Context.MODE_PRIVATE)
        val editor = preferecias.edit()
        val nombre = txtnombre.text.toString()
        editor.putString("nombre", nombre)
        editor.commit()
        val intent = Intent(this, Semestre::class.java)
        startActivity(intent)

    }
}
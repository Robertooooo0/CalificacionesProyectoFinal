package com.example.calificacionesproyectofinal

import DatabaseManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class AgregarSemestre : AppCompatActivity() {
    private lateinit var editTextNombre: EditText
    private lateinit var buttonGuardar: Button
    private lateinit var databaseManager: DatabaseManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_semestre)

        editTextNombre = findViewById(R.id.editTextNombre)
        buttonGuardar = findViewById(R.id.buttonGuardar)

        databaseManager = DatabaseManager(this)
        databaseManager.open()

        buttonGuardar.setOnClickListener {
            val nombre = editTextNombre.text.toString()
            val anoEstudio = 2023 // Puedes cambiar esto según el año de estudio que desees guardar

            val resultado = databaseManager.insert(nombre, anoEstudio)

            if (resultado != -1L) {
                // Inserción exitosa
                Toast.makeText(this, "Datos guardados exitosamente", Toast.LENGTH_SHORT).show()
                editTextNombre.text.clear()
            } else {
                // Error al guardar
                Toast.makeText(this, "Error al guardar datos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

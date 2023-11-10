package com.example.calificacionesproyectofinal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class agregra_unidades : AppCompatActivity() {
    lateinit var txtguardarunidades: EditText
    lateinit var btnguardarunidades: Button
    lateinit var databaseManager: DatabaseManager // Asegúrate de inicializar esto en el onCreate
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregra_unidades)

        txtguardarunidades = findViewById(R.id.txtguardarunidades)
        btnguardarunidades = findViewById(R.id.btnguardarunidades)
        databaseManager = DatabaseManager(this) // Inicializa tu DatabaseManager

        btnguardarunidades.setOnClickListener {
            val nombreUnidad = txtguardarunidades.text.toString().trim()

            if (nombreUnidad.isNotEmpty()) { // Verifica si el campo no está vacío
                val idSemestre = 0 // Reemplaza esto por la lógica para obtener el ID del semestre

                // Guardar en la base de datos
                databaseManager.open()
                val result = databaseManager.insertUnidad(nombreUnidad, idSemestre)
                databaseManager.close()

                if (result != -1L) {
                    // Éxito al guardar
                    Toast.makeText(this, "Unidad guardada correctamente", Toast.LENGTH_SHORT).show()
                    onBackPressed()
                } else {
                    // Error al guardar
                    Toast.makeText(this, "Error al guardar la unidad", Toast.LENGTH_SHORT).show()
                }
            } else {
                // Campo vacío, muestra un mensaje al usuario
                Toast.makeText(
                    this,
                    "Por favor, ingresa el nombre de la unidad",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}

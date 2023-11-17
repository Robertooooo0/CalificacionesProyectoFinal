package com.example.calificacionesproyectofinal

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class agregra_unidades : AppCompatActivity() {
    lateinit var txtguardarunidades: EditText
    lateinit var btnguardarunidades: Button
    lateinit var databaseManager: DatabaseManager
    var idSemestre: Int = -1 // Inicializa el ID del semestre con un valor predeterminado

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregra_unidades)

        txtguardarunidades = findViewById(R.id.txtguardarunidades)
        btnguardarunidades = findViewById(R.id.btnguardarunidades)
        databaseManager = DatabaseManager(this)

        // Recibir el ID del semestre desde la actividad anterior
        idSemestre = intent.getIntExtra("IdSemestre", -1)

        btnguardarunidades.setOnClickListener {
            val nombreUnidad = txtguardarunidades.text.toString().trim()

            if (nombreUnidad.isNotEmpty() && idSemestre != -1) {
                // Guardar en la base de datos utilizando el ID del semestre recibido
                databaseManager.open()
                val result = databaseManager.insertUnidad(nombreUnidad, idSemestre)
                databaseManager.close()

                if (result != -1L) {
                    Toast.makeText(this, "Unidad guardada correctamente", Toast.LENGTH_SHORT).show()
                    val intent = Intent()
                    intent.putExtra("IdSemestre", idSemestre)
                    setResult(Activity.RESULT_OK, intent)
                    finish()
                } else {
                    Toast.makeText(this, "Error al guardar la unidad", Toast.LENGTH_SHORT).show()
                }

                intent.putExtra("IdSemestre", idSemestre)
                    setResult(Activity.RESULT_OK, intent)
                    finish()

            } else {
                Toast.makeText(
                    this,
                    "Por favor, ingresa el nombre de la unidad o selecciona un semestre válido",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}
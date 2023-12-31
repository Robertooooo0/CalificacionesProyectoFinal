package com.example.calificacionesproyectofinal


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
            val nombre =
                editTextNombre.text.toString().trim() // Eliminar espacios iniciales y finales

            if (nombre.isNotEmpty()) { // Verificar que el nombre no esté vacío
                val resultado = databaseManager.insertSemestre(nombre)

                if (resultado != -1L) {
                    // Inserción exitosa
                    Toast.makeText(this, "Datos guardados exitosamente", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, Semestre::class.java)
                    startActivity(intent)
                } else {
                    // Error al guardar
                    Toast.makeText(this, "Error al guardar datos", Toast.LENGTH_SHORT).show()
                }
            } else {
                // Nombre vacío, mostrar un mensaje o realizar una acción apropiada
                Toast.makeText(this, "El nombre no puede estar vacío", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
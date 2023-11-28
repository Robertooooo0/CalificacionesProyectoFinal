package com.example.calificacionesproyectofinal

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class editar_materias : AppCompatActivity() {
    private fun mostrarMensaje(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_materias)

        val editTextNombreMateria = findViewById<EditText>(R.id.editTextNombreMateria)
        val editTextCalificacion =
            findViewById<EditText>(R.id.editTextCalificacio)

        val idMateria = intent.getIntExtra("IdMateria", -1)
        val nombreMateria = intent.getStringExtra("NombreMateria")
        val calificacionMateria = intent.getDoubleExtra("CalificacionMateria", 0.0)

        editTextNombreMateria.setText(nombreMateria)
        editTextCalificacion.setText(calificacionMateria.toString())

        val txtIdMateria = findViewById<TextView>(R.id.id_materia)
        txtIdMateria.text = "ID de la Materia: $idMateria"

        val btnEliminarMateria = findViewById<Button>(R.id.btnEliminarMateria)
        val btnModificarMateria = findViewById<Button>(R.id.btnModificarMateria)

        btnEliminarMateria.setOnClickListener {
            if (idMateria != -1) {
                val dbManager = DatabaseManager(this)
                dbManager.open()

                val rowsDeleted = dbManager.eliminarMateria(idMateria)

                if (rowsDeleted > 0) {
                    // La materia se eliminó correctamente
                    mostrarMensaje("Materia eliminada correctamente")
                } else {
                    // Hubo un error al eliminar la materia
                    mostrarMensaje("Error al eliminar la materia")
                }

                dbManager.close()
            }
        }

        btnModificarMateria.setOnClickListener {
            val nuevoNombre = editTextNombreMateria.text.toString()
            val nuevaCalificacion = editTextCalificacion.text.toString().toDoubleOrNull() ?: 0.0

            if (idMateria != -1) {
                val dbManager = DatabaseManager(this)
                dbManager.open()

                val rowsUpdated =
                    dbManager.actualizarMateria(idMateria, nuevoNombre, nuevaCalificacion)

                if (rowsUpdated > 0) {
                    // La materia se actualizó correctamente
                    mostrarMensaje("Materia actualizada correctamente")
                } else {
                    // Hubo un error al actualizar la materia
                    mostrarMensaje("Error al actualizar la materia")
                }

                dbManager.close()
            }
        }
    }
}
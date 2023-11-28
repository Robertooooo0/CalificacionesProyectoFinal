package com.example.calificacionesproyectofinal

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MateriasAdapter(private val materiasList: List<Materia>) :
    RecyclerView.Adapter<MateriasAdapter.MateriasViewHolder>() {

    class MateriasViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nombreMateriaTextView: TextView = itemView.findViewById(R.id.nombreMateriaTextView)
        val calificacionMateriaTextView: TextView = itemView.findViewById(R.id.calificacionMateriaTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MateriasViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_materia, parent, false)
        return MateriasViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MateriasViewHolder, position: Int) {
        val currentMateria = materiasList[position]
        holder.nombreMateriaTextView.text = currentMateria.nombre
        holder.calificacionMateriaTextView.text = currentMateria.calificacion.toString()

        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, editar_materias::class.java).apply {
                // Pasa los detalles de la materia a la actividad de edición
                putExtra("IdMateria", currentMateria.id) // Reemplaza 'id' con el campo adecuado en tu clase Materia
                putExtra("NombreMateria", currentMateria.nombre) // Pasar el nombre de la materia
                putExtra("CalificacionMateria", currentMateria.calificacion) // Pasar la calificación
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = materiasList.size
}

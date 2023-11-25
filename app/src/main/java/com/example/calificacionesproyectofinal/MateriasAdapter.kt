package com.example.calificacionesproyectofinal

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
    }

    override fun getItemCount() = materiasList.size
}

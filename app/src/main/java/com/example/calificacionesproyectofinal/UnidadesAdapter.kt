package com.example.calificacionesproyectofinal

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UnidadesAdapter (private val unidadesList: List<String>) : RecyclerView.Adapter<UnidadesAdapter.UnidadViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UnidadViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_unidad, parent, false)
        return UnidadViewHolder(view)
    }

    override fun onBindViewHolder(holder: UnidadViewHolder, position: Int) {
        val unidad = unidadesList[position]
        holder.bind(unidad)
    }

    override fun getItemCount(): Int {
        return unidadesList.size
    }

    inner class UnidadViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(unidad: String) {
            itemView.findViewById<TextView>(R.id.txtUnidad).text = unidad
        }
    }
}
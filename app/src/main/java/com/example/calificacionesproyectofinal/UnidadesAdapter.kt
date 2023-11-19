package com.example.calificacionesproyectofinal

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
class UnidadesAdapter(

private val unidadesList: List<String>,
private val idSemestre: Int, // Agregamos el ID del semestre al constructor del adaptador
private val listener: OnUnidadClickListener
) : RecyclerView.Adapter<UnidadesAdapter.UnidadViewHolder>() {

    interface OnUnidadClickListener {
        fun onUnidadClick(idSemestre: Int, idUnidad: Int, nombreUnidad: String)
    }

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

    inner class UnidadViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private val txtUnidad: TextView = itemView.findViewById(R.id.txtUnidad)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(unidad: String) {
            txtUnidad.text = unidad
        }

        override fun onClick(view: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                val idUnidad = obtenerIdUnidad(position) // Obtenemos el ID de la unidad
                val nombreUnidad = unidadesList[position]

                listener.onUnidadClick(idSemestre, idUnidad, nombreUnidad)
            }
        }
    }

    private fun obtenerIdUnidad(position: Int): Int {
        // Implementa la lógica para obtener el ID de la unidad aquí
        return position + 1 // Ejemplo: posición + 1 como ID de la unidad
    }
}

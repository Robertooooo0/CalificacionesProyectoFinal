package com.example.calificacionesproyectofinal

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
class UnidadesAdapter(private val unidadesList: List<String>, private val listener: OnUnidadClickListener) :
    RecyclerView.Adapter<UnidadesAdapter.UnidadViewHolder>() {

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
                // Aquí puedes agregar la lógica para obtener los IDs correspondientes
                val idSemestre = obtenerIdSemestre() // Reemplaza con tu lógica para obtener el ID del semestre
                val idUnidad = obtenerIdUnidad(position) // Reemplaza con tu lógica para obtener el ID de la unidad
                val nombreUnidad = unidadesList[position]

                listener.onUnidadClick(idSemestre, idUnidad, nombreUnidad)
            }
        }
    }

    // Método para obtener el ID del semestre
    private fun obtenerIdSemestre(): Int {
        // Implementa la lógica para obtener el ID del semestre aquí
        return 1 // Reemplaza con el ID real del semestre
    }

    // Método para obtener el ID de la unidad
    private fun obtenerIdUnidad(position: Int): Int {
        // Implementa la lógica para obtener el ID de la unidad aquí
        return position + 1 // Ejemplo: posición + 1 como ID de la unidad
    }
}


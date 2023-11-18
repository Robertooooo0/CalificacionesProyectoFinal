package com.example.calificacionesproyectofinal

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdapterMostrarUnidades(private val unidadesList: List<String>, private val listener: OnUnidadClickListener) :
    RecyclerView.Adapter<AdapterMostrarUnidades.UnidadViewHolder>() {

    interface OnUnidadClickListener {
        fun onUnidadClick(idSemestre: Int, idUnidad: Int, nombreUnidad: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UnidadViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_agregar_materia_con_unidad, parent, false)
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
        private val txtUnidad: TextView = itemView.findViewById(R.id.textView9)
        private val checkBox: CheckBox = itemView.findViewById(R.id.checkBox)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(unidad: String) {
            txtUnidad.text = unidad
            checkBox.isChecked = false // Set initial state as needed
        }

        override fun onClick(view: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                val idSemestre = obtenerIdSemestre()
                val idUnidad = obtenerIdUnidad(position)
                val nombreUnidad = unidadesList[position]

                listener.onUnidadClick(idSemestre, idUnidad, nombreUnidad)
            }
        }
    }

    private fun obtenerIdSemestre(): Int {
        return 1 // Replace with actual logic to obtain semester ID
    }

    private fun obtenerIdUnidad(position: Int): Int {
        return position + 1 // Example: position + 1 as unit ID
    }
}




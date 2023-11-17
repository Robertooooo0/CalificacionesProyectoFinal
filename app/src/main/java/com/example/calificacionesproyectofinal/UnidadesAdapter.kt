package com.example.calificacionesproyectofinal

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UnidadesAdapter(private val unidadesList: List<String>, private val listener: OnUnidadClickListener) :
    RecyclerView.Adapter<UnidadesAdapter.UnidadViewHolder>() {

    interface OnUnidadClickListener {
        fun onUnidadClick(id: Int, nombre: String)
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
                val nombreUnidad = unidadesList[position]
                listener.onUnidadClick(position, nombreUnidad)
            }
        }
    }
}

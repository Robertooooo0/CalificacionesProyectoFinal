package com.example.calificacionesproyectofinal

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UnidadesAdapter(
    private val unidadesList: List<String>,
    private val idSemestre: Int,
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
                val idUnidad = obtenerIdUnidad(position)
                val nombreUnidad = unidadesList[position]

                listener.onUnidadClick(idSemestre, idUnidad, nombreUnidad)
            }
        }
    }

    private fun obtenerIdUnidad(position: Int): Int {
        // Aquí se debería obtener el ID de la unidad asociado con el elemento en la posición 'position'
        // Podrías usar el índice de la lista como ID si los elementos en la lista están relacionados con IDs consecutivos.
        // Por ejemplo:
        // return position + 1 // Esto podría ser suficiente si los elementos en la lista están numerados consecutivamente.

        // Si la lista de unidades está vinculada a IDs específicos de unidades (como el ID de la base de datos),
        // entonces necesitarías extraer el ID de la lista de IDs de unidades o del modelo asociado.

        // Por ejemplo, si las unidades están vinculadas a un ID de materia, y este adaptador se usa para mostrar
        // unidades de una materia específica, podrías necesitar un modelo de datos que contenga información sobre
        // las unidades, sus IDs y la relación con la materia.
        // Eso podría verse así:
        // val unidad = unidadesList[position]
        // val unidadId = unidad.id // Obtener el ID de la unidad desde el modelo de datos

        // En este ejemplo, estoy suponiendo que 'unidadesList' es una lista de modelos de datos con un ID asociado.

        // También podrías necesitar manejar casos nulos o verificar la integridad de tus datos aquí.

        return position + 1 // Si no tienes un modelo con IDs, usar el índice de la lista como ID de la unidad.
    }
}

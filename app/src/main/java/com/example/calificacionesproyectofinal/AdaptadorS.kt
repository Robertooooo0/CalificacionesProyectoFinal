package com.example.calificacionesproyectofinal


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class AdaptadorS (private var contexto: Context, private var listaNombres: List<NombreSemestre>) :
    RecyclerView.Adapter<AdaptadorS.ViewHolderSemestre>() {



    class ViewHolderSemestre(item: View) :
        RecyclerView.ViewHolder(item) {
        var nombre: TextView = item.findViewById(R.id.txtSemestreNombre)


    }
    // sirve para especificar la vista
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderSemestre {
        val layoutItem =
            LayoutInflater.from(parent.context).inflate(R.layout.itemsemestre, parent, false)
        return ViewHolderSemestre(layoutItem)
    }

    // sirve para decir cuantas vececes se va a repetir
    override fun getItemCount(): Int =listaNombres.size


    // llena los datos en cada repeticion
    override fun onBindViewHolder(holder: ViewHolderSemestre, position: Int) {
        val semestre = listaNombres[position]
        val activity = contexto as Semestre
        holder.nombre.text = semestre.nombre


        holder.itemView.setOnClickListener {
            activity.onClickSemestre(semestre)
        }


    }
}
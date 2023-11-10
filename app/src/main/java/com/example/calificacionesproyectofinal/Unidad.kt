package com.example.calificacionesproyectofinal

import androidx.room.PrimaryKey

data class Unidad(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nombre: String
)
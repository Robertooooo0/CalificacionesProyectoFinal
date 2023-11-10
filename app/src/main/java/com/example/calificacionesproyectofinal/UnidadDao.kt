package com.example.calificacionesproyectofinal

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UnidadDao {
    @Insert
    suspend fun insertUnidad(unidad: Unidad)
    @Query("SELECT * FROM Unidad")
    suspend fun getAllUnidades(): List<Unidad>

    // Otros métodos para actualizar, borrar, o cualquier consulta necesaria.
}
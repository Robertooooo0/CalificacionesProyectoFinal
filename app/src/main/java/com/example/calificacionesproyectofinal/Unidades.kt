package com.example.calificacionesproyectofinal
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Unidades : AppCompatActivity(), UnidadesAdapter.OnUnidadClickListener {

    lateinit var txtSemestre: TextView
    lateinit var txtIdSemestre: TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: UnidadesAdapter
    private var unidadesList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_unidades)

        txtSemestre = findViewById(R.id.txtSemestreN)
        txtIdSemestre = findViewById(R.id.txtIdSemestre)

        val nombreS = intent.getStringExtra("NombreSemestre").toString()
        val idSemestre = intent.getIntExtra("IdSemestre", -1) // Recibir el ID del semestre

        txtSemestre.text = nombreS
        txtIdSemestre.text = "ID del Semestre: $idSemestre" // Mostrar el ID del semestre

        val btnSiguienteUnidades = findViewById<Button>(R.id.btnSiguienteunidades)

        btnSiguienteUnidades.setOnClickListener {
            val intent = Intent(this, agregra_unidades::class.java)
            intent.putExtra("IdSemestre", idSemestre) // Envía el ID del semestre a agregar_materia
            startActivityForResult(intent, REQUEST_CODE_AGREGAR_UNIDAD)
        }

        // Obtener la lista de unidades del semestre desde la base de datos
        unidadesList.addAll(obtenerUnidadesPorIdSemestre(idSemestre))

        // Configurar el RecyclerView con el listener
        recyclerView = findViewById(R.id.rcvMostrarUnidades)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = UnidadesAdapter(unidadesList, idSemestre, this) // Pasar el ID del semestre al adaptador
        recyclerView.adapter = adapter
    }

    private fun obtenerUnidadesPorIdSemestre(idSemestre: Int): List<String> {
        val unidadesList = mutableListOf<String>()

        // Acceder a la base de datos para obtener las unidades correspondientes al ID de semestre
        val databaseManager = DatabaseManager(this)
        databaseManager.open()

        // Hacer la consulta para obtener las unidades del semestre específico
        val cursor = databaseManager.queryData(
            DatabaseHelper.TABLE_UNIDADES,
            arrayOf(DatabaseHelper.COLUMN_NOMBRE_UNIDAD),
            "${DatabaseHelper.COLUMN_ID_SEMESTRE} = ?",
            arrayOf(idSemestre.toString()),
            null
        )

        // Recorrer el cursor y agregar las unidades a la lista
        cursor?.use {
            while (it.moveToNext()) {
                val nombreUnidad = it.getString(it.getColumnIndex(DatabaseHelper.COLUMN_NOMBRE_UNIDAD))
                unidadesList.add(nombreUnidad)
            }
        }

        databaseManager.close()

        return unidadesList
    }

    override fun onUnidadClick(idSemestre: Int, idUnidad: Int, nombreUnidad: String) {
        val intent = Intent(this, Materias::class.java)
        intent.putExtra("IdSemestre", idSemestre)
        intent.putExtra("IdUnidad", idUnidad)
        intent.putExtra("NombreUnidad", nombreUnidad)
        startActivity(intent)
    }

    companion object {
        private const val REQUEST_CODE_AGREGAR_UNIDAD = 123
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_AGREGAR_UNIDAD && resultCode == Activity.RESULT_OK) {
            val idSemestre = data?.getIntExtra("IdSemestre", -1)
            if (idSemestre != null && idSemestre != -1) {
                actualizarListaUnidades(idSemestre)
            }
        }
    }

    private fun actualizarListaUnidades(idSemestre: Int) {
        unidadesList.clear()
        unidadesList.addAll(obtenerUnidadesPorIdSemestre(idSemestre))
        adapter.notifyDataSetChanged()
    }
}

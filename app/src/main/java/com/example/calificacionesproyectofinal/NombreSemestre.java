package com.example.calificacionesproyectofinal;

public class NombreSemestre {
    private String nombre;
    private  int idSemestre;

    public  NombreSemestre(String nombre, int IdSemestre) {
        this.nombre = nombre;
        this.idSemestre = IdSemestre;
    }


    public String getNombre() {
        return nombre;
    }
    public int getIdSemestre() { return idSemestre;}
    }


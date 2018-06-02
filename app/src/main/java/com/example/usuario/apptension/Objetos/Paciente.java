package com.example.usuario.apptension.Objetos;

/**
 * Created by USUARIO on 28/12/2017.
 */

public class Paciente {
    String nombre;
    String apellido;
    String num_segsoc;
    String sistole;
    String diastole;
    String id;



    public Paciente(String nombre, String apellido, String num_segsoc, String sistole, String diastole, String id) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.num_segsoc = num_segsoc;
        this.sistole = sistole;
        this.diastole = diastole;
        this.id = id;
    }

    public Paciente() {
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNum_segsoc() {
        return num_segsoc;
    }

    public void setNum_segsoc(String num_segsoc) {
        this.num_segsoc = num_segsoc;
    }

    public String getSistole() {
        return sistole;
    }

    public void setSistole(String sistole) {
        this.sistole = sistole;
    }

    public String getDiastole() {
        return diastole;
    }

    public void setDiastole(String diastole) {
        this.diastole = diastole;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Paciente{" + " num_segsoc = ' " + num_segsoc + '\'' + ", sistole='" + sistole + '\'' + ", diastole='" + diastole + '\'' + '}';
    }
}

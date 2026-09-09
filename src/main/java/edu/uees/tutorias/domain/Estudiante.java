package edu.uees.tutorias.domain;

public class Estudiante extends Usuario {
    private final String carrera;

    public Estudiante(String id, String nombre, String email, String carrera) {
        super(id, nombre, email);
        this.carrera = carrera;
    }

    public String getCarrera() { return carrera; }
}

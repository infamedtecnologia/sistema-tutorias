package edu.uees.tutorias.domain;

/**
 * Estudiante que puede solicitar tutorias.
 * No conoce el mecanismo de persistencia ni de notificacion: solo
 * representa datos e identidad, tal como corresponde a una entidad
 * de dominio con responsabilidad acotada (alta cohesion).
 */
public class Estudiante extends Usuario {

    private final String carrera;

    public Estudiante(String id, String nombre, String email, String carrera) {
        super(id, nombre, email);
        this.carrera = carrera;
    }

    public String getCarrera() {
        return carrera;
    }

    @Override
    public String toString() {
        return "Estudiante{" + getNombre() + ", " + carrera + "}";
    }
}

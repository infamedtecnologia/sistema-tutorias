package edu.uees.tutorias.domain;

import java.util.Objects;

/**
 * Representa a cualquier persona registrada en el sistema.
 *
 * Estudiante y Docente comparten identidad y datos de contacto: ambos
 * "son" un usuario del sistema y ambos deben poder ser notificados.
 * Por eso la relacion es de generalizacion (herencia) y no solo de
 * reutilizacion de codigo: el sistema necesita tratar a ambos de forma
 * uniforme quando, por ejemplo, se envia una notificacion.
 */
public abstract class Usuario {

    private final String id;
    private final String nombre;
    private final String email;

    protected Usuario(String id, String nombre, String email) {
        this.id = Objects.requireNonNull(id, "id no puede ser nulo");
        this.nombre = Objects.requireNonNull(nombre, "nombre no puede ser nulo");
        this.email = validarEmail(email);
    }

    private String validarEmail(String email) {
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("email invalido: " + email);
        }
        return email;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario)) return false;
        Usuario usuario = (Usuario) o;
        return id.equals(usuario.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

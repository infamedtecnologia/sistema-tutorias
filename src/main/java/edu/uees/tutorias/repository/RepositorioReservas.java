package edu.uees.tutorias.repository;

import edu.uees.tutorias.domain.Estudiante;
import edu.uees.tutorias.domain.Reserva;

import java.util.List;
import java.util.Optional;

/**
 * Abstraccion de persistencia para reservas.
 *
 * ServicioReservas depende de esta interfaz, no de una base de datos
 * concreta. Si mañana el sistema cambia de almacenamiento en memoria a
 * una base relacional o a un servicio externo, basta con escribir una
 * nueva implementacion; la logica de dominio no se modifica.
 */
public interface RepositorioReservas {
    void guardar(Reserva reserva);
    Optional<Reserva> buscarPorId(String id);
    List<Reserva> listarPorEstudiante(Estudiante estudiante);
}

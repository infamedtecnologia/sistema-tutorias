package edu.uees.tutorias.domain;

import java.util.Objects;

/**
 * Registra el encuentro entre un estudiante y un horario de un docente,
 * y protege las transiciones validas de su propio ciclo de vida.
 *
 * La Reserva es responsable unicamente de su estado (PENDIENTE,
 * CONFIRMADA, CANCELADA, REPROGRAMADA); no sabe como se notifica a los
 * usuarios ni como se persiste. Esa separacion es la que permite
 * cambiar el mecanismo de notificacion o de almacenamiento sin tocar
 * esta clase (baja acoplamiento con esas dos preocupaciones).
 */
public class Reserva {

    private final String id;
    private final Estudiante estudiante;
    private Horario horario;
    private EstadoReserva estado;

    public Reserva(String id, Estudiante estudiante, Horario horario) {
        this.id = Objects.requireNonNull(id);
        this.estudiante = Objects.requireNonNull(estudiante);
        this.horario = Objects.requireNonNull(horario);
        this.estado = EstadoReserva.PENDIENTE;
    }

    public void confirmar() {
        if (estado != EstadoReserva.PENDIENTE) {
            throw new IllegalStateException("Solo una reserva PENDIENTE puede confirmarse");
        }
        estado = EstadoReserva.CONFIRMADA;
    }

    public void cancelar() {
        if (estado == EstadoReserva.CANCELADA) {
            throw new IllegalStateException("La reserva ya esta cancelada");
        }
        horario.liberar();
        estado = EstadoReserva.CANCELADA;
    }

    public void reprogramar(Horario nuevoHorario) {
        if (estado == EstadoReserva.CANCELADA) {
            throw new IllegalStateException("No se puede reprogramar una reserva cancelada");
        }
        horario.liberar();
        nuevoHorario.marcarComoReservado();
        this.horario = nuevoHorario;
        this.estado = EstadoReserva.REPROGRAMADA;
    }

    public String getId() {
        return id;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public Horario getHorario() {
        return horario;
    }

    public EstadoReserva getEstado() {
        return estado;
    }

    @Override
    public String toString() {
        return "Reserva{" + id + ", " + estudiante.getNombre() + ", " + estado + "}";
    }
}

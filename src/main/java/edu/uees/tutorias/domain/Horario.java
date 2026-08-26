package edu.uees.tutorias.domain;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

/**
 * Bloque de tiempo que un docente ofrece para tutorias.
 *
 * El propio Horario protege su regla de disponibilidad: nadie externo
 * puede "poner" el estado directamente (no hay setEstado publico).
 * Solo el propio objeto decide, a traves de marcarComoReservado() y
 * liberar(), si la transicion es valida. Esto evita que el estado
 * quede inconsistente por un error en otra clase.
 */
public class Horario {

    private final String id;
    private final Docente docente;
    private final LocalDate fecha;
    private final LocalTime horaInicio;
    private final LocalTime horaFin;
    private EstadoHorario estado;

    Horario(String id, Docente docente, LocalDate fecha, LocalTime horaInicio, LocalTime horaFin) {
        if (!horaInicio.isBefore(horaFin)) {
            throw new IllegalArgumentException("horaInicio debe ser anterior a horaFin");
        }
        this.id = id;
        this.docente = docente;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.estado = EstadoHorario.DISPONIBLE;
    }

    public boolean seSolapaCon(Horario otro) {
        if (!this.fecha.equals(otro.fecha)) {
            return false;
        }
        return this.horaInicio.isBefore(otro.horaFin) && otro.horaInicio.isBefore(this.horaFin);
    }

    public void marcarComoReservado() {
        if (estado != EstadoHorario.DISPONIBLE) {
            throw new IllegalStateException("El horario " + id + " no esta disponible");
        }
        estado = EstadoHorario.RESERVADO;
    }

    public void liberar() {
        estado = EstadoHorario.DISPONIBLE;
    }

    public boolean estaDisponible() {
        return estado == EstadoHorario.DISPONIBLE;
    }

    public String getId() {
        return id;
    }

    public Docente getDocente() {
        return docente;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }

    public EstadoHorario getEstado() {
        return estado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Horario)) return false;
        Horario horario = (Horario) o;
        return id.equals(horario.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Horario{" + fecha + " " + horaInicio + "-" + horaFin + ", " + estado + "}";
    }
}

package edu.uees.tutorias.domain;

public class Reserva {
    private final String id;
    private final Estudiante estudiante;
    private final Docente docente;
    private Horario horario;
    private final String materia;
    private final String modalidad;
    private final String linkReunion;
    private final String notas;
    private final int recordatorioMinutos;
    private EstadoReserva estado;

    Reserva(ReservaBuilder builder) {
        this.id = builder.id;
        this.estudiante = builder.estudiante;
        this.docente = builder.docente;
        this.horario = builder.horario;
        this.materia = builder.materia;
        this.modalidad = builder.modalidad;
        this.linkReunion = builder.linkReunion;
        this.notas = builder.notas;
        this.recordatorioMinutos = builder.recordatorioMinutos;
        this.estado = EstadoReserva.PENDIENTE;
        this.horario.marcarComoReservado();
    }

    public void confirmar() {
        if (estado != EstadoReserva.PENDIENTE) {
            throw new IllegalStateException("Solo una reserva PENDIENTE puede ser confirmada.");
        }
        this.estado = EstadoReserva.CONFIRMADA;
    }

    public void cancelar() {
        if (estado == EstadoReserva.CANCELADA) {
            throw new IllegalStateException("La reserva ya está cancelada.");
        }
        this.estado = EstadoReserva.CANCELADA;
        this.horario.liberar();
    }

    public void reprogramar(Horario nuevoHorario) {
        if (nuevoHorario.getEstado() != EstadoHorario.DISPONIBLE) {
            throw new IllegalStateException("El nuevo horario no está disponible.");
        }
        this.horario.liberar();
        this.horario = nuevoHorario;
        this.horario.marcarComoReservado();
        this.estado = EstadoReserva.REPROGRAMADA;
    }

    public String getId() { return id; }
    public Estudiante getEstudiante() { return estudiante; }
    public Docente getDocente() { return docente; }
    public Horario getHorario() { return horario; }
    public String getMateria() { return materia; }
    public String getModalidad() { return modalidad; }
    public String getLinkReunion() { return linkReunion; }
    public String getNotas() { return notas; }
    public int getRecordatorioMinutos() { return recordatorioMinutos; }
    public EstadoReserva getEstado() { return estado; }

    @Override
    public String toString() {
        return "Reserva {" +
                "id='" + id + '\'' +
                ", estudiante=" + estudiante.getNombre() +
                ", docente=" + docente.getNombre() +
                ", materia='" + materia + '\'' +
                ", modalidad='" + modalidad + '\'' +
                ", estado=" + estado +
                '}';
    }
}

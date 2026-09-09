package edu.uees.tutorias.domain;

public class Horario {
    private final String id;
    private final String fecha;
    private final String horaInicio;
    private final String horaFin;
    private EstadoHorario estado;

    public Horario(String id, String fecha, String horaInicio, String horaFin) {
        this.id = id;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.estado = EstadoHorario.DISPONIBLE;
    }

    public boolean seSolapaCon(Horario otro) {
        return this.fecha.equals(otro.fecha) && this.horaInicio.equals(otro.horaInicio);
    }

    public void marcarComoReservado() {
        if (estado != EstadoHorario.DISPONIBLE) {
            throw new IllegalStateException("El horario ya no está disponible.");
        }
        this.estado = EstadoHorario.RESERVADO;
    }

    public void liberar() {
        this.estado = EstadoHorario.DISPONIBLE;
    }

    public String getId() { return id; }
    public String getFecha() { return fecha; }
    public String getHoraInicio() { return horaInicio; }
    public String getHoraFin() { return horaFin; }
    public EstadoHorario getEstado() { return estado; }
}

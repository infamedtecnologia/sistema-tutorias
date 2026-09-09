package edu.uees.tutorias.domain;

public class ReservaBuilder {
    String id;
    Estudiante estudiante;
    Docente docente;
    Horario horario;

    String materia = "Tutoría General";
    String modalidad = "Presencial";
    String linkReunion = null;
    String notas = "";
    int recordatorioMinutos = 15;

    public ReservaBuilder(String id, Estudiante estudiante, Docente docente, Horario horario) {
        this.id = id;
        this.estudiante = estudiante;
        this.docente = docente;
        this.horario = horario;
    }

    public static ReservaBuilder de(String id, Estudiante estudiante, Docente docente, Horario horario) {
        return new ReservaBuilder(id, estudiante, docente, horario);
    }

    public ReservaBuilder conMateria(String materia) {
        this.materia = materia;
        return this;
    }

    public ReservaBuilder conModalidad(String modalidad) {
        this.modalidad = modalidad;
        return this;
    }

    public ReservaBuilder conLinkReunion(String linkReunion) {
        this.linkReunion = linkReunion;
        return this;
    }

    public ReservaBuilder conNotas(String notas) {
        this.notas = notas;
        return this;
    }

    public ReservaBuilder conRecordatorio(int minutos) {
        this.recordatorioMinutos = minutos;
        return this;
    }

    public Reserva build() {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalStateException("El ID de la reserva es obligatorio.");
        }
        if (estudiante == null) {
            throw new IllegalStateException("El estudiante es obligatorio.");
        }
        if (docente == null) {
            throw new IllegalStateException("El docente es obligatorio.");
        }
        if (horario == null) {
            throw new IllegalStateException("El horario es obligatorio.");
        }

        if ("Virtual".equalsIgnoreCase(modalidad) && (linkReunion == null || linkReunion.trim().isEmpty())) {
            throw new IllegalStateException("Para tutorías Virtuales es obligatorio incluir el enlace de reunión.");
        }

        return new Reserva(this);
    }
}

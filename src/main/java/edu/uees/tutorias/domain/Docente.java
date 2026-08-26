package edu.uees.tutorias.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Docente que publica y administra sus horarios de tutoria.
 *
 * La lista de horarios se mantiene encapsulada: nadie fuera de esta
 * clase puede agregarlos o quitarlos directamente, solo a traves de
 * publicarHorario(). Esto protege la regla "un docente no puede
 * publicar dos horarios que se solapen".
 */
public class Docente extends Usuario {

    private final String especialidad;
    private final List<Horario> horarios = new ArrayList<>();

    public Docente(String id, String nombre, String email, String especialidad) {
        super(id, nombre, email);
        this.especialidad = especialidad;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public Horario publicarHorario(java.time.LocalDate fecha,
                                    java.time.LocalTime horaInicio,
                                    java.time.LocalTime horaFin) {
        Horario nuevo = new Horario(
                this.getId() + "-" + (horarios.size() + 1), this, fecha, horaInicio, horaFin);
        if (seSolapaConExistente(nuevo)) {
            throw new IllegalStateException(
                    "El horario propuesto se solapa con uno ya publicado por " + getNombre());
        }
        horarios.add(nuevo);
        return nuevo;
    }

    private boolean seSolapaConExistente(Horario candidato) {
        return horarios.stream().anyMatch(h -> h.seSolapaCon(candidato));
    }

    /** Vista de solo lectura: la lista real solo se modifica desde publicarHorario(). */
    public List<Horario> getHorarios() {
        return Collections.unmodifiableList(horarios);
    }

    @Override
    public String toString() {
        return "Docente{" + getNombre() + ", " + especialidad + "}";
    }
}

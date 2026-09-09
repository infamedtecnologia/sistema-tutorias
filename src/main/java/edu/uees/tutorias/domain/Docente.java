package edu.uees.tutorias.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Docente extends Usuario {
    private final String especialidad;
    private final List<Horario> horariosPublicados = new ArrayList<>();

    public Docente(String id, String nombre, String email, String especialidad) {
        super(id, nombre, email);
        this.especialidad = especialidad;
    }

    public String getEspecialidad() { return especialidad; }

    public void publicarHorario(Horario horario) {
        for (Horario h : horariosPublicados) {
            if (h.seSolapaCon(horario)) {
                throw new IllegalArgumentException("El horario a publicar se solapa con uno existente.");
            }
        }
        horariosPublicados.add(horario);
    }

    public List<Horario> getHorariosPublicados() {
        return Collections.unmodifiableList(horariosPublicados);
    }
}

package edu.uees.tutorias.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ReservaBuilderTest {

    @Test
    @DisplayName("Debe construir reserva con valores por defecto")
    void testReservaBuilderDefault() {
        Estudiante e = new Estudiante("E1", "Juan", "j@uees.ec", "Sistemas");
        Docente d = new Docente("D1", "Pedro", "p@uees.ec", "Software");
        Horario h = new Horario("H1", "2026-09-10", "10:00", "11:00");

        Reserva r = ReservaBuilder.de("R1", e, d, h).build();

        assertEquals("R1", r.getId());
        assertEquals("Presencial", r.getModalidad());
        assertEquals("Tutoría General", r.getMateria());
        assertEquals(EstadoReserva.PENDIENTE, r.getEstado());
    }

    @Test
    @DisplayName("Debe lanzar excepción si reserva Virtual no incluye link de reunión")
    void testReservaVirtualSinLink() {
        Estudiante e = new Estudiante("E1", "Juan", "j@uees.ec", "Sistemas");
        Docente d = new Docente("D1", "Pedro", "p@uees.ec", "Software");
        Horario h = new Horario("H1", "2026-09-10", "10:00", "11:00");

        assertThrows(IllegalStateException.class, () -> {
            ReservaBuilder.de("R2", e, d, h).conModalidad("Virtual").build();
        });
    }
}

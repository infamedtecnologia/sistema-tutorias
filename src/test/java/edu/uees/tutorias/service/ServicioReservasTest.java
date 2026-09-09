package edu.uees.tutorias.service;

import edu.uees.tutorias.domain.*;
import edu.uees.tutorias.repository.RepositorioReservasEnMemoria;
import edu.uees.tutorias.strategy.CancelacionEstandarStrategy;
import edu.uees.tutorias.strategy.ResultadoCancelacion;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ServicioReservasTest {

    @Test
    @DisplayName("ServicioReservas debe registrar y cancelar reservas correctamente")
    void testServicioFlujoCompleto() {
        ServicioReservas servicio = new ServicioReservas(new RepositorioReservasEnMemoria());

        Estudiante e = new Estudiante("E1", "Juan", "j@uees.ec", "Sistemas");
        Docente d = new Docente("D1", "Pedro", "p@uees.ec", "Software");
        Horario h = new Horario("H1", "2026-09-10", "10:00", "11:00");

        Reserva r = ReservaBuilder.de("R1", e, d, h).build();
        servicio.registrarReserva(r);

        ResultadoCancelacion res = servicio.cancelarReserva("R1", 36, new CancelacionEstandarStrategy(), "Viaje de estudios");
        assertTrue(res.isExitosa());
        assertEquals(EstadoReserva.CANCELADA, r.getEstado());
        assertEquals(EstadoHorario.DISPONIBLE, h.getEstado());
    }
}

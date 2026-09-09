package edu.uees.tutorias.observer;

import edu.uees.tutorias.domain.*;
import edu.uees.tutorias.factory.NotificadorEmailFactory;
import edu.uees.tutorias.repository.RepositorioReservasEnMemoria;
import edu.uees.tutorias.service.ServicioReservas;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ObserverTest {

    @Test
    @DisplayName("ServicioReservas debe notificar a los observadores al cambiar de estado")
    void testObserverNotification() {
        ServicioReservas servicio = new ServicioReservas(new RepositorioReservasEnMemoria());
        
        final boolean[] notificado = {false};
        servicio.agregarObserver((reserva, anterior, nuevo, motivo) -> {
            notificado[0] = true;
            assertEquals(EstadoReserva.PENDIENTE, anterior);
            assertEquals(EstadoReserva.CONFIRMADA, nuevo);
        });

        Estudiante e = new Estudiante("E1", "Juan", "j@uees.ec", "Sistemas");
        Docente d = new Docente("D1", "Pedro", "p@uees.ec", "Software");
        Horario h = new Horario("H1", "2026-09-10", "10:00", "11:00");

        Reserva r = ReservaBuilder.de("R1", e, d, h).build();
        servicio.registrarReserva(r);
        servicio.confirmarReserva("R1");

        assertTrue(notificado[0]);
    }
}

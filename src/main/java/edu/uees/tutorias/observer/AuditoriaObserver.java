package edu.uees.tutorias.observer;

import edu.uees.tutorias.domain.EstadoReserva;
import edu.uees.tutorias.domain.Reserva;
import java.time.LocalDateTime;

public class AuditoriaObserver implements ReservaObserver {
    @Override
    public void alCambiarEstadoReserva(Reserva reserva, EstadoReserva estadoAnterior, EstadoReserva estadoNuevo, String motivo) {
        System.out.println("[AUDITORÍA LOG " + LocalDateTime.now() + "] Reserva: " + reserva.getId() + 
                " | Transición: " + estadoAnterior + " -> " + estadoNuevo + 
                " | Motivo: " + (motivo.isEmpty() ? "Operación estándar" : motivo));
    }
}

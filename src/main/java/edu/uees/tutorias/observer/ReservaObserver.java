package edu.uees.tutorias.observer;

import edu.uees.tutorias.domain.EstadoReserva;
import edu.uees.tutorias.domain.Reserva;

public interface ReservaObserver {
    void alCambiarEstadoReserva(Reserva reserva, EstadoReserva estadoAnterior, EstadoReserva estadoNuevo, String motivo);
}

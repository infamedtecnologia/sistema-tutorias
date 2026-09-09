package edu.uees.tutorias.observer;

import edu.uees.tutorias.domain.EstadoReserva;
import edu.uees.tutorias.domain.Reserva;

public class CalendarioObserver implements ReservaObserver {
    @Override
    public void alCambiarEstadoReserva(Reserva reserva, EstadoReserva estadoAnterior, EstadoReserva estadoNuevo, String motivo) {
        if (estadoNuevo == EstadoReserva.CONFIRMADA) {
            System.out.println("[CALENDARIO DOCENTE] Bloqueado en agenda de " + reserva.getDocente().getNombre() + 
                    " el horario " + reserva.getHorario().getFecha() + " " + reserva.getHorario().getHoraInicio());
        } else if (estadoNuevo == EstadoReserva.CANCELADA) {
            System.out.println("[CALENDARIO DOCENTE] Liberado espacio en agenda de " + reserva.getDocente().getNombre() + 
                    " para fecha " + reserva.getHorario().getFecha());
        }
    }
}

package edu.uees.tutorias.observer;

import edu.uees.tutorias.domain.EstadoReserva;
import edu.uees.tutorias.domain.Reserva;
import edu.uees.tutorias.factory.NotificadorFactory;

public class NotificacionObserver implements ReservaObserver {
    private final NotificadorFactory factory;

    public NotificacionObserver(NotificadorFactory factory) {
        this.factory = factory;
    }

    @Override
    public void alCambiarEstadoReserva(Reserva reserva, EstadoReserva estadoAnterior, EstadoReserva estadoNuevo, String motivo) {
        String msgEstudiante = "Hola " + reserva.getEstudiante().getNombre() + ", su reserva " + reserva.getId() + 
                " cambió de " + estadoAnterior + " a " + estadoNuevo + ". " + (motivo.isEmpty() ? "" : "Motivo: " + motivo);
        String msgDocente = "Estimado/a " + reserva.getDocente().getNombre() + ", la reserva " + reserva.getId() + 
                " del estudiante " + reserva.getEstudiante().getNombre() + " ahora está " + estadoNuevo + ".";

        factory.notificarUsuario(reserva.getEstudiante().getEmail(), msgEstudiante);
        factory.notificarUsuario(reserva.getDocente().getEmail(), msgDocente);
    }
}

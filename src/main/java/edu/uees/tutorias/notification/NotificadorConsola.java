package edu.uees.tutorias.notification;

import edu.uees.tutorias.domain.Reserva;

/**
 * Implementacion alternativa pensada para pruebas y demostraciones
 * locales. Su existencia evidencia el Open/Closed Principle: se
 * agrega un canal de notificacion nuevo sin modificar Notificador,
 * ServicioReservas ni ninguna implementacion previa.
 */
public class NotificadorConsola implements Notificador {

    @Override
    public void notificar(Reserva reserva, TipoEvento evento) {
        System.out.println("[CONSOLA] " + evento + " -> " + reserva);
    }
}

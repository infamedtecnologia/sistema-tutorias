package edu.uees.tutorias.notification;

import edu.uees.tutorias.domain.Reserva;

/**
 * Implementacion concreta que "envia" el evento por correo electronico.
 * En un entorno real aqui se integraria un proveedor de correo (SMTP,
 * SendGrid, etc.); para esta actividad se simula con salida de texto.
 */
public class NotificadorEmail implements Notificador {

    @Override
    public void notificar(Reserva reserva, TipoEvento evento) {
        String destinatario = reserva.getEstudiante().getEmail();
        System.out.printf(
                "[EMAIL a %s] Evento %s para la reserva %s%n",
                destinatario, evento, reserva.getId());
    }
}

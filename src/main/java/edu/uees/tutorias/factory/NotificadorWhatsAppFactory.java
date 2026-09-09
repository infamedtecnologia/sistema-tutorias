package edu.uees.tutorias.factory;

public class NotificadorWhatsAppFactory extends NotificadorFactory {
    @Override
    public Notificador crearNotificador() {
        return new NotificadorWhatsApp();
    }
}

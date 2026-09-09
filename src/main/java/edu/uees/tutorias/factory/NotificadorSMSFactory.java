package edu.uees.tutorias.factory;

public class NotificadorSMSFactory extends NotificadorFactory {
    @Override
    public Notificador crearNotificador() {
        return new NotificadorSMS();
    }
}

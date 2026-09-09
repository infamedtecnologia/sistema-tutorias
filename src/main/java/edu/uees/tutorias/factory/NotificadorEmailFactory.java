package edu.uees.tutorias.factory;

public class NotificadorEmailFactory extends NotificadorFactory {
    @Override
    public Notificador crearNotificador() {
        return new NotificadorEmail();
    }
}

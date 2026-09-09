package edu.uees.tutorias.factory;

public class NotificadorTeamsFactory extends NotificadorFactory {
    @Override
    public Notificador crearNotificador() {
        return new NotificadorTeams();
    }
}

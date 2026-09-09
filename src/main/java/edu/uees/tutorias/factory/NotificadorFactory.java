package edu.uees.tutorias.factory;

public abstract class NotificadorFactory {
    public abstract Notificador crearNotificador();

    public void notificarUsuario(String destinatario, String mensaje) {
        Notificador notificador = crearNotificador();
        notificador.enviar(destinatario, mensaje);
    }
}

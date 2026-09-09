package edu.uees.tutorias.factory;

public interface Notificador {
    void enviar(String destinatario, String mensaje);
    String getCanal();
}

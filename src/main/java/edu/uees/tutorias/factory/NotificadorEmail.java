package edu.uees.tutorias.factory;

public class NotificadorEmail implements Notificador {
    @Override
    public void enviar(String destinatario, String mensaje) {
        System.out.println("[NOTIFICACIÓN - EMAIL] Para <" + destinatario + ">: " + mensaje);
    }

    @Override
    public String getCanal() { return "Correo Electrónico"; }
}

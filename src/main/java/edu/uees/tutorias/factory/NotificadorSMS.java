package edu.uees.tutorias.factory;

public class NotificadorSMS implements Notificador {
    @Override
    public void enviar(String destinatario, String mensaje) {
        System.out.println("[NOTIFICACIÓN - SMS] Para <" + destinatario + ">: " + mensaje);
    }

    @Override
    public String getCanal() { return "SMS Celular"; }
}

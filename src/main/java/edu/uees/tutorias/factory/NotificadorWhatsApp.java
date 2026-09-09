package edu.uees.tutorias.factory;

public class NotificadorWhatsApp implements Notificador {
    @Override
    public void enviar(String destinatario, String mensaje) {
        System.out.println("[NOTIFICACIÓN - WHATSAPP] Para <" + destinatario + ">: " + mensaje);
    }

    @Override
    public String getCanal() { return "WhatsApp Business"; }
}

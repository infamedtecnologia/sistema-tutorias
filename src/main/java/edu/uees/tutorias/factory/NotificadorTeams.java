package edu.uees.tutorias.factory;

public class NotificadorTeams implements Notificador {
    @Override
    public void enviar(String destinatario, String mensaje) {
        System.out.println("[NOTIFICACIÓN - TEAMS] Para <" + destinatario + ">: " + mensaje);
    }

    @Override
    public String getCanal() { return "Microsoft Teams"; }
}

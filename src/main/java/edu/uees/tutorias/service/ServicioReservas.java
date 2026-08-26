package edu.uees.tutorias.service;

import edu.uees.tutorias.domain.Estudiante;
import edu.uees.tutorias.domain.Horario;
import edu.uees.tutorias.domain.Reserva;
import edu.uees.tutorias.notification.Notificador;
import edu.uees.tutorias.notification.TipoEvento;
import edu.uees.tutorias.repository.RepositorioReservas;

import java.util.Optional;
import java.util.UUID;

/**
 * Orquesta el ciclo de vida de una reserva: crear, confirmar, cancelar
 * y reprogramar. No conoce como se guarda una reserva ni como se
 * notifica a un estudiante: recibe esas dos capacidades por
 * constructor como interfaces (RepositorioReservas y Notificador).
 *
 * Esta separacion es la aplicacion concreta de SRP y DIP mencionada
 * en la Parte 4: ServicioReservas tiene una unica razon de cambio
 * (las reglas del proceso de reserva) y no depende de detalles de
 * infraestructura.
 */
public class ServicioReservas {

    private final RepositorioReservas repositorio;
    private final Notificador notificador;

    public ServicioReservas(RepositorioReservas repositorio, Notificador notificador) {
        this.repositorio = repositorio;
        this.notificador = notificador;
    }

    public Reserva crearReserva(Estudiante estudiante, Horario horario) {
        if (!horario.estaDisponible()) {
            throw new IllegalStateException("El horario " + horario.getId() + " no esta disponible");
        }
        horario.marcarComoReservado();
        Reserva reserva = new Reserva(UUID.randomUUID().toString(), estudiante, horario);
        repositorio.guardar(reserva);
        notificador.notificar(reserva, TipoEvento.RESERVA_CREADA);
        return reserva;
    }

    public void confirmarReserva(String idReserva) {
        Reserva reserva = obtenerOFallar(idReserva);
        reserva.confirmar();
        notificador.notificar(reserva, TipoEvento.RESERVA_CONFIRMADA);
    }

    public void cancelarReserva(String idReserva) {
        Reserva reserva = obtenerOFallar(idReserva);
        reserva.cancelar();
        notificador.notificar(reserva, TipoEvento.RESERVA_CANCELADA);
    }

    public void reprogramarReserva(String idReserva, Horario nuevoHorario) {
        Reserva reserva = obtenerOFallar(idReserva);
        reserva.reprogramar(nuevoHorario);
        notificador.notificar(reserva, TipoEvento.RESERVA_REPROGRAMADA);
    }

    private Reserva obtenerOFallar(String idReserva) {
        Optional<Reserva> reserva = repositorio.buscarPorId(idReserva);
        return reserva.orElseThrow(() ->
                new IllegalArgumentException("No existe una reserva con id " + idReserva));
    }
}

package edu.uees.tutorias.service;

import edu.uees.tutorias.domain.*;
import edu.uees.tutorias.observer.ReservaObserver;
import edu.uees.tutorias.repository.RepositorioReservas;
import edu.uees.tutorias.strategy.EstrategiaCancelacion;
import edu.uees.tutorias.strategy.ResultadoCancelacion;

import java.util.ArrayList;
import java.util.List;

public class ServicioReservas {
    private final RepositorioReservas repositorio;
    private final List<ReservaObserver> observers = new ArrayList<>();

    public ServicioReservas(RepositorioReservas repositorio) {
        this.repositorio = repositorio;
    }

    public void agregarObserver(ReservaObserver observer) {
        this.observers.add(observer);
    }

    public void removerObserver(ReservaObserver observer) {
        this.observers.remove(observer);
    }

    private void notificarObservers(Reserva reserva, EstadoReserva anterior, EstadoReserva nuevo, String motivo) {
        for (ReservaObserver obs : observers) {
            obs.alCambiarEstadoReserva(reserva, anterior, nuevo, motivo);
        }
    }

    public void registrarReserva(Reserva reserva) {
        repositorio.guardar(reserva);
        System.out.println("-> Reserva " + reserva.getId() + " registrada correctamente.");
    }

    public void confirmarReserva(String reservaId) {
        Reserva reserva = repositorio.buscarPorId(reservaId)
                .orElseThrow(() -> new IllegalArgumentException("Reserva no encontrada: " + reservaId));
        
        EstadoReserva anterior = reserva.getEstado();
        reserva.confirmar();
        notificarObservers(reserva, anterior, reserva.getEstado(), "Confirmación realizada por el sistema");
    }

    public ResultadoCancelacion cancelarReserva(String reservaId, int horasAnticipacion, EstrategiaCancelacion estrategia, String motivo) {
        Reserva reserva = repositorio.buscarPorId(reservaId)
                .orElseThrow(() -> new IllegalArgumentException("Reserva no encontrada: " + reservaId));

        ResultadoCancelacion resultado = estrategia.evaluarCancelacion(reserva, horasAnticipacion);

        if (resultado.isExitosa()) {
            EstadoReserva anterior = reserva.getEstado();
            reserva.cancelar();
            notificarObservers(reserva, anterior, reserva.getEstado(), motivo + " (" + resultado.getMensaje() + ")");
        } else {
            System.out.println("-> CANCELACIÓN RECHAZADA: " + resultado.getMensaje());
        }

        return resultado;
    }

    public void reprogramarReserva(String reservaId, Horario nuevoHorario, String motivo) {
        Reserva reserva = repositorio.buscarPorId(reservaId)
                .orElseThrow(() -> new IllegalArgumentException("Reserva no encontrada: " + reservaId));

        EstadoReserva anterior = reserva.getEstado();
        reserva.reprogramar(nuevoHorario);
        notificarObservers(reserva, anterior, reserva.getEstado(), motivo);
    }
}

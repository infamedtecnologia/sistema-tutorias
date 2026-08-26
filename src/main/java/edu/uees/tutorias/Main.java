package edu.uees.tutorias;

import edu.uees.tutorias.domain.Docente;
import edu.uees.tutorias.domain.Estudiante;
import edu.uees.tutorias.domain.Horario;
import edu.uees.tutorias.domain.Reserva;
import edu.uees.tutorias.notification.Notificador;
import edu.uees.tutorias.notification.NotificadorEmail;
import edu.uees.tutorias.repository.RepositorioReservas;
import edu.uees.tutorias.repository.RepositorioReservasEnMemoria;
import edu.uees.tutorias.service.ServicioReservas;

import java.time.LocalDate;
import java.time.LocalTime;

/** Punto de entrada de demostracion del flujo principal del sistema. */
public class Main {

    public static void main(String[] args) {
        Docente docente = new Docente("D1", "Ana Torres", "ana.torres@uees.edu.ec", "Bases de Datos");
        Horario horario = docente.publicarHorario(
                LocalDate.of(2026, 9, 1), LocalTime.of(10, 0), LocalTime.of(11, 0));

        Estudiante estudiante = new Estudiante("E1", "Luis Perez", "luis.perez@uees.edu.ec", "Software");

        RepositorioReservas repositorio = new RepositorioReservasEnMemoria();
        Notificador notificador = new NotificadorEmail();
        ServicioReservas servicioReservas = new ServicioReservas(repositorio, notificador);

        Reserva reserva = servicioReservas.crearReserva(estudiante, horario);
        servicioReservas.confirmarReserva(reserva.getId());

        System.out.println("Estado final: " + reserva);
    }
}

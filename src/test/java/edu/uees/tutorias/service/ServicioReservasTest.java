package edu.uees.tutorias.service;

import edu.uees.tutorias.domain.Docente;
import edu.uees.tutorias.domain.Estudiante;
import edu.uees.tutorias.domain.EstadoReserva;
import edu.uees.tutorias.domain.Horario;
import edu.uees.tutorias.domain.Reserva;
import edu.uees.tutorias.notification.Notificador;
import edu.uees.tutorias.notification.TipoEvento;
import edu.uees.tutorias.repository.RepositorioReservas;
import edu.uees.tutorias.repository.RepositorioReservasEnMemoria;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Se usa una implementacion de prueba de Notificador (en lugar de
 * NotificadorEmail) precisamente porque ServicioReservas depende de
 * la interfaz, no de una implementacion concreta: esto demuestra en
 * la practica el beneficio del Dependency Inversion Principle.
 */
class ServicioReservasTest {

    private final List<TipoEvento> eventosNotificados = new ArrayList<>();
    private final Notificador notificadorDePrueba = (reserva, evento) -> eventosNotificados.add(evento);

    private RepositorioReservas repositorio;
    private ServicioReservas servicioReservas;
    private Docente docente;
    private Estudiante estudiante;
    private Horario horario;

    @BeforeEach
    void setUp() {
        repositorio = new RepositorioReservasEnMemoria();
        servicioReservas = new ServicioReservas(repositorio, notificadorDePrueba);
        docente = new Docente("D1", "Ana Torres", "ana@uees.edu.ec", "BD");
        horario = docente.publicarHorario(LocalDate.of(2026, 9, 1), LocalTime.of(10, 0), LocalTime.of(11, 0));
        estudiante = new Estudiante("E1", "Luis Perez", "luis@uees.edu.ec", "Software");
    }

    @Test
    void crearReservaDejaElHorarioComoReservadoYPersisteLaReserva() {
        Reserva reserva = servicioReservas.crearReserva(estudiante, horario);

        assertEquals(EstadoReserva.PENDIENTE, reserva.getEstado());
        assertTrue(!horario.estaDisponible());
        assertTrue(repositorio.buscarPorId(reserva.getId()).isPresent());
        assertEquals(TipoEvento.RESERVA_CREADA, eventosNotificados.get(0));
    }

    @Test
    void noSePuedeReservarUnHorarioYaReservado() {
        servicioReservas.crearReserva(estudiante, horario);
        Estudiante otro = new Estudiante("E2", "Maria Ruiz", "maria@uees.edu.ec", "Software");

        assertThrows(IllegalStateException.class,
                () -> servicioReservas.crearReserva(otro, horario));
    }

    @Test
    void cancelarReservaLiberaElHorario() {
        Reserva reserva = servicioReservas.crearReserva(estudiante, horario);

        servicioReservas.cancelarReserva(reserva.getId());

        assertEquals(EstadoReserva.CANCELADA, reserva.getEstado());
        assertTrue(horario.estaDisponible());
    }

    @Test
    void confirmarReservaCambiaElEstadoYNotifica() {
        Reserva reserva = servicioReservas.crearReserva(estudiante, horario);

        servicioReservas.confirmarReserva(reserva.getId());

        assertEquals(EstadoReserva.CONFIRMADA, reserva.getEstado());
        assertEquals(TipoEvento.RESERVA_CONFIRMADA, eventosNotificados.get(1));
    }

    @Test
    void docenteNoPuedePublicarHorariosQueSeSolapan() {
        assertThrows(IllegalStateException.class, () ->
                docente.publicarHorario(LocalDate.of(2026, 9, 1), LocalTime.of(10, 30), LocalTime.of(11, 30)));
    }
}

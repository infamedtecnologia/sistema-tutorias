package edu.uees.tutorias.notification;

import edu.uees.tutorias.domain.Reserva;

/**
 * Abstraccion minima para comunicar eventos de una reserva.
 *
 * Se mantiene deliberadamente pequena (un solo metodo) siguiendo el
 * Interface Segregation Principle: cualquier implementacion (email,
 * SMS, push, consola de pruebas) solo necesita resolver "como
 * notificar", nada mas. ServicioReservas depende de esta interfaz y
 * no de una implementacion concreta (Dependency Inversion Principle),
 * de modo que se puede sustituir el canal de comunicacion sin
 * modificar la logica de negocio.
 */
public interface Notificador {
    void notificar(Reserva reserva, TipoEvento evento);
}

package edu.uees.tutorias;

import edu.uees.tutorias.domain.*;
import edu.uees.tutorias.factory.*;
import edu.uees.tutorias.observer.*;
import edu.uees.tutorias.repository.*;
import edu.uees.tutorias.service.ServicioReservas;
import edu.uees.tutorias.strategy.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("==========================================================================");
        System.out.println("   UEES - UCOM0310 | ACTIVIDAD AE3: INCREMENTO 1 DEL PROYECTO INTEGRADOR");
        System.out.println("   Sistema de Gestión de Tutorías (Factory Method, Builder, Observer, Strategy)");
        System.out.println("==========================================================================");

        // 1. Instanciar repositorios y servicio
        RepositorioReservas repositorio = new RepositorioReservasEnMemoria();
        ServicioReservas servicio = new ServicioReservas(repositorio);

        // 2. Configurar el patrón OBSERVER
        NotificadorFactory emailFactory = new NotificadorEmailFactory();
        servicio.agregarObserver(new NotificacionObserver(emailFactory));
        servicio.agregarObserver(new AuditoriaObserver());
        servicio.agregarObserver(new CalendarioObserver());

        // 3. Crear actores del dominio
        Estudiante estudiante = new Estudiante("EST-001", "Cristian Calle", "cristian.calle@uees.edu.ec", "Sistemas");
        Docente docente = new Docente("DOC-001", "Ph.D. Jaime Sayago", "jaime.sayago@uees.edu.ec", "Arquitectura de Software");

        Horario h1 = new Horario("HOR-101", "2026-09-10", "09:00", "10:00");
        Horario h2 = new Horario("HOR-102", "2026-09-11", "15:00", "16:00");

        docente.publicarHorario(h1);
        docente.publicarHorario(h2);

        // 4. Demostración Patrón BUILDER
        System.out.println("\n--- [1. CREACIÓN DE RESERVAS CON BUILDER] ---");
        Reserva r1 = ReservaBuilder.de("RES-001", estudiante, docente, h1)
                .conMateria("Diseño de Software - Patrones GoF")
                .conModalidad("Virtual")
                .conLinkReunion("https://teams.microsoft.com/meet/res-001")
                .conRecordatorio(30)
                .conNotas("Revisión de incremento 1")
                .build();

        servicio.registrarReserva(r1);

        // 5. Demostración Patrón OBSERVER en Confirmación
        System.out.println("\n--- [2. CONFIRMACIÓN DE RESERVA (OBSERVER DISPARADO)] ---");
        servicio.confirmarReserva("RES-001");

        // 6. Demostración Patrón STRATEGY en Cancelaciones
        System.out.println("\n--- [3. CANCELACIÓN DE RESERVAS CON STRATEGY] ---");
        
        System.out.println("\nCaso A: Cancelación Estándar (30 horas de anticipación)");
        EstrategiaCancelacion estandar = new CancelacionEstandarStrategy();
        servicio.cancelarReserva("RES-001", 30, estandar, "Cruce con otra clase");

        System.out.println("\nCaso B: Cancelación de Tutoría Prioritaria (3 horas de anticipación)");
        Reserva r2 = ReservaBuilder.de("RES-002", estudiante, docente, h2)
                .conMateria("Examen Parcial de Diseño")
                .build();
        servicio.registrarReserva(r2);
        servicio.confirmarReserva("RES-002");

        EstrategiaCancelacion prioritaria = new CancelacionPrioritariaStrategy();
        servicio.cancelarReserva("RES-002", 3, prioritaria, "Emergencia de última hora");

        System.out.println("\n==========================================================================");
        System.out.println("   INCREMENTO 1 EJECUTADO CORRECTAMENTE CON ÉXITO");
        System.out.println("==========================================================================");
    }
}

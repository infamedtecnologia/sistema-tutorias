package edu.uees.tutorias.strategy;

import edu.uees.tutorias.domain.Reserva;

public class CancelacionPrioritariaStrategy implements EstrategiaCancelacion {
    @Override
    public ResultadoCancelacion evaluarCancelacion(Reserva reserva, int horasAnticipacion) {
        if (horasAnticipacion < 6) {
            return new ResultadoCancelacion(false, 100.0, "RECHAZADA: Tutoría prioritaria/examen no se puede cancelar con menos de 6 horas.");
        } else {
            return new ResultadoCancelacion(true, 30.0, "Cancelación de tutoría prioritaria aceptada con recargo del 30%.");
        }
    }
}

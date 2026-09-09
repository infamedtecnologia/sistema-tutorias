package edu.uees.tutorias.strategy;

import edu.uees.tutorias.domain.Reserva;

public class CancelacionTardiaStrategy implements EstrategiaCancelacion {
    @Override
    public ResultadoCancelacion evaluarCancelacion(Reserva reserva, int horasAnticipacion) {
        if (horasAnticipacion < 12) {
            return new ResultadoCancelacion(true, 50.0, "Cancelación tardía a menos de 12h. Penalización de 50% en crédito de tutorías.");
        } else {
            return new ResultadoCancelacion(true, 15.0, "Cancelación moderada. Penalización del 15%.");
        }
    }
}

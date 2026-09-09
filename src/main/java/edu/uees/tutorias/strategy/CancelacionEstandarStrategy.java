package edu.uees.tutorias.strategy;

import edu.uees.tutorias.domain.Reserva;

public class CancelacionEstandarStrategy implements EstrategiaCancelacion {
    @Override
    public ResultadoCancelacion evaluarCancelacion(Reserva reserva, int horasAnticipacion) {
        if (horasAnticipacion >= 24) {
            return new ResultadoCancelacion(true, 0.0, "Cancelación estándar a tiempo. Sin penalización.");
        } else {
            return new ResultadoCancelacion(true, 20.0, "Cancelación a menos de 24h. Aplica penalización leve (20%).");
        }
    }
}

package edu.uees.tutorias.strategy;

import edu.uees.tutorias.domain.Reserva;

public interface EstrategiaCancelacion {
    ResultadoCancelacion evaluarCancelacion(Reserva reserva, int horasAnticipacion);
}

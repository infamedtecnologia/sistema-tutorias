package edu.uees.tutorias.strategy;

import edu.uees.tutorias.domain.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StrategyTest {

    @Test
    @DisplayName("Estrategia Estandar debe permitir cancelacion a mas de 24h sin penalizacion")
    void testCancelacionEstandar() {
        EstrategiaCancelacion estandar = new CancelacionEstandarStrategy();
        ResultadoCancelacion res = estandar.evaluarCancelacion(null, 30);
        assertTrue(res.isExitosa());
        assertEquals(0.0, res.getPorcentajePenalizacion());
    }

    @Test
    @DisplayName("Estrategia Prioritaria debe rechazar cancelacion a menos de 6h")
    void testCancelacionPrioritariaRechazada() {
        EstrategiaCancelacion prioritaria = new CancelacionPrioritariaStrategy();
        ResultadoCancelacion res = prioritaria.evaluarCancelacion(null, 3);
        assertFalse(res.isExitosa());
        assertEquals(100.0, res.getPorcentajePenalizacion());
    }
}

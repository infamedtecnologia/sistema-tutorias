package edu.uees.tutorias.factory;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FactoryMethodTest {

    @Test
    @DisplayName("Factory Method debe instanciar el tipo de Notificador correcto")
    void testFactoryCreators() {
        NotificadorFactory emailFactory = new NotificadorEmailFactory();
        assertTrue(emailFactory.crearNotificador() instanceof NotificadorEmail);

        NotificadorFactory teamsFactory = new NotificadorTeamsFactory();
        assertTrue(teamsFactory.crearNotificador() instanceof NotificadorTeams);
        assertEquals("Microsoft Teams", teamsFactory.crearNotificador().getCanal());
    }
}

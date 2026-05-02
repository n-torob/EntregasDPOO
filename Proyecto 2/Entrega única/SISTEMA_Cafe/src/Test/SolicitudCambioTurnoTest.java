package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Mundo.EstadoSolicitud;
import Mundo.SolicitudCambioTurno;

public class SolicitudCambioTurnoTest {

    private SolicitudCambioTurno solicitud;

    @BeforeEach
    public void setUp() {
        solicitud = new SolicitudCambioTurno("SOL1", null);
    }

    @Test
    public void testCrearSolicitudCambioTurno() {
        assertNotNull(solicitud);
        assertEquals(EstadoSolicitud.PENDIENTE, solicitud.getEstado());
    }

    @Test
    public void testAprobarSolicitud() {
        solicitud.aprobar();
        assertEquals(EstadoSolicitud.APROBADA, solicitud.getEstado());
    }

    @Test
    public void testRechazarSolicitud() {
        solicitud.rechazar();
        assertEquals(EstadoSolicitud.RECHAZADA, solicitud.getEstado());
    }
}
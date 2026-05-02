package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Mundo.EstadoSugerencia;
import Mundo.SugerenciaPlatillo;

public class SugerenciaPlatilloTest {

    private SugerenciaPlatillo sugerencia;

    @BeforeEach
    public void setUp() {
        sugerencia = new SugerenciaPlatillo("SUG1", "Brownie", "Brownie con helado");
    }

    @Test
    public void testCrearSugerenciaPlatillo() {
        assertNotNull(sugerencia);
        assertEquals(EstadoSugerencia.PENDIENTE, sugerencia.getEstado());
    }

    @Test
    public void testAprobarSugerencia() {
        sugerencia.aprobar();
        assertEquals(EstadoSugerencia.APROBADA, sugerencia.getEstado());
    }

    @Test
    public void testRechazarSugerencia() {
        sugerencia.rechazar();
        assertEquals(EstadoSugerencia.RECHAZADA, sugerencia.getEstado());
    }
}

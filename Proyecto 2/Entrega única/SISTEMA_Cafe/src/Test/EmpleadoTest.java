package Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Mundo.Empleado;
import Mundo.EstadoSolicitud;
import Mundo.EstadoSugerencia;
import Mundo.JuegoDeMesa;
import Mundo.SolicitudCambioTurno;
import Mundo.SugerenciaPlatillo;

public class EmpleadoTest {

    private Empleado empleado;

    private static class EmpleadoPrueba extends Empleado {
        public EmpleadoPrueba(String login, String password, String nombre,
                ArrayList<JuegoDeMesa> juegosFavoritos, String idEmpleado) {
            super(login, password, nombre, juegosFavoritos, idEmpleado);
        }
    } //Como Empleado es abstracto, se crea una clase de prueba para instanciarlo. Un clase abstracta no se puede instanciar directamente, por eso se crea esta clase de prueba que hereda de Empleado.

    @BeforeEach
    public void setUp() {
        empleado = new EmpleadoPrueba("empleado1", "1234", "Luis",
                new ArrayList<JuegoDeMesa>(), "EMP1");
    }

    @Test
    public void testCrearEmpleado() {
        assertNotNull(empleado);
    }

    @Test
    public void testConsultarTurnos() {
        ArrayList<?> turnos = empleado.consultarTurnos();
        assertNotNull(turnos);
        assertTrue(turnos.isEmpty());
    }

    @Test
    public void testSolicitarCambioTurno() {
        SolicitudCambioTurno solicitud = empleado.solicitarCambioTurno("SOL1", null);

        assertNotNull(solicitud);
        assertEquals("SOL1", solicitud.getIdSolicitud());
        assertEquals(EstadoSolicitud.PENDIENTE, solicitud.getEstado());
    }

    @Test
    public void testCrearSugerenciaPlatillo() {
        SugerenciaPlatillo sugerencia =
                empleado.crearSugerenciaPlatillo("SUG1", "Hamburguesa", "Con queso");

        assertNotNull(sugerencia);
        assertEquals("SUG1", sugerencia.getIdSugerencia());
        assertEquals(EstadoSugerencia.PENDIENTE, sugerencia.getEstado());
    }
}
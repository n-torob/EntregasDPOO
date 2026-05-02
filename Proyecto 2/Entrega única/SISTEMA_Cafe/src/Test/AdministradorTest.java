package Test;
import Mundo.Administrador;
import Mundo.EstadoJuego;
import Mundo.EstadoSolicitud;
import Mundo.EstadoSugerencia;
import Mundo.JuegoDeMesa;
import Mundo.Prestamo;
import Mundo.RubroVenta;
import Mundo.SolicitudCambioTurno;
import Mundo.SugerenciaPlatillo;
import Mundo.Venta;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

public class AdministradorTest {
    private Administrador administrador;

    @BeforeEach
    public void setUp() {

        administrador = new Administrador("admin1", "1234", "Carlos");

    }

    @Test

    public void testAprobarSolicitudCambio() {

        SolicitudCambioTurno solicitud = new SolicitudCambioTurno("SOL1", null);

        

        administrador.aprobarSolicitudCambio(solicitud);

        

        assertEquals(EstadoSolicitud.APROBADA, solicitud.getEstado());

    }

    @Test

    public void testRechazarSolicitudCambio() {

        SolicitudCambioTurno solicitud = new SolicitudCambioTurno("SOL2", null);

        

        administrador.rechazarSolicitudCambio(solicitud);

        

        assertEquals(EstadoSolicitud.RECHAZADA, solicitud.getEstado());

    }

    @Test

    public void testAprobarSugerencia() {

        SugerenciaPlatillo sugerencia = new SugerenciaPlatillo("SUG1", "Platillo prueba", "Descripcion prueba");

        

        administrador.aprobarSugerencia(sugerencia);

        

        assertEquals(EstadoSugerencia.APROBADA, sugerencia.getEstado());

    }

    @Test

    public void testRechazarSugerencia() {

        SugerenciaPlatillo sugerencia = new SugerenciaPlatillo("SUG2", "Platillo prueba 2", "Descripcion prueba 2");

        

        administrador.rechazarSugerencia(sugerencia);

        

        assertEquals(EstadoSugerencia.RECHAZADA, sugerencia.getEstado());

    }

    @Test

    public void testActualizarEstadoJuego() {

        JuegoDeMesa juego = new JuegoDeMesa("J1", "Ajedrez", 1850, "Empresa", 2, 2, 8, null, false, null);

        

        administrador.actualizarEstadoJuego(juego, EstadoJuego.DISPONIBLE);

        

        assertEquals(EstadoJuego.DISPONIBLE, juego.getEstadoJuego());

    }

    @Test

    public void testConsultarVentasPorFecha() {

        ArrayList<Venta> ventas = administrador.consultarVentasPorFecha("2024-01-01 a 2024-12-31");

        

        assertNotNull(ventas);

        assertTrue(ventas.isEmpty());

    }

    @Test

    public void testConsultarVentasPorRubro() {

        ArrayList<Venta> ventas = administrador.consultarVentasPorRubro(RubroVenta.COMIDA);

        

        assertNotNull(ventas);

        assertTrue(ventas.isEmpty());

    }

    @Test

    public void testConsultarHistorialPrestamos() {

        ArrayList<Prestamo> prestamos = administrador.consultarHistorialPrestamos();

        

        assertNotNull(prestamos);

        assertTrue(prestamos.isEmpty());

    }
    
}

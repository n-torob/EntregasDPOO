package Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Mundo.CategoriaJuego;
import Mundo.EstadoJuego;
import Mundo.EstadoPrestamo;
import Mundo.JuegoDeMesa;
import Mundo.JuegoPrestamo;
import Mundo.Mesa;
import Mundo.Prestamo;
import Mundo.Usuario;

public class PrestamoTest {

    private Prestamo prestamo;
    private JuegoPrestamo juegoPrestamo1;
    private JuegoPrestamo juegoPrestamo2;

    private static class UsuarioPrueba extends Usuario {
        public UsuarioPrueba(String login, String password, String nombre) {
            super(login, password, nombre);
            setJuegosFavoritos(new ArrayList<JuegoDeMesa>());
        }
    } //Como Usuario es abstracto, se crea una clase de prueba para instanciarlo. Un clase abstracta no se puede instanciar directamente, por eso se crea esta clase de prueba que hereda de Usuario.

    @BeforeEach
    public void setUp() {
        Usuario solicitante = new UsuarioPrueba("cliente1", "1234", "Luis");
        Mesa mesa = new Mesa("M1", true, 4, false, false);
        prestamo = new Prestamo("P1", "2024-05-01", solicitante, mesa);

        JuegoDeMesa juego1 = new JuegoDeMesa(
                "J1",
                "Parques",
                1995,
                "Mattel",
                3,
                4,
                10,
                CategoriaJuego.TABLERO,
                true,
                EstadoJuego.DISPONIBLE);

        JuegoDeMesa juego2 = new JuegoDeMesa(
                "J2",
                "Ajedrez",
                1850,
                "Clasico",
                2,
                2,
                6,
                CategoriaJuego.TABLERO,
                false,
                EstadoJuego.DISPONIBLE);

        juegoPrestamo1 = new JuegoPrestamo(juego1, 3, 0);
        juegoPrestamo2 = new JuegoPrestamo(juego2, 2, 0);
    }

    @Test
    public void testCrearPrestamo() {
        assertNotNull(prestamo);
        assertTrue(prestamo.tieneCupoParaMasJuegos());
    }

    @Test
    public void testAgregarJuegoAPrestamo() {
        prestamo.agregarJuegoAPrestamo(juegoPrestamo1);

        assertEquals(1, prestamo.getJuegos().size());
        assertTrue(prestamo.tieneCupoParaMasJuegos());
    }

    @Test
    public void testPrestamoSinCupoParaMasJuegos() {
        prestamo.agregarJuegoAPrestamo(juegoPrestamo1);
        prestamo.agregarJuegoAPrestamo(juegoPrestamo2);

        assertEquals(2, prestamo.getJuegos().size());
        assertFalse(prestamo.tieneCupoParaMasJuegos());
    }

    @Test
    public void testFinalizarPrestamo() {
        prestamo.finalizarPrestamo("2024-05-03");

        assertEquals(EstadoPrestamo.DEVUELTO, prestamo.getEstadoPrestamo());
        assertEquals("2024-05-03", prestamo.getFechaDevolucion());
    }


}
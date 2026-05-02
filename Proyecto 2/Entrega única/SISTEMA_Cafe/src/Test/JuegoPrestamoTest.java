package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Mundo.CategoriaJuego;
import Mundo.EstadoJuego;
import Mundo.JuegoDeMesa;
import Mundo.JuegoPrestamo;

public class JuegoPrestamoTest {

    private JuegoPrestamo juegoPrestamo;

    @BeforeEach
    public void setUp() {
        JuegoDeMesa juego = new JuegoDeMesa(
                "J1",
                "Rummikub",
                1995,
                "Hasbro",
                2,
                4,
                8,
                CategoriaJuego.TABLERO,
                false,
                EstadoJuego.DISPONIBLE
        );

        juegoPrestamo = new JuegoPrestamo(juego, 3, 0);
    }

    @Test
    public void testCrearJuegoPrestamo() {
        assertNotNull(juegoPrestamo);
    }

    @Test
    public void testHayDisponibilidad() {
        assertTrue(juegoPrestamo.hayDisponibilidad());
    }

    @Test
    public void testPrestar() {
        juegoPrestamo.prestar();
        assertTrue(juegoPrestamo.hayDisponibilidad());
    }

    @Test
    public void testPrestarHastaAgotarDisponibilidad() {
        juegoPrestamo.prestar();
        juegoPrestamo.prestar();
        juegoPrestamo.prestar();

        assertFalse(juegoPrestamo.hayDisponibilidad());
    }

    @Test
    
    public void testPrestarSinDisponibilidad() {
        JuegoDeMesa juego = new JuegoDeMesa(
                "J2",
                "Ajedrez",
                1850,
                "Empresa",
                2,
                2,
                6,
                CategoriaJuego.TABLERO,
                false,
                EstadoJuego.DISPONIBLE
        );

        JuegoPrestamo sinCopias = new JuegoPrestamo(juego, 0, 0);

        assertThrows(IllegalArgumentException.class, () -> sinCopias.prestar());
    }

    @Test
    public void testDevolver() {
        JuegoDeMesa juego = new JuegoDeMesa(
                "J3",
                "Uno",
                1971,
                "Mattel",
                2,
                10,
                7,
                CategoriaJuego.CARTAS,
                false,
                EstadoJuego.DISPONIBLE
        );

        JuegoPrestamo prestamo = new JuegoPrestamo(juego, 1, 2);

        prestamo.devolver();

        assertTrue(prestamo.hayDisponibilidad());
    }

    @Test
    public void testDevolverSinPrestados() {
        assertThrows(IllegalArgumentException.class, () -> juegoPrestamo.devolver());
    }

    @Test
    public void testAumentarCopiasDisponibles() {
        JuegoDeMesa juego = new JuegoDeMesa(
                "J4",
                "Parques",
                1995,
                "Mattel",
                3,
                4,
                10,
                CategoriaJuego.TABLERO,
                false,
                EstadoJuego.DISPONIBLE
        );

        JuegoPrestamo prestamo = new JuegoPrestamo(juego, 0, 0);
        prestamo.aumentarCopiasDisponibles(2);

        assertTrue(prestamo.hayDisponibilidad());
    }

    @Test
    public void testReducirCopiasDisponibles() {
        juegoPrestamo.reducirCopiasDisponibles(3);

        assertFalse(juegoPrestamo.hayDisponibilidad());
    }

    @Test
    public void testReducirCopiasDisponiblesExcedidas() {
        assertThrows(IllegalArgumentException.class, () -> juegoPrestamo.reducirCopiasDisponibles(4));
    }
}
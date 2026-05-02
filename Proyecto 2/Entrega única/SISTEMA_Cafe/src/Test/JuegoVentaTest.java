package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Mundo.CategoriaJuego;
import Mundo.EstadoJuego;
import Mundo.JuegoDeMesa;
import Mundo.JuegoVenta;

public class JuegoVentaTest {

    private JuegoVenta juegoVenta;

    @BeforeEach
    public void setUp() {
        JuegoDeMesa juego = new JuegoDeMesa(
                "J1",
                "Uno",
                1995,
                "Mattel",
                3,
                4,
                10,
                CategoriaJuego.TABLERO,
                true,
                EstadoJuego.DISPONIBLE
        );

        juegoVenta = new JuegoVenta(juego, 120000, 5);
    }

    @Test
    public void testCrearJuegoVenta() {
        assertNotNull(juegoVenta);
    }

    @Test
    public void testHayStockSuficiente() {
        assertTrue(juegoVenta.hayStock(3));
    }

    @Test
    public void testHayStockExacto() {
        assertTrue(juegoVenta.hayStock(5));
    }

    @Test
    public void testNoHayStockSuficiente() {
        assertFalse(juegoVenta.hayStock(6));
    }

    @Test
    public void testReducirStock() {
        juegoVenta.reducirStock(5);
        assertFalse(juegoVenta.hayStock(1));
    }

    @Test
    public void testReducirStockCantidadExcedida() {
        assertThrows(IllegalArgumentException.class, () -> juegoVenta.reducirStock(6));
    }

    @Test
    public void testAumentarStock() {
        juegoVenta.aumentarStock(3);
        assertTrue(juegoVenta.hayStock(8));
    }
}
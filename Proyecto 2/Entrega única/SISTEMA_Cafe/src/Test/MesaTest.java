package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Mundo.CategoriaJuego;
import Mundo.EstadoJuego;
import Mundo.JuegoDeMesa;
import Mundo.Mesa;

public class MesaTest {

    private Mesa mesa;

    @BeforeEach
    public void setUp() {
        mesa = new Mesa("M1", true, 4, true, true);
    }

    @Test
    public void testCrearMesa() {
        assertNotNull(mesa);
    }

    @Test
    public void testEsCompatibleConJuego() {
        JuegoDeMesa juego = new JuegoDeMesa(
                "J1",
                "Ajedrez",
                1850,
                "Empresa",
                2,
                4,
                0,
                CategoriaJuego.TABLERO,
                false,
                EstadoJuego.DISPONIBLE);

        assertTrue(mesa.esCompatibleConJuego(juego));
    }

    @Test
    public void testNoEsCompatibleConJuegoPorCantidad() {
        JuegoDeMesa juego = new JuegoDeMesa(
                "J2",
                "Parques",
                1995,
                "Mattel",
                5,
                6,
                0,
                CategoriaJuego.TABLERO,
                true,
                EstadoJuego.DISPONIBLE);

        assertFalse(mesa.esCompatibleConJuego(juego));
    }

    @Test
    public void testOcuparMesa() {
        mesa.ocuparMesa(3, false, true);

        JuegoDeMesa juego = new JuegoDeMesa(
                "J3",
                "Rummikub",
                1977,
                "Hasbro",
                3,
                4,
                10,
                CategoriaJuego.TABLERO,
                false,
                EstadoJuego.DISPONIBLE);

        assertTrue(mesa.esCompatibleConJuego(juego));
        assertFalse(mesa.isDisponible());
    }

    @Test
    public void testLiberarMesa() {
        mesa.ocuparMesa(4, true, true);
        mesa.liberarMesa();

        JuegoDeMesa juego = new JuegoDeMesa(
                "J4",
                "Uno",
                1971,
                "Mattel",
                1,
                4,
                1,
                CategoriaJuego.CARTAS,
                false,
                EstadoJuego.DISPONIBLE);

        assertTrue(mesa.isDisponible());
        assertFalse(mesa.esCompatibleConJuego(juego));
    }

    @Test
    public void testEdadMinimaParticipantesConNinios() {
        mesa.ocuparMesa(4, true, false);
        assertEquals(0, mesa.getEdadMinimaParticipantes());
    }

    @Test
    public void testEdadMinimaParticipantesConJovenes() {
        mesa.ocuparMesa(4, false, true);
        assertEquals(10, mesa.getEdadMinimaParticipantes());
    }

    @Test
    public void testEdadMinimaParticipantesSoloAdultos() {
        mesa.ocuparMesa(4, false, false);
        assertEquals(18, mesa.getEdadMinimaParticipantes());
    }
}
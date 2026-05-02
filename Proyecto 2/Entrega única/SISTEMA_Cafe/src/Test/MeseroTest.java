package Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Mundo.CategoriaJuego;
import Mundo.EstadoJuego;
import Mundo.JuegoDeMesa;
import Mundo.Mesero;

public class MeseroTest {

    private Mesero mesero;
    private JuegoDeMesa juegoConocido;
    private JuegoDeMesa juegoNoConocido;

    @BeforeEach
    public void setUp() {
        juegoConocido = new JuegoDeMesa(
                "J1",
                "Parques",
                1995,
                "Mattel",
                3,
                4,
                10,
                CategoriaJuego.TABLERO,
                true,
                EstadoJuego.DISPONIBLE
        );

        juegoNoConocido = new JuegoDeMesa(
                "J2",
                "Ajedrez",
                1850,
                "Clasico",
                2,
                2,
                6,
                CategoriaJuego.TABLERO,
                false,
                EstadoJuego.DISPONIBLE
        );

        ArrayList<JuegoDeMesa> juegosFavoritos = new ArrayList<JuegoDeMesa>();
        ArrayList<JuegoDeMesa> juegosQueConoce = new ArrayList<JuegoDeMesa>();
        juegosQueConoce.add(juegoConocido);

        mesero = new Mesero(
                "mesero1",
                "1234",
                "Luis",
                juegosFavoritos,
                "MES1",
                juegosQueConoce
        );
    }

    @Test
    public void testCrearMesero() {
        assertNotNull(mesero);
    }

    @Test
    public void testPuedeExplicarJuegoConocido() {
        assertTrue(mesero.puedeExplicarJuego(juegoConocido));
    }

    @Test
    public void testNoPuedeExplicarJuegoNoConocido() {
        assertFalse(mesero.puedeExplicarJuego(juegoNoConocido));
    }
}
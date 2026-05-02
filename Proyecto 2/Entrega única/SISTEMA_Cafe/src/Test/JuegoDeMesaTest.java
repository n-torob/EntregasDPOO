package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Mundo.CategoriaJuego;
import Mundo.EstadoJuego;
import Mundo.JuegoDeMesa;
import Mundo.Mesa;

public class JuegoDeMesaTest {

	private JuegoDeMesa juego;
	private Mesa mesaApta;
	private Mesa mesaMuchosJugadores;
	private Mesa mesaPocosJugadores;
	private Mesa mesaSinJovenes;

	@BeforeEach
	public void setUp() {
		juego = new JuegoDeMesa("J1", "Rummikub", 1995, "Hasbro", 3, 4, 10, CategoriaJuego.TABLERO, true,
				EstadoJuego.DISPONIBLE);

		mesaApta = new Mesa("M1", true, 4, false, false);
		mesaMuchosJugadores = new Mesa("M2", true, 5, false, false);
		mesaPocosJugadores = new Mesa("M3", true, 2, false, false);
		mesaSinJovenes = new Mesa("M4", true, 4, true, false);
	}

	@Test
	public void testCrearJuegoDeMesa() {
		assertNotNull(juego);
	}

	@Test
	public void testEsAptoParaMesaValida() {
		assertTrue(juego.esAptoParaMesa(mesaApta));
	}

	@Test
	public void testNoEsAptoPorExcesoDeJugadores() {
		assertFalse(juego.esAptoParaMesa(mesaMuchosJugadores));
	}

	@Test
	public void testNoEsAptoPorFaltaDeJugadores() {
		assertFalse(juego.esAptoParaMesa(mesaPocosJugadores));
	}

	@Test
	public void testNoEsAptoPorRestriccionDeEdad() {
		assertFalse(juego.esAptoParaMesa(mesaSinJovenes));
	}
}
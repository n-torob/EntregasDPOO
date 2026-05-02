package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Mundo.CategoriaJuego;
import Mundo.EstadoJuego;
import Mundo.JuegoDeMesa;
import Mundo.Usuario;

public class UsuarioTest {

    private Usuario usuario;
    private JuegoDeMesa juego1;
    private JuegoDeMesa juego2;

    private static class UsuarioPrueba extends Usuario {
        public UsuarioPrueba(String login, String password, String nombre) {
            super(login, password, nombre);
        }
    }//Como Usuario es abstracto, se crea una clase de prueba para instanciarlo. Un clase abstracta no se puede instanciar directamente, por eso se crea esta clase de prueba que hereda de Usuario.

    @BeforeEach
    public void setUp() {
        usuario = new UsuarioPrueba("usuario1", "1234", "Luis");

        juego1 = new JuegoDeMesa(
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

        juego2 = new JuegoDeMesa(
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
    }

    @Test
    public void testCrearUsuario() {
        assertNotNull(usuario);
        assertTrue(usuario.getJuegosFavoritos().isEmpty());
    }

    @Test
    public void testAgregarFavorito() {
        usuario.agregarFavorito(juego1);

        assertEquals(1, usuario.getJuegosFavoritos().size());
        assertTrue(usuario.getJuegosFavoritos().contains(juego1));
    }

    @Test
    public void testEliminarFavorito() {
        usuario.agregarFavorito(juego1);
        usuario.agregarFavorito(juego2);

        usuario.eliminarFavorito(juego1);

        assertEquals(1, usuario.getJuegosFavoritos().size());
        assertFalse(usuario.getJuegosFavoritos().contains(juego1));
        assertTrue(usuario.getJuegosFavoritos().contains(juego2));
    }

    @Test
    public void testActivarBono() {
        usuario.activarBono(15000);

        assertTrue(usuario.isTieneBonoActivo());
        assertEquals(15000, usuario.getBonoDescuento());
    }

    @Test
    public void testNoAcumularBonoSiYaEstaActivo() {
        usuario.activarBono(15000);
        usuario.activarBono(30000);

        assertTrue(usuario.isTieneBonoActivo());
        assertEquals(15000, usuario.getBonoDescuento());
    }

    @Test
    public void testUsarBono() {
        usuario.activarBono(15000);
        usuario.usarBono();

        assertFalse(usuario.isTieneBonoActivo());
        assertEquals(0, usuario.getBonoDescuento());
    }
}
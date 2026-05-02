package Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Mundo.Cliente;
import Mundo.JuegoDeMesa;

public class ClienteTest {

    private Cliente cliente;

    @BeforeEach
    public void setUp() {
        ArrayList<JuegoDeMesa> juegosFavoritos = new ArrayList<JuegoDeMesa>();
        cliente = new Cliente("cliente1", "1234", "Luis", juegosFavoritos, "C1", 100);
    }

    @Test
    public void testCrearCliente() {
        assertNotNull(cliente);
    }

    @Test
    public void testSumarPuntos() {
        cliente.sumarPuntos(50);
        assertEquals(150, cliente.getPuntosFidelidad());
    }

    @Test
    public void testRedimirPuntosExitosamente() {
        cliente.redimirPuntos(40);
        assertEquals(60, cliente.getPuntosFidelidad());
    }

    @Test
    public void testRedimirPuntosExactos() {
        cliente.redimirPuntos(100);
        assertEquals(0, cliente.getPuntosFidelidad());
    }

    @Test
    public void testRedimirPuntosInsuficientes() {
        assertThrows(IllegalArgumentException.class, () -> {
            cliente.redimirPuntos(200);
        });
    }
}
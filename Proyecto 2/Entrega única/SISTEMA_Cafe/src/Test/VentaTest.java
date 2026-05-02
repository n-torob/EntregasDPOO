package Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Mundo.JuegoDeMesa;
import Mundo.RubroVenta;
import Mundo.Usuario;
import Mundo.Venta;

public class VentaTest {

    private Venta venta;

    private static class UsuarioPrueba extends Usuario {
        public UsuarioPrueba(String login, String password, String nombre) {
            super(login, password, nombre);
            setJuegosFavoritos(new ArrayList<JuegoDeMesa>());
        }
    }

    @BeforeEach
    public void setUp() {
        Usuario comprador = new UsuarioPrueba("cliente1", "1234", "Luis");
        venta = new Venta("V1", "2024-05-01", RubroVenta.JUEGOS, 5000, 2000, comprador);
    }

    @Test
    public void testCrearVenta() {
        assertNotNull(venta);
    }

    @Test
    public void testCalcularImpuestosJuegos() {
        venta.setSubtotal(100000);
        double impuestos = venta.calcularImpuestos();

        assertEquals(19000, impuestos, 0.001);
    }

    @Test
    public void testCalcularImpuestosCafeteria() {
        Usuario comprador = new UsuarioPrueba("cliente2", "1234", "Ana");
        Venta ventaCafeteria = new Venta("V2", "2024-05-02", RubroVenta.CAFETERIA, 3000, 0, comprador);
        ventaCafeteria.setSubtotal(100000);

        double impuestos = ventaCafeteria.calcularImpuestos();

        assertEquals(8000, impuestos, 0.001);
    }

    @Test
    public void testCalcularTotal() {
        venta.setSubtotal(100000);
        venta.calcularImpuestos();

        double total = venta.calcularTotal();

        assertEquals(122000, total, 0.001);
    }

    @Test
    public void testCalcularPuntos() {
        venta.setTotal(122000);

        int puntos = venta.calcularPuntos();

        assertEquals(1220, puntos);
    }
}
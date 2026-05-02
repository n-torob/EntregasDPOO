package Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Mundo.Cliente;
import Mundo.EstadoReserva;
import Mundo.JuegoDeMesa;
import Mundo.Mesa;
import Mundo.Reserva;

public class ReservaTest {

    private Reserva reserva;

    @BeforeEach
    public void setUp() {
        Cliente cliente = new Cliente("cliente1", "1234", "Luis", new ArrayList<JuegoDeMesa>(), "C1", 100);
        Mesa mesa = new Mesa("M1", true, 4, false, false);
        reserva = new Reserva("R1", EstadoReserva.ACTIVA, "2024-05-01", cliente, mesa);
    }

    @Test
    public void testCrearReserva() {
        assertNotNull(reserva);
    }

    @Test
    public void testConfirmarReserva() {
        reserva.confirmar();
        assertEquals(EstadoReserva.ACTIVA, reserva.getEstadoReserva());
    }

    @Test
    public void testRechazarReserva() {
        reserva.rechazar();
        assertEquals(EstadoReserva.RECHAZADA, reserva.getEstadoReserva());
    }

  
}
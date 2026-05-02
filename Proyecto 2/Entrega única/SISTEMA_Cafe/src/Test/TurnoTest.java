package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Mundo.DiaSemana;
import Mundo.Turno;

public class TurnoTest {

    private Turno turno;

    @BeforeEach
    public void setUp() {
        turno = new Turno("T1", DiaSemana.LUNES, "08:00", "12:00");
    }

    @Test
    public void testCrearTurno() {
        assertNotNull(turno);
    }

    @Test
    public void testEsValidoConDatosCorrectos() {
        assertTrue(turno.esValido());
    }

    @Test
    public void testNoEsValidoSinDiaSemana() {
        Turno turnoInvalido = new Turno("T2", null, "08:00", "12:00");
        assertFalse(turnoInvalido.esValido());
    }

    @Test
    public void testNoEsValidoSinHoraInicio() {
        Turno turnoInvalido = new Turno("T3", DiaSemana.MARTES, null, "12:00");
        assertFalse(turnoInvalido.esValido());
    }

    @Test
    public void testNoEsValidoSinHoraFin() {
        Turno turnoInvalido = new Turno("T4", DiaSemana.MIERCOLES, "08:00", null);
        assertFalse(turnoInvalido.esValido());
    }

    @Test
    public void testNoEsValidoConHoraInicioVacia() {
        Turno turnoInvalido = new Turno("T5", DiaSemana.JUEVES, "   ", "12:00");
        assertFalse(turnoInvalido.esValido());
    }

    @Test
    public void testNoEsValidoConHoraFinVacia() {
        Turno turnoInvalido = new Turno("T6", DiaSemana.VIERNES, "08:00", "   ");
        assertFalse(turnoInvalido.esValido());
    }

}
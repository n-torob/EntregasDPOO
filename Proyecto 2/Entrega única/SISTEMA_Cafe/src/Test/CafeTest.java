package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Mundo.Cafe;

public class CafeTest {

    private Cafe cafe;

    @BeforeEach
    public void setUp() {
        cafe = new Cafe("DulcesnDados", "Calle 123", 20);
    }

    @Test
    public void testCrearCafe() {
        assertNotNull(cafe);
    }

    @Test
    public void testTieneCapacidadExacta() {
        assertTrue(cafe.tieneCapacidad(20));
    }

    @Test
    public void testTieneCapacidadMenor() {
        assertTrue(cafe.tieneCapacidad(10));
    }

    @Test
    public void testNoTieneCapacidad() {
        assertFalse(cafe.tieneCapacidad(25));
    }
}

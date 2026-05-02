package Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Mundo.Pasteleria;

public class PasteleriaTest {

    private Pasteleria pasteleria;

    @BeforeEach
    public void setUp() {
        ArrayList<String> alergenos = new ArrayList<String>();
        alergenos.add("Gluten");
        alergenos.add("Leche");

        pasteleria = new Pasteleria("P1", "Torta", 12000, alergenos);
    }

    @Test
    public void testCrearPasteleria() {
        assertNotNull(pasteleria);
    }

    @Test
    public void testInformarAlergenos() {
        ArrayList<String> alergenos = pasteleria.informarAlergenos();

        assertNotNull(alergenos);
        assertEquals(2, alergenos.size());
        assertTrue(alergenos.contains("Gluten"));
        assertTrue(alergenos.contains("Leche"));
    }

    @Test
    public void testAgregarAlergeno() {
        pasteleria.agregarAlergeno("Huevo");

        assertTrue(pasteleria.informarAlergenos().contains("Huevo"));
        assertEquals(3, pasteleria.informarAlergenos().size());
    }

    @Test
    public void testEliminarAlergenoExistente() {
        boolean eliminado = pasteleria.eliminarAlergeno("Gluten");

        assertTrue(eliminado);
        assertFalse(pasteleria.informarAlergenos().contains("Gluten"));
        assertEquals(1, pasteleria.informarAlergenos().size());
    }

    @Test
    public void testEliminarAlergenoInexistente() {
        boolean eliminado = pasteleria.eliminarAlergeno("Mani");

        assertFalse(eliminado);
        assertEquals(2, pasteleria.informarAlergenos().size());
    }

    @Test
    public void testToString() {
        String texto = pasteleria.toString();

        assertTrue(texto.contains("Torta"));
        assertTrue(texto.contains("12000"));
        assertTrue(texto.contains("Gluten"));
    }
}
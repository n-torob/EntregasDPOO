package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.Combo;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class ComboTest {

    private Combo combo1;

    @BeforeEach
    void setUp( ) throws Exception
    {
        ArrayList<ProductoMenu> productos = new ArrayList<>();
        productos.add( new ProductoMenu("papas medianas",6500) );
        productos.add( new ProductoMenu("gaseosa",2000) );
        combo1 = new Combo( "corral", 0.10, productos );
    }

    @Test
    void testGetNombre( ){
        assertEquals("corral", combo1.getNombre(), "Nombre del combo no es el esperado.");
    }

    @Test
    void testGetPrecio(){
        assertEquals(7650, combo1.getPrecio(), "Precio del combo no es el esperado.");
    }

    @Test
    void testGenerartextoFactura(){
        String texto = "Combo " + combo1.getNombre() + "\n"
                + " Descuento: " + 0.1 + "\n"
                + "            " + combo1.getPrecio() + "\n";
        assertEquals(texto, combo1.generarTextoFactura(), "Texto de la factura no es el esperado.");
    }
}

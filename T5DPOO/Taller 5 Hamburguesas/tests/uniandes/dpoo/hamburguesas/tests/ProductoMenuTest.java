package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class ProductoMenuTest {

	ProductoMenu producto;
	
	@BeforeEach
	void setUp() throws Exception{
		producto = new ProductoMenu("Corral",14000);
	}
	
	@Test
	void testGetNombre() {
		assertEquals("Corral",producto.getNombre(),"Nombre del producto no es correcto.");
	}
	
	@Test
	void testGetPrecio() {
		assertEquals(14000,producto.getPrecio(),"Precio del producto incorrecto.");
	}
	
	@Test
	void testGenerarTextoFactura() {
		String texto = producto.getNombre() + "\n" +"            " + String.valueOf(producto.getPrecio( )) + "\n" ;
		assertEquals(texto, producto.generarTextoFactura(),"Información de la factura es incorrecta");
		
	}
	
}

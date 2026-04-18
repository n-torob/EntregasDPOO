package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;
import uniandes.dpoo.hamburguesas.mundo.ProductoAjustado;
import uniandes.dpoo.hamburguesas.mundo.Ingrediente;

public class ProductoAjustadoTest {
	
	ProductoMenu productoBase = new ProductoMenu("Corral",14000);
	ProductoAjustado p1 = new ProductoAjustado(productoBase);	
	Ingrediente ingr1 = new Ingrediente("tomate",1000 );
	Ingrediente ingr2 = new Ingrediente("queso mozzarella",2500);
	ArrayList<Ingrediente> prueba= new ArrayList<Ingrediente>();
	ArrayList<Ingrediente> prueba1= new ArrayList<Ingrediente>();
	
	@BeforeEach
	void setUp() throws Exception{
		ProductoAjustado p1 = new ProductoAjustado(productoBase);		
	}
	
	@Test
	void getNombreTest() {
		assertEquals("Corral",p1.getNombre(),"Nombre del producto incorrecto");
	}
	
	@Test
	void getPrecioTest() {
		assertEquals(0,p1.getPrecio(),"Precio ajustado del producto incorrecto");
	}
	
	@Test
	void agregarIngredientetest() {
		p1.agregarIngrediente(ingr1);
		
		prueba1.add(ingr1);
		
		assertEquals(prueba1,p1.ingredientesAgregados(),"Lista de ingredientes agregados incorrecto");
	}
	
	@Test
	void eliminarIngredientetest() {
		p1.eliminarIngrediente(ingr2);
		
		prueba1.add(ingr2);
		
		assertEquals(prueba1,p1.ingredientesEliminados(),"Lista de ingredientes agregados incorrecto");
	}
	
	@Test 
	void generarTextoFacturaTest() {
		p1.agregarIngrediente(ingr1);
		prueba1.add(ingr1);
		
		p1.eliminarIngrediente(ingr2);
		prueba1.add(ingr2);
		
		String Factura = productoBase.toString() +
                "    +" + ingr1.getNombre() +
                "                " + ingr1.getCostoAdicional() +
                "    -" + ingr2.getNombre() +
                "            " + p1.getPrecio() + "\n";
		assertEquals(Factura, p1.generarTextoFactura(),"Factura contiene informacion incorrecta");
	}
	
}

package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import uniandes.dpoo.hamburguesas.mundo.Pedido;
import uniandes.dpoo.hamburguesas.mundo.Producto;  
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;
/**
 * 
 */
public class PedidoTest {

    Pedido pedido1;
    Pedido pedido2;
    int id ;
    ArrayList<Producto> productos = new ArrayList<Producto>();

    @BeforeEach
    void setUp( ) throws Exception
    {
        pedido1 = new Pedido( "Juan Pepito", "Calle 123");
        pedido2 = new Pedido( "María Alberta", "Avenida 26");
    }

    @AfterEach
    void tearDown( ) throws Exception
    {
    }

    @Test
    void getIdPedidoTest( ){
    	int id = pedido1.getIdPedido();
        assertEquals(id+1, pedido2.getIdPedido(), "El id del pedido no es el esperado.");
    }

    @Test
    void getNombreClienteTest( ){
        assertEquals("Juan Pepito", pedido1.getNombreCliente(), "El nombre del cliente no es el esperado.");
        assertEquals("María Alberta", pedido2.getNombreCliente(), "El nombre del cliente no es el esperado.");
    }
    
    @Test
    void agregarProductoTest() {
    	ProductoMenu producto1 = new ProductoMenu("Corral",14000);
    	
        productos.add(producto1);
        pedido1.agregarProducto(producto1);
        
        assertEquals(productos, pedido1.getProductos(), "El producto agregado no es el esperado.");
    }
    
    @Test
    void getPrecioTotalPedidoTest() {
    	ProductoMenu producto1 = new ProductoMenu("Corral",14000);
    	productos.add(producto1);
    	pedido1.agregarProducto(producto1);
    	int precio = 0;
    	
    	for( Producto item : productos )
        {
            precio += item.getPrecio( );
        }
    	
    	precio = precio + ( int ) ( precio * .19 );
    	
    	assertEquals(precio, pedido1.getPrecioTotalPedido(), "Precio total incorrecto");
    }
    
    @Test
    void generarTextoFacturaTest() {
    	ProductoMenu producto1 = new ProductoMenu("Corral",14000);
    	productos.add(producto1);
    	pedido1.agregarProducto(producto1);
    	
    	int precioNeto = 0;
    	
    	for( Producto item : productos )
        {
    		precioNeto += item.getPrecio( );
        }
    	
    	int precioIVA = ( int ) ( precioNeto * .19 );
    	
    	int precio = precioIVA + precioNeto;
    	
    	
    	String Factura = "Cliente: " + pedido1.getNombreCliente() + "\n"
    			+ "Dirección: " +  "Calle 123" + "\n"
    			+ "----------------\n"
    			+"Corral" + "\n"
    			+ "            " + "14000" + "\n"
    			+ "----------------\n"   
    			+ "Precio Neto:  " + precioNeto + "\n"
    			+ "IVA:          " + precioIVA + "\n"
    			+ "Precio Total: " + precio + "\n";
    	
    	assertEquals(Factura, pedido1.generarTextoFactura(),"Factura generada incorrectamente");
    }
    
    @Test
    void guardarFacturaTest()throws FileNotFoundException{
    	ProductoMenu producto1 = new ProductoMenu("Corral",14000);
    	productos.add(producto1);
    	pedido1.agregarProducto(producto1);
    	
    	File archivo = new File("FacturaTest.txt");
    	
    	pedido1.guardarFactura(archivo);
    	
    	Scanner scanner = new Scanner(archivo);
        StringBuilder contenido = new StringBuilder();
        while (scanner.hasNextLine()) {
            contenido.append(scanner.nextLine()).append("\n");
        }
        scanner.close();

        String esperado = pedido1.generarTextoFactura();
        assertEquals(esperado, contenido.toString());

        archivo.delete();
    	
    }
    


}

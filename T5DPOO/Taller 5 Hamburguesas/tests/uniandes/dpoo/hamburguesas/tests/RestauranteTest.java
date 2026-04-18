package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.Restaurante;
import uniandes.dpoo.hamburguesas.excepciones.HamburguesaException;
import uniandes.dpoo.hamburguesas.excepciones.NoHayPedidoEnCursoException;
import uniandes.dpoo.hamburguesas.excepciones.YaHayUnPedidoEnCursoException;

public class RestauranteTest {

    Restaurante restaurante;

    @BeforeEach
    void setUp( ) throws Exception
    {
        restaurante = new Restaurante( );
        new File( "./facturas/" ).mkdirs( );
    }

    @AfterEach
    void tearDown( ) throws Exception
    {
    }

    @Test
    void iniciarPedidoTest( ) throws YaHayUnPedidoEnCursoException
    {
        restaurante.iniciarPedido( "Juan Pepito", "Calle 123" );

        assertNotNull( restaurante.getPedidoEnCurso( ), "No se ha iniciado un pedido aún" );
    }

    @Test
    void iniciarPedidoYaEnCursoTest( ) throws YaHayUnPedidoEnCursoException
    {
        restaurante.iniciarPedido( "Juan Pepito", "Calle 123" );

        assertThrows( YaHayUnPedidoEnCursoException.class, ( ) -> {
            restaurante.iniciarPedido( "Maria", "Carrera 45" );
        }, "Debería lanzar YaHayUnPedidoEnCursoException." );
    }

    @Test
    void cerrarYGuardarPedidoTest( ) throws YaHayUnPedidoEnCursoException, IOException, NoHayPedidoEnCursoException
    {
        restaurante.iniciarPedido( "Juan Pepito", "Calle 123" );
        restaurante.cerrarYGuardarPedido( );

        assertNull( restaurante.getPedidoEnCurso( ), "El pedido en curso debería ser null tras cerrar." );
        assertEquals( 1, restaurante.getPedidos( ).size( ), "Debería haber 1 pedido en el historial." );
    }

    @Test
    void cerrarSinPedidoTest( )
    {
        assertThrows( NoHayPedidoEnCursoException.class, ( ) -> {
            restaurante.cerrarYGuardarPedido( );
        }, "Debería lanzar NoHayPedidoEnCursoException." );
    }

    @Test
    void cargarInformacionRestauranteTest( ) throws HamburguesaException, NumberFormatException, IOException
    {
        File archivoIngredientes = new File( "./data/ingredientes.txt" );
        File archivoMenu = new File( "./data/menu.txt" );
        File archivoCombos = new File( "./data/combos.txt" );

        restaurante.cargarInformacionRestaurante( archivoIngredientes, archivoMenu, archivoCombos );

        assertEquals( 15, restaurante.getIngredientes( ).size( ), "El número de ingredientes no es el esperado." );
        assertEquals( 22, restaurante.getMenuBase( ).size( ), "El número de productos en el menú no es el esperado." );
        assertEquals( 4, restaurante.getMenuCombos( ).size( ), "El número de combos no es el esperado." );
    }

}

package Pruebas;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import Mundo.*;
import Sistema.SistemaCafe;

public class LectorArchivos {

    public static void cargarEscenario(String ruta, SistemaCafe sistema) {

        try {
            BufferedReader br = new BufferedReader(new FileReader(ruta));
            String linea;
            int paso = 1;

            System.out.println("\n======================================");
            System.out.println("       EJECUCION DE ESCENARIO");
            System.out.println("======================================\n");

            while ((linea = br.readLine()) != null) {

                if (linea.trim().isEmpty()) continue;

                System.out.println("--------------------------------------------------");
                System.out.println("PASO " + paso);
                System.out.println("Entrada: " + linea);
                System.out.println("--------------------------------------------------");

                String[] p = linea.split(",");

                try {

                    switch (p[0]) {

                        case "USUARIO":
                            Cliente c = new Cliente(
                                    p[2], p[3], p[4],
                                    new ArrayList<JuegoDeMesa>(),
                                    p[5], Integer.parseInt(p[6])
                            );
                            sistema.agregarUsuario(c);

                            System.out.println("Usuario creado: " + p[2]);
                            break;

                        case "MESA":
                            sistema.agregarMesa(new Mesa(p[1], true, 0, false, false));

                            System.out.println("Mesa disponible: " + p[1]);
                            break;

                        case "JUEGO":
                            JuegoDeMesa j = new JuegoDeMesa(
                                    p[1], p[2], 2000, "X",
                                    Integer.parseInt(p[3]),
                                    Integer.parseInt(p[4]),
                                    Integer.parseInt(p[5]),
                                    CategoriaJuego.TABLERO,
                                    Boolean.parseBoolean(p[6]),
                                    EstadoJuego.BUENO
                            );

                            sistema.agregarJuegoPrestamo(new JuegoPrestamo(j, 2, 0));

                            System.out.println("Juego agregado: " + p[2]);
                            break;

                        case "RESERVA":
                            Cliente cliente = (Cliente) sistema.buscarUsuarioPorLogin(p[1]);

                            Reserva r = sistema.crearReserva(
                                    cliente,
                                    Integer.parseInt(p[2]),
                                    Boolean.parseBoolean(p[3]),
                                    Boolean.parseBoolean(p[4]),
                                    p[5]
                            );

                            if (r != null) {
                                System.out.println("Reserva exitosa");
                                System.out.println("Mesa asignada: " + r.getMesa().getIdMesa());
                            } else {
                                System.out.println("Reserva rechazada");
                            }
                            break;

                        case "PRESTAMO":
                            Usuario u = sistema.buscarUsuarioPorLogin(p[1]);
                            Mesa m = sistema.buscarMesaPorId(p[2]);

                            ArrayList<JuegoPrestamo> juegos = new ArrayList<>();
                            JuegoPrestamo jp = buscarJuego(sistema, p[3]);
                            juegos.add(jp);

                            Prestamo prestamo = sistema.realizarPrestamo(u, m, juegos, p[4]);

                            if (prestamo != null) {
                                System.out.println("Prestamo aprobado");
                                System.out.println("Juego prestado: " + jp.getJuego().getNombre());
                            } else {
                                System.out.println("Prestamo rechazado");
                            }
                            break;

                        case "VENTA":
                            Usuario comprador = sistema.buscarUsuarioPorLogin(p[1]);

                            int cantidad = Integer.parseInt(p[2]);
                            double precio = Double.parseDouble(p[3]);
                            double propina = Double.parseDouble(p[4]);

                            ArrayList<DetalleVenta> detalles = new ArrayList<>();
                            detalles.add(new DetalleVenta("D1", cantidad, precio));

                            Venta v = sistema.registrarVenta(
                                    comprador,
                                    detalles,
                                    propina,
                                    p[5],
                                    RubroVenta.valueOf(p[6])
                            );

                            if (v != null) {
                                double total = cantidad * precio + propina;
                                System.out.println("Venta realizada");
                                System.out.println("Total pagado: " + total);
                            } else {
                                System.out.println("Venta rechazada");
                            }
                            break;
                    }

                } catch (Exception e) {
                    System.out.println("Error en operacion");
                }

                // Estado del sistema
                System.out.println("\nEstado del sistema:");
                System.out.println("Usuarios: " + sistema.getUsuarios().size());
                System.out.println("Reservas: " + sistema.getReservas().size());
                System.out.println("Prestamos: " + sistema.getPrestamos().size());
                System.out.println("Ventas: " + sistema.getVentas().size());

                System.out.println("--------------------------------------------------\n");

                paso++;
            }

            br.close();

            System.out.println("======================================");
            System.out.println("        FIN DEL ESCENARIO");
            System.out.println("======================================\n");

        } catch (Exception e) {
            System.out.println("No se pudo leer el archivo.");
        }
    }

    private static JuegoPrestamo buscarJuego(SistemaCafe sistema, String id) {
        for (JuegoPrestamo j : sistema.getJuegosPrestamo()) {
            if (j.getJuego().getIdJuego().equals(id)) {
                return j;
            }
        }
        return null;
    }
}
package Consola;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import Mundo.*;
import Persistencia.PersistenciaProyecto;
import Sistema.SistemaCafe;

public class MainEmpleado {

    private Scanner sc = new Scanner(System.in);
    private SistemaCafe sistema;
    private Empleado empleadoActivo; // usuario autenticado en esta sesión

    // Iniciar

    // carga los datos, ejecuta el login y lanza el menú principal
    // al salir del menú guarda los datos automáticamente
    public void iniciar() {
        sistema = new SistemaCafe(new Cafe("", "", 0));
        cargarDatos();
        login();
        if (empleadoActivo != null) {
            menuPrincipal();
        }
        guardarDatos();
    }

    // Login

    // solicita credenciales en loop hasta autenticar o que el usuario elija volver
    // verifica que el usuario autenticado sea instancia de Empleado
    private void login() {
        int opcion;
        do {
            System.out.println("\n--- Acceso Empleados ---");
            System.out.println("1. Iniciar sesión");
            System.out.println("0. Volver al menú principal");
            System.out.print("Opción: ");
            opcion = leerInt();

            if (opcion == 1) {
                System.out.print("Login: ");    String login = sc.nextLine();
                System.out.print("Password: "); String pass  = sc.nextLine();

                Usuario u = sistema.autenticar(login, pass);

                if (u instanceof Empleado) {
                    empleadoActivo = (Empleado) u;
                    System.out.println("Bienvenido, " + empleadoActivo.getNombre() + ".");
                } else if (u != null) {
                    // autenticó correctamente pero no es empleado
                    System.out.println("Este acceso es solo para empleados.");
                } else {
                    System.out.println("Login o password incorrectos.");
                }
            }
        } while (opcion != 0 && empleadoActivo == null);
    }

    // Menú principal

    // loop principal del empleado, cada opción delega a un método específico
    private void menuPrincipal() {
        int opcion;
        do {
            System.out.println("\n========== MENÚ EMPLEADO ==========");
            System.out.println("1. Ver mis turnos");
            System.out.println("2. Solicitar cambio de turno");
            System.out.println("3. Sugerir platillo");
            System.out.println("4. Solicitar préstamo de juego");
            System.out.println("5. Devolver préstamo");
            System.out.println("6. Comprar juego");
            System.out.println("7. Comprar platillo");
            System.out.println("8. Gestionar favoritos");
            System.out.println("0. Cerrar sesión");
            System.out.print("Opción: ");
            opcion = leerInt();

            switch (opcion) {
                case 1: verTurnos();            break;
                case 2: solicitarCambioTurno(); break;
                case 3: sugerirPlatillo();      break;
                case 4: solicitarPrestamo();    break;
                case 5: devolverPrestamo();     break;
                case 6: comprarJuego();         break;
                case 7: comprarPlatillo();      break;
                case 8: gestionarFavoritos();   break;
                case 0: System.out.println("Sesión cerrada."); break;
                default: System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
    }

    // Turnos

    // muestra los turnos asignados al empleado activo
    // consultarTurnos() fue corregido en Empleado.java para retornar la lista real

    private void verTurnos() {
        ArrayList<Turno> turnos = empleadoActivo.consultarTurnos();
        System.out.println("\n--- Mis Turnos ---");
        if (turnos.isEmpty()) {
            System.out.println("No tiene turnos asignados.");
            return;
        }
        for (Turno t : turnos) {
            System.out.println("[" + t.getIdTurno() + "] " + t.getDiaSemana()
                + " | " + t.getHoraInicio() + " - " + t.getHoraFin());
        }
    }

    // muestra los turnos del empleado, solicita el id del turno y el tipo de solicitud
    // llama a sistema.registrarSolicitudCambio() y confirma el registro

    private void solicitarCambioTurno() {
        verTurnos();
        ArrayList<Turno> turnos = empleadoActivo.consultarTurnos();
        if (turnos.isEmpty()) return;

        System.out.print("ID del turno a cambiar: "); String idTurno = sc.nextLine();
        Turno turno = sistema.buscarTurnoPorId(idTurno);
        if (turno == null) { System.out.println("Turno no encontrado."); return; }

        System.out.println("Tipo de solicitud:");
        System.out.println("1. CAMBIO");
        System.out.println("2. INTERCAMBIO");
        System.out.print("Opción: ");
        int op = leerInt();

        TipoSolicitud tipo;
        if (op == 1) {
            tipo = TipoSolicitud.CAMBIO;
        } else if (op == 2) {
            tipo = TipoSolicitud.INTERCAMBIO;
        } else {
            System.out.println("Opción inválida.");
            return;
        }

        SolicitudCambioTurno solicitud = sistema.registrarSolicitudCambio(empleadoActivo, turno, tipo);
        if (solicitud != null) {
            System.out.println("Solicitud registrada: " + solicitud.getIdSolicitud()
                + " | Estado: " + solicitud.getEstado());
        } else {
            System.out.println("No se pudo registrar la solicitud.");
        }
    }

    // Sugerencias

    // solicita nombre y descripción del platillo y llama a sistema.registrarSugerenciaPlatillo()
    // la sugerencia queda en estado PENDIENTE hasta que el administrador la evalúe

    private void sugerirPlatillo() {
        System.out.print("Nombre del platillo: ");  String nombre = sc.nextLine();
        System.out.print("Descripción: ");          String desc   = sc.nextLine();

        SugerenciaPlatillo s = sistema.registrarSugerenciaPlatillo(empleadoActivo, nombre, desc);
        if (s != null) {
            System.out.println("Sugerencia registrada: " + s.getIdSugerencia()
                + " | Estado: " + s.getEstado());
        } else {
            System.out.println("No se pudo registrar la sugerencia.");
        }
    }

    // Préstamos

    // el empleado no necesita mesa para pedir préstamo
    // llama a sistema.realizarPrestamo() con mesa null

    private void solicitarPrestamo() {
        verCatalogoJuegos();
        System.out.print("ID del juego: "); String idJuego = sc.nextLine();
        JuegoPrestamo jp = sistema.buscarJuegoPrestamo(idJuego);
        if (jp == null) { System.out.println("Juego no encontrado."); return; }

        System.out.print("Fecha (AAAA-MM-DD): "); String fecha = sc.nextLine();

        ArrayList<JuegoPrestamo> lista = new ArrayList<>();
        lista.add(jp);

        // mesa null porque los empleados no necesitan mesa para pedir préstamo
        Prestamo pr = sistema.realizarPrestamo(empleadoActivo, null, lista, fecha);

        if (pr != null) {
            System.out.println("Préstamo realizado: " + pr.getIdPrestamo());
            if (pr.isAdvertenciaReglas())
                System.out.println("ADVERTENCIA: no hay mesero capacitado para este juego.");
        } else {
            System.out.println("Préstamo rechazado. Verifique disponibilidad del juego.");
        }
    }

    // muestra los préstamos activos del empleado y solicita id y fecha de devolución

    private void devolverPrestamo() {
        listarMisPrestamos();
        System.out.print("ID Préstamo a devolver: "); String id    = sc.nextLine();
        System.out.print("Fecha de devolución: ");    String fecha = sc.nextLine();

        Prestamo pr = sistema.buscarPrestamoPorId(id);
        if (pr == null || !pr.getSolicitante().equals(empleadoActivo)) {
            System.out.println("Préstamo no encontrado o no le pertenece.");
            return;
        }

        System.out.println(sistema.devolverPrestamo(id, fecha)
            ? "Préstamo devuelto correctamente."
            : "No se pudo devolver el préstamo.");
    }

    // lista solo los préstamos activos del empleado activo

    private void listarMisPrestamos() {
        System.out.println("\n--- Mis Préstamos Activos ---");
        boolean hay = false;
        for (Prestamo pr : sistema.getPrestamos()) {
            if (pr.getSolicitante().equals(empleadoActivo)
                    && pr.getEstadoPrestamo() == EstadoPrestamo.ACTIVO) {
                System.out.println("[" + pr.getIdPrestamo() + "] " + pr.getFechaInicio()
                    + " | Juegos: " + pr.getJuegos().size());
                hay = true;
            }
        }
        if (!hay) System.out.println("No tiene préstamos activos.");
    }

    // Ventas

    // el empleado tiene 20% de descuento
    // se aplica reduciendo el precio unitario antes de registrar la venta

    private void comprarJuego() {
        System.out.println("\n--- Catálogo de Juegos en Venta ---");
        ArrayList<JuegoVenta> catalogo = sistema.getJuegosVenta();
        if (catalogo.isEmpty()) { System.out.println("No hay juegos en venta."); return; }

        for (JuegoVenta jv : catalogo) {
            double precioConDescuento = jv.getPrecio() * 0.80; // 20% de descuento para empleados
            System.out.println("[" + jv.getJuego().getIdJuego() + "] "
                + jv.getJuego().getNombre()
                + " | Precio normal: $" + jv.getPrecio()
                + " | Precio empleado: $" + precioConDescuento
                + " | Stock: " + jv.getStockVenta());
        }

        System.out.print("ID del juego: "); String idJuego = sc.nextLine();
        JuegoVenta jv = sistema.buscarJuegoVenta(idJuego);
        if (jv == null) { System.out.println("Juego no encontrado."); return; }

        System.out.print("Cantidad: "); int cantidad = leerInt();
        if (!jv.hayStock(cantidad)) { System.out.println("Stock insuficiente."); return; }

        // aplicar 20% de descuento al precio unitario
        double precioConDescuento = jv.getPrecio() * 0.80;

        ArrayList<DetalleVenta> detalles = new ArrayList<>();
        detalles.add(new DetalleVenta(idJuego, cantidad, precioConDescuento));

        // mesa null porque la venta de juegos no requiere mesa
        Venta v = sistema.registrarVenta(empleadoActivo, detalles, 0, fechaHoy(), RubroVenta.JUEGOS, null);
        if (v != null) {
            jv.reducirStock(cantidad);
            System.out.println("Compra realizada con 20% de descuento. Total: $" + v.getTotal());
        } else {
            System.out.println("No se pudo registrar la venta.");
        }
    }

    // el empleado tiene 20% de descuento en platillos también
    // no necesita mesa asignada para comprar platillos
    private void comprarPlatillo() {
        System.out.println("\n--- Menú de Platillos ---");
        ArrayList<Platillo> platillos = sistema.getPlatillos();
        if (platillos.isEmpty()) { System.out.println("No hay platillos disponibles."); return; }

        for (Platillo p : platillos) {
            double precioConDescuento = p.getPrecio() * 0.80; // 20% de descuento para empleados
            String extra = "";
            if (p instanceof Bebida) {
                Bebida b = (Bebida) p;
                extra = " | " + (b.isAlcoholica() ? "Alcohólica" : "No alcohólica")
                      + " | " + (b.isCaliente() ? "Caliente" : "Fría");
            } else if (p instanceof Pasteleria) {
                extra = " | Alérgenos: " + ((Pasteleria) p).getPosiblesAlergenos();
            }
            System.out.println("[" + p.getIdPlatillo() + "] " + p.getNombre()
                + " | Precio empleado: $" + precioConDescuento + extra);
        }

        System.out.print("ID del platillo: "); String idPlatillo = sc.nextLine();
        Platillo platillo = sistema.buscarPlatilloPorId(idPlatillo);
        if (platillo == null) { System.out.println("Platillo no encontrado."); return; }

        // propina sugerida es el 10% del precio antes de impuestos
        System.out.print("Propina sugerida: $" + (platillo.getPrecio() * 0.10) + ". ¿Cuánto desea dar? ");
        double propina = leerDouble();

        // aplicar 20% de descuento al precio unitario
        double precioConDescuento = platillo.getPrecio() * 0.80;

        ArrayList<DetalleVenta> detalles = new ArrayList<>();
        detalles.add(new DetalleVenta(idPlatillo, 1, precioConDescuento));

        // mesa null porque el empleado no necesita mesa para comprar platillos
        Venta v = sistema.registrarVenta(empleadoActivo, detalles, propina, fechaHoy(),
            RubroVenta.CAFETERIA, null);

        if (v != null) {
            System.out.println("Compra realizada con 20% de descuento. Total: $" + v.getTotal());
        } else {
            System.out.println("No se pudo registrar la venta.");
        }
    }

    // Favoritos

    // muestra favoritos actuales y permite agregar o eliminar

    private void gestionarFavoritos() {
        System.out.println("\n--- Mis Favoritos ---");
        ArrayList<JuegoDeMesa> favs = empleadoActivo.getJuegosFavoritos();
        if (favs.isEmpty()) {
            System.out.println("No tiene juegos favoritos.");
        } else {
            for (JuegoDeMesa j : favs) {
                System.out.println("[" + j.getIdJuego() + "] " + j.getNombre());
            }
        }

        System.out.println("\n1. Agregar favorito");
        System.out.println("2. Eliminar favorito");
        System.out.println("0. Volver");
        System.out.print("Opción: ");
        int op = leerInt();

        if (op == 1) {
            verCatalogoJuegos();
            System.out.print("ID del juego: "); String id = sc.nextLine();
            JuegoPrestamo jp = sistema.buscarJuegoPrestamo(id);
            if (jp != null) {
                empleadoActivo.agregarFavorito(jp.getJuego());
                System.out.println("Agregado a favoritos.");
            } else {
                System.out.println("Juego no encontrado.");
            }
        } else if (op == 2) {
            System.out.print("ID del juego a eliminar: "); String id = sc.nextLine();
            JuegoPrestamo jp = sistema.buscarJuegoPrestamo(id);
            if (jp != null) {
                empleadoActivo.eliminarFavorito(jp.getJuego());
                System.out.println("Eliminado de favoritos.");
            } else {
                System.out.println("Juego no encontrado.");
            }
        }
    }

    // Catálogo

    // muestra todos los juegos del inventario de préstamo con su disponibilidad

    private void verCatalogoJuegos() {
        ArrayList<JuegoPrestamo> lista = sistema.getJuegosPrestamo();
        if (lista.isEmpty()) { System.out.println("No hay juegos disponibles."); return; }
        System.out.println("\n--- Catálogo de Juegos de Préstamo ---");
        for (JuegoPrestamo jp : lista) {
            JuegoDeMesa j = jp.getJuego();
            String disp = jp.hayDisponibilidad()
                ? "Disponible (" + jp.getCopiasDisponibles() + " copias)"
                : "No disponible";
            System.out.println("[" + j.getIdJuego() + "] " + j.getNombre()
                + " | " + j.getCategoria()
                + " | Jugadores: " + j.getNumJugadoresMin() + "-" + j.getNumJugadoresMax()
                + " | " + disp);
        }
    }   

    // Helpers internos

    // retorna la fecha actual en formato AAAA-MM-DD

    private String fechaHoy() {
        return java.time.LocalDate.now().toString();
    }

    // Carga y guardado de datos

    // carga todos los datos desde los archivos de persistencia

    private void cargarDatos() {
        PersistenciaProyecto p = new PersistenciaProyecto();
        try {
            Cafe cafe = p.cargarCafe();
            if (cafe != null) {
                sistema = new SistemaCafe(cafe);
            } else {
                System.out.println("No se encontró configuración del café. Contacte al administrador.");
                return;
            }

            ArrayList<JuegoDeMesa> juegos = p.cargarJuegosDeMesa();
            sistema.setJuegosPrestamo(p.cargarInventarioPrestamo(juegos));

            ArrayList<Usuario> usuarios = new ArrayList<>();
            usuarios.addAll(p.cargarClientes(juegos));
            usuarios.addAll(p.cargarMeseros(juegos));
            usuarios.addAll(p.cargarCocineros(juegos));
            usuarios.addAll(p.cargarAdministradores());
            sistema.setUsuarios(usuarios);

            sistema.setMesas(p.cargarMesas());
            sistema.setPlatillos(p.cargarPlatillos());
            sistema.setTurnos(p.cargarTurnos());
            sistema.setVentas(p.cargarVentas());
            sistema.setPrestamos(p.cargarPrestamos(juegos));
            sistema.setReservas(p.cargarReservas());
            sistema.setSolicitudesCambio(p.cargarSolicitudes());
            sistema.setSugerenciasPlatillo(p.cargarSugerencias());

        } catch (IOException e) {
            System.out.println("Error al cargar datos: " + e.getMessage());
        }
    }

    // guarda todos los datos en los archivos de persistencia al cerrar sesión

    private void guardarDatos() {
        PersistenciaProyecto p = new PersistenciaProyecto();
        try {
            // extraer la lista de JuegoDeMesa desde el inventario de préstamo
            ArrayList<JuegoDeMesa> juegos = new ArrayList<>();
            for (JuegoPrestamo jp : sistema.getJuegosPrestamo()) juegos.add(jp.getJuego());

            // separar usuarios por tipo para guardarlos en sus archivos correspondientes
            ArrayList<Cliente>       clientes  = new ArrayList<>();
            ArrayList<Mesero>        meseros   = new ArrayList<>();
            ArrayList<Cocinero>      cocineros = new ArrayList<>();
            ArrayList<Administrador> admins    = new ArrayList<>();
            for (Usuario u : sistema.getUsuarios()) {
                if (u instanceof Cliente)            clientes.add((Cliente) u);
                else if (u instanceof Mesero)        meseros.add((Mesero) u);
                else if (u instanceof Cocinero)      cocineros.add((Cocinero) u);
                else if (u instanceof Administrador) admins.add((Administrador) u);
            }

            p.guardarCafe(sistema.getCafe());
            p.guardarJuegosDeMesa(juegos);
            p.guardarInventarioPrestamo(sistema.getJuegosPrestamo());
            p.guardarClientes(clientes);
            p.guardarMeseros(meseros);
            p.guardarCocineros(cocineros);
            p.guardarAdministradores(admins);
            p.guardarMesas(sistema.getMesas());
            p.guardarPlatillos(sistema.getPlatillos());
            p.guardarTurnos(sistema.getTurnos());
            p.guardarVentas(sistema.getVentas());
            p.guardarPrestamos(sistema.getPrestamos());
            p.guardarReservas(sistema.getReservas());
            p.guardarSolicitudes(sistema.getSolicitudesCambio());
            p.guardarSugerencias(sistema.getSugerenciasPlatillo());

        } catch (IOException e) {
            System.out.println("Error al guardar datos: " + e.getMessage());
        }
    }

    // Helpers de entrada

    // lee un entero desde consola, repite si la entrada no es válida

    private int leerInt() {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Ingrese un número entero: ");
            }
        }
    }

    // lee un double desde consola, repite si la entrada no es válida
    private double leerDouble() {
        while (true) {
            try {
                return Double.parseDouble(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Ingrese un número: ");
            }
        }
    }

    // lee un boolean desde consola, repite si la entrada no es true ni false
    private boolean leerBoolean() {
        while (true) {
            String input = sc.nextLine().trim().toLowerCase();
            if (input.equals("true"))  return true;
            if (input.equals("false")) return false;
            System.out.print("Ingrese true o false: ");
        }
    }
}
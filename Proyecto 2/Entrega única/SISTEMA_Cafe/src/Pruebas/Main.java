package Pruebas;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import Mundo.*;
import Persistencia.PersistenciaProyecto;
import Sistema.SistemaCafe;

public class Main {

    private static Scanner sc = new Scanner(System.in);
    private static SistemaCafe sistema;

    public static void main(String[] args) {

        System.out.println("=========================================");
        System.out.println("       SISTEMA DE GESTION - CAFE");
        System.out.println("=========================================");

        PersistenciaProyecto persistencia = new PersistenciaProyecto();

        // Intentar cargar datos existentes
        Cafe cafe = null;
        try {
            cafe = persistencia.cargarCafe();
        } catch (IOException e) {
            System.out.println("No se pudieron cargar datos previos.");
        }

        if (cafe != null) {
            System.out.println("\nDatos encontrados. Cafe: " + cafe.getNombre());
            System.out.print("Cargar datos existentes? (s/n): ");
            String resp = sc.nextLine().trim().toLowerCase();
            if (resp.equals("s")) {
                sistema = new SistemaCafe(cafe);
                cargarDatos(persistencia);
                System.out.println("Datos cargados correctamente.\n");
            } else {
                cafe = null;
            }
        }

        if (cafe == null) {
            System.out.println("\nConfigurar el cafe:");
            System.out.print("Nombre: ");
            String nombre = sc.nextLine();
            System.out.print("Direccion: ");
            String dir = sc.nextLine();
            System.out.print("Capacidad maxima: ");
            int cap = leerInt();
            sistema = new SistemaCafe(new Cafe(nombre, dir, cap));
            System.out.println("Cafe '" + nombre + "' creado correctamente.\n");
        }

        int opcion;
        do {
            mostrarMenu();
            opcion = leerInt();
            switch (opcion) {
                case 1: menuUsuarios();  break;
                case 2: menuMesas();     break;
                case 3: menuJuegos();    break;
                case 4: menuReservas();  break;
                case 5: menuPrestamos(); break;
                case 6: menuVentas();    break;
                case 7: verEstado();     break;
                case 8: guardarDatos();  break;
                case 0:
                    guardarDatos();
                    System.out.println("Saliendo del sistema.");
                    break;
                default: System.out.println("Opcion invalida.");
            }
        } while (opcion != 0);
    }

    // =========================================================
    // MENU PRINCIPAL
    // =========================================================
    private static void mostrarMenu() {
        System.out.println("\n========== MENU PRINCIPAL ==========");
        System.out.println("1. Gestionar Usuarios");
        System.out.println("2. Gestionar Mesas");
        System.out.println("3. Gestionar Juegos");
        System.out.println("4. Gestionar Reservas");
        System.out.println("5. Gestionar Prestamos");
        System.out.println("6. Gestionar Ventas");
        System.out.println("7. Ver estado del sistema");
        System.out.println("8. Guardar datos");
        System.out.println("0. Salir");
        System.out.print("Opcion: ");
    }

    // =========================================================
    // USUARIOS
    // =========================================================
    private static void menuUsuarios() {
        System.out.println("\n--- Usuarios ---");
        System.out.println("1. Agregar Cliente");
        System.out.println("2. Agregar Mesero");
        System.out.println("3. Agregar Cocinero");
        System.out.println("4. Agregar Administrador");
        System.out.println("5. Listar usuarios");
        System.out.print("Opcion: ");
        switch (leerInt()) {
            case 1: agregarCliente();       break;
            case 2: agregarMesero();        break;
            case 3: agregarCocinero();      break;
            case 4: agregarAdministrador(); break;
            case 5: listarUsuarios();       break;
        }
    }

    private static void agregarCliente() {
        System.out.print("Login: ");      String login  = sc.nextLine();
        System.out.print("Password: ");   String pass   = sc.nextLine();
        System.out.print("Nombre: ");     String nombre = sc.nextLine();
        System.out.print("ID Cliente: "); String id     = sc.nextLine();
        sistema.agregarUsuario(new Cliente(login, pass, nombre, new ArrayList<>(), id, 0));
        System.out.println("Cliente agregado.");
    }

    private static void agregarMesero() {
        System.out.print("Login: ");       String login  = sc.nextLine();
        System.out.print("Password: ");    String pass   = sc.nextLine();
        System.out.print("Nombre: ");      String nombre = sc.nextLine();
        System.out.print("ID Empleado: "); String id     = sc.nextLine();
        sistema.agregarUsuario(new Mesero(login, pass, nombre, new ArrayList<>(), id, new ArrayList<>()));
        System.out.println("Mesero agregado.");
    }

    private static void agregarCocinero() {
        System.out.print("Login: ");       String login  = sc.nextLine();
        System.out.print("Password: ");    String pass   = sc.nextLine();
        System.out.print("Nombre: ");      String nombre = sc.nextLine();
        System.out.print("ID Empleado: "); String id     = sc.nextLine();
        sistema.agregarUsuario(new Cocinero(login, pass, nombre, new ArrayList<>(), id));
        System.out.println("Cocinero agregado.");
    }

    private static void agregarAdministrador() {
        System.out.print("Login: ");    String login  = sc.nextLine();
        System.out.print("Password: "); String pass   = sc.nextLine();
        System.out.print("Nombre: ");   String nombre = sc.nextLine();
        sistema.agregarUsuario(new Administrador(login, pass, nombre));
        System.out.println("Administrador agregado.");
    }

    private static void listarUsuarios() {
        ArrayList<Usuario> lista = sistema.getUsuarios();
        if (lista.isEmpty()) { System.out.println("No hay usuarios registrados."); return; }
        System.out.println("\n--- Lista de Usuarios ---");
        for (Usuario u : lista) {
            System.out.println("[" + u.getClass().getSimpleName() + "] "
                + u.getLogin() + " - " + u.getNombre());
        }
    }

    // =========================================================
    // MESAS
    // =========================================================
    private static void menuMesas() {
        System.out.println("\n--- Mesas ---");
        System.out.println("1. Agregar Mesa");
        System.out.println("2. Listar mesas");
        System.out.print("Opcion: ");
        switch (leerInt()) {
            case 1: agregarMesa(); break;
            case 2: listarMesas(); break;
        }
    }

    private static void agregarMesa() {
        System.out.print("ID Mesa: "); String id = sc.nextLine();
        sistema.agregarMesa(new Mesa(id, true, 0, false, false));
        System.out.println("Mesa " + id + " agregada.");
    }

    private static void listarMesas() {
        ArrayList<Mesa> lista = sistema.getMesas();
        if (lista.isEmpty()) { System.out.println("No hay mesas registradas."); return; }
        System.out.println("\n--- Lista de Mesas ---");
        for (Mesa m : lista) {
            String estado = m.isDisponible()
                ? "Disponible"
                : "Ocupada (" + m.getCantidadPersonas() + " personas)";
            System.out.println(m.getIdMesa() + " - " + estado);
        }
    }

    // =========================================================
    // JUEGOS
    // =========================================================
    private static void menuJuegos() {
        System.out.println("\n--- Juegos ---");
        System.out.println("1. Agregar Juego de Prestamo");
        System.out.println("2. Listar juegos");
        System.out.print("Opcion: ");
        switch (leerInt()) {
            case 1: agregarJuego(); break;
            case 2: listarJuegos(); break;
        }
    }

    private static void agregarJuego() {
        System.out.print("ID: ");               String id      = sc.nextLine();
        System.out.print("Nombre: ");           String nombre  = sc.nextLine();
        System.out.print("Anio publicacion: "); int anio       = leerInt();
        System.out.print("Empresa: ");          String empresa = sc.nextLine();
        System.out.print("Min jugadores: ");    int min        = leerInt();
        System.out.print("Max jugadores: ");    int max        = leerInt();
        System.out.print("Edad minima: ");      int edad       = leerInt();
        System.out.println("Categoria (CARTAS / TABLERO / ACCION):");
        System.out.print("> ");                 String cat     = sc.nextLine().toUpperCase().trim();
        System.out.print("Es dificil (true/false): "); boolean dificil = Boolean.parseBoolean(sc.nextLine());
        System.out.print("Copias disponibles: "); int copias = leerInt();

        try {
            CategoriaJuego categoria = CategoriaJuego.valueOf(cat);
            JuegoDeMesa juego = new JuegoDeMesa(id, nombre, anio, empresa, min, max, edad, categoria, dificil, EstadoJuego.BUENO);
            sistema.agregarJuegoPrestamo(new JuegoPrestamo(juego, copias, 0));
            System.out.println("Juego '" + nombre + "' agregado.");
        } catch (IllegalArgumentException e) {
            System.out.println("Categoria invalida. Use: CARTAS, TABLERO o ACCION.");
        }
    }

    private static void listarJuegos() {
        ArrayList<JuegoPrestamo> lista = sistema.getJuegosPrestamo();
        if (lista.isEmpty()) { System.out.println("No hay juegos registrados."); return; }
        System.out.println("\n--- Lista de Juegos de Prestamo ---");
        for (JuegoPrestamo jp : lista) {
            JuegoDeMesa j = jp.getJuego();
            System.out.println("[" + j.getIdJuego() + "] " + j.getNombre()
                + " | Jugadores: " + j.getNumJugadoresMin() + "-" + j.getNumJugadoresMax()
                + " | Edad min: " + j.getEdadMinima()
                + " | Disponibles: " + jp.getCopiasDisponibles());
        }
    }

    // =========================================================
    // RESERVAS
    // =========================================================
    private static void menuReservas() {
        System.out.println("\n--- Reservas ---");
        System.out.println("1. Crear Reserva");
        System.out.println("2. Cancelar Reserva");
        System.out.println("3. Listar Reservas");
        System.out.print("Opcion: ");
        switch (leerInt()) {
            case 1: crearReserva();    break;
            case 2: cancelarReserva(); break;
            case 3: listarReservas();  break;
        }
    }

    private static void crearReserva() {
        System.out.print("Login del cliente: "); String login = sc.nextLine();
        Usuario u = sistema.buscarUsuarioPorLogin(login);
        if (!(u instanceof Cliente)) { System.out.println("Cliente no encontrado."); return; }

        System.out.print("Fecha (AAAA-MM-DD): ");       String fecha    = sc.nextLine();
        System.out.print("Cantidad de personas: ");      int cantidad    = leerInt();
        System.out.print("Hay ninos (true/false): ");    boolean ninios  = Boolean.parseBoolean(sc.nextLine());
        System.out.print("Hay jovenes (true/false): ");  boolean jovenes = Boolean.parseBoolean(sc.nextLine());

        Reserva r = sistema.crearReserva((Cliente) u, cantidad, ninios, jovenes, fecha);
        if (r != null && r.getEstadoReserva() == EstadoReserva.ACTIVA) {
            System.out.println("Reserva creada: " + r.getIdReserva()
                + " | Mesa asignada: " + r.getMesa().getIdMesa());
        } else {
            System.out.println("Reserva rechazada (sin mesas disponibles o capacidad insuficiente).");
        }
    }

    private static void cancelarReserva() {
        System.out.print("ID Reserva: "); String id = sc.nextLine();
        System.out.println(sistema.cancelarReserva(id) ? "Reserva cancelada." : "Reserva no encontrada.");
    }

    private static void listarReservas() {
        ArrayList<Reserva> lista = sistema.getReservas();
        if (lista.isEmpty()) { System.out.println("No hay reservas registradas."); return; }
        System.out.println("\n--- Lista de Reservas ---");
        for (Reserva r : lista) {
            String mesa = r.getMesa() != null ? r.getMesa().getIdMesa() : "Sin mesa";
            System.out.println("[" + r.getIdReserva() + "] " + r.getFecha()
                + " | Estado: " + r.getEstadoReserva()
                + " | Mesa: " + mesa);
        }
    }

    // =========================================================
    // PRESTAMOS
    // =========================================================
    private static void menuPrestamos() {
        System.out.println("\n--- Prestamos ---");
        System.out.println("1. Realizar Prestamo");
        System.out.println("2. Devolver Prestamo");
        System.out.println("3. Listar Prestamos");
        System.out.print("Opcion: ");
        switch (leerInt()) {
            case 1: realizarPrestamo(); break;
            case 2: devolverPrestamo(); break;
            case 3: listarPrestamos();  break;
        }
    }

    private static void realizarPrestamo() {
        System.out.print("Login del usuario: "); String login = sc.nextLine();
        Usuario u = sistema.buscarUsuarioPorLogin(login);
        if (u == null) { System.out.println("Usuario no encontrado."); return; }

        Mesa mesa = null;
        if (u instanceof Cliente) {
            System.out.print("ID de la mesa: "); String idMesa = sc.nextLine();
            mesa = sistema.buscarMesaPorId(idMesa);
            if (mesa == null) { System.out.println("Mesa no encontrada."); return; }
        }

        System.out.print("ID del juego: "); String idJuego = sc.nextLine();
        JuegoPrestamo jp = sistema.buscarJuegoPrestamo(idJuego);
        if (jp == null) { System.out.println("Juego no encontrado."); return; }

        System.out.print("Fecha (AAAA-MM-DD): "); String fecha = sc.nextLine();

        ArrayList<JuegoPrestamo> lista = new ArrayList<>();
        lista.add(jp);
        Prestamo pr = sistema.realizarPrestamo(u, mesa, lista, fecha);
        if (pr != null) {
            System.out.println("Prestamo realizado: " + pr.getIdPrestamo());
            if (pr.isAdvertenciaReglas())
                System.out.println("ADVERTENCIA: no hay mesero capacitado para este juego.");
        } else {
            System.out.println("Prestamo rechazado.");
        }
    }

    private static void devolverPrestamo() {
        System.out.print("ID Prestamo: ");                      String id    = sc.nextLine();
        System.out.print("Fecha de devolucion (AAAA-MM-DD): "); String fecha = sc.nextLine();
        System.out.println(sistema.devolverPrestamo(id, fecha)
            ? "Prestamo devuelto correctamente."
            : "No se pudo devolver el prestamo.");
    }

    private static void listarPrestamos() {
        ArrayList<Prestamo> lista = sistema.getPrestamos();
        if (lista.isEmpty()) { System.out.println("No hay prestamos registrados."); return; }
        System.out.println("\n--- Lista de Prestamos ---");
        for (Prestamo pr : lista) {
            System.out.println("[" + pr.getIdPrestamo() + "] " + pr.getFechaInicio()
                + " | Estado: " + pr.getEstadoPrestamo());
        }
    }

    // =========================================================
    // VENTAS
    // =========================================================
    private static void menuVentas() {
        System.out.println("\n--- Ventas ---");
        System.out.println("1. Registrar Venta");
        System.out.println("2. Consultar por rubro");
        System.out.println("3. Consultar por fecha");
        System.out.println("4. Listar todas las ventas");
        System.out.print("Opcion: ");
        switch (leerInt()) {
            case 1: registrarVenta();                   break;
            case 2: consultarPorRubro();                break;
            case 3: consultarPorFecha();                break;
            case 4: listarVentas(sistema.getVentas());  break;
        }
    }

    private static void registrarVenta() {
        System.out.print("Login del comprador: "); String login = sc.nextLine();
        Usuario u = sistema.buscarUsuarioPorLogin(login);
        if (u == null) { System.out.println("Usuario no encontrado."); return; }

        System.out.print("Cantidad: ");           int cantidad   = leerInt();
        System.out.print("Precio unitario: ");    double precio  = leerDouble();
        System.out.print("Propina: ");            double propina = leerDouble();
        System.out.print("Fecha (AAAA-MM-DD): "); String fecha   = sc.nextLine();
        System.out.println("Rubro (CAFETERIA / JUEGOS):");
        System.out.print("> "); String rubro = sc.nextLine().toUpperCase().trim();

        try {
            ArrayList<DetalleVenta> detalles = new ArrayList<>();
            detalles.add(new DetalleVenta("D1", cantidad, precio));
            Venta v = sistema.registrarVenta(u, detalles, propina, fecha, RubroVenta.valueOf(rubro));
            if (v != null) {
                System.out.println("Venta registrada: " + v.getIdVenta() + " | Total: " + v.getTotal());
            } else {
                System.out.println("Venta rechazada.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Rubro invalido. Use: CAFETERIA o JUEGOS.");
        }
    }

    private static void consultarPorRubro() {
        System.out.println("Rubro (CAFETERIA / JUEGOS):");
        System.out.print("> "); String rubro = sc.nextLine().toUpperCase().trim();
        try {
            listarVentas(sistema.consultarVentasPorRubro(RubroVenta.valueOf(rubro)));
        } catch (IllegalArgumentException e) {
            System.out.println("Rubro invalido.");
        }
    }

    private static void consultarPorFecha() {
        System.out.print("Fecha (AAAA-MM-DD): "); String fecha = sc.nextLine();
        listarVentas(sistema.consultarVentasPorFecha(fecha));
    }

    private static void listarVentas(ArrayList<Venta> lista) {
        if (lista.isEmpty()) { System.out.println("No hay ventas."); return; }
        System.out.println("\n--- Lista de Ventas ---");
        for (Venta v : lista) {
            System.out.println("[" + v.getIdVenta() + "] " + v.getFecha()
                + " | Rubro: " + v.getRubro()
                + " | Total: " + v.getTotal());
        }
    }

    // =========================================================
    // ESTADO DEL SISTEMA
    // =========================================================
    private static void verEstado() {
        Cafe cafe = sistema.getCafe();
        System.out.println("\n========== ESTADO DEL SISTEMA ==========");
        System.out.println("Cafe:      " + cafe.getNombre() + " (" + cafe.getDireccion() + ")");
        System.out.println("Capacidad: " + cafe.getCapacidadMaxima() + " personas");
        System.out.println("-----------------------------------------");
        System.out.println("Usuarios:  " + sistema.getUsuarios().size());
        System.out.println("Mesas:     " + sistema.getMesas().size());
        System.out.println("Juegos:    " + sistema.getJuegosPrestamo().size());
        System.out.println("Reservas:  " + sistema.getReservas().size());
        System.out.println("Prestamos: " + sistema.getPrestamos().size());
        System.out.println("Ventas:    " + sistema.getVentas().size());
        System.out.println("=========================================");
    }

    // =========================================================
    // CARGAR DATOS
    // =========================================================
    private static void cargarDatos(PersistenciaProyecto p) {
        try {
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

    // =========================================================
    // GUARDAR DATOS
    // =========================================================
    private static void guardarDatos() {
        PersistenciaProyecto p = new PersistenciaProyecto();
        try {
            ArrayList<JuegoDeMesa> juegos = new ArrayList<>();
            for (JuegoPrestamo jp : sistema.getJuegosPrestamo()) juegos.add(jp.getJuego());

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

            System.out.println("Datos guardados correctamente en data/.");
        } catch (IOException e) {
            System.out.println("Error al guardar: " + e.getMessage());
        }
    }

    // =========================================================
    // HELPERS
    // =========================================================
    private static int leerInt() {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Ingrese un numero entero: ");
            }
        }
    }

    private static double leerDouble() {
        while (true) {
            try {
                return Double.parseDouble(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Ingrese un numero: ");
            }
        }
    }
}

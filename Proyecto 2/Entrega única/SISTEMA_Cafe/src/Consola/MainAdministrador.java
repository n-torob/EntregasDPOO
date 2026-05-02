package Consola;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import Mundo.*;
import Persistencia.PersistenciaProyecto;
import Sistema.SistemaCafe;

public class MainAdministrador {

    private Scanner sc = new Scanner(System.in);
    private SistemaCafe sistema;
    private Administrador adminActivo; // usuario autenticado en esta sesión

    // Iniciar

    // carga los datos, ejecuta el login y lanza el menú principal
    // al salir del menú guarda los datos automáticamente
    
    public void iniciar() {
        sistema = new SistemaCafe(new Cafe("", "", 0));
        cargarDatos();
        login();
        if (adminActivo != null) {
            menuPrincipal();
        }
        guardarDatos();
    }

    // Login

    // solicita credenciales en loop hasta autenticar o que el usuario elija volver
    // verifica que el usuario autenticado sea instancia de Administrador
    private void login() {
        int opcion;
        do {
            System.out.println("\n--- Acceso Administración ---");
            System.out.println("1. Iniciar sesión");
            System.out.println("0. Volver al menú principal");
            System.out.print("Opción: ");
            opcion = leerInt();

            if (opcion == 1) {
                System.out.print("Login: ");    String login = sc.nextLine();
                System.out.print("Password: "); String pass  = sc.nextLine();

                Usuario u = sistema.autenticar(login, pass);

                if (u instanceof Administrador) {
                    adminActivo = (Administrador) u;
                    System.out.println("Bienvenido, " + adminActivo.getNombre() + ".");
                } else if (u != null) {
                    // autenticó correctamente pero no es administrador
                    System.out.println("Este acceso es solo para administradores.");
                } else {
                    System.out.println("Login o password incorrectos.");
                }
            }
        } while (opcion != 0 && adminActivo == null);
    }

    // Menú principal

    // loop principal del administrador, cada opción delega a un método específico
    private void menuPrincipal() {
        int opcion;
        do {
            System.out.println("\n========== MENÚ ADMINISTRADOR ==========");
            System.out.println("1. Registrar empleado");
            System.out.println("2. Gestionar turnos");
            System.out.println("3. Gestionar solicitudes de cambio de turno");
            System.out.println("4. Gestionar sugerencias de platillos");
            System.out.println("5. Agregar platillo al menú");
            System.out.println("6. Gestionar inventario");
            System.out.println("7. Consultar historial de préstamos");
            System.out.println("8. Consultar ventas");
            System.out.println("9. Gestionar juegos");
            System.out.println("0. Cerrar sesión");
            System.out.print("Opción: ");
            opcion = leerInt();

            switch (opcion) {
                case 1: registrarEmpleado();        break;
                case 2: gestionarTurnos();          break;
                case 3: gestionarSolicitudes();     break;
                case 4: gestionarSugerencias();     break;
                case 5: agregarPlatillo();          break;
                case 6: gestionarInventario();      break;
                case 7: consultarPrestamos();       break;
                case 8: consultarVentas();          break;
                case 9: gestionarJuegos();          break;
                case 0: System.out.println("Sesión cerrada."); break;
                default: System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
    }

    // Empleados

    // solicita tipo, login, password y nombre del empleado
    // llama a sistema.registrarEmpleado() que valida login único y crea el objeto
    private void registrarEmpleado() {
        System.out.println("\nTipo de empleado:");
        System.out.println("1. Mesero");
        System.out.println("2. Cocinero");
        System.out.print("Opción: ");
        int op = leerInt();

        String tipo;
        if (op == 1) {
            tipo = "MESERO";
        } else if (op == 2) {
            tipo = "COCINERO";
        } else {
            System.out.println("Opción inválida.");
            return;
        }

        System.out.print("Login: ");    String login  = sc.nextLine();
        System.out.print("Password: "); String pass   = sc.nextLine();
        System.out.print("Nombre: ");   String nombre = sc.nextLine();

        Empleado e = sistema.registrarEmpleado(login, pass, nombre, tipo);
        if (e != null) {
            System.out.println("Empleado registrado: " + e.getIdEmpleado() + " | " + e.getNombre());
        } else {
            System.out.println("El login ya existe. Elija otro.");
        }
    }

    // Turnos

    // lista empleados y sus turnos, permite asignar un turno nuevo a un empleado
    private void gestionarTurnos() {
        listarEmpleadosConTurnos();

        System.out.println("\n1. Asignar turno a empleado");
        System.out.println("0. Volver");
        System.out.print("Opción: ");
        int op = leerInt();

        if (op == 1) {
            System.out.print("ID del empleado: "); String idEmpleado = sc.nextLine();

            System.out.print("ID del turno: ");    String idTurno    = sc.nextLine();
            System.out.println("Día (LUNES/MARTES/MIERCOLES/JUEVES/VIERNES/SABADO/DOMINGO):");
            System.out.print("> "); String dia = sc.nextLine().toUpperCase().trim();
            System.out.print("Hora inicio (HH:MM): "); String horaInicio = sc.nextLine();
            System.out.print("Hora fin (HH:MM): ");    String horaFin    = sc.nextLine();

            try {
                DiaSemana diaSemana = DiaSemana.valueOf(dia);
                Turno turno = new Turno(idTurno, diaSemana, horaInicio, horaFin);

                if (sistema.asignarTurnoAEmpleado(idEmpleado, turno)) {
                    System.out.println("Turno asignado correctamente.");
                } else {
                    System.out.println("Empleado no encontrado.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Día inválido.");
            }
        }
    }

    // lista todos los empleados con sus turnos asignados
    private void listarEmpleadosConTurnos() {
        System.out.println("\n--- Empleados y Turnos ---");
        boolean hay = false;
        for (Usuario u : sistema.getUsuarios()) {
            if (u instanceof Empleado) {
                Empleado emp = (Empleado) u;
                System.out.println("[" + emp.getIdEmpleado() + "] " + emp.getNombre()
                    + " (" + emp.getClass().getSimpleName() + ")");
                ArrayList<Turno> turnos = emp.consultarTurnos();
                if (turnos.isEmpty()) {
                    System.out.println("   Sin turnos asignados.");
                } else {
                    for (Turno t : turnos) {
                        System.out.println("   - [" + t.getIdTurno() + "] "
                            + t.getDiaSemana() + " " + t.getHoraInicio() + "-" + t.getHoraFin());
                    }
                }
                hay = true;
            }
        }
        if (!hay) System.out.println("No hay empleados registrados.");
    }

    // Solicitudes de cambio de turno

    // lista las solicitudes pendientes y permite aprobarlas o rechazarlas
    // sistema.aprobarSolicitudCambio() valida cobertura mínima antes de aprobar
    private void gestionarSolicitudes() {
        System.out.println("\n--- Solicitudes de Cambio de Turno Pendientes ---");
        boolean hay = false;
        for (SolicitudCambioTurno s : sistema.getSolicitudesCambio()) {
            if (s.getEstado() == EstadoSolicitud.PENDIENTE) {
                System.out.println("[" + s.getIdSolicitud() + "] Tipo: " + s.getTipoSolicitud()
                    + " | Estado: " + s.getEstado());
                hay = true;
            }
        }
        if (!hay) { System.out.println("No hay solicitudes pendientes."); return; }

        System.out.print("ID de la solicitud: "); String id = sc.nextLine();

        System.out.println("1. Aprobar");
        System.out.println("2. Rechazar");
        System.out.print("Opción: ");
        int op = leerInt();

        if (op == 1) {
            // aprobarSolicitudCambio verifica cobertura mínima (1 cocinero, 2 meseros con turno)
            System.out.println(sistema.aprobarSolicitudCambio(id)
                ? "Solicitud aprobada."
                : "No se pudo aprobar. Verifique cobertura mínima del café.");
        } else if (op == 2) {
            System.out.println(sistema.rechazarSolicitudCambio(id)
                ? "Solicitud rechazada."
                : "Solicitud no encontrada.");
        } else {
            System.out.println("Opción inválida.");
        }
    }

    // Sugerencias de platillos

    // lista las sugerencias pendientes y permite aprobarlas o rechazarlas
    private void gestionarSugerencias() {
        System.out.println("\n--- Sugerencias de Platillos Pendientes ---");
        boolean hay = false;
        for (SugerenciaPlatillo s : sistema.getSugerenciasPlatillo()) {
            if (s.getEstado() == EstadoSugerencia.PENDIENTE) {
                System.out.println("[" + s.getIdSugerencia() + "] " + s.getNombrePlatillo()
                    + " | " + s.getDescripcion());
                hay = true;
            }
        }
        if (!hay) { System.out.println("No hay sugerencias pendientes."); return; }

        System.out.print("ID de la sugerencia: "); String id = sc.nextLine();

        System.out.println("1. Aprobar");
        System.out.println("2. Rechazar");
        System.out.print("Opción: ");
        int op = leerInt();

        if (op == 1) {
            System.out.println(sistema.aprobarSugerencia(id)
                ? "Sugerencia aprobada."
                : "Sugerencia no encontrada.");
        } else if (op == 2) {
            System.out.println(sistema.rechazarSugerencia(id)
                ? "Sugerencia rechazada."
                : "Sugerencia no encontrada.");
        } else {
            System.out.println("Opción inválida.");
        }
    }

    // Platillos

    // permite agregar una Bebida o Pastelería al menú del café
    private void agregarPlatillo() {
        System.out.println("\nTipo de platillo:");
        System.out.println("1. Bebida");
        System.out.println("2. Pastelería");
        System.out.print("Opción: ");
        int op = leerInt();

        System.out.print("ID: ");      String id     = sc.nextLine();
        System.out.print("Nombre: ");  String nombre = sc.nextLine();
        System.out.print("Precio: ");  int precio    = leerInt();

        if (op == 1) {
            System.out.print("¿Es alcohólica? (true/false): "); boolean alco    = leerBoolean();
            System.out.print("¿Es caliente? (true/false): ");   boolean cal     = leerBoolean();
            sistema.agregarPlatillo(new Bebida(id, nombre, precio, alco, cal));
            System.out.println("Bebida agregada al menú.");
        } else if (op == 2) {
            System.out.print("Alérgenos (separados por coma): "); String alergenosStr = sc.nextLine();
            ArrayList<String> alergenos = new ArrayList<>();
            // separar la cadena de alérgenos por coma y eliminar espacios
            for (String a : alergenosStr.split(",")) {
                alergenos.add(a.trim());
            }
            sistema.agregarPlatillo(new Pasteleria(id, nombre, precio, alergenos));
            System.out.println("Pastelería agregada al menú.");
        } else {
            System.out.println("Opción inválida.");
        }
    }

    // Inventario

    // submenú para reabastecer venta, reabastecer préstamo, mover venta a préstamo y cambiar estado
    private void gestionarInventario() {
        System.out.println("\n--- Gestión de Inventario ---");
        System.out.println("1. Reabastecer inventario de venta");
        System.out.println("2. Reabastecer inventario de préstamo");
        System.out.println("3. Mover juego de venta a préstamo");
        System.out.println("4. Cambiar estado de un juego");
        System.out.println("5. Agregar juego nuevo al sistema");
        System.out.println("0. Volver");
        System.out.print("Opción: ");
        int op = leerInt();

        switch (op) {
            case 1: reabastecerVenta();       break;
            case 2: reabastecerPrestamo();    break;
            case 3: moverVentaAPrestamo();    break;
            case 4: cambiarEstadoJuego();     break;
            case 5: agregarJuego();           break;
            case 0: break;
            default: System.out.println("Opción inválida.");
        }
    }

    // solicita id del juego y cantidad, llama a sistema.reabastecerJuegoVenta()
    private void reabastecerVenta() {
        listarInventarioVenta();
        System.out.print("ID del juego: "); String id  = sc.nextLine();
        System.out.print("Cantidad: ");     int cantidad = leerInt();

        JuegoVenta jv = sistema.buscarJuegoVenta(id);
        if (jv == null) { System.out.println("Juego no encontrado."); return; }

        sistema.reabastecerJuegoVenta(jv, cantidad);
        System.out.println("Stock actualizado. Nuevo stock: " + jv.getStockVenta());
    }

    // solicita id del juego y cantidad, llama a sistema.reabastecerJuegoPrestamo()
    private void reabastecerPrestamo() {
        listarInventarioPrestamo();
        System.out.print("ID del juego: "); String id       = sc.nextLine();
        System.out.print("Cantidad: ");     int cantidad    = leerInt();

        JuegoPrestamo jp = sistema.buscarJuegoPrestamo(id);
        if (jp == null) { System.out.println("Juego no encontrado."); return; }

        sistema.reabastecerJuegoPrestamo(jp, cantidad);
        System.out.println("Copias actualizadas. Disponibles: " + jp.getCopiasDisponibles());
    }

    // solicita id del juego y cantidad, llama a sistema.moverJuegoVentaAPrestamo()
    // retorna false si no hay stock suficiente en venta
    private void moverVentaAPrestamo() {
        listarInventarioVenta();
        System.out.print("ID del juego: "); String id       = sc.nextLine();
        System.out.print("Cantidad: ");     int cantidad    = leerInt();

        System.out.println(sistema.moverJuegoVentaAPrestamo(id, cantidad)
            ? "Juego movido correctamente."
            : "No se pudo mover. Verifique que el juego exista en ambos inventarios y haya stock suficiente.");
    }

    // muestra los estados posibles y actualiza el estado del juego indicado
    private void cambiarEstadoJuego() {
        listarInventarioPrestamo();
        System.out.print("ID del juego: "); String id = sc.nextLine();

        JuegoDeMesa juego = sistema.buscarJuego(id);
        if (juego == null) { System.out.println("Juego no encontrado."); return; }

        System.out.println("Estado actual: " + juego.getEstadoJuego());
        System.out.println("Nuevo estado (NUEVO/BUENO/FALTA_PIEZA/EN_REPARACION/DESAPARECIDO/ROBADO):");
        System.out.print("> "); String estado = sc.nextLine().toUpperCase().trim();

        try {
            adminActivo.actualizarEstadoJuego(juego, EstadoJuego.valueOf(estado));
            System.out.println("Estado actualizado a: " + juego.getEstadoJuego());
        } catch (IllegalArgumentException e) {
            System.out.println("Estado inválido.");
        }
    }

    // permite agregar un juego nuevo al inventario de préstamo
    private void agregarJuego() {
        System.out.print("ID: ");               String id      = sc.nextLine();
        System.out.print("Nombre: ");           String nombre  = sc.nextLine();
        System.out.print("Año publicación: ");  int anio       = leerInt();
        System.out.print("Empresa: ");          String empresa = sc.nextLine();
        System.out.print("Min jugadores: ");    int min        = leerInt();
        System.out.print("Max jugadores: ");    int max        = leerInt();
        System.out.print("Edad mínima: ");      int edad       = leerInt();
        System.out.println("Categoría (CARTAS/TABLERO/ACCION):");
        System.out.print("> ");                 String cat     = sc.nextLine().toUpperCase().trim();
        System.out.print("¿Es difícil? (true/false): "); boolean dificil = leerBoolean();
        System.out.print("Copias disponibles: "); int copias = leerInt();

        try {
            CategoriaJuego categoria = CategoriaJuego.valueOf(cat);
            JuegoDeMesa juego = new JuegoDeMesa(id, nombre, anio, empresa, min, max, edad,
                categoria, dificil, EstadoJuego.NUEVO);
            sistema.agregarJuegoPrestamo(new JuegoPrestamo(juego, copias, 0));
            System.out.println("Juego '" + nombre + "' agregado al inventario de préstamo.");
        } catch (IllegalArgumentException e) {
            System.out.println("Categoría inválida. Use: CARTAS, TABLERO o ACCION.");
        }
    }

    // Préstamos

    // lista el historial completo de préstamos con sus detalles
    private void consultarPrestamos() {
        ArrayList<Prestamo> lista = sistema.consultarHistorialPrestamos();
        System.out.println("\n--- Historial de Préstamos ---");
        if (lista.isEmpty()) { System.out.println("No hay préstamos registrados."); return; }

        for (Prestamo pr : lista) {
            String solicitante = pr.getSolicitante() != null ? pr.getSolicitante().getNombre() : "N/A";
            String mesa = pr.getMesa() != null ? pr.getMesa().getIdMesa() : "Sin mesa";
            System.out.println("[" + pr.getIdPrestamo() + "] " + pr.getFechaInicio()
                + " | Estado: " + pr.getEstadoPrestamo()
                + " | Solicitante: " + solicitante
                + " | Mesa: " + mesa
                + " | Juegos: " + pr.getJuegos().size());
        }
    }

    // Ventas

    // permite consultar ventas filtradas por rubro o por fecha
    private void consultarVentas() {
        System.out.println("\n--- Consultar Ventas ---");
        System.out.println("1. Por rubro");
        System.out.println("2. Por fecha");
        System.out.println("3. Ver todas");
        System.out.print("Opción: ");
        int op = leerInt();

        ArrayList<Venta> resultado;

        if (op == 1) {
            System.out.println("Rubro (CAFETERIA/JUEGOS):");
            System.out.print("> "); String rubro = sc.nextLine().toUpperCase().trim();
            try {
                resultado = sistema.consultarVentasPorRubro(RubroVenta.valueOf(rubro));
            } catch (IllegalArgumentException e) {
                System.out.println("Rubro inválido."); return;
            }
        } else if (op == 2) {
            System.out.print("Fecha (AAAA-MM-DD): "); String fecha = sc.nextLine();
            resultado = sistema.consultarVentasPorFecha(fecha);
        } else if (op == 3) {
            resultado = sistema.getVentas();
        } else {
            System.out.println("Opción inválida."); return;
        }

        listarVentas(resultado);
    }

    // muestra el detalle de cada venta con subtotal, impuestos, propina y total
    private void listarVentas(ArrayList<Venta> lista) {
        System.out.println("\n--- Ventas ---");
        if (lista.isEmpty()) { System.out.println("No hay ventas para mostrar."); return; }

        for (Venta v : lista) {
            String comprador = v.getComprador() != null ? v.getComprador().getNombre() : "N/A";
            System.out.println("[" + v.getIdVenta() + "] " + v.getFecha()
                + " | Rubro: " + v.getRubro()
                + " | Comprador: " + comprador
                + " | Subtotal: $" + v.getSubtotal()
                + " | Impuestos: $" + v.getImpuestos()
                + " | Propina: $" + v.getPropina()
                + " | Total: $" + v.getTotal());
        }
    }

    // Juegos

    // submenú para ver el estado de los juegos en el inventario de préstamo y venta
    private void gestionarJuegos() {
        System.out.println("\n--- Gestión de Juegos ---");
        System.out.println("1. Ver inventario de préstamo");
        System.out.println("2. Ver inventario de venta");
        System.out.println("0. Volver");
        System.out.print("Opción: ");
        int op = leerInt();

        if (op == 1) {
            listarInventarioPrestamo();
        } else if (op == 2) {
            listarInventarioVenta();
        }
    }

    // lista todos los juegos del inventario de préstamo con su estado y copias
    private void listarInventarioPrestamo() {
        ArrayList<JuegoPrestamo> lista = sistema.getJuegosPrestamo();
        System.out.println("\n--- Inventario de Préstamo ---");
        if (lista.isEmpty()) { System.out.println("No hay juegos en préstamo."); return; }

        for (JuegoPrestamo jp : lista) {
            JuegoDeMesa j = jp.getJuego();
            System.out.println("[" + j.getIdJuego() + "] " + j.getNombre()
                + " | Estado: " + j.getEstadoJuego()
                + " | Disponibles: " + jp.getCopiasDisponibles()
                + " | Prestadas: " + jp.getCopiasPrestadas());
        }
    }

    // lista todos los juegos del inventario de venta con su precio y stock
    private void listarInventarioVenta() {
        ArrayList<JuegoVenta> lista = sistema.getJuegosVenta();
        System.out.println("\n--- Inventario de Venta ---");
        if (lista.isEmpty()) { System.out.println("No hay juegos en venta."); return; }

        for (JuegoVenta jv : lista) {
            System.out.println("[" + jv.getJuego().getIdJuego() + "] " + jv.getJuego().getNombre()
                + " | Precio: $" + jv.getPrecio()
                + " | Stock: " + jv.getStockVenta());
        }
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
                // si no hay configuración del café, crear una nueva
                System.out.println("No se encontró configuración del café.");
                System.out.print("Nombre del café: ");   String nombre = sc.nextLine();
                System.out.print("Dirección: ");         String dir    = sc.nextLine();
                System.out.print("Capacidad máxima: ");  int cap       = leerInt();
                sistema = new SistemaCafe(new Cafe(nombre, dir, cap));
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
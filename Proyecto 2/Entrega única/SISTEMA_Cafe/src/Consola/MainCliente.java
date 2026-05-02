package Consola;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import Mundo.*;
import Persistencia.PersistenciaProyecto;
import Sistema.SistemaCafe;

public class MainCliente {

    private Scanner sc = new Scanner(System.in);
    private SistemaCafe sistema;
    private Cliente clienteActivo; // usuario autenticado en esta sesión

    // Iniciar

    // carga los datos, ejecuta el login y lanza el menú principal
    // al salir del menú guarda los datos automáticamente

    public void iniciar() {
        sistema = new SistemaCafe(new Cafe("", "", 0));
        cargarDatos();
        login();
        if (clienteActivo != null) {
            menuPrincipal();
        }
        guardarDatos();
    }

    // Login y registro

    // muestra opciones de iniciar sesión o registrarse
    // el loop termina cuando el cliente se autentica o elige volver

    private void login() {
        int opcion;
        do {
            System.out.println("\n--- Acceso Clientes ---");
            System.out.println("1. Iniciar sesión");
            System.out.println("2. Registrarse");
            System.out.println("0. Volver");
            System.out.print("Opción: ");
            opcion = leerInt();

            switch (opcion) {
                case 1: iniciarSesion(); break;
                case 2: registrarse();   break;
                case 0: break;
                default: System.out.println("Opción inválida.");
            }
        } while (opcion != 0 && clienteActivo == null);
    }

    // llama a sistema.autenticar() y verifica que el usuario sea instancia de Cliente
    // si las credenciales son de un empleado o administrador, rechaza el acceso

    private void iniciarSesion() {
        System.out.print("Login: ");    String login = sc.nextLine();
        System.out.print("Password: "); String pass  = sc.nextLine();

        Usuario u = sistema.autenticar(login, pass);

        if (u instanceof Cliente) {
            clienteActivo = (Cliente) u;
            System.out.println("Bienvenido, " + clienteActivo.getNombre() + ".");
        } else if (u != null) {
            // autenticó correctamente pero no es cliente
            System.out.println("Este acceso es solo para clientes.");
        } else {
            System.out.println("Login o password incorrectos.");
        }
    }

    // llama a sistema.registrarCliente() con los datos ingresados
    // si el login ya existe, el sistema retorna null e informa al usuario

    private void registrarse() {
        System.out.print("Login: ");    String login  = sc.nextLine();
        System.out.print("Password: "); String pass   = sc.nextLine();
        System.out.print("Nombre: ");   String nombre = sc.nextLine();

        Cliente c = sistema.registrarCliente(login, pass, nombre);

        if (c != null) {
            System.out.println("Registro exitoso. Ya puede iniciar sesión.");
        } else {
            System.out.println("El login ya existe. Elija otro.");
        }
    }

    // Menú principal

    // esto es lo que ve el cliente despues de iniciar sesión, cada opción lleva a un método específico

    private void menuPrincipal() {
        int opcion;
        do {
            System.out.println("\n========== MENÚ CLIENTE ==========");
            System.out.println("1. Crear reserva");
            System.out.println("2. Cancelar reserva");
            System.out.println("3. Solicitar préstamo");
            System.out.println("4. Devolver préstamo");
            System.out.println("5. Comprar juego");
            System.out.println("6. Comprar platillo");
            System.out.println("7. Ver mis puntos y bono");
            System.out.println("8. Gestionar favoritos");
            System.out.println("9. Ver catálogo de juegos");
            System.out.println("0. Cerrar sesión");
            System.out.print("Opción: ");
            opcion = leerInt();

            switch (opcion) {
                case 1: crearReserva();      break;
                case 2: cancelarReserva();   break;
                case 3: solicitarPrestamo(); break;
                case 4: devolverPrestamo();  break;
                case 5: comprarJuego();      break;
                case 6: comprarPlatillo();   break;
                case 7: verPuntos();         break;
                case 8: gestionarFavoritos(); break;
                case 9: verCatalogoJuegos(); break;
                case 0: System.out.println("Sesión cerrada."); break;
                default: System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
    }

    // Reservas

    // solicita los datos de la reserva y llama a sistema.crearReserva()
    // informa si fue aceptada o rechazada por capacidad

    private void crearReserva() {
        System.out.print("Fecha (AAAA-MM-DD): ");      String fecha    = sc.nextLine();
        System.out.print("Cantidad de personas: ");     int cantidad    = leerInt();
        System.out.print("Hay niños (true/false): ");   boolean ninios  = leerBoolean();
        System.out.print("Hay jóvenes (true/false): "); boolean jovenes = leerBoolean();

        Reserva r = sistema.crearReserva(clienteActivo, cantidad, ninios, jovenes, fecha);

        if (r != null && r.getEstadoReserva() == EstadoReserva.ACTIVA) {
            System.out.println("Reserva creada: " + r.getIdReserva()
                + " | Mesa: " + r.getMesa().getIdMesa());
        } else {
            System.out.println("Reserva rechazada: sin mesas disponibles o capacidad insuficiente.");
        }
    }

    // muestra las reservas del cliente, solicita el id y llama a sistema.cancelarReserva()
    // verifica que la reserva pertenezca al cliente activo antes de cancelar

    private void cancelarReserva() {
        listarMisReservas();
        System.out.print("ID Reserva a cancelar: "); String id = sc.nextLine();

        Reserva r = sistema.buscarReservaPorId(id);
        if (r == null || !r.getCliente().equals(clienteActivo)) {
            System.out.println("Reserva no encontrada o no le pertenece.");
            return;
        }

        System.out.println(sistema.cancelarReserva(id)
            ? "Reserva cancelada."
            : "No se pudo cancelar la reserva.");
    }

    // lista solo las reservas del cliente activo

    private void listarMisReservas() {
        System.out.println("\n--- Mis Reservas ---");
        boolean hay = false;
        for (Reserva r : sistema.getReservas()) {
            if (r.getCliente().equals(clienteActivo)) {
                String mesa = r.getMesa() != null ? r.getMesa().getIdMesa() : "Sin mesa";
                System.out.println("[" + r.getIdReserva() + "] " + r.getFecha()
                    + " | Estado: " + r.getEstadoReserva()
                    + " | Mesa: " + mesa);
                hay = true;
            }
        }
        if (!hay) System.out.println("No tiene reservas.");
    }

    // Préstamos

    // busca automáticamente la mesa activa del cliente
    // si no tiene reserva activa, no puede pedir préstamo
    // llama a sistema.realizarPrestamo() y muestra advertencia si el juego es difícil

    private void solicitarPrestamo() {
        Mesa mesaCliente = buscarMesaActivaCliente();
        if (mesaCliente == null) {
            System.out.println("Necesita una reserva activa para solicitar un préstamo.");
            return;
        }

        verCatalogoJuegos();
        System.out.print("ID del juego: "); String idJuego = sc.nextLine();
        JuegoPrestamo jp = sistema.buscarJuegoPrestamo(idJuego);
        if (jp == null) { System.out.println("Juego no encontrado."); return; }

        System.out.print("Fecha (AAAA-MM-DD): "); String fecha = sc.nextLine();

        ArrayList<JuegoPrestamo> lista = new ArrayList<>();
        lista.add(jp);
        Prestamo pr = sistema.realizarPrestamo(clienteActivo, mesaCliente, lista, fecha);

        if (pr != null) {
            System.out.println("Préstamo realizado: " + pr.getIdPrestamo());
            if (pr.isAdvertenciaReglas())
                // se activa cuando el juego es difícil y no hay mesero capacitado
                System.out.println("ADVERTENCIA: no hay mesero capacitado para este juego.");
        } else {
            System.out.println("Préstamo rechazado. Verifique disponibilidad, compatibilidad con la mesa o restricciones de bebida.");
        }
    }

    // muestra los préstamos activos del cliente, solicita id y fecha de devolución
    // verifica que el préstamo pertenezca al cliente activo antes de devolver

    private void devolverPrestamo() {
        listarMisPrestamos();
        System.out.print("ID Préstamo a devolver: "); String id    = sc.nextLine();
        System.out.print("Fecha de devolución: ");    String fecha = sc.nextLine();

        Prestamo pr = sistema.buscarPrestamoPorId(id);
        if (pr == null || !pr.getSolicitante().equals(clienteActivo)) {
            System.out.println("Préstamo no encontrado o no le pertenece.");
            return;
        }

        System.out.println(sistema.devolverPrestamo(id, fecha)
            ? "Préstamo devuelto correctamente."
            : "No se pudo devolver el préstamo.");
    }

    // lista solo los préstamos activos del cliente activo

    private void listarMisPrestamos() {
        System.out.println("\n--- Mis Préstamos Activos ---");
        boolean hay = false;
        for (Prestamo pr : sistema.getPrestamos()) {
            if (pr.getSolicitante().equals(clienteActivo)
                    && pr.getEstadoPrestamo() == EstadoPrestamo.ACTIVO) {
                System.out.println("[" + pr.getIdPrestamo() + "] " + pr.getFechaInicio()
                    + " | Juegos: " + pr.getJuegos().size());
                hay = true;
            }
        }
        if (!hay) System.out.println("No tiene préstamos activos.");
    }

    // Ventas — juegos

    // muestra el catálogo de venta, permite seleccionar juego y cantidad
    // aplica bono de descuento o puntos de fidelidad si el cliente los tiene
    // llama a sistema.registrarVenta() con rubro JUEGOS y reduce el stock manualmente

    private void comprarJuego() {
        System.out.println("\n--- Catálogo de Juegos en Venta ---");
        ArrayList<JuegoVenta> catalogo = sistema.getJuegosVenta();
        if (catalogo.isEmpty()) { System.out.println("No hay juegos en venta."); return; }

        for (JuegoVenta jv : catalogo) {
            System.out.println("[" + jv.getJuego().getIdJuego() + "] "
                + jv.getJuego().getNombre()
                + " | Precio: $" + jv.getPrecio()
                + " | Stock: " + jv.getStockVenta());
        }

        System.out.print("ID del juego: "); String idJuego = sc.nextLine();
        JuegoVenta jv = sistema.buscarJuegoVenta(idJuego);
        if (jv == null) { System.out.println("Juego no encontrado."); return; }

        System.out.print("Cantidad: "); int cantidad = leerInt();
        if (!jv.hayStock(cantidad)) { System.out.println("Stock insuficiente."); return; }

        // ofrecer bono si está activo (no acumulable con puntos)
        if (clienteActivo.isTieneBonoActivo()) {
            System.out.print("Tiene un bono de $" + clienteActivo.getBonoDescuento() + ". ¿Usarlo? (true/false): ");
            if (leerBoolean()) {
                clienteActivo.usarBono();
                System.out.println("Bono aplicado.");
            }
        } else if (clienteActivo.getPuntosFidelidad() > 0) {
            // ofrecer puntos solo si no hay bono activo
            System.out.println("Tiene " + clienteActivo.getPuntosFidelidad() + " puntos. ¿Cuántos desea redimir? (0 para ninguno): ");
            int puntos = leerInt();
            if (puntos > 0) {
                try {
                    clienteActivo.redimirPuntos(puntos);
                    System.out.println("Puntos redimidos: " + puntos);
                } catch (IllegalArgumentException e) {
                    System.out.println("Puntos insuficientes. No se aplicó descuento.");
                }
            }
        }

        ArrayList<DetalleVenta> detalles = new ArrayList<>();
        detalles.add(new DetalleVenta(idJuego, cantidad, jv.getPrecio()));

        // mesa null en ventas de juegos ya que no requieren mesa

        Venta v = sistema.registrarVenta(clienteActivo, detalles, 0, fechaHoy(), RubroVenta.JUEGOS, null);
        if (v != null) {
            jv.reducirStock(cantidad); // reducir stock después de confirmar la venta
            System.out.println("Compra realizada. Total: $" + v.getTotal()
                + " | Puntos ganados: " + v.getPuntosGanados());
        } else {
            System.out.println("No se pudo registrar la venta.");
        }
    }

    // Ventas — platillos

    // muestra el menú de platillos con alérgenos y características
    // verifica restricción bebida caliente vs juego ACCION activo en la mesa
    // llama a sistema.registrarVenta() con rubro CAFETERIA
    // sistema.registrarVenta() verifica internamente la restricción de bebida alcohólica con menores


    private void comprarPlatillo() {
        System.out.println("\n--- Menú de Platillos ---");
        ArrayList<Platillo> platillos = sistema.getPlatillos();
        if (platillos.isEmpty()) { System.out.println("No hay platillos disponibles."); return; }

        for (Platillo p : platillos) {
            String extra = "";
            if (p instanceof Bebida) {
                Bebida b = (Bebida) p;
                extra = " | " + (b.isAlcoholica() ? "Alcohólica" : "No alcohólica")
                      + " | " + (b.isCaliente() ? "Caliente" : "Fría");
            } else if (p instanceof Pasteleria) {
                Pasteleria pa = (Pasteleria) p;
                extra = " | Alérgenos: " + pa.getPosiblesAlergenos();
            }
            System.out.println("[" + p.getIdPlatillo() + "] " + p.getNombre()
                + " | $" + p.getPrecio() + extra);
        }

        System.out.print("ID del platillo: "); String idPlatillo = sc.nextLine();
        Platillo platillo = sistema.buscarPlatilloPorId(idPlatillo);
        if (platillo == null) { System.out.println("Platillo no encontrado."); return; }

        // buscar la mesa activa del cliente para verificar restricciones
        Mesa mesaCliente = buscarMesaActivaCliente();

        // verificar restricción bebida caliente vs juego ACCION en la mesa
        if (platillo instanceof Bebida && mesaCliente != null) {
            Bebida b = (Bebida) platillo;
            if (b.isCaliente() && tieneJuegoAccionActivo(mesaCliente)) {
                System.out.println("No puede pedir una bebida caliente: hay un juego ACCION activo en su mesa.");
                return;
            }
        }

        // propina sugerida es el 10% del precio antes de impuestos
        System.out.print("Propina sugerida: $" + (platillo.getPrecio() * 0.10) + ". ¿Cuánto desea dar? ");
        double propina = leerDouble();

        ArrayList<DetalleVenta> detalles = new ArrayList<>();
        detalles.add(new DetalleVenta(idPlatillo, 1, platillo.getPrecio()));

        Venta v = sistema.registrarVenta(clienteActivo, detalles, propina, fechaHoy(),
            RubroVenta.CAFETERIA, mesaCliente);

        if (v != null) {
            System.out.println("Compra realizada. Total: $" + v.getTotal()
                + " | Puntos ganados: " + v.getPuntosGanados());
            // avisar que bebida caliente bloquea futuros préstamos de juegos ACCION
            if (mesaCliente != null && platillo instanceof Bebida && ((Bebida) platillo).isCaliente()) {
                System.out.println("AVISO: bebida caliente registrada. No podrá pedir juegos ACCION en esta mesa.");
            }
        } else {
            // el sistema rechaza si hay bebida alcohólica con menores en la mesa
            System.out.println("Venta rechazada. Verifique restricciones (bebida alcohólica con menores).");
        }
    }

    // Puntos y favoritos

    // muestra los puntos de fidelidad y el estado del bono de descuento
    private void verPuntos() {
        System.out.println("\n--- Mis Puntos ---");
        System.out.println("Puntos de fidelidad: " + clienteActivo.getPuntosFidelidad());
        if (clienteActivo.isTieneBonoActivo()) {
            System.out.println("Bono de descuento activo: $" + clienteActivo.getBonoDescuento());
        } else {
            System.out.println("Sin bono de descuento activo.");
        }
    }

    // muestra favoritos actuales y permite agregar o eliminar
    // busca el juego por id en el inventario de préstamo

    private void gestionarFavoritos() {
        System.out.println("\n--- Mis Favoritos ---");
        ArrayList<JuegoDeMesa> favs = clienteActivo.getJuegosFavoritos();
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
                clienteActivo.agregarFavorito(jp.getJuego());
                System.out.println("Agregado a favoritos.");
            } else {
                System.out.println("Juego no encontrado.");
            }
        } else if (op == 2) {
            System.out.print("ID del juego a eliminar: "); String id = sc.nextLine();
            JuegoPrestamo jp = sistema.buscarJuegoPrestamo(id);
            if (jp != null) {
                clienteActivo.eliminarFavorito(jp.getJuego());
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
                + " | Edad mín: " + j.getEdadMinima()
                + " | " + disp);
        }
    }

    // Helpers internos

    // recorre las reservas buscando una activa del cliente activo con mesa asignada
    private Mesa buscarMesaActivaCliente() {
        for (Reserva r : sistema.getReservas()) {
            if (r.getCliente().equals(clienteActivo)
                    && r.getEstadoReserva() == EstadoReserva.ACTIVA
                    && r.getMesa() != null) {
                return r.getMesa();
            }
        }
        return null;
    }

    // verifica si hay un préstamo activo con algún juego de categoría ACCION en la mesa dada
    // se usa para bloquear la compra de bebida caliente en esa mesa

    private boolean tieneJuegoAccionActivo(Mesa mesa) {
        for (Prestamo pr : sistema.getPrestamos()) {
            if (pr.getEstadoPrestamo() == EstadoPrestamo.ACTIVO
                    && pr.getMesa() != null
                    && pr.getMesa().equals(mesa)) {
                for (JuegoPrestamo jp : pr.getJuegos()) {
                    if (jp.getJuego().getCategoria() == CategoriaJuego.ACCION) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // retorna la fecha actual en formato AAAA-MM-DD usando la clase LocalDate de Java
    private String fechaHoy() {
        return java.time.LocalDate.now().toString();
    }

    // Carga y guardado de datos

    // carga todos los datos desde los archivos de persistencia
    // si no existe configuración del café, el sistema no puede operar

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


    // Estos primeros dos metodos ya existian en el main hecho por Claude para el P1.

    // lee un entero desde la consola, repite si la entrada no es válida
    private int leerInt() {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Ingrese un número entero: ");
            }
        }
    }

    // lee un double desde la consola, repite si la entrada no es válida
    private double leerDouble() {
        while (true) {
            try {
                return Double.parseDouble(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Ingrese un número: ");
            }
        }
    }

    // NUEVO: lee un boolean desde la consola, acepta true/false. Repite si la entrada no es válida (por el while (true))

    private boolean leerBoolean() {
    while (true) {
        String input = sc.nextLine().trim().toLowerCase();
        if (input.equals("true"))  return true;
        if (input.equals("false")) return false;
        System.out.print("Ingrese true o false: ");
    }
}

}

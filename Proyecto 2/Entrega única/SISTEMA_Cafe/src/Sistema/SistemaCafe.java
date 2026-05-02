package Sistema;

import java.util.ArrayList;

import Mundo.Administrador;
import Mundo.Bebida;          // NUEVO: necesario para verificar restricciones de bebida en registrarVenta
import Mundo.Cafe;
import Mundo.Cliente;
import Mundo.Cocinero;
import Mundo.DetalleVenta;
import Mundo.Empleado;
import Mundo.EstadoJuego;
import Mundo.EstadoPrestamo;
import Mundo.EstadoReserva;
import Mundo.JuegoDeMesa;
import Mundo.JuegoPrestamo;
import Mundo.JuegoVenta;
import Mundo.Mesa;
import Mundo.Mesero;
import Mundo.Platillo;        // NUEVO: necesario para buscarPlatilloPorId
import Mundo.Prestamo;
import Mundo.Reserva;
import Mundo.RubroVenta;
import Mundo.SolicitudCambioTurno;
import Mundo.SugerenciaPlatillo;
import Mundo.TipoSolicitud;
import Mundo.Turno;
import Mundo.Usuario;
import Mundo.Venta;

public class SistemaCafe {

    // Atributos
    private Cafe cafe;
    private ArrayList<Usuario> usuarios;
    private ArrayList<JuegoVenta> juegosVenta;
    private ArrayList<JuegoPrestamo> juegosPrestamo;
    private ArrayList<Mesa> mesas;
    private ArrayList<Reserva> reservas;
    private ArrayList<Prestamo> prestamos;
    private ArrayList<Platillo> platillos;
    private ArrayList<Venta> ventas;
    private ArrayList<Turno> turnos;
    private ArrayList<SolicitudCambioTurno> solicitudesCambio;
    private ArrayList<SugerenciaPlatillo> sugerenciasPlatillo;

    // Contadores para generación de IDs
    private int consecutivoReserva;
    private int consecutivoPrestamo;
    private int consecutivoVenta;
    private int consecutivoSolicitud;
    private int consecutivoSugerencia;
    private int consecutivoCliente;   // NUEVO: para generar idCliente en registrarCliente
    private int consecutivoEmpleado;  // NUEVO: para generar idEmpleado en registrarEmpleado

    // Constructor
    public SistemaCafe(Cafe cafe) {
        this.cafe = cafe;
        this.usuarios = new ArrayList<Usuario>();
        this.juegosVenta = new ArrayList<JuegoVenta>();
        this.juegosPrestamo = new ArrayList<JuegoPrestamo>();
        this.mesas = new ArrayList<Mesa>();
        this.reservas = new ArrayList<Reserva>();
        this.prestamos = new ArrayList<Prestamo>();
        this.platillos = new ArrayList<Platillo>();
        this.ventas = new ArrayList<Venta>();
        this.turnos = new ArrayList<Turno>();
        this.solicitudesCambio = new ArrayList<SolicitudCambioTurno>();
        this.sugerenciasPlatillo = new ArrayList<SugerenciaPlatillo>();

        this.consecutivoReserva = 1;
        this.consecutivoPrestamo = 1;
        this.consecutivoVenta = 1;
        this.consecutivoSolicitud = 1;
        this.consecutivoSugerencia = 1;
        this.consecutivoCliente = 1;   // NUEVO
        this.consecutivoEmpleado = 1;  // NUEVO
    }

    // Getters y Setters

    public Cafe getCafe() {
        return cafe;
    }

    public void setCafe(Cafe cafe) {
        this.cafe = cafe;
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public ArrayList<JuegoVenta> getJuegosVenta() {
        return juegosVenta;
    }

    public void setJuegosVenta(ArrayList<JuegoVenta> juegosVenta) {
        this.juegosVenta = juegosVenta;
    }

    public ArrayList<JuegoPrestamo> getJuegosPrestamo() {
        return juegosPrestamo;
    }

    public void setJuegosPrestamo(ArrayList<JuegoPrestamo> juegosPrestamo) {
        this.juegosPrestamo = juegosPrestamo;
    }

    public ArrayList<Mesa> getMesas() {
        return mesas;
    }

    public void setMesas(ArrayList<Mesa> mesas) {
        this.mesas = mesas;
    }

    public ArrayList<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(ArrayList<Reserva> reservas) {
        this.reservas = reservas;
    }

    public ArrayList<Prestamo> getPrestamos() {
        return prestamos;
    }

    public void setPrestamos(ArrayList<Prestamo> prestamos) {
        this.prestamos = prestamos;
    }

    public ArrayList<Platillo> getPlatillos() {
        return platillos;
    }

    public void setPlatillos(ArrayList<Platillo> platillos) {
        this.platillos = platillos;
    }

    public ArrayList<Venta> getVentas() {
        return ventas;
    }

    public void setVentas(ArrayList<Venta> ventas) {
        this.ventas = ventas;
    }

    public ArrayList<Turno> getTurnos() {
        return turnos;
    }

    public void setTurnos(ArrayList<Turno> turnos) {
        this.turnos = turnos;
    }

    public ArrayList<SolicitudCambioTurno> getSolicitudesCambio() {
        return solicitudesCambio;
    }

    public void setSolicitudesCambio(ArrayList<SolicitudCambioTurno> solicitudesCambio) {
        this.solicitudesCambio = solicitudesCambio;
    }

    public ArrayList<SugerenciaPlatillo> getSugerenciasPlatillo() {
        return sugerenciasPlatillo;
    }

    public void setSugerenciasPlatillo(ArrayList<SugerenciaPlatillo> sugerenciasPlatillo) {
        this.sugerenciasPlatillo = sugerenciasPlatillo;
    }

    // Métodos para agregar entidades

    public void agregarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public void agregarJuegoVenta(JuegoVenta juegoVenta) {
        juegosVenta.add(juegoVenta);
    }

    public void agregarJuegoPrestamo(JuegoPrestamo juegoPrestamo) {
        juegosPrestamo.add(juegoPrestamo);
    }

    public void agregarMesa(Mesa mesa) {
        mesas.add(mesa);
    }

    public void agregarPlatillo(Platillo platillo) {
        platillos.add(platillo);
    }

    public void agregarTurno(Turno turno) {
        turnos.add(turno);
    }

    // Autenticación y registro
    // NUEVO: esta sección no existía en el sistema original

    // NUEVO: verifica login y password. Retorna el Usuario si las credenciales son correctas, null si no.
    public Usuario autenticar(String login, String password) {
        Usuario usuario = buscarUsuarioPorLogin(login);
        if (usuario == null) {
            return null;
        }
        if (usuario.getPassword().equals(password)) {
            return usuario;
        }
        return null;
    }

    // NUEVO: crea y registra un cliente. Retorna null si el login ya existe.
    public Cliente registrarCliente(String login, String password, String nombre) {
        if (buscarUsuarioPorLogin(login) != null) {
            return null;
        }
        String idCliente = "C" + consecutivoCliente;
        consecutivoCliente++;
        Cliente cliente = new Cliente(login, password, nombre, null, idCliente, 0);
        usuarios.add(cliente);
        return cliente;
    }

    // NUEVO: crea y registra un empleado (MESERO o COCINERO). Retorna null si el login ya existe o el tipo es inválido.
    public Empleado registrarEmpleado(String login, String password, String nombre, String tipo) {
        if (buscarUsuarioPorLogin(login) != null) {
            return null;
        }
        String idEmpleado = "E" + consecutivoEmpleado;
        consecutivoEmpleado++;

        Empleado empleado = null;
        if (tipo.equalsIgnoreCase("MESERO")) {
            empleado = new Mesero(login, password, nombre, new ArrayList<>(), idEmpleado, new ArrayList<>());
        } else if (tipo.equalsIgnoreCase("COCINERO")) {
            empleado = new Cocinero(login, password, nombre, new ArrayList<>(), idEmpleado);
        } else {
            return null;
        }

        usuarios.add(empleado);
        return empleado;
    }

    // Busquedas

    public Usuario buscarUsuarioPorLogin(String login) {
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getLogin().equals(login)) {
                return usuarios.get(i);
            }
        }
        return null;
    }

    public Mesa buscarMesaPorId(String idMesa) {
        for (int i = 0; i < mesas.size(); i++) {
            if (mesas.get(i).getIdMesa().equals(idMesa)) {
                return mesas.get(i);
            }
        }
        return null;
    }

    public Prestamo buscarPrestamoPorId(String idPrestamo) {
        for (int i = 0; i < prestamos.size(); i++) {
            if (prestamos.get(i).getIdPrestamo().equals(idPrestamo)) {
                return prestamos.get(i);
            }
        }
        return null;
    }

    public Reserva buscarReservaPorId(String idReserva) {
        for (int i = 0; i < reservas.size(); i++) {
            if (reservas.get(i).getIdReserva().equals(idReserva)) {
                return reservas.get(i);
            }
        }
        return null;
    }

    public Venta buscarVentaPorId(String idVenta) {
        for (int i = 0; i < ventas.size(); i++) {
            if (ventas.get(i).getIdVenta().equals(idVenta)) {
                return ventas.get(i);
            }
        }
        return null;
    }

    public SolicitudCambioTurno buscarSolicitudPorId(String idSolicitud) {
        for (int i = 0; i < solicitudesCambio.size(); i++) {
            if (solicitudesCambio.get(i).getIdSolicitud().equals(idSolicitud)) {
                return solicitudesCambio.get(i);
            }
        }
        return null;
    }

    public SugerenciaPlatillo buscarSugerenciaPorId(String idSugerencia) {
        for (int i = 0; i < sugerenciasPlatillo.size(); i++) {
            if (sugerenciasPlatillo.get(i).getIdSugerencia().equals(idSugerencia)) {
                return sugerenciasPlatillo.get(i);
            }
        }
        return null;
    }

    public JuegoPrestamo buscarJuegoPrestamo(String idJuego) {
        for (int i = 0; i < juegosPrestamo.size(); i++) {
            JuegoPrestamo jp = juegosPrestamo.get(i);
            if (jp.getJuego().getIdJuego().equals(idJuego)) {
                return jp;
            }
        }
        return null;
    }

    public JuegoDeMesa buscarJuego(String idJuego) {
        for (int i = 0; i < juegosPrestamo.size(); i++) {
            JuegoPrestamo jp = juegosPrestamo.get(i);
            if (jp.getJuego().getIdJuego().equals(idJuego)) {
                return jp.getJuego();
            }
        }
        return null;
    }

    // CORREGIDO: antes recibía int (índice de lista), ahora recibe String idJuego para consistencia con el resto de búsquedas
    public JuegoVenta buscarJuegoVenta(String idJuego) {
        for (int i = 0; i < juegosVenta.size(); i++) {
            if (juegosVenta.get(i).getJuego().getIdJuego().equals(idJuego)) {
                return juegosVenta.get(i);
            }
        }
        return null;
    }

    // NUEVO: necesario para verificar el tipo de platillo en registrarVenta
    
    public Platillo buscarPlatilloPorId(String idPlatillo) {
        for (int i = 0; i < platillos.size(); i++) {
            if (platillos.get(i).getIdPlatillo().equals(idPlatillo)) {
                return platillos.get(i);
            }
        }
        return null;
    }

    // NUEVO: necesario para que la consola pueda buscar turnos por id al asignarlos

    public Turno buscarTurnoPorId(String idTurno) {
        for (int i = 0; i < turnos.size(); i++) {
            if (turnos.get(i).getIdTurno().equals(idTurno)) {
                return turnos.get(i);
            }
        }
        return null;
    }

    // Reservas

    public Reserva crearReserva(Cliente cliente, int cantidadPersonas, boolean hayNinios, boolean hayJovenes, String fecha) {
        if (cliente == null || cantidadPersonas <= 0) {
            return null;
        }

        if (!validarCapacidad(cantidadPersonas)) {
            return new Reserva(generarIdReserva(), EstadoReserva.RECHAZADA, fecha, cliente, null);
        }

        Mesa mesaDisponible = asignarMesa();

        if (mesaDisponible == null) {
            return new Reserva(generarIdReserva(), EstadoReserva.RECHAZADA, fecha, cliente, null);
        }

        mesaDisponible.ocuparMesa(cantidadPersonas, hayNinios, hayJovenes);

        Reserva reserva = new Reserva(generarIdReserva(), EstadoReserva.ACTIVA, fecha, cliente, mesaDisponible);
        reservas.add(reserva);

        return reserva;
    }

    public boolean cancelarReserva(String idReserva) {
        Reserva reserva = buscarReservaPorId(idReserva);

        if (reserva == null) {
            return false;
        }

        if (reserva.getMesa() != null) {
            reserva.getMesa().liberarMesa();
        }

        reserva.setEstadoReserva(EstadoReserva.FINALIZADA);
        return true;
    }

    protected boolean validarCapacidad(int cantidadPersonas) {
        int ocupacionActual = 0;

        for (int i = 0; i < mesas.size(); i++) {
            if (!mesas.get(i).isDisponible()) {
                ocupacionActual += mesas.get(i).getCantidadPersonas();
            }
        }

        return ocupacionActual + cantidadPersonas <= cafe.getCapacidadMaxima();
    }

    protected Mesa asignarMesa() {
        for (int i = 0; i < mesas.size(); i++) {
            if (mesas.get(i).isDisponible()) {
                return mesas.get(i);
            }
        }
        return null;
    }

    // Prestamos

    public Prestamo realizarPrestamo(Usuario solicitante, Mesa mesa, ArrayList<JuegoPrestamo> listaJuegos, String fechaInicio) {
        if (solicitante == null || listaJuegos == null || listaJuegos.isEmpty()) {
            return null;
        }

        if (listaJuegos.size() > 2) {
            return null;
        }

        if (solicitante instanceof Administrador) {
            return null;
        }

        if (solicitante instanceof Cliente && mesa == null) {
            return null;
        }

        if (mesa != null) {
            int juegosActivosMesa = contarJuegosActivosEnMesa(mesa);
            if (juegosActivosMesa + listaJuegos.size() > 2) {
                return null;
            }
        }

        boolean advertencia = false;

        for (int i = 0; i < listaJuegos.size(); i++) {
            JuegoPrestamo juegoPrestamo = listaJuegos.get(i);

            if (!verificarDisponibilidadJuego(juegoPrestamo)) {
                return null;
            }

            if (mesa != null && !verificarRestriccionesPrestamo(mesa, juegoPrestamo)) {
                return null;
            }

            // CORREGIDO: bloquea préstamo de juego ACCION si hay bebida caliente en la mesa
            // El flag tieneBebidaCaliente se activa en registrarVenta cuando se vende una bebida caliente

            if (mesa != null && juegoPrestamo.getJuego().getCategoria() == Mundo.CategoriaJuego.ACCION) {
                if (mesa.isTieneBebidaCaliente()) {
                    return null;
                }
            }

            if (juegoPrestamo.getJuego().isEsDificil() && !hayMeseroCapacitado(juegoPrestamo.getJuego())) {
                advertencia = true;
            }
        }

        Prestamo prestamo = new Prestamo(generarIdPrestamo(), fechaInicio, solicitante, mesa);

        for (int i = 0; i < listaJuegos.size(); i++) {
            JuegoPrestamo juegoPrestamo = listaJuegos.get(i);
            juegoPrestamo.prestar();
            prestamo.agregarJuegoAPrestamo(juegoPrestamo);
        }

        prestamo.setAdvertenciaReglas(advertencia);
        prestamos.add(prestamo);

        return prestamo;
    }

    public boolean devolverPrestamo(String idPrestamo, String fechaDevolucion) {
        Prestamo prestamo = buscarPrestamoPorId(idPrestamo);

        if (prestamo == null) {
            return false;
        }

        if (prestamo.getEstadoPrestamo() != EstadoPrestamo.ACTIVO) {
            return false;
        }

        for (int i = 0; i < prestamo.getJuegos().size(); i++) {
            prestamo.getJuegos().get(i).devolver();
        }

        prestamo.finalizarPrestamo(fechaDevolucion);
        return true;
    }

    protected boolean verificarDisponibilidadJuego(JuegoPrestamo juego) {
        return juego != null && juego.hayDisponibilidad();
    }

    protected boolean verificarRestriccionesPrestamo(Mesa mesa, JuegoPrestamo juegoPrestamo) {
        if (mesa == null || juegoPrestamo == null) {
            return false;
        }

        JuegoDeMesa juego = juegoPrestamo.getJuego();

        if (mesa.getCantidadPersonas() < juego.getNumJugadoresMin() ||
            mesa.getCantidadPersonas() > juego.getNumJugadoresMax()) {
            return false;
        }

        if (mesa.getEdadMinimaParticipantes() < juego.getEdadMinima()) {
            return false;
        }

        return true;
    }

    protected int contarJuegosActivosEnMesa(Mesa mesa) {
        int cantidad = 0;

        for (int i = 0; i < prestamos.size(); i++) {
            Prestamo prestamo = prestamos.get(i);

            if (prestamo.getEstadoPrestamo() == EstadoPrestamo.ACTIVO &&
                prestamo.getMesa() != null &&
                prestamo.getMesa().equals(mesa)) {
                cantidad += prestamo.getJuegos().size();
            }
        }

        return cantidad;
    }

    protected boolean hayMeseroCapacitado(JuegoDeMesa juego) {
        for (int i = 0; i < usuarios.size(); i++) {
            Usuario usuario = usuarios.get(i);

            if (usuario instanceof Mesero) {
                Mesero mesero = (Mesero) usuario;
                if (mesero.puedeExplicarJuego(juego)) {
                    return true;
                }
            }
        }

        return false;
    }

    public ArrayList<Prestamo> consultarHistorialPrestamos() {
        return prestamos;
    }

    // Ventas

    // CORREGIDO: recibe Mesa como parámetro adicional para verificar restricciones de bebida
    // CORREGIDO: verifica que bebidas alcohólicas no se vendan a mesas con menores
    // NUEVO: marca la mesa con tieneBebidaCaliente=true si se vende una bebida caliente,
    //        para que realizarPrestamo pueda bloquear juegos ACCION en esa mesa

    public Venta registrarVenta(Usuario comprador, ArrayList<DetalleVenta> listaItems,
                                double propina, String fecha, RubroVenta rubro, Mesa mesa) {
        if (comprador == null || listaItems == null || listaItems.isEmpty() || rubro == null) {
            return null;
        }

        if (comprador instanceof Administrador) {
            return null;
        }

        if (rubro == RubroVenta.CAFETERIA && mesa != null) {
            for (int i = 0; i < listaItems.size(); i++) {
                Platillo platillo = buscarPlatilloPorId(listaItems.get(i).getIdDetalle());
                if (platillo instanceof Bebida) {
                    Bebida bebida = (Bebida) platillo;
                    // CORREGIDO: bloquea venta si la bebida alcohólica no es apta para la mesa
                    if (!bebida.esAptaParaMesa(mesa)) {
                        return null;
                    }
                    // NUEVO: activa la advertencia en la mesa para bloquear préstamo de juegos ACCION
                    if (bebida.isCaliente()) {
                        mesa.setTieneBebidaCaliente(true);
                    }
                }
            }
        }

        Venta venta = new Venta(generarIdVenta(), fecha, rubro, propina, 0, comprador);

        for (int i = 0; i < listaItems.size(); i++) {
            venta.agregarDetalleVenta(listaItems.get(i));
        }

        venta.calcularSubtotal();
        venta.calcularImpuestos();
        venta.calcularTotal();
        venta.calcularPuntos();

        if (comprador instanceof Cliente) {
            Cliente cliente = (Cliente) comprador;
            cliente.sumarPuntos(venta.getPuntosGanados());
        }

        ventas.add(venta);
        return venta;
    }

    public ArrayList<Venta> consultarVentasPorRubro(RubroVenta rubro) {
        ArrayList<Venta> resultado = new ArrayList<Venta>();

        for (int i = 0; i < ventas.size(); i++) {
            if (ventas.get(i).getRubro() == rubro) {
                resultado.add(ventas.get(i));
            }
        }

        return resultado;
    }

    public ArrayList<Venta> consultarVentasPorFecha(String fecha) {
        ArrayList<Venta> resultado = new ArrayList<Venta>();

        for (int i = 0; i < ventas.size(); i++) {
            if (ventas.get(i).getFecha().equals(fecha)) {
                resultado.add(ventas.get(i));
            }
        }

        return resultado;
    }

    // Solicitudes de cambio de turno

    public SolicitudCambioTurno registrarSolicitudCambio(Empleado empleado, Turno turnoActual, TipoSolicitud tipoSolicitud) {
        if (empleado == null || turnoActual == null || tipoSolicitud == null) {
            return null;
        }

        SolicitudCambioTurno solicitud = new SolicitudCambioTurno(generarIdSolicitud(), tipoSolicitud);
        solicitudesCambio.add(solicitud);

        return solicitud;
    }

    public boolean aprobarSolicitudCambio(String idSolicitud) {
        SolicitudCambioTurno solicitud = buscarSolicitudPorId(idSolicitud);

        if (solicitud == null) {
            return false;
        }

        if (!validarCoberturaTurno()) {
            return false;
        }

        solicitud.aprobar();
        return true;
    }

    public boolean rechazarSolicitudCambio(String idSolicitud) {
        SolicitudCambioTurno solicitud = buscarSolicitudPorId(idSolicitud);

        if (solicitud == null) {
            return false;
        }

        solicitud.rechazar();
        return true;
    }

    // CORREGIDO: antes contaba todos los empleados registrados en el sistema,
    // ahora cuenta solo los que tienen al menos un turno asignado
    protected boolean validarCoberturaTurno() {
        int cantidadMeseros = 0;
        int cantidadCocineros = 0;

        for (int i = 0; i < usuarios.size(); i++) {
            Usuario usuario = usuarios.get(i);

            if (usuario instanceof Mesero) {
                Mesero mesero = (Mesero) usuario;
                if (!mesero.getTurnos().isEmpty()) { // CORREGIDO: solo cuenta si tiene turnos
                    cantidadMeseros++;
                }
            } else if (usuario instanceof Cocinero) {
                Cocinero cocinero = (Cocinero) usuario;
                if (!cocinero.getTurnos().isEmpty()) { // CORREGIDO: solo cuenta si tiene turnos
                    cantidadCocineros++;
                }
            }
        }

        return cantidadMeseros >= 2 && cantidadCocineros >= 1;
    }

    // Sugerencias de platillos

    public SugerenciaPlatillo registrarSugerenciaPlatillo(Empleado empleado, String nombrePlatillo, String descripcion) {
        if (empleado == null || nombrePlatillo == null || descripcion == null) {
            return null;
        }

        SugerenciaPlatillo sugerencia = new SugerenciaPlatillo(generarIdSugerencia(), nombrePlatillo, descripcion);
        sugerenciasPlatillo.add(sugerencia);

        return sugerencia;
    }

    public boolean aprobarSugerencia(String idSugerencia) {
        SugerenciaPlatillo sugerencia = buscarSugerenciaPorId(idSugerencia);

        if (sugerencia == null) {
            return false;
        }

        sugerencia.aprobar();
        return true;
    }

    public boolean rechazarSugerencia(String idSugerencia) {
        SugerenciaPlatillo sugerencia = buscarSugerenciaPorId(idSugerencia);

        if (sugerencia == null) {
            return false;
        }

        sugerencia.rechazar();
        return true;
    }

    // Inventario y Administración

    public void reabastecerJuegoPrestamo(JuegoPrestamo juegoPrestamo, int cantidad) {
        if (juegoPrestamo != null && cantidad > 0) {
            juegoPrestamo.aumentarCopiasDisponibles(cantidad);
        }
    }

    public void reabastecerJuegoVenta(JuegoVenta juegoVenta, int cantidad) {
        if (juegoVenta != null && cantidad > 0) {
            juegoVenta.aumentarStock(cantidad);
        }
    }

    // NUEVO: mueve unidades del inventario de venta al de préstamo.
    // Antes este método vivía en Administrador con llamadas estáticas incorrectas a SistemaCafe.
    public boolean moverJuegoVentaAPrestamo(String idJuego, int cantidad) {
        JuegoVenta juegoVenta = buscarJuegoVenta(idJuego);
        JuegoPrestamo juegoPrestamo = buscarJuegoPrestamo(idJuego);

        if (juegoVenta == null || juegoPrestamo == null) {
            return false;
        }

        if (!juegoVenta.hayStock(cantidad)) {
            return false;
        }

        juegoVenta.reducirStock(cantidad);
        juegoPrestamo.aumentarCopiasDisponibles(cantidad);
        return true;
    }

    public boolean reportarJuegoComoRobado(JuegoPrestamo juegoPrestamo) {
        if (juegoPrestamo == null) {
            return false;
        }

        juegoPrestamo.getJuego().setEstadoJuego(EstadoJuego.ROBADO);

        if (juegoPrestamo.getCopiasPrestadas() > 0) {
            juegoPrestamo.setCopiasPrestadas(juegoPrestamo.getCopiasPrestadas() - 1);
        }

        return true;
    }

    public boolean repararJuego(JuegoPrestamo juegoPrestamo) {
        if (juegoPrestamo == null) {
            return false;
        }

        juegoPrestamo.getJuego().setEstadoJuego(EstadoJuego.BUENO);
        return true;
    }

    // Turnos

    // CORREGIDO: la relación de Empleado a Turno no estaba concreta en el sistema original.
    // asignarTurnoAEmpleado centraliza la asignación y mantiene la lista global de turnos sincronizada

    public boolean asignarTurnoAEmpleado(String idEmpleado, Turno turno) {
        if (turno == null) {
            return false;
        }

        for (int i = 0; i < usuarios.size(); i++) {
            Usuario usuario = usuarios.get(i);
            if (usuario instanceof Empleado) {
                Empleado empleado = (Empleado) usuario;
                if (empleado.getIdEmpleado().equals(idEmpleado)) {
                    empleado.agregarTurno(turno);
                    if (!turnos.contains(turno)) {
                        turnos.add(turno);
                    }
                    return true;
                }
            }
        }

        return false;
    }

    // Generación de IDs

    private String generarIdReserva() {
        String id = "R" + consecutivoReserva;
        consecutivoReserva++;
        return id;
    }

    private String generarIdPrestamo() {
        String id = "P" + consecutivoPrestamo;
        consecutivoPrestamo++;
        return id;
    }

    private String generarIdVenta() {
        String id = "V" + consecutivoVenta;
        consecutivoVenta++;
        return id;
    }

    private String generarIdSolicitud() {
        String id = "SC" + consecutivoSolicitud;
        consecutivoSolicitud++;
        return id;
    }

    private String generarIdSugerencia() {
        String id = "SP" + consecutivoSugerencia;
        consecutivoSugerencia++;
        return id;
    }
}
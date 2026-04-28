package Sistema;

import java.util.ArrayList;

import Mundo.Administrador;
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
import Mundo.Platillo;
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

    // Busqueda

    public JuegoPrestamo buscarJuegoPrestamo (String idJuego) {
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

    public JuegoVenta buscarJuegoVenta(int idJuego) {
        if (idJuego >= 0 && idJuego < juegosVenta.size()) {
            return juegosVenta.get(idJuego);
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
    public Venta registrarVenta(Usuario comprador, ArrayList<DetalleVenta> listaItems, double propina, String fecha, RubroVenta rubro) {
        if (comprador == null || listaItems == null || listaItems.isEmpty() || rubro == null) {
            return null;
        }

        if (comprador instanceof Administrador) {
            return null;
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

    protected boolean validarCoberturaTurno() {
        int cantidadMeseros = 0;
        int cantidadCocineros = 0;

        for (int i = 0; i < usuarios.size(); i++) {
            Usuario usuario = usuarios.get(i);

            if (usuario instanceof Mesero) {
                cantidadMeseros++;
            } else if (usuario instanceof Cocinero) {
                cantidadCocineros++;
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
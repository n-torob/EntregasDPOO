package Mundo;

import java.util.ArrayList;
import Sistema.SistemaCafe;

public class Administrador extends Usuario {
	
	public Administrador(String login, String password, String nombre) {
        super(login, password, nombre);
    }
	
	//Metodos

    public void aprobarSolicitudCambio(SolicitudCambioTurno solicitud) {
        solicitud.setEstado(EstadoSolicitud.APROBADA);
    }

    public void rechazarSolicitudCambio(SolicitudCambioTurno solicitud) {
        solicitud.setEstado(EstadoSolicitud.RECHAZADA);
    }

    public void aprobarSugerencia(SugerenciaPlatillo sugerencia) {
        sugerencia.setEstado(EstadoSugerencia.APROBADA);
    }

    public void rechazarSugerencia(SugerenciaPlatillo sugerencia) {
        sugerencia.setEstado(EstadoSugerencia.RECHAZADA);
    }
    public void reabastecerInventario(String idJuego, int cantidad, String tipoInventario) {
    	if (tipoInventario.equals("VENTA")) {
            JuegoVenta juego = SistemaCafe.buscarJuegoVenta(idJuego);
            if (juego != null) {
                juego.aumentarStock(cantidad);
            }
        }

        if (tipoInventario.equals("PRESTAMO")) {
            JuegoPrestamo juego = SistemaCafe.buscarJuegoPrestamo(idJuego);
            if (juego != null) {
                juego.aumentarCopiasDisponibles(cantidad);
            }
        }
    }

    public boolean moverJuegoAVentaAPrestamo(String idJuego, int cantidad,ArrayList<JuegoVenta> inventarioVenta, ArrayList<JuegoPrestamo> inventarioPrestamo) {
    	JuegoVenta juegoVenta = SistemaCafe.buscarJuegoVenta(idJuego);
        JuegoPrestamo juegoPrestamo = SistemaCafe.buscarJuegoPrestamo(idJuego);

        if (juegoVenta != null && juegoPrestamo != null && juegoVenta.hayStock(cantidad)) {
            juegoVenta.reducirStock(cantidad);
            juegoPrestamo.aumentarCopiasDisponibles(cantidad);
            return true;
        }

        return false;
      
    }

    public void repararJuego(String idJuego) {
    	JuegoDeMesa juego = SistemaCafe.buscarJuego(idJuego);
        if (juego != null) {
            juego.setEstadoJuego(EstadoJuego.EN_REPARACION);
        }
    }

    public void reportarJuegoDesaparecido(String idJuego) {
    	JuegoDeMesa juego = SistemaCafe.buscarJuego(idJuego);
        if (juego != null) {
            juego.setEstadoJuego(EstadoJuego.DESAPARECIDO);
        }
    }

    public void actualizarEstadoJuego(JuegoDeMesa juego, EstadoJuego estado) {
        juego.setEstadoJuego(estado);
    }

    public ArrayList<Venta> consultarVentasPorFecha(String rangoFechas) {
        return new ArrayList<Venta>();
    }

    public ArrayList<Venta> consultarVentasPorRubro(RubroVenta rubro) {
        return new ArrayList<Venta>();
    }

    public ArrayList<Prestamo> consultarHistorialPrestamos() {
        return new ArrayList<Prestamo>();
    }
	

}

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
	
    // En este apartado solamente se borraron los 4 metodos que generaban error porque ya existen en SistemaCafe.

}

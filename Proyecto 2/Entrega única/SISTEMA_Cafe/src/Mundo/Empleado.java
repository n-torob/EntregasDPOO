package Mundo;

import java.util.ArrayList;

public abstract class Empleado extends Usuario {

	private String idEmpleado;

	public Empleado(String login, String password, String nombre, ArrayList<JuegoDeMesa> juegosFavoritos,
			String idEmpleado) {
		super(login, password, nombre);
		setJuegosFavoritos(juegosFavoritos);
		this.idEmpleado = idEmpleado;
	}

	public String getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(String idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public ArrayList<Turno> consultarTurnos() {
		return new ArrayList<Turno>();
	}

	public SolicitudCambioTurno solicitarCambioTurno(String idSolicitud, TipoSolicitud tipoSolicitud) {
		return new SolicitudCambioTurno(idSolicitud, tipoSolicitud);
	}

	public SugerenciaPlatillo crearSugerenciaPlatillo(String idSugerencia, String nombrePlatillo, String descripcion) {
		return new SugerenciaPlatillo(idSugerencia, nombrePlatillo, descripcion);
	}
}
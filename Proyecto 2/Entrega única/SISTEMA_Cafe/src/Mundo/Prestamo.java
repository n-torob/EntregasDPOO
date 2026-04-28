package Mundo;

import java.util.ArrayList;

public class Prestamo {

	private String idPrestamo;
	private EstadoPrestamo estadoPrestamo;
	private String fechaInicio;
	private String fechaDevolucion;
	private boolean advertenciaReglas;
	private ArrayList<JuegoPrestamo> juegos;
	private Usuario solicitante;
	private Mesa mesa;

	public Prestamo(String idPrestamo, String fechaInicio, Usuario solicitante, Mesa mesa) {
		this.idPrestamo = idPrestamo;
		this.estadoPrestamo = EstadoPrestamo.ACTIVO;
		this.fechaInicio = fechaInicio;
		this.fechaDevolucion = "";
		this.advertenciaReglas = false;
		this.juegos = new ArrayList<JuegoPrestamo>();
		this.solicitante = solicitante;
		this.mesa = mesa;
	}

	public String getIdPrestamo() {
		return idPrestamo;
	}

	public void setIdPrestamo(String idPrestamo) {
		this.idPrestamo = idPrestamo;
	}

	public EstadoPrestamo getEstadoPrestamo() {
		return estadoPrestamo;
	}

	public void setEstadoPrestamo(EstadoPrestamo estadoPrestamo) {
		this.estadoPrestamo = estadoPrestamo;
	}

	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getFechaDevolucion() {
		return fechaDevolucion;
	}

	public void setFechaDevolucion(String fechaDevolucion) {
		this.fechaDevolucion = fechaDevolucion;
	}

	public boolean isAdvertenciaReglas() {
		return advertenciaReglas;
	}

	public void setAdvertenciaReglas(boolean advertenciaReglas) {
		this.advertenciaReglas = advertenciaReglas;
	}

	public ArrayList<JuegoPrestamo> getJuegos() {
		return juegos;
	}

	public void setJuegos(ArrayList<JuegoPrestamo> juegos) {
		this.juegos = juegos;
	}

	public Usuario getSolicitante() {
		return solicitante;
	}

	public void setSolicitante(Usuario solicitante) {
		this.solicitante = solicitante;
	}

	public Mesa getMesa() {
		return mesa;
	}

	public void setMesa(Mesa mesa) {
		this.mesa = mesa;
	}

	public void agregarJuegoAPrestamo(JuegoPrestamo juego) {
		juegos.add(juego);
	}

	public void finalizarPrestamo(String fechaDevolucion) {
		this.fechaDevolucion = fechaDevolucion;
		this.estadoPrestamo = EstadoPrestamo.DEVUELTO;
	}

	public boolean tieneCupoParaMasJuegos() {
		return juegos.size() < 2;
	}

	@Override
	public String toString() {
		String nombreSolicitante;
		if (solicitante != null) {
			nombreSolicitante = solicitante.getNombre();
		} else {
			nombreSolicitante = "null";
		}

		String idMesaStr;
		if (mesa != null) {
			idMesaStr = mesa.getIdMesa();
		} else {
			idMesaStr = "null";
		}

		return "Prestamo{" +
				"idPrestamo='" + idPrestamo + '\'' +
				", estadoPrestamo=" + estadoPrestamo +
				", fechaInicio='" + fechaInicio + '\'' +
				", fechaDevolucion='" + fechaDevolucion + '\'' +
				", advertenciaReglas=" + advertenciaReglas +
				", juegos=" + juegos +
				", solicitante=" + nombreSolicitante +
				", mesa=" + idMesaStr +
				'}';
	}
}
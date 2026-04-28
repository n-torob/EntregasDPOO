package Mundo;

import java.util.ArrayList;

public class Cliente extends Usuario{
	
	//Atributos
	private String idCliente;
	private int puntosFidelidad;
	
	public Cliente(String login, String password, String nombre, ArrayList<JuegoDeMesa> juegosFavoritos,
			String idCliente, int puntosFidelidad) {
		super(login, password, nombre);
		this.idCliente = idCliente;
		this.puntosFidelidad = puntosFidelidad;
	}

	public String getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	public int getPuntosFidelidad() {
		return puntosFidelidad;
	}

	public void setPuntosFidelidad(int puntosFidelidad) {
		this.puntosFidelidad = puntosFidelidad;
	}
	
	//Metodos
	public void sumarPuntos( int puntos) {
		this.puntosFidelidad += puntos;
	}
	
	public void redimirPuntos ( int puntos) {
		if (puntos <= puntosFidelidad) {
			this.puntosFidelidad -= puntos;
		} else {
			throw new IllegalArgumentException("No tiene suficientes puntos para redimir");

		}
	}
	
	

}

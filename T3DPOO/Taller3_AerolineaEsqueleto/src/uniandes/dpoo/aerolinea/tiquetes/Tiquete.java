package uniandes.dpoo.aerolinea.tiquetes;

import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;
public class Tiquete {
	
	private String codigo;
	private int tarifa;
	private boolean usado;
	private Vuelo vuelo;
	private Cliente clienteComprador;
	/**
	 * @param codigo
	 * @param tarifa
	 * @param usado
	 * @param vuelo
	 * @param clienteComprador
	 */
	public Tiquete(String codigo,Vuelo vuelo, Cliente clienteComprador, int tarifa ) {
		this.codigo = codigo;
		this.tarifa = tarifa;
		this.vuelo = vuelo;
		this.clienteComprador = clienteComprador;
	}
		
	public Cliente getCliente() {
		return clienteComprador;
	}
	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public void setTarifa(int tarifa) {
		this.tarifa = tarifa;
	}

	public void setVuelo(Vuelo vuelo) {
		this.vuelo = vuelo;
	}

	public void setClienteComprador(Cliente clienteComprador) {
		this.clienteComprador = clienteComprador;
	}

	public Vuelo getVuelo() {
		return vuelo;
	}
	
	public String getCodigo() {
		return codigo;
	}
	
	public int getTarifa() {
		return tarifa;
	}
	
	public void marcarComoUsado() {
		usado = true;
	}
	
	public boolean esUsado() {
		return usado;
	}
	
	
	
}

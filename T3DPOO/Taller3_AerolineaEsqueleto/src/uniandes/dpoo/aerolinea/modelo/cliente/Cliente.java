package uniandes.dpoo.aerolinea.modelo.cliente;

import java.util.ArrayList;
import uniandes.dpoo.aerolinea.tiquetes.Tiquete;

public abstract class Cliente {

	public String identificador;
	public ArrayList<Tiquete> tiquetes;
	public enum TipoCliente {
		CORPORATIVO,NATURAL
	};
	private TipoCliente tipoCliente;
	
	public Cliente(String identificador, String tipoCliente) {
		this.identificador = identificador;
		this.tipoCliente = TipoCliente.valueOf(tipoCliente.toUpperCase());
	}
	
	public String getTipoCliente() {
		return tipoCliente.name();
	}
	
	public String getIdentificador() {
		return identificador;
	}
	
	public void agregarTiquete(Tiquete tiquete) {
		tiquetes.add(tiquete);
	}
	
	public int calcularValorTotalTiquetes() {
		
		int valorTotal =0;
		
		for(Tiquete t: tiquetes) {
			valorTotal+= t.getTarifa();
		}
		
		return valorTotal;
	}
	
	public void usarTiquetes() {
		for(Tiquete t: tiquetes) {
			t.marcarComoUsado();
		}
	}

}


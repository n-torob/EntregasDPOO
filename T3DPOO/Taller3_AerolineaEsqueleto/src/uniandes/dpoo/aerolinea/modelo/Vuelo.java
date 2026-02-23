package uniandes.dpoo.aerolinea.modelo;

import java.util.Collection;
import java.util.ArrayList;
import uniandes.dpoo.aerolinea.modelo.tarifas.*;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;
import uniandes.dpoo.aerolinea.tiquetes.*;

public class Vuelo {
	
	private String fecha;
	private Ruta ruta;
	private Avion avion;
	private Collection<Tiquete> tiquetes = new ArrayList<>();
	
	public Vuelo(Ruta ruta, String fecha, Avion avion) {
		this.ruta = ruta;
		this.fecha = fecha;
		this.avion = avion;
	}
	
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	public void setRuta(Ruta ruta) {
		this.ruta = ruta;
	}

	public void setAvion(Avion avion) {
		this.avion = avion;
	}
	
	public Ruta getRuta() {
		return ruta;
	}
	
	public String getFecha() {
		return fecha;
	}
	
	public Avion getAvion() {
		return avion;
	}
	
	public Collection<Tiquete> getTiquetes(){
		return tiquetes;
	}
	
	public int venderTiquetes(Cliente cliente, CalculadoraTarifas calculadora, int cantidad ) {
		
		int capacidadDisponible = avion.getCapacidad() - tiquetes.size(); //se obtiene la capacidad del avion y se le restan los tiquetes ya vendidos, para obtener la capacidad dsponible
		
		if(cantidad > capacidadDisponible) return -1; //si no hay suficientes asientos retorna -1
		
		int tarifa = calculadora.calcularTarifa(this, cliente);
		
		GeneradorTiquetes generador = new GeneradorTiquetes();
		
		for( int i = 0; i < cantidad;i++) {
			Tiquete tiquete = generador.generarTiquete(this, cliente, tarifa);
			tiquetes.add(tiquete);
			cliente.agregarTiquete(tiquete);
		}
		
		
		
		return tarifa * cantidad;
	}
	
	
}

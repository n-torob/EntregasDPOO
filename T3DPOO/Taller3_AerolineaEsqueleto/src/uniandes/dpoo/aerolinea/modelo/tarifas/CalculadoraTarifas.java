package uniandes.dpoo.aerolinea.modelo.tarifas;

import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;
import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.Ruta;
import uniandes.dpoo.aerolinea.modelo.Aeropuerto;

public abstract class CalculadoraTarifas {
	
	public final double IMPUESTO = 0.28;
	
	public int calcularTarifa(Vuelo vuelo, Cliente cliente) {

		int costo = (int) (calcularCostoBase(vuelo, cliente) - calcularCostoBase(vuelo, cliente)*calcularPorcentajeDescuento(cliente));
		
		return costo;
	}
	
	protected abstract int calcularCostoBase(Vuelo vuelo, Cliente cliente) ;
	
	protected abstract double calcularPorcentajeDescuento(Cliente cliente) ;
	
	protected int calcularDistanciaVuelo(Ruta ruta) {
		
		int distancia = Aeropuerto.calcularDistancia(ruta.getOrigen(), ruta.getDestino());
		
		return distancia;
	}
	
	protected int calcularValorImpuestos(int costoBase) {
		return (int) (costoBase*IMPUESTO);
	}
	
	
	
}

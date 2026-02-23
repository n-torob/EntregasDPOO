package uniandes.dpoo.aerolinea.modelo.tarifas;


import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;
import uniandes.dpoo.aerolinea.modelo.cliente.ClienteNatural;
import uniandes.dpoo.aerolinea.modelo.cliente.ClienteCorporativo;
import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.Aeropuerto;

public class CalculadoraTarifasTemporadaBaja extends CalculadoraTarifas{

	protected final int COSTO_POR_KM_NATURAL = 600;
	protected final int COSTO_POR_KM_CORPORATIVO = 900;
	protected final double DESCUENTO_PEQ = 0.02;
	protected final double DESCUENTO_MEDIANAS = 0.1;
	protected final double DESCUENTO_GRANDES = 0.2;
	
	public CalculadoraTarifasTemporadaBaja() {
		super();
	}

	@Override
	public int calcularCostoBase(Vuelo vuelo, Cliente cliente) {
		
		int costo = 0;
		
		if(cliente instanceof ClienteNatural) {
			costo = (this.calcularDistanciaVuelo(vuelo.getRuta()))*COSTO_POR_KM_NATURAL;
		}
		else if (cliente instanceof ClienteCorporativo) {
			ClienteCorporativo corporativo = (ClienteCorporativo) cliente;
			
			costo = (this.calcularDistanciaVuelo(vuelo.getRuta()))*COSTO_POR_KM_CORPORATIVO;
			
		}
		
		return costo;
	}
	
	@Override
	public double calcularPorcentajeDescuento(Cliente cliente) {
		
		if (!(cliente instanceof ClienteCorporativo)) return 0;
		
		ClienteCorporativo corporativo = (ClienteCorporativo) cliente;
		
		double descuento = 0;
		
		if(corporativo.getTamanoEmpresa()==3) {//PEQ
			
			descuento = DESCUENTO_PEQ;
			
		}
		else if (corporativo.getTamanoEmpresa()==2) {//MEDIANAS
			
			descuento = DESCUENTO_MEDIANAS;
			
		}
		else if (corporativo.getTamanoEmpresa()==1) {//GRANDES
			
			descuento = DESCUENTO_GRANDES;
			
		}
		
		
		return descuento;
	}
	
	
}

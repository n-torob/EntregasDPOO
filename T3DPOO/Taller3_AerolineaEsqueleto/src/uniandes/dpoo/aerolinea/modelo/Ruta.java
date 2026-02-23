package uniandes.dpoo.aerolinea.modelo;

import java.util.Set;

/**
 * Esta clase tiene la información de una ruta entre dos aeropuertos que cubre una aerolínea.
 */
public class Ruta
{
	private String horaSalida;
	private String horaLlegada;
	private String codigoRuta;
	private Aeropuerto origen;
	private Aeropuerto destino;
    
	public Ruta(Aeropuerto origen, Aeropuerto destino, String horaSalida, String horaLlegada, String codigoRuta) {
		this.origen = origen;
		this.destino = destino;		
		this.horaSalida = horaSalida;
		this.horaLlegada = horaLlegada;
		this.codigoRuta = codigoRuta;
	}
	
	public void setHoraSalida(String horaSalida) {
		this.horaSalida = horaSalida;
	}

	public void setHoraLlegada(String horaLlegada) {
		this.horaLlegada = horaLlegada;
	}

	public void setCodigoRuta(String codigoRuta) {
		this.codigoRuta = codigoRuta;
	}

	public void setOrigen(Aeropuerto origen) {
		this.origen = origen;
	}

	public void setDestino(Aeropuerto destino) {
		this.destino = destino;
	}

	public String getCodigoRuta() {
		
		return codigoRuta;
	}
	
	public Aeropuerto getOrigen() {
		return origen;
	}
	
	public Aeropuerto getDestino() {
		return destino;
	}
	
	public String getHoraSalida() {
		return horaSalida;
	}
	
	public String getHoraLlegada() {
		return horaLlegada;
	}
	
	
	public int getDuracion() {
		
		int duracion=0;
		
		if (getMinutos(getHoraSalida())>getMinutos(getHoraLlegada())) {
			duracion += (getHoras(getHoraLlegada()) - getHoras(getHoraSalida())-1) + 60-getMinutos(getHoraSalida());
		}
		else {
			duracion += (getHoras(getHoraLlegada()) - getHoras(getHoraSalida())) + getMinutos(getHoraLlegada()) - getMinutos(getHoraSalida());
		}
		
		return duracion;
	}
	
	
	
	/**
     * Dada una cadena con una hora y minutos, retorna los minutos.
     * 
     * Por ejemplo, para la cadena '715' retorna 15.
     * @param horaCompleta Una cadena con una hora, donde los minutos siempre ocupan los dos últimos caracteres
     * @return Una cantidad de minutos entre 0 y 59
     */
    public static int getMinutos( String horaCompleta )
    {
        int minutos = Integer.parseInt( horaCompleta ) % 100;
        return minutos;
    }

    /**
     * Dada una cadena con una hora y minutos, retorna las horas.
     * 
     * Por ejemplo, para la cadena '715' retorna 7.
     * @param horaCompleta Una cadena con una hora, donde los minutos siempre ocupan los dos últimos caracteres
     * @return Una cantidad de horas entre 0 y 23
     */
    public static int getHoras( String horaCompleta )
    {
        int horas = Integer.parseInt( horaCompleta ) / 100;
        return horas;
    }

    
}

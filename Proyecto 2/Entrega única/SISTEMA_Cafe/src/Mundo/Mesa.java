package Mundo;

public class Mesa {

	private String idMesa;
	private boolean disponible;
	private int cantidadPersonas;
	private boolean hayNinios;
	private boolean hayJovenes;
	
	public Mesa(String idMesa, boolean disponible, int cantidadPersonas, boolean hayNinios, boolean hayJovenes) {
		super();
		this.idMesa = idMesa;
		this.disponible = disponible;
		this.cantidadPersonas = cantidadPersonas;
		this.hayNinios = hayNinios;
		this.hayJovenes = hayJovenes;
	}

	public String getIdMesa() {
		return idMesa;
	}

	public void setIdMesa(String idMesa) {
		this.idMesa = idMesa;
	}

	public boolean isDisponible() {
		return disponible;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}

	public int getCantidadPersonas() {
		return cantidadPersonas;
	}

	public void setCantidadPersonas(int cantidadPersonas) {
		this.cantidadPersonas = cantidadPersonas;
	}

	public boolean isHayNinios() {
		return hayNinios;
	}

	public void setHayNinios(boolean hayNinios) {
		this.hayNinios = hayNinios;
	}

	public boolean isHayJovenes() {
		return hayJovenes;
	}

	public void setHayJovenes(boolean hayJovenes) {
		this.hayJovenes = hayJovenes;
	}
	
	public boolean esCompatibleConJuego(JuegoDeMesa juego) {
        return juego.esAptoParaMesa(this);
    }
	
	public void ocuparMesa(int cantidadPersonas, boolean hayNinios, boolean hayJovenes) {
        this.cantidadPersonas = cantidadPersonas;
        this.hayNinios = hayNinios;
        this.hayJovenes = hayJovenes;
        this.disponible = false;
    }

    public void liberarMesa() {
        this.disponible = true;
        this.cantidadPersonas = 0;
        this.hayNinios = false;
        this.hayJovenes = false;
    }
	
	public int getEdadMinimaParticipantes() {
    if (hayNinios) {
        return 0;
    } else if (hayJovenes) {
        return 5;
    } else {
        return 18;
    }
}
	


}

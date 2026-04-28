package Mundo;

public class JuegoPrestamo {
	
	private JuegoDeMesa juego;
	private int copiasDisponibles;
	private int copiasPrestadas;
	
	public JuegoPrestamo(JuegoDeMesa juego, int copiasDisponibles, int copiasPrestadas) {
		super();
		this.juego = juego;
		this.copiasDisponibles = copiasDisponibles;
		this.copiasPrestadas = copiasPrestadas;
	}

	public JuegoDeMesa getJuego() {
		return juego;
	}

	public void setJuego(JuegoDeMesa juego) {
		this.juego = juego;
	}

	public int getCopiasDisponibles() {
		return copiasDisponibles;
	}

	public void setCopiasDisponibles(int copiasDisponibles) {
		this.copiasDisponibles = copiasDisponibles;
	}

	public int getCopiasPrestadas() {
		return copiasPrestadas;
	}

	public void setCopiasPrestadas(int copiasPrestadas) {
		this.copiasPrestadas = copiasPrestadas;
	}
	
	//Metodos
	public boolean hayDisponibilidad() {
		return copiasDisponibles>0;
	}
	public void prestar() {
		if (copiasDisponibles>0) {
			copiasDisponibles--;
			copiasPrestadas++;
		}
		else {
			throw new IllegalArgumentException("No hay copias disponibles para prestar");
			
		}
	}
	public void devolver () {
		if (copiasPrestadas<=0) {
			throw new IllegalArgumentException("No hay copias prestadas para devolver");
		}
		copiasDisponibles++;
		copiasPrestadas--;

	}
	public void aumentarCopiasDisponibles(int cantidad) {
        copiasDisponibles += cantidad;
    }

    public void reducirCopiasDisponibles(int cantidad) {
		if (cantidad > copiasDisponibles) {
			throw new IllegalArgumentException("No se pueden reducir más copias de las disponibles");
		}
        copiasDisponibles -= cantidad;
    }
	
	
	

}

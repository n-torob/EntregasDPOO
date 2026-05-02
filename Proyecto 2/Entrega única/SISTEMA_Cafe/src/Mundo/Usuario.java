package Mundo;

import java.util.ArrayList;
public abstract class Usuario {
	
	//Atributos
	private String login;
	private String password;
	private String nombre;
	private ArrayList<JuegoDeMesa> juegosFavoritos;
	private double bonoDescuento; // NUEVO: monto del bono ganado en un torneo amistoso.
	private boolean tieneBonoActivo; // NUEVO: centinela para evitar acumulación de bonos.
	
	public Usuario(String login, String password, String nombre) {
		this.login = login;
		this.password = password;
		this.nombre = nombre;
		this.juegosFavoritos = new ArrayList<>();
		this.bonoDescuento = 0.0; // NUEVO: inicia sin bono.
		this.tieneBonoActivo = false; // NUEVO: inicia sin bono activo.
	}

	// Getters y Setters
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ArrayList<JuegoDeMesa> getJuegosFavoritos() {
		return juegosFavoritos;
	}

	public void setJuegosFavoritos(ArrayList<JuegoDeMesa> juegosFavoritos) {
		this.juegosFavoritos = juegosFavoritos;
	}

	// NUEVO: getter y setter para bonoDescuento
	public double getBonoDescuento() {
		return bonoDescuento;
	}
 
	public void setBonoDescuento(double bonoDescuento) {
		this.bonoDescuento = bonoDescuento;
	}
 
	// NUEVO: getter y setter para tieneBonoActivo
	public boolean isTieneBonoActivo() {
		return tieneBonoActivo;
	}
 
	public void setTieneBonoActivo(boolean tieneBonoActivo) {
		this.tieneBonoActivo = tieneBonoActivo;
	}
	
	//Metodos
	public void agregarFavorito(JuegoDeMesa juego) {
		juegosFavoritos.add(juego);
	}
	public void eliminarFavorito(JuegoDeMesa juego) {
		juegosFavoritos.remove(juego);
	} 

	// NUEVO: activa el bono solo si no hay uno activo (no acumulable).
	public void activarBono(double monto) {
		if (!tieneBonoActivo) {	
			this.bonoDescuento = monto;
			this.tieneBonoActivo = true;
		}
	}
 
	// NUEVO: usa el bono, lo resetea a 0 y desactiva el centinela.
	public void usarBono() {
		this.bonoDescuento = 0;
		this.tieneBonoActivo = false;
	}
	
	
	
	
	

}

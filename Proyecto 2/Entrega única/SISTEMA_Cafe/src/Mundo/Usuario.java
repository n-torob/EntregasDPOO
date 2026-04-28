package Mundo;

import java.util.ArrayList;
public abstract class Usuario {
	
	//Atributos
	private String login;
	private String password;
	private String nombre;
	private ArrayList<JuegoDeMesa> juegosFavoritos;
	
	public Usuario(String login, String password, String nombre) {
		this.login = login;
		this.password = password;
		this.nombre = nombre;
		this.juegosFavoritos = new ArrayList<>();
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
	
	//Metodos
	public void agregarFavorito(JuegoDeMesa juego) {
		juegosFavoritos.add(juego);
	}
	public void eliminarFavorito(JuegoDeMesa juego) {
		juegosFavoritos.remove(juego);
	} 
	
	
	
	
	

}

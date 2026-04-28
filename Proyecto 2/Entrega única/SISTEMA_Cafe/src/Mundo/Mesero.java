package Mundo;

import java.util.ArrayList;

public class Mesero extends Empleado{
	
	//Atributos
	private ArrayList<JuegoDeMesa> juegosQueConoce;

	public Mesero(String login, String password, String nombre, ArrayList<JuegoDeMesa> juegosFavoritos,
			String idEmpleado, ArrayList<JuegoDeMesa> juegosQueConoce) {
		super(login, password, nombre, juegosFavoritos, idEmpleado);
		this.juegosQueConoce = juegosQueConoce;
	}

	public ArrayList<JuegoDeMesa> getJuegosQueConoce() {
		return juegosQueConoce;
	}

	public void setJuegosQueConoce(ArrayList<JuegoDeMesa> juegosQueConoce) {
		this.juegosQueConoce = juegosQueConoce;
	}
	
	//Metodos
	public boolean puedeExplicarJuego (JuegoDeMesa juego) {
		return juegosQueConoce.contains(juego);
	}
	

}

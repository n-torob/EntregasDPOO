package Mundo;

public class Cafe {
	
	private String nombre;
	private String direccion;
	private int capacidadMaxima;
	
	public Cafe(String nombre, String direccion, int capacidadMaxima) {
		this.nombre = nombre;
		this.direccion = direccion;
		this.capacidadMaxima = capacidadMaxima;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getCapacidadMaxima() {
		return capacidadMaxima;
	}

	public void setCapacidadMaxima(int capacidadMaxima) {
		this.capacidadMaxima = capacidadMaxima;
	}
	
	//Metodos
	public boolean tieneCapacidad (int cantidadPersonas) {
		return cantidadPersonas <= capacidadMaxima;
	}
	
	
	
	
	

}

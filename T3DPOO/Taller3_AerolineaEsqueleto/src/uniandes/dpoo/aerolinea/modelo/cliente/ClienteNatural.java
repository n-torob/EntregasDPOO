package uniandes.dpoo.aerolinea.modelo.cliente;

public class ClienteNatural extends Cliente{
	
	public static final String NATURAL =  "Natural";
	private String nombre;
	
	/**
	 * @param nombre
	 */
	public ClienteNatural(String nombre) {
		super(nombre, NATURAL);
		this.nombre = nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNATURAL() {
		return NATURAL;
	}

	public String getNombre() {
		return nombre;
	}
	
	
	

}

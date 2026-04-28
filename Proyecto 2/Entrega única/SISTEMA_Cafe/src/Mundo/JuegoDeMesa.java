package Mundo;

public class JuegoDeMesa {
	
	private String idJuego;
    private String nombre;
    private int anioPublicacion;
    private String empresaMatriz;
    private int numJugadoresMin;
    private int numJugadoresMax;
    private int edadMinima;
    private CategoriaJuego categoria;
    private boolean esDificil;
    private EstadoJuego estadoJuego;
    
	public JuegoDeMesa(String idJuego, String nombre, int anioPublicacion, String empresaMatriz, int numJugadoresMin,
			int numJugadoresMax, int edadMinima, CategoriaJuego categoria, boolean esDificil, EstadoJuego estadoJuego) {
		super();
		this.idJuego = idJuego;
		this.nombre = nombre;
		this.anioPublicacion = anioPublicacion;
		this.empresaMatriz = empresaMatriz;
		this.numJugadoresMin = numJugadoresMin;
		this.numJugadoresMax = numJugadoresMax;
		this.edadMinima = edadMinima;
		this.categoria = categoria;
		this.esDificil = esDificil;
		this.estadoJuego = estadoJuego;
	}

	public String getIdJuego() {
		return idJuego;
	}

	public void setIdJuego(String idJuego) {
		this.idJuego = idJuego;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getAnioPublicacion() {
		return anioPublicacion;
	}

	public void setAnioPublicacion(int anioPublicacion) {
		this.anioPublicacion = anioPublicacion;
	}

	public String getEmpresaMatriz() {
		return empresaMatriz;
	}

	public void setEmpresaMatriz(String empresaMatriz) {
		this.empresaMatriz = empresaMatriz;
	}

	public int getNumJugadoresMin() {
		return numJugadoresMin;
	}

	public void setNumJugadoresMin(int numJugadoresMin) {
		this.numJugadoresMin = numJugadoresMin;
	}

	public int getNumJugadoresMax() {
		return numJugadoresMax;
	}

	public void setNumJugadoresMax(int numJugadoresMax) {
		this.numJugadoresMax = numJugadoresMax;
	}

	public int getEdadMinima() {
		return edadMinima;
	}

	public void setEdadMinima(int edadMinima) {
		this.edadMinima = edadMinima;
	}

	public CategoriaJuego getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaJuego categoria) {
		this.categoria = categoria;
	}

	public boolean isEsDificil() {
		return esDificil;
	}

	public void setEsDificil(boolean esDificil) {
		this.esDificil = esDificil;
	}

	public EstadoJuego getEstadoJuego() {
		return estadoJuego;
	}

	public void setEstadoJuego(EstadoJuego estadoJuego) {
		this.estadoJuego = estadoJuego;
	}
	
	//Metodos
	
	public boolean esAptoParaMesa (Mesa mesa) {
		int cantidadPersonas = mesa.getCantidadPersonas();
		int edadMinimaMesa = mesa.getEdadMinimaParticipantes();
		
		return cantidadPersonas >= numJugadoresMin && cantidadPersonas <= numJugadoresMax && edadMinimaMesa >= edadMinima;
	}
    
    

}

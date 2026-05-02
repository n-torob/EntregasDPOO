package Mundo;

public class JuegoVenta {
	
	//Atributos
	private JuegoDeMesa juego; // NUEVO: referencia al juego base, necesaria para buscarJuegoVenta(String idJuego) en SistemaCafe
	private int precio;
	private int stockVenta;
	
	public JuegoVenta(JuegoDeMesa juego, int precio, int stockVenta) { // NUEVO: Ahora recibe el juego como parámetro.
		this.juego = juego;
		this.precio = precio;
		this.stockVenta = stockVenta;
	}

	// NUEVO: getter y setter para el juego.

	public JuegoDeMesa getJuego() {
		return juego;
	}

	public void setJuego(JuegoDeMesa juego) {
		this.juego = juego;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public int getStockVenta() {
		return stockVenta;
	}

	public void setStockVenta(int stockVenta) {
		this.stockVenta = stockVenta;
	}
	
	//Metodos
	public boolean hayStock (int cantidad) {
		return stockVenta >= cantidad;
	}
	public void reducirStock(int cantidad) {
    if (cantidad > stockVenta) {
        throw new IllegalArgumentException("No hay suficiente stock");
    }
    stockVenta -= cantidad;
}
	public void aumentarStock (int cantidad) {
		stockVenta += cantidad;
	}
	
	
	

}

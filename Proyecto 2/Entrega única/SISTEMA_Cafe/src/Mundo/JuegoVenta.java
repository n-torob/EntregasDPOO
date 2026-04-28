package Mundo;

public class JuegoVenta {
	
	//Atributos
	private int precio;
	private int stockVenta;
	
	public JuegoVenta(int precio, int stockVenta) {
		super();
		this.precio = precio;
		this.stockVenta = stockVenta;
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

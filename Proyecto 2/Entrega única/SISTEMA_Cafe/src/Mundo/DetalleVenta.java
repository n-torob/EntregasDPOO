package Mundo;

public class DetalleVenta {

    private String idDetalle;
    private int cantidad;
    private double precioUnitario;
    private double subtotalLinea;

    public DetalleVenta(String idDetalle, int cantidad, double precioUnitario) {
        this.idDetalle = idDetalle;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotalLinea = calcularSubtotalLinea();
    }

    public String getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(String idDetalle) {
        this.idDetalle = idDetalle;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
        this.subtotalLinea = calcularSubtotalLinea();
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
        this.subtotalLinea = calcularSubtotalLinea();
    }

    public double getSubtotalLinea() {
        return subtotalLinea;
    }

    public void setSubtotalLinea(double subtotalLinea) {
        this.subtotalLinea = subtotalLinea;
    }

    public double calcularSubtotalLinea() {
        return cantidad * precioUnitario;
    }

    @Override
    public String toString() {
        return "DetalleVenta{" +
                "idDetalle='" + idDetalle + '\'' +
                ", cantidad=" + cantidad +
                ", precioUnitario=" + precioUnitario +
                ", subtotalLinea=" + subtotalLinea +
                '}';
    }
}
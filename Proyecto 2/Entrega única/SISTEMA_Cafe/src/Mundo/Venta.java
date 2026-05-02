package Mundo;

import java.util.ArrayList;

public class Venta {

    private String idVenta;
    private String fecha;
    private RubroVenta rubro;
    private double subtotal;
    private double impuestos;
    private double propina;
    private double total;
    private int puntosGanados;
    private int puntosRedimidos;
    private ArrayList<DetalleVenta> detallesVenta;
    private Usuario comprador;

    public Venta(String idVenta, String fecha, RubroVenta rubro, double propina, int puntosRedimidos, Usuario comprador) {
        this.idVenta = idVenta;
        this.fecha = fecha;
        this.rubro = rubro;
        this.propina = propina;
        this.puntosRedimidos = puntosRedimidos;
        this.comprador = comprador;
        this.subtotal = 0;
        this.impuestos = 0;
        this.total = 0;
        this.puntosGanados = 0;
        this.detallesVenta = new ArrayList<DetalleVenta>();
    }

    public String getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(String idVenta) {
        this.idVenta = idVenta;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public RubroVenta getRubro() {
        return rubro;
    }

    public void setRubro(RubroVenta rubro) {
        this.rubro = rubro;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getImpuestos() {
        return impuestos;
    }

    public void setImpuestos(double impuestos) {
        this.impuestos = impuestos;
    }

    public double getPropina() {
        return propina;
    }

    public void setPropina(double propina) {
        this.propina = propina;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getPuntosGanados() {
        return puntosGanados;
    }

    public void setPuntosGanados(int puntosGanados) {
        this.puntosGanados = puntosGanados;
    }

    public int getPuntosRedimidos() {
        return puntosRedimidos;
    }

    public void setPuntosRedimidos(int puntosRedimidos) {
        this.puntosRedimidos = puntosRedimidos;
    }

    public ArrayList<DetalleVenta> getDetallesVenta() {
        return detallesVenta;
    }

    public void setDetallesVenta(ArrayList<DetalleVenta> detallesVenta) {
        this.detallesVenta = detallesVenta;
    }

    // NUEVO: getter y setter para comprador
    
    public Usuario getComprador() {
        return comprador;
    }

    public void setComprador(Usuario comprador) {
        this.comprador = comprador;
    }

    public void agregarDetalleVenta(DetalleVenta detalle) {
        detallesVenta.add(detalle);
    }

    public double calcularSubtotal() {
        double suma = 0;

        for (int i = 0; i < detallesVenta.size(); i++) {
            suma += detallesVenta.get(i).calcularSubtotalLinea();
        }

        subtotal = suma;
        return subtotal;
    }

    public double calcularImpuestos() {
        if (rubro == RubroVenta.JUEGOS) {
            impuestos = subtotal * 0.19;
        } else if (rubro == RubroVenta.CAFETERIA) {
            impuestos = subtotal * 0.08;
        } else {
            impuestos = 0;
        }

        return impuestos;
    }

    public double calcularTotal() {
        total = subtotal + impuestos + propina - puntosRedimidos;
        return total;
    }

    public int calcularPuntos() {
        puntosGanados = (int) (total * 0.01);
        return puntosGanados;
    }

    @Override
    public String toString() {
        return "Venta{" +
                "idVenta='" + idVenta + '\'' +
                ", fecha='" + fecha + '\'' +
                ", rubro=" + rubro +
                ", subtotal=" + subtotal +
                ", impuestos=" + impuestos +
                ", propina=" + propina +
                ", total=" + total +
                ", puntosGanados=" + puntosGanados +
                ", puntosRedimidos=" + puntosRedimidos +
                ", detallesVenta=" + detallesVenta +
                '}';
    }
}
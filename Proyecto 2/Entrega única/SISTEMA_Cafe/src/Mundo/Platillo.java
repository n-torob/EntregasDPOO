package Mundo;

public abstract class Platillo {

    private String idPlatillo;
    private String nombre;
    private int precio;

    public Platillo(String idPlatillo, String nombre, int precio) {
        this.idPlatillo = idPlatillo;
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getIdPlatillo() {
        return idPlatillo;
    }

    public void setIdPlatillo(String idPlatillo) {
        this.idPlatillo = idPlatillo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Platillo{" +
                "idPlatillo='" + idPlatillo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                '}';
    }
}
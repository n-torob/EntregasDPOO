package Mundo;

public class Bebida extends Platillo {

    private boolean alcoholica;
    private boolean caliente;

    public Bebida(String idPlatillo, String nombre, int precio, boolean alcoholica, boolean caliente) {
        super(idPlatillo, nombre, precio);
        this.alcoholica = alcoholica;
        this.caliente = caliente;
    }

    public boolean isAlcoholica() {
        return alcoholica;
    }

    public void setAlcoholica(boolean alcoholica) {
        this.alcoholica = alcoholica;
    }

    public boolean isCaliente() {
        return caliente;
    }

    public void setCaliente(boolean caliente) {
        this.caliente = caliente;
    }

    public boolean esAptaParaMesa(Mesa mesa) {
        if (alcoholica && (mesa.isHayNinios() || mesa.isHayJovenes())) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Bebida{" +
                "idPlatillo='" + getIdPlatillo() + '\'' +
                ", nombre='" + getNombre() + '\'' +
                ", precio=" + getPrecio() +
                ", alcoholica=" + alcoholica +
                ", caliente=" + caliente +
                '}';
    }
}
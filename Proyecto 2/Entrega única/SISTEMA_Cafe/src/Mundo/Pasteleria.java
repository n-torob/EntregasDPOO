package Mundo;

import java.util.ArrayList;

public class Pasteleria extends Platillo {

    private ArrayList<String> posiblesAlergenos;

    public Pasteleria(String idPlatillo, String nombre, int precio, ArrayList<String> posiblesAlergenos) {
        super(idPlatillo, nombre, precio);
        this.posiblesAlergenos = posiblesAlergenos;
    }

    public ArrayList<String> getPosiblesAlergenos() {
        return posiblesAlergenos;
    }

    public void setPosiblesAlergenos(ArrayList<String> posiblesAlergenos) {
        this.posiblesAlergenos = posiblesAlergenos;
    }

    public ArrayList<String> informarAlergenos() {
        return posiblesAlergenos;
    }

    public void agregarAlergeno(String alergeno) {
        posiblesAlergenos.add(alergeno);
    }

    public boolean eliminarAlergeno(String alergeno) {
        return posiblesAlergenos.remove(alergeno);
    }

    @Override
    public String toString() {
        return "Pasteleria{" +
                "idPlatillo='" + getIdPlatillo() + '\'' +
                ", nombre='" + getNombre() + '\'' +
                ", precio=" + getPrecio() +
                ", posiblesAlergenos=" + posiblesAlergenos +
                '}';
    }
}
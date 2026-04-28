package Mundo;

public class SugerenciaPlatillo {

    private String idSugerencia;
    private String nombrePlatillo;
    private String descripcion;
    private EstadoSugerencia estado;

    public SugerenciaPlatillo(String idSugerencia, String nombrePlatillo, String descripcion) {
        this.idSugerencia = idSugerencia;
        this.nombrePlatillo = nombrePlatillo;
        this.descripcion = descripcion;
        this.estado = EstadoSugerencia.PENDIENTE;
    }

    public String getIdSugerencia() {
        return idSugerencia;
    }

    public void setIdSugerencia(String idSugerencia) {
        this.idSugerencia = idSugerencia;
    }

    public String getNombrePlatillo() {
        return nombrePlatillo;
    }

    public void setNombrePlatillo(String nombrePlatillo) {
        this.nombrePlatillo = nombrePlatillo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public EstadoSugerencia getEstado() {
        return estado;
    }

    public void setEstado(EstadoSugerencia estado) {
        this.estado = estado;
    }

    public void aprobar() {
        this.estado = EstadoSugerencia.APROBADA;
    }

    public void rechazar() {
        this.estado = EstadoSugerencia.RECHAZADA;
    }

    @Override
    public String toString() {
        return "SugerenciaPlatillo{" +
                "idSugerencia='" + idSugerencia + '\'' +
                ", nombrePlatillo='" + nombrePlatillo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", estado=" + estado +
                '}';
    }
}
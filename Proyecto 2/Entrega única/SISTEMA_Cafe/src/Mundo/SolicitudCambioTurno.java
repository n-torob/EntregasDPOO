package Mundo;

public class SolicitudCambioTurno {

    private String idSolicitud;
    private EstadoSolicitud estado;
    private TipoSolicitud tipoSolicitud;

    public SolicitudCambioTurno(String idSolicitud, TipoSolicitud tipoSolicitud) {
        this.idSolicitud = idSolicitud;
        this.tipoSolicitud = tipoSolicitud;
        this.estado = EstadoSolicitud.PENDIENTE;
    }

    public String getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(String idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public EstadoSolicitud getEstado() {
        return estado;
    }

    public void setEstado(EstadoSolicitud estado) {
        this.estado = estado;
    }

    public TipoSolicitud getTipoSolicitud() {
        return tipoSolicitud;
    }

    public void setTipoSolicitud(TipoSolicitud tipoSolicitud) {
        this.tipoSolicitud = tipoSolicitud;
    }

    public void aprobar() {
        this.estado = EstadoSolicitud.APROBADA;
    }

    public void rechazar() {
        this.estado = EstadoSolicitud.RECHAZADA;
    }

    @Override
    public String toString() {
        return "SolicitudCambioTurno{" +
                "idSolicitud='" + idSolicitud + '\'' +
                ", estado=" + estado +
                ", tipoSolicitud=" + tipoSolicitud +
                '}';
    }
}
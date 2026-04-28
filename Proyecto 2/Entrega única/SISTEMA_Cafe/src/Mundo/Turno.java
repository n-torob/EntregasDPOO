package Mundo;

public class Turno {

    private String idTurno;
    private DiaSemana diaSemana;
    private String horaInicio;
    private String horaFin;

    public Turno(String idTurno, DiaSemana diaSemana, String horaInicio, String horaFin) {
        this.idTurno = idTurno;
        this.diaSemana = diaSemana;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }

    public String getIdTurno() {
        return idTurno;
    }

    public void setIdTurno(String idTurno) {
        this.idTurno = idTurno;
    }

    public DiaSemana getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(DiaSemana diaSemana) {
        this.diaSemana = diaSemana;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public boolean esValido() {
        if (diaSemana == null) {
            return false;
        }
        if (horaInicio == null || horaFin == null) {
            return false;
        }
        if (horaInicio.isBlank() || horaFin.isBlank()) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Turno{" +
                "idTurno='" + idTurno + '\'' +
                ", diaSemana=" + diaSemana +
                ", horaInicio='" + horaInicio + '\'' +
                ", horaFin='" + horaFin + '\'' +
                '}';
    }
}
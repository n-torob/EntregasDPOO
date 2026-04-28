package Mundo;

public class Reserva {

	private String idReserva;
	private EstadoReserva estadoReserva;
	private String fecha;
	private Cliente cliente;
	private Mesa mesa;

	public Reserva(String idReserva, EstadoReserva estadoReserva, String fecha, Cliente cliente, Mesa mesa) {
		this.idReserva = idReserva;
		this.estadoReserva = estadoReserva;
		this.fecha = fecha;
		this.cliente = cliente;
		this.mesa = mesa;
	}

	public String getIdReserva() {
		return idReserva;
	}

	public void setIdReserva(String idReserva) {
		this.idReserva = idReserva;
	}

	public EstadoReserva getEstadoReserva() {
		return estadoReserva;
	}

	public void setEstadoReserva(EstadoReserva estadoReserva) {
		this.estadoReserva = estadoReserva;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Mesa getMesa() {
		return mesa;
	}

	public void setMesa(Mesa mesa) {
		this.mesa = mesa;
	}

	public void confirmar() {
		this.estadoReserva = EstadoReserva.ACTIVA;
	}

	public void rechazar() {
		this.estadoReserva = EstadoReserva.RECHAZADA;
	}

	@Override
	public String toString() {

		String nombreCliente;
		if (cliente != null) {
			nombreCliente = cliente.getNombre();
		} else {
			nombreCliente = "null";
		}

		String idMesaStr;
		if (mesa != null) {
			idMesaStr = mesa.getIdMesa();
		} else {
			idMesaStr = "null";
		}

		return "Reserva{" +
				"idReserva='" + idReserva + '\'' +
				", estadoReserva=" + estadoReserva +
				", fecha='" + fecha + '\'' +
				", cliente=" + nombreCliente +
				", mesa=" + idMesaStr +
				'}';
	}
}
package ni.edu.ucem.webapi.modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Disponibilidad {

	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
	private Date fechaIngreso;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
	private Date fechaSalida;
	private List<Cuarto> cuartos;

	public Disponibilidad(Date fechaIngreso, Date fechaSalida) {
		this(fechaIngreso, fechaSalida, new ArrayList<Cuarto>());
	}

	public Disponibilidad(Date fechaIngreso, Date fechaSalida, List<Cuarto> cuartos) {
		this.fechaIngreso = fechaIngreso;
		this.fechaSalida = fechaSalida;
		this.cuartos = cuartos;
	}

	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public Date getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public List<Cuarto> getCuartos() {
		return cuartos;
	}

	public void setCuartos(List<Cuarto> cuartos) {
		this.cuartos = cuartos;
	}

}

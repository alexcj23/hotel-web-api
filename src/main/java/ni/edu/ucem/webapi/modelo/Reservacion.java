package ni.edu.ucem.webapi.modelo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Reservacion {

	private Integer id;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
	private Date desde;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
	private Date hasta;
	private Cuarto cuarto;
	private Huesped huesped;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDesde() {
		return desde;
	}

	public void setDesde(Date desde) {
		this.desde = desde;
	}

	public Date getHasta() {
		return hasta;
	}

	public void setHasta(Date hasta) {
		this.hasta = hasta;
	}

	public Cuarto getCuarto() {
		return cuarto;
	}

	public void setCuarto(Cuarto cuarto) {
		this.cuarto = cuarto;
	}

	public Huesped getHuesped() {
		return huesped;
	}

	public void setHuesped(Huesped huesped) {
		this.huesped = huesped;
	}

}

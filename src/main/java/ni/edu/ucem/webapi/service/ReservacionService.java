package ni.edu.ucem.webapi.service;

import java.util.Date;

import ni.edu.ucem.webapi.core.qo.QueryOption;
import ni.edu.ucem.webapi.modelo.Disponibilidad;
import ni.edu.ucem.webapi.modelo.Pagina;
import ni.edu.ucem.webapi.modelo.Reservacion;

public interface ReservacionService {

	public Reservacion obtenerReservacion(final int id);

	public Disponibilidad consultarDisponibilidad(Date fechaDesde, Date fechaHasta);

	public Pagina<Reservacion> obtenerTodasLasReservaciones(final QueryOption options);
	
	public void reservar(final Reservacion reservacion);

}

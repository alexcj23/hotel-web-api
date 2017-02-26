package ni.edu.ucem.webapi.serviceImpl;

import java.util.Date;

import org.springframework.stereotype.Service;

import ni.edu.ucem.webapi.core.qo.QueryOption;
import ni.edu.ucem.webapi.dao.DisponibilidadDAO;
import ni.edu.ucem.webapi.dao.ReservacionDAO;
import ni.edu.ucem.webapi.modelo.Cuarto;
import ni.edu.ucem.webapi.modelo.Disponibilidad;
import ni.edu.ucem.webapi.modelo.Pagina;
import ni.edu.ucem.webapi.modelo.Reservacion;
import ni.edu.ucem.webapi.service.ReservacionService;

@Service
public class ReservacionServiceImpl implements ReservacionService {

	private ReservacionDAO reservacioDAO;
	private DisponibilidadDAO disponibilidadDAO;

	public ReservacionServiceImpl(ReservacionDAO reservacioDAO, DisponibilidadDAO disponibilidadDAO) {
		this.reservacioDAO = reservacioDAO;
		this.disponibilidadDAO = disponibilidadDAO;
	}

	@Override
	public Reservacion obtenerReservacion(int id) {
		return reservacioDAO.obtenerPorId(id);
	}

	@Override
	public Disponibilidad consultarDisponibilidad(Date fechaDesde, Date fechaHasta) {
		return disponibilidadDAO.buscarDisponibilidad(fechaDesde, fechaHasta);
	}

	@Override
	public Pagina<Reservacion> obtenerTodasLasReservaciones(QueryOption options) {
		return new Pagina<Reservacion>(reservacioDAO.obtenerTodas(options), reservacioDAO.contar(), options.getOffset(),
				options.getLimit());
	}

	@Override
	public void reservar(Reservacion reservacion) {
		Disponibilidad dsp = this.consultarDisponibilidad(reservacion.getDesde(), reservacion.getHasta());
		boolean cuartoDisponible = false;
		for (Cuarto cuarto : dsp.getCuartos()) {
			if (cuarto.getId().equals(reservacion.getCuarto().getId()))
				cuartoDisponible = true;
		}
		if (!cuartoDisponible)
			throw new IllegalArgumentException("El cuarto se encuentra reservado");
		this.reservacioDAO.agregar(reservacion);
	}

}

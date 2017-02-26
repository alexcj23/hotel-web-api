package ni.edu.ucem.webapi.web.reservacion;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ni.edu.ucem.webapi.core.ApiResponse;
import ni.edu.ucem.webapi.core.ListApiResponse;
import ni.edu.ucem.webapi.core.ApiResponse.Status;
import ni.edu.ucem.webapi.core.qo.QueryOption;
import ni.edu.ucem.webapi.modelo.Disponibilidad;
import ni.edu.ucem.webapi.modelo.Pagina;
import ni.edu.ucem.webapi.modelo.Reservacion;
import ni.edu.ucem.webapi.service.ReservacionService;

@RestController
@RequestMapping("/v1")
public class ReservacionResource {

	private ReservacionService reservacionService;

	@Autowired
	public ReservacionResource(ReservacionService reservacionService) {
		this.reservacionService = reservacionService;
	}

	@RequestMapping(value = "/reservaciones/{reservacion-id}", method = RequestMethod.GET, produces = "application/json")
	public ApiResponse obtenerPorId(@PathVariable("reservacion-id") final int id) {
		Reservacion reservacion = this.reservacionService.obtenerReservacion(id);
		return new ApiResponse(Status.OK, reservacion);
	}

	@RequestMapping(value = "/reservaciones", method = RequestMethod.GET, produces = "application/json")
	public ListApiResponse<Reservacion> obtenerReservaciones(HttpServletRequest request) {
		final QueryOption qo = new QueryOption(request, Reservacion.class);
		Pagina<Reservacion> pagina = this.reservacionService.obtenerTodasLasReservaciones(qo);
		return new ListApiResponse<Reservacion>(Status.OK, pagina);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/reservaciones", method = RequestMethod.POST, produces = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public ApiResponse reservar(@Valid @RequestBody final Reservacion reservacion, BindingResult result) {
		if (result.hasErrors()) {
			throw new IllegalArgumentException(result.getFieldError().getDefaultMessage());
		}
		if (reservacion.getCuarto().getId() == null || reservacion.getCuarto().getId() < 1
				|| reservacion.getHuesped().getId() == null || reservacion.getHuesped().getId() < 1) {
			throw new IllegalArgumentException("Debe especificar el Id de cuarto y Id de huesped");
		}

		this.reservacionService.reservar(reservacion);
		return new ApiResponse(Status.OK, reservacion);

	}

	// OK-
	// http://localhost:8080/v1/disponibilidad/cupos?desde=24/02/2017&hasta=26/02/2017
	// FAIL-
	// http://localhost:8080/v1/disponibilidad/cupos?desde=20170224&hasta=20170226
	@RequestMapping(value = "/disponibilidad/cupos", method = RequestMethod.GET, produces = "application/json")
	public ApiResponse disponibilidad(
			@RequestParam(value = "desde", required = true) @DateTimeFormat(pattern = "dd/MM/yyyy") final Date fechaDesde,
			@RequestParam(value = "hasta", required = true) @DateTimeFormat(pattern = "dd/MM/yyyy") final Date fechaHasta) {
		Disponibilidad dsp = this.reservacionService.consultarDisponibilidad(fechaDesde, fechaHasta);
		return new ApiResponse(Status.OK, dsp);
	}

}

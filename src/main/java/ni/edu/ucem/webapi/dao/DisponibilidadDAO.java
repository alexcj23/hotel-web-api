package ni.edu.ucem.webapi.dao;

import java.util.Date;
import ni.edu.ucem.webapi.modelo.Disponibilidad;

public interface DisponibilidadDAO {

	public Disponibilidad buscarDisponibilidad(Date fechaIngreso, Date fechaSalida);

}

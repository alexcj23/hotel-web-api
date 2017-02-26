package ni.edu.ucem.webapi.dao;

import java.util.List;

import ni.edu.ucem.webapi.core.qo.QueryOption;
import ni.edu.ucem.webapi.modelo.Reservacion;

public interface ReservacionDAO {
	
	public Reservacion obtenerPorId(final int id);
	
	public int contar();
	
	public List<Reservacion> obtenerTodas(QueryOption queryOption);
	
	public void agregar(final Reservacion reservacion);

    public void guardar(final Reservacion reservacion);

    public void eliminar(final int id);

}

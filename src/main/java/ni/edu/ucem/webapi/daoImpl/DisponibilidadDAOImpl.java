package ni.edu.ucem.webapi.daoImpl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import ni.edu.ucem.webapi.dao.DisponibilidadDAO;
import ni.edu.ucem.webapi.modelo.Cuarto;
import ni.edu.ucem.webapi.modelo.Disponibilidad;

@Repository
public class DisponibilidadDAOImpl implements DisponibilidadDAO {

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	public DisponibilidadDAOImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	@Override
	public Disponibilidad buscarDisponibilidad(Date fechaIngreso, Date fechaSalida) {
		Disponibilidad disponibilidad = new Disponibilidad(fechaIngreso, fechaSalida);
		String sql = "select c.* from cuarto as c left join (%s) as r on c.id = r.id where r.id is null";
		sql = String.format(sql,
				"select cuarto as id from reservacion where desde between :f_d and :f_h or hasta between :f_d and :f_h");
		
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("f_d", fechaIngreso);
		parameters.put("f_h", fechaSalida);
		
		List<Cuarto> cuartos = this.namedParameterJdbcTemplate.query(sql, parameters,
				new BeanPropertyRowMapper<Cuarto>(Cuarto.class));
		disponibilidad.setCuartos(cuartos);
		return disponibilidad;
	}

}

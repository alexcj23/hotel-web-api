package ni.edu.ucem.webapi.daoImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ni.edu.ucem.webapi.dao.HuespedDAO;
import ni.edu.ucem.webapi.modelo.Huesped;

@Repository
public class HuespedDAOImpl implements HuespedDAO {

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public HuespedDAOImpl(final JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Huesped obtenerPorId(int id) {
		final String sql = "select * from huesped where id = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] { id }, new BeanPropertyRowMapper<Huesped>(Huesped.class));
	}

}

package ni.edu.ucem.webapi.daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import ni.edu.ucem.webapi.core.qo.QueryOption;
import ni.edu.ucem.webapi.dao.CuartoDAO;
import ni.edu.ucem.webapi.dao.HuespedDAO;
import ni.edu.ucem.webapi.dao.ReservacionDAO;
import ni.edu.ucem.webapi.modelo.Reservacion;

@Repository
public class ReservacionDAOImpl implements ReservacionDAO {

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	private CuartoDAO cuartoDAO;
	@Autowired
	private HuespedDAO huespedDAO;

	@Autowired
	public ReservacionDAOImpl(final JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	private final RowMapper<Reservacion> ROW_MAPPER_RESERVACION = new RowMapper<Reservacion>() {

		@Override
		public Reservacion mapRow(ResultSet rs, int rowNum) throws SQLException {
			Reservacion reservacion = new Reservacion();
			reservacion.setId(rs.getInt(1));
			reservacion.setDesde(rs.getDate(2));
			reservacion.setHasta(rs.getDate(3));
			reservacion.setCuarto(cuartoDAO.obtenerPorId(rs.getInt(4)));
			reservacion.setHuesped(huespedDAO.obtenerPorId(rs.getInt(5)));
			return reservacion;
		}

	};

	@Override
	public Reservacion obtenerPorId(int id) {
		final String sql = "select * from reservacion where id = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] { id }, ROW_MAPPER_RESERVACION);
	}

	@Override
	public int contar() {
		final String sql = "select count(*) from reservacion";
		return this.jdbcTemplate.queryForObject(sql, Integer.class);
	}

	@Override
	public List<Reservacion> obtenerTodas(QueryOption queryOption) {
		final String sql = queryOption.build();
		return this.jdbcTemplate.query(sql, ROW_MAPPER_RESERVACION);
	}

	@Override
	public void agregar(Reservacion reservacion) {
		final String sql = new StringBuilder().append("INSERT INTO reservacion").append(" ")
				.append("(desde, hasta, cuarto, huesped)").append(" ").append("VALUES(?,?,?,?)").toString();
		final Object[] parametros = new Object[4];
		parametros[0] = reservacion.getDesde();
		parametros[1] = reservacion.getHasta();
		parametros[2] = reservacion.getCuarto().getId();
		parametros[3] = reservacion.getHuesped().getId();
		this.jdbcTemplate.update(sql, parametros);
	}

	@Override
	public void guardar(Reservacion reservacion) {
		final String sql = new StringBuilder().append("UPDATE reservacion").append(" ").append("set  desde = ?")
				.append("    ,hasta = ?").append("    ,cuarto = ?").append("    ,huesped= ?").append("where id = ?")
				.toString();
		final Object[] parametros = new Object[5];
		parametros[0] = reservacion.getDesde();
		parametros[1] = reservacion.getHasta();
		parametros[2] = reservacion.getCuarto().getId();
		parametros[3] = reservacion.getHuesped().getId();
		parametros[4] = reservacion.getId();
		this.jdbcTemplate.update(sql, parametros);

	}

	@Override
	public void eliminar(int id) {
		final String sql = "delete from reservacion where id = ?";
		this.jdbcTemplate.update(sql, new Object[] { id });
	}

}

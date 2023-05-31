package com.ejemplo.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.ejemplo.model.Persona;

@Repository
public class PersonaRepository {

	@Autowired
	private NamedParameterJdbcTemplate namedJdbcTemplate;

	public void insert(Persona persona) {

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("nombre", persona.getNombre());
		parameter.addValue("apellido", persona.getApellido());
		parameter.addValue("estado", 1);
		parameter.addValue("identificacion", persona.getIdentificacion());

		String sql = "insert into persona (nombre,apellido,identificacion,estado) "
				+ "values(:nombre,:apellido,:identificacion,:estado)";

		namedJdbcTemplate.update(sql, parameter);

	}
	public int insert2(Persona persona) {

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("nombre", persona.getNombre());
		parameter.addValue("apellido", persona.getApellido());
		parameter.addValue("estado", 1);
		parameter.addValue("identificacion", persona.getIdentificacion());

		String sql = "insert into persona (nombre,apellido,identificacion,estado) "
				+ "values(:nombre,:apellido,:identificacion,:estado)";


		KeyHolder keyHolder = new GeneratedKeyHolder();

		namedJdbcTemplate.update(sql, parameter, keyHolder);

		// Obtener el valor generado para la columna 'codigo'
		if (keyHolder.getKey() != null) {
			Long codigoGenerado = keyHolder.getKey().longValue();
			return codigoGenerado.intValue();
		}
		return 0;

	}

	public List<Persona> select() {

		String sql = "select * from persona ";

		List<Persona> lstPersona = namedJdbcTemplate.query(sql, new RowMapper<Persona>() {

			@Override
			public Persona mapRow(ResultSet rs, int rowNum) throws SQLException {

				Persona per = new Persona();
				per.setApellido(rs.getString("apellido"));
				per.setNombre(rs.getString("nombre"));
				per.setIdentificacion(rs.getString("identificacion"));
				per.setEstado(rs.getInt("estado"));
				per.setCodigo(rs.getInt("codigo"));

				return per;
			}

		});
		return lstPersona;

	}

	public void update(Persona persona) {
	}

	public void delete(Persona persona) {
	}

}

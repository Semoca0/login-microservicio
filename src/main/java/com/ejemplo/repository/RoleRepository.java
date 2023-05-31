package com.ejemplo.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ejemplo.model.Role;

@Repository
public class RoleRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate namedJdbcTemplate;

	
	public List<Role> listar() {

		String sql = "select * from role";

		List<Role> lstRole = namedJdbcTemplate.query(sql, new RowMapper<Role>() {

			@Override
			public Role mapRow(ResultSet rs, int rowNum) throws SQLException {

				Role role = new Role();				
				role.setCodigo(rs.getInt("rol_codigo"));
				role.setNombre(rs.getString("role_nombre"));
				role.setEstado(rs.getInt("rol_estado"));

				return role;
			}

		});

		return lstRole;

	}
	
	public void insert(Role role) {

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		
		parameter.addValue("nombre", role.getNombre());
		parameter.addValue("estado", 1);

		String sql = "insert into role(role_nombre,rol_estado)"
				+ "values(:nombre,:estado)";

		namedJdbcTemplate.update(sql, parameter);
	}
	
	public void update(Role role) {


	}
	
	public void delete(Role role) {


	}
	

}

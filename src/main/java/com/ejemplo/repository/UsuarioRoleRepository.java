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
import com.ejemplo.model.Usuario;
import com.ejemplo.model.UsuarioRole;

@Repository
public class UsuarioRoleRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate namedJdbcTemplate;

	
	public List<UsuarioRole> listar() {

		String sql = "select * from usuario_role ur join role p on ur .rol_codigo= p.rol_codigo join usuario  u on ur.usu_codigo=u.usu_codigo ";

		List<UsuarioRole> lstUsuarioRole = namedJdbcTemplate.query(sql, new RowMapper<UsuarioRole>() {

			@Override
			public UsuarioRole mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				
				Usuario user = new Usuario();
				user.setUsuario(rs.getString("usu_usuario"));
				user.setCodigo(rs.getInt("usu_codigo"));
				
				Role role = new Role();
				role.setCodigo(rs.getInt("rol_codigo"));
				role.setNombre(rs.getString("role_nombre"));

				UsuarioRole usuarioRole = new UsuarioRole();
				usuarioRole.setCodigo(rs.getInt("usr_codigo"));
				usuarioRole.setEstado(rs.getInt("usr_estado"));
				usuarioRole.setUser(user);
				usuarioRole.setRole(role);
				

				

				return usuarioRole;
			}

		});

		return lstUsuarioRole;
	}
	
	public void insert(UsuarioRole usuarioRole) {

		MapSqlParameterSource parameter = new MapSqlParameterSource();

		parameter.addValue("usuUsuario", usuarioRole.getUser().getCodigo());

		parameter.addValue("estado", 1);
		parameter.addValue("role", usuarioRole.getRole().getCodigo());

		String sql = "insert into usuario_role(usuario_codigo,usuario_estado,role_codigo)"
				+ "values(:usuUsuario,:estado,:role)";

		namedJdbcTemplate.update(sql, parameter);

	}
	
	public void update(UsuarioRole usuarioRole) {



	}
	
	public void delete(UsuarioRole usuarioRole) {



	}

	

}

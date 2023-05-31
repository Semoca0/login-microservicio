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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.ejemplo.model.Persona;
import com.ejemplo.model.Usuario;

@Repository
public class UsuarioRepository {

	@Autowired
	private NamedParameterJdbcTemplate namedJdbcTemplate;
	@Autowired
	private BCryptPasswordEncoder encoder;

	public Usuario buscarUsuarioClaveEstadoPorUsuario(String usuario) {

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("usuario", usuario);

		String sql = "select usuario,clave,estado from usuario where usuario=:usuario";

		List<Usuario> lstUsuario = namedJdbcTemplate.query(sql, parameter, new RowMapper<Usuario>() {

			@Override
			public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {

				Usuario user = new Usuario();
				user.setClave(rs.getString("clave"));
				user.setEstado(rs.getInt("estado"));
				user.setUsuario(rs.getString("usuario"));
				return user;
			}

		});

		if (lstUsuario.size() == 0) {
			throw new RuntimeException("el usuario no existe -> " + usuario);
		}

		return lstUsuario.get(0);
	}

	public List<String> buscarRolePorUsuario(String usuario) {

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("usuario", usuario);

		String sql = "select r.role_nombre from usuario u inner join usuario_role ur on u.codigo  = ur.codigo inner "
				+ "join \"role\" r on ur.role_codigo  = r.codigo  where u.usuario  =:usuario";

		List<String> lstRoles = namedJdbcTemplate.query(sql, parameter, new RowMapper<String>() {

			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {

				return rs.getString("role_nombre");
			}

		});

		if (lstRoles.size() == 0) {
			throw new RuntimeException("Usuario sin permisos -> " + usuario);
		}

		return lstRoles;
	}

	public List<Usuario> listar() {

		String sql = "select * from usuario join persona p on usuario u u.persona_codigo= p.codigo ";

		List<Usuario> lstUsuario = namedJdbcTemplate.query(sql, new RowMapper<Usuario>() {

			@Override
			public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {

				Persona persona = new Persona();
				persona.setNombre(rs.getString("per_nombre"));
				persona.setCodigo(rs.getInt("per_codigo"));

				Usuario user = new Usuario();
				user.setPersona(persona);
				user.setEstado(rs.getInt("usu_estado"));
				user.setUsuario(rs.getString("usu_usuario"));
				user.setClave(rs.getString("usu_clave"));
				user.setCodigo(rs.getInt("usu_codigo"));

				return user;
			}

		});

		return lstUsuario;
	}

	public int insert(Usuario usuario) {

		MapSqlParameterSource parameter = new MapSqlParameterSource();

		parameter.addValue("clave", encoder.encode(usuario.getClave()));
		parameter.addValue("nombre", usuario.getUsuario());
		parameter.addValue("estado", 1);
		parameter.addValue("persona", usuario.getPersona().getCodigo());

		String sql = "insert into usuario(usuario,estado,clave,persona_codigo)"
				+ "values(:nombre,:estado,:clave,:persona)";


		KeyHolder keyHolder = new GeneratedKeyHolder();

		namedJdbcTemplate.update(sql, parameter, keyHolder);

		// Obtener el valor generado para la columna 'codigo'
		if (keyHolder.getKey() != null) {
			Long codigoGenerado = keyHolder.getKey().longValue();
			return codigoGenerado.intValue();
		}
		return 0;
	}

	public void update(Usuario usuario) {


	}

	public void delete(Usuario usuario) {


	}
	

}

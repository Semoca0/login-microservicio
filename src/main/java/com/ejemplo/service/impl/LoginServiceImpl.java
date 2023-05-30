package com.ejemplo.service.impl;

import com.ejemplo.model.Usuario;
import com.ejemplo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoginServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Usuario usuario = usuarioRepository.buscarUsuarioClaveEstadoPorUsuario(username);

		boolean estado = false;

		if (usuario.getEstado() == 1) {
			estado = true;

		}

		List<GrantedAuthority> lstRole = buscarRolePorUsuario(usuario.getUsuario());

		// List<GrantedAuthority> lstRole = buscarRolePorUsuario(username);

		return new User(username, usuario.getClave(), estado, true, true, true, lstRole);
	}

	private List<GrantedAuthority> buscarRolePorUsuario(String usuario) {

		List<String> lstStrRole = usuarioRepository.buscarRolePorUsuario(usuario);

		List<GrantedAuthority> lstGraRole = new ArrayList<GrantedAuthority>();

		for (String role : lstStrRole) {
			SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role.toUpperCase());
			lstGraRole.add(authority);
		}

		return lstGraRole;

	}
}

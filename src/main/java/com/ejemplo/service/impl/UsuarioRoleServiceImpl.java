package com.ejemplo.service.impl;

import com.ejemplo.model.UsuarioRole;
import com.ejemplo.repository.UsuarioRoleRepository;
import com.ejemplo.service.IUsuarioRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class UsuarioRoleServiceImpl implements IUsuarioRoleService{

	@Autowired
	private UsuarioRoleRepository repo;


	@Override
	public List<UsuarioRole> listar() {
		
		return repo.listar();
	}


	@Override
	public void insert(UsuarioRole usuarioRole, HttpServletRequest request) {


		repo.insert(usuarioRole);
		
	}


	@Override
	public void update(UsuarioRole usuarioRole, HttpServletRequest request) {
		

		
		repo.update(usuarioRole);
		
	}


	@Override
	public void delete(UsuarioRole usuarioRole, HttpServletRequest request) {
		

		
		repo.delete(usuarioRole);
		
	}
	

}

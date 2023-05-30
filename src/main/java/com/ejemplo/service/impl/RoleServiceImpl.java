package com.ejemplo.service.impl;

import com.ejemplo.model.Role;
import com.ejemplo.repository.RoleRepository;
import com.ejemplo.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class RoleServiceImpl implements IRoleService {

	@Autowired
	private RoleRepository repo;


	@Override
	public void delete(Role role, HttpServletRequest request) {



		repo.delete(role);
	}

	@Override
	public void update(Role role, HttpServletRequest request) {



		repo.update(role);

	}

	@Override
	public void insert(Role role, HttpServletRequest request) {



		repo.insert(role);

	}

	@Override
	public List<Role> listar() {

		return repo.listar();
	}

}

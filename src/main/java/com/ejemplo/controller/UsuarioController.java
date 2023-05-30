package com.ejemplo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ejemplo.model.Usuario;
import com.ejemplo.service.IUsuarioService;

@RestController
@RequestMapping(path = "api/usuario")
public class UsuarioController {

	@Autowired
	private IUsuarioService service;

	@GetMapping(path = "listar")
	public List<Usuario> listar() {

		return service.listar();

	}

	@PostMapping(path = "insert")
	public void insert(@RequestBody Usuario usuario, HttpServletRequest request) {

		service.insert(usuario);
		
	}
	
	@PutMapping(path = "update")
	public void update(@RequestBody Usuario usuario, HttpServletRequest request) {
		
		service.update(usuario, request);
		
	}
	
	@PutMapping(path = "delete")
	public void delete(@RequestBody Usuario usuario, HttpServletRequest request) {
		
		service.delete(usuario, request);
		
	}
	
	@GetMapping(path = "listar-role/{usuario}")
	public List<String> listarRole(@PathVariable String usuario) {

		return service.listarRole(usuario);

	}

}

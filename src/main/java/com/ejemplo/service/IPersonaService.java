package com.ejemplo.service;

import com.ejemplo.model.Persona;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IPersonaService {

	public void insert(Persona persona);

	public List<Persona> select();

	public void update(Persona persona, HttpServletRequest request);

	public void delete(Persona persona, HttpServletRequest request);

}

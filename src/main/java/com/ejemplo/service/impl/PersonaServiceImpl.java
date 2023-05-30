package com.ejemplo.service.impl;

import com.ejemplo.model.Persona;
import com.ejemplo.repository.PersonaRepository;
import com.ejemplo.service.IPersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class PersonaServiceImpl implements IPersonaService {

    @Autowired
    private PersonaRepository personaRepository;


    @Override
    public void insert(Persona persona) {
        personaRepository.insert(persona);

    }

    @Override
    public List<Persona> select() {

        return personaRepository.select();
    }

    @Override
    public void update(Persona persona, HttpServletRequest request) {


        personaRepository.update(persona);

    }

    @Override
    public void delete(Persona persona, HttpServletRequest request) {
        personaRepository.delete(persona);

    }


}

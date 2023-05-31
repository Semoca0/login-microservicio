package com.ejemplo.service.impl;

import com.ejemplo.model.Usuario;
import com.ejemplo.model.UsuarioRole;
import com.ejemplo.repository.PersonaRepository;
import com.ejemplo.repository.UsuarioRepository;
import com.ejemplo.repository.UsuarioRoleRepository;
import com.ejemplo.service.IPersonaService;
import com.ejemplo.service.IRoleService;
import com.ejemplo.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    @Autowired
    private UsuarioRepository repo;
    @Autowired
    private UsuarioRoleRepository uRol;
    @Autowired
    private IRoleService role;
    @Autowired
    private PersonaRepository personaService;

    @Override
    public void insert(Usuario usuario) {
       int persona= personaService.insert2(usuario.getPersona());
       usuario.getPersona().setCodigo(persona);
       int usuarioCodigo= repo.insert(usuario);
       usuario.setCodigo(usuarioCodigo);
        UsuarioRole ur = new UsuarioRole();
        ur.setEstado(1);
        ur.setUser(usuario);
        ur.setRole(role.listar().get(1));
        uRol.insert(ur);
    }

    @Override
    public List<Usuario> listar() {

        return repo.listar();
    }

    @Override
    public void update(Usuario usuario, HttpServletRequest request) {

        repo.update(usuario);

    }

    @Override
    public void delete(Usuario usuario, HttpServletRequest request) {

        repo.delete(usuario);

    }


    @Override
    public List<String> listarRole(String usuario) {

        return repo.buscarRolePorUsuario(usuario);
    }

}

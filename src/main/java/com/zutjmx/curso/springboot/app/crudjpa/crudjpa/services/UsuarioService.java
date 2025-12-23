package com.zutjmx.curso.springboot.app.crudjpa.crudjpa.services;

import java.util.List;

import com.zutjmx.curso.springboot.app.crudjpa.crudjpa.entities.Usuario;

public interface UsuarioService {

    List<Usuario> findAll();
    Usuario save(Usuario usuario);
    
}

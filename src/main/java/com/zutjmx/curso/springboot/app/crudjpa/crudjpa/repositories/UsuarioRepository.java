package com.zutjmx.curso.springboot.app.crudjpa.crudjpa.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.zutjmx.curso.springboot.app.crudjpa.crudjpa.entities.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

    boolean existsByUsername(String username);

    Optional<Usuario> findByUsername(String username);
    
}
